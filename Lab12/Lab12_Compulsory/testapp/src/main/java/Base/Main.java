package Base;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;


public class Main {
    public static void main(String[] args) {
        String classPath = "src/main/java/Base/TestProgram.java";

        try {

            File file = new File(classPath);
            URL url = file.toURI().toURL();
            URLClassLoader urlLoader = new URLClassLoader(new URL[]{url}, Main.class.getClassLoader());
            Class<?> aClass = urlLoader.loadClass("Base.TestProgram");

            Analyzer.displayClassInfo(aClass);
            Tester.invokeTestMethods(aClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
