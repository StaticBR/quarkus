package org.jboss.protean.arc.example;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.enterprise.util.AnnotationLiteral;
import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;

@Qualifier
@Inherited
@Target({ TYPE, METHOD, FIELD, PARAMETER })
@Retention(RUNTIME)
public @interface MyQualifier {

    @Nonbinding
    String alpha() default "";

    String bravo() default "";

    static class OneLiteral extends AnnotationLiteral<MyQualifier> implements MyQualifier {

        @Override
        public String alpha() {
            return "1";
        }

        @Override
        public String bravo() {
            return "1";
        }

    }

}