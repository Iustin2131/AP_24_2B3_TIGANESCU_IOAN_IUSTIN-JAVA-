package Base;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Stream;


public class Main {

    public static void main(String[] args) {

        Path path = Paths.get("C:\\Users\\IUSTIN\\Documents\\GitHub\\AP_24_2B3_TIGANESCU_IOAN_IUSTIN-JAVA-\\Lab12\\Lab12_Homework\\testapp\\src\\main\\java\\Base");

        processPath(path);
    }

    private static void processPath(Path path) {
        try {
            if (Files.isDirectory(path)) {
                try (Stream<Path> paths = Files.walk(path)) {
                    paths
                            .filter(Files::isRegularFile)
                            .forEach(Main::handleFile);
                }
            } else if (Files.isRegularFile(path)) {
                handleFile(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleFile(Path path) {
        File file = path.toFile();
        if (file.getName().endsWith(".class")) {
            analyzeClass(file);
        } else if (file.getName().endsWith(".jar")) {
            try (JarFile jarFile = new JarFile(file)) {
                Enumeration<JarEntry> entries = jarFile.entries();
                while (entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    if (entry.getName().endsWith(".class")) {
                        String className = entry.getName()
                                .replace(".class", "")  // Remove .class extension
                                .replace("/", ".");  // Replace directory separators with dots
                        try (URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{new URL("jar:file:" + file.getPath() + "!/")})) {
                            Class<?> aClass = Class.forName(className, true, classLoader);
                            PrototypeGenerator prototypeGenerator = new PrototypeGenerator();
                            prototypeGenerator.displayPrototype(aClass);
                            Tester.invokeTestMethods(aClass);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void analyzeClass(File file) {
        try (URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{file.toURI().toURL()})) {
            String baseDir = "C:\\Users\\IUSTIN\\Documents\\GitHub\\AP_24_2B3_TIGANESCU_IOAN_IUSTIN-JAVA-\\Lab12\\Lab12_Homework\\testapp\\src\\main\\java\\";
            String className = file.getPath()
                    .replace(baseDir, "")
                    .replace(".class", "")
                    .replace(File.separator, ".");   
            Class<?> aClass = Class.forName(className, true, classLoader);
            PrototypeGenerator prototypeGenerator = new PrototypeGenerator();
            prototypeGenerator.displayPrototype(aClass);
            Tester.invokeTestMethods(aClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
