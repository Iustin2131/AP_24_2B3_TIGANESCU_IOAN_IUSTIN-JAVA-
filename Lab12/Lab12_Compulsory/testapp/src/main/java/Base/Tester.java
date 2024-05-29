package Base;

import java.lang.reflect.Method;

public class Tester {

    public static void invokeTestMethods(Class<?> aClass) {
        int passed = 0, failed = 0;

        for (Method method : aClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                if (method.getParameterCount() == 0 && java.lang.reflect.Modifier.isStatic(method.getModifiers())) {
                    try {
                        method.invoke(null);
                        passed++;
                    } catch (Throwable ex) {
                        System.out.printf("Test %s failed: %s %n", method, ex.getCause());
                        failed++;
                    }
                }
            }
        }

        System.out.printf("Passed: %d, Failed %d%n", passed, failed);
    }
}
