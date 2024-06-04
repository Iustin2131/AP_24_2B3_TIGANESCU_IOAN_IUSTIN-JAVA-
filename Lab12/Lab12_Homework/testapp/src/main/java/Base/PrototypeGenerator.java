package Base;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class PrototypeGenerator {

    public void displayPrototype(Class<?> aClass) {
        System.out.println("Class: " + aClass.getName());

        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("Field: " + field);
        }

        Constructor<?>[] constructors = aClass.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println("Constructor: " + constructor);
        }

        Method[] methods = aClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("Method: " + method);
        }
    }
}