package Base;

import java.lang.reflect.Method;


public class Analyzer {


    public static void displayClassInfo(Class<?> aClass) {
        System.out.println("Class: " + aClass.getName());

        Method[] methods = aClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("Method: " + method);
        }
    }

}
