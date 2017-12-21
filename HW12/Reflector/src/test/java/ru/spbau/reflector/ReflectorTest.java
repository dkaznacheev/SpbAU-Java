package ru.spbau.reflector;

import com.sun.org.apache.xml.internal.resolver.readers.ExtendedXMLCatalogReader;
import org.junit.Test;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;


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

            System.out.println("It compiled, okay?");


            ClassLoader cl = ClassLoader.getSystemClassLoader();
            Class<?> compiled = cl.loadClass("ru.spbau.reflector.autoClasses.Myclass");
            System.out.println(compiled.getSimpleName());

        }catch (ClassNotFoundException e) {
            System.out.println("Couldn't load class!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}