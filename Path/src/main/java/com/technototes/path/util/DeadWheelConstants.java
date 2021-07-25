package com.technototes.path.util;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;

@Retention(RetentionPolicy.RUNTIME)
@Target(value={FIELD, ANNOTATION_TYPE})
public @interface DeadWheelConstants {
    double radius();
    double ratio() default 1;
    double ticksPerRev();

    @Retention(RetentionPolicy.RUNTIME)
    @Target(value={FIELD})
    @DeadWheelConstants(radius = 0.68897, ticksPerRev = 8192)
    @interface OpenOdometry{
    }

    class Utils{
        static DeadWheelConstants get(Annotation a){
            return a.annotationType().getAnnotation(DeadWheelConstants.class);
        }
    }
}
