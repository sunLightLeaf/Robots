package ru.eatTheFrog.Robots.model.RobotSchemes;

import ru.eatTheFrog.Robots.model.static_modules.Algebra.LimitsApplyer;
import ru.eatTheFrog.Robots.model.static_modules.Algebra.MutationDistributor;

import java.util.Arrays;

public class REFLECTION_SchemeDoubleFieldsMutator {
    public static void mutateScheme(AbstractScheme scheme) {
        Arrays.stream(scheme.getClass().getFields())
                .filter(x -> x.getType().equals(double.class))
                .forEach(field -> {
                    try {
                        var value = (double)field.get(scheme);
                        var distribution = MutationDistributor.getRandomDistribution(AbstractScheme.defaultMediocrityRate);
                        var newDistribution = Arrays.stream(field.getAnnotationsByType(MediocrityRate.class))
                                .findFirst().map(ann -> {
                                    return ann.rate();
                                }).orElse(distribution);
                        var newValue = value + newDistribution*value;
                        var limitedValue = Arrays.stream(field.getAnnotationsByType(DoubleRange.class))
                            .findFirst().map(ann -> {
                                return LimitsApplyer.applyLimit(newValue, ann.min(), ann.max());
                        }).orElse(newValue);

                        field.set(scheme, limitedValue);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                });
    }
}
