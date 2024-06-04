package Base;

import java.lang.reflect.Method;

public class Tester {
    public static void invokeTestMethods(Class<?> aClass) {
        for (Method method : aClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                try {
                    System.out.println("Testing method: " + method.getName());
                    Object instance = method.getDeclaringClass().newInstance();
                    Object[] parameters = generateMockParameters(method);
                    method.invoke(instance, parameters);
                    System.out.println("Test passed");
                } catch (Exception e) {
                    System.out.println("Test failed: " + e.getCause());
                }
            }
        }
    }

    private static Object[] generateMockParameters(Method method) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        Object[] parameters = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i].isPrimitive()) {
                if (parameterTypes[i].equals(int.class)) {
                    parameters[i] = 0;
                } else if (parameterTypes[i].equals(boolean.class)) {
                    parameters[i] = false;
                }
                // Add more cases for other primitive types if needed
            } else if (parameterTypes[i].equals(String.class)) {
                parameters[i] = "";
            }
        }
        return parameters;
    }
}