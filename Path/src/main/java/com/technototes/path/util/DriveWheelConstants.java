package com.technototes.path.util;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;

@Retention(RetentionPolicy.RUNTIME)
@Target(value={FIELD, ANNOTATION_TYPE})
public @interface DriveWheelConstants {
    double radius();
    double ratio();
    double ticksPerRev() default 28;
    double maxVelo() default 6000;


    class Utils{
        static DriveWheelConstants get(Annotation a){
            return a.annotationType().getAnnotation(DriveWheelConstants.class);
        }
    }
}
