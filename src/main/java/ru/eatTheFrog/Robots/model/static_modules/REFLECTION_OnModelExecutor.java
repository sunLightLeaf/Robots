package ru.eatTheFrog.Robots.model.static_modules;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;


// ОЧЕНЬ ОПАСНЫЙ КЛАСС! ИСПОЛЬЗУЙТЕ С УМОМ.
public class REFLECTION_OnModelExecutor {
    @Retention(RetentionPolicy.RUNTIME)
    public @interface OnModelAction{}

    public static void executeOnModels(Object object) {
        Arrays.stream(object.getClass().getDeclaredMethods()).filter(
                method -> Arrays.stream(method.getAnnotationsByType(OnModelAction.class))
                        .anyMatch(y -> y.annotationType().equals(OnModelAction.class))
        )
                .forEach(z -> {
                    try {
                        z.invoke(object);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });

    }
}
