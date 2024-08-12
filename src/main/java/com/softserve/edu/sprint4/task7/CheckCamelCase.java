package com.softserve.edu.sprint4.task7;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

class ClassForAnnot {
    @CamelCase
    public static void example() {
    }

    @CamelCase
    public void Example() {
    }

    public static void _main(String args[]) {
    }
}

class Class1 {
    @CamelCase
    public void correct() {
    }

    @CamelCase
    public void InCorrect() {
    }

    @CamelCase
    public void JustMethod() {
    }
}

class Class2 {
    @CamelCase
    public void correct() {
    }

    @CamelCase
    public void oneMoreCorrect() {
    }
}

public class CheckCamelCase {
    public static final String CAMELCASE_PATTERN = "^[a-z][a-zA-Z0-9]*$";
    public static final String ERROR_MESSAGE_TEMPLATE = "method %s.%s doesn't satisfy camelCase naming convention";

    public static boolean checkAndPrint(Class<?> clazz) {
        boolean allMethodsAreValid = true;

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(CamelCase.class)) {
                String methodName = method.getName();

                if (!methodName.matches(CAMELCASE_PATTERN)) {

                    System.out.printf(ERROR_MESSAGE_TEMPLATE, clazz.getSimpleName(), methodName);
                    System.out.println();

                    allMethodsAreValid = false;
                }
            }
        }

        return allMethodsAreValid;
    }

}
