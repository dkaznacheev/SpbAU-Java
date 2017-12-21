package ru.spbau.reflector;

import org.junit.Test;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Paths;


public class ReflectorTest {
    private static final String PACKAGE = "ru.spbau.reflector.testClasses";
    private static final String CLASS = "MyClass";
    @Test
    public void classCompilesTest() {
        try {
            Reflector.printStructure(Class.forName(PACKAGE + "." + CLASS));


            final StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("package ru.spbau.reflector.autoClasses;\n\n");
            BufferedReader reader = Files.newBufferedReader(Paths.get(CLASS + ".java"));
            reader.lines().forEach(line -> stringBuilder.append(line + "\n"));
            reader.close();

            FileWriter writer = new FileWriter("src/test/java/ru/spbau/reflector/autoClasses/" + CLASS + ".java");
            writer.write(stringBuilder.toString());
            writer.close();


            File file = new File("src/test/java/ru/spbau/reflector/autoClasses/" + CLASS + ".java");

            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            compiler.run(null, null, null, file.getPath());

            File cmpFile = new File("src/test/java/ru/spbau/reflector/autoClasses/" + CLASS + ".class");

            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { cmpFile.toURI().toURL() });
            Class<?> compiled = Class.forName("MyClass.class", true, classLoader);
            System.out.println(compiled.getSimpleName());

        } catch (ClassNotFoundException e) {
            System.out.println("!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}