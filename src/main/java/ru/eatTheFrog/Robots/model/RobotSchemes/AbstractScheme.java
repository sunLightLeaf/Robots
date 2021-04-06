package ru.eatTheFrog.Robots.model.RobotSchemes;

import com.rits.cloning.Cloner;
import ru.eatTheFrog.Robots.model.Entities.EntityType;
import ru.eatTheFrog.Robots.model.Entities.Food.FoodType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Random;

@Retention(RetentionPolicy.RUNTIME)
@interface DoubleRange {
    double min() default Double.MIN_VALUE;
    double max() default Double.MAX_VALUE;
}
@Retention(RetentionPolicy.RUNTIME)
@interface MediocrityRate {
    double rate();
}
public abstract class AbstractScheme {
    // ПОЛЯ ТИПА DOUBLE В РОБОТЕ ДОЛЖНЫ БЫТЬ НАЗВАНЫ ТАК ЖЕ КАК В SCHEME ПО КОТОРОЙ ОН СОЗДАЁТСЯ
    // ИБО ОНИ АВТОМАТИЧЕСКИ КОПИРУЮТСЯ ПРИ СОЗДАНИИ НОВОГО РОБОТА ПО ИМЕНАМ!
    public static double defaultMediocrityRate = 1000;

    public double x;
    public double y;
    public double slipping;
    public double length;

    public double thickness;
    @DoubleRange(min = 0,max = 255) // НЕ ТРОГАТЬ! ИНАЧЕ ЗНАЧЕНИЕ ЦВЕТА МОЖЕТ ВЫЙТИ ЗА РАМКИ ДОЗВОЛЕННОГО!
    public double redColor = 100;
    @DoubleRange(min = 0,max = 255) // НЕ ТРОГАТЬ! ИНАЧЕ ЗНАЧЕНИЕ ЦВЕТА МОЖЕТ ВЫЙТИ ЗА РАМКИ ДОЗВОЛЕННОГО!
    public double greenColor = 100;
    @DoubleRange(min = 0,max = 255) // НЕ ТРОГАТЬ! ИНАЧЕ ЗНАЧЕНИЕ ЦВЕТА МОЖЕТ ВЫЙТИ ЗА РАМКИ ДОЗВОЛЕННОГО!
    public double blueColor = 100;
    public EntityType dangerType;
    public FoodType edibleType;
    public EntityType type;
    public double maxVelocity;
    public double attack;
    public double wanderizeIrregularityRate;
    public double acceleration;
    public double maxAngularVelocity;
    public double angularAcceleration;
    public double inclinationToVegetarianism;



    public double solutionKMaxToAttack;
    public double solutionKsuperiority;
    public double solutionKvolumeEstimator;

    public AbstractScheme mutate() {
        REFLECTION_SchemeDoubleFieldsMutator.mutateScheme(this);
        return this;
    }

    public AbstractScheme randomizePosition(double width, double height) {
        var random = new Random();
        this.x = (Math.abs(random.nextInt()) % width);
        this.y = (Math.abs(random.nextInt()) % height);

        return this;
    }
    public AbstractScheme copy() {
        Cloner cloner=new Cloner();
        return cloner.shallowClone(this);
    }


}
