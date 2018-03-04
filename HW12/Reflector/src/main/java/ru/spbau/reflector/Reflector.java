package ru.spbau.reflector;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.StringJoiner;

public class Reflector {
    private static void writeIndents(Writer writer, int indent) throws IOException {
        for (int i = 0; i < indent; i++) {
            writer.write("\t");
        }
    }


    private static void writeInterfaces(Writer writer, Class<?> someClass) throws IOException {
        Class<?>[] superClasses = someClass.getInterfaces();
        if (superClasses.length > 0) {
            StringJoiner joiner = new StringJoiner(", ");
            for (Class<?> superClass : superClasses) {
                joiner.add(superClass.getSimpleName());
            }
            writer.write(joiner.toString());
        }
    }

    private static void writeCaption(Writer writer, Class<?> someClass, int indent) throws IOException {
        String modifiers = Modifier.toString(someClass.getModifiers());
        writer.write(modifiers + " ");
        if (someClass.isInterface()) {
            writer.write(someClass.getSimpleName());
            if (someClass.getInterfaces().length > 0) {
                writer.write(" extends ");
                writeInterfaces(writer, someClass);
            }
        } else {
            writer.write("class ");
            writer.write(someClass.getSimpleName());
            Class<?> superClass = someClass.getSuperclass();
            if (superClass != Object.class) {
                writer.write(" extends " + superClass.getSimpleName());
            }
            if (someClass.getInterfaces().length > 0) {
                writer.write(" implements ");
                writeInterfaces(writer, someClass);
            }
        }
        writer.write(" {");
    }

    private static void writeField(Writer writer, Field field, int indent) throws IOException {
        String modifiers = Modifier.toString(field.getModifiers());
        writeIndents(writer, indent);
        writer.write(modifiers + " ");
        writer.write(field.getType().getSimpleName() + " ");
        writer.write(field.getName()+";\n");
    }

    private static void writeFields(Writer writer, Class<?> someClass, int indent) throws IOException{
        Field[] fields = someClass.getFields();
        if (fields.length > 0) {
            writer.write("\n");
        }
        for (Field field : fields) {
            writeField(writer, field, indent);
        }
    }

    private static void writeClasses(Writer writer,  Class<?> someClass, int indent) throws IOException {
        Class<?>[] classes = someClass.getClasses();
        if (classes.length > 0) {
            writer.write("\n");
        }
        for (Class<?> innerClass : classes) {
            writeClass(writer, innerClass, indent);
            writer.write("\n");
        }
    }

    private static void writeParameters(Writer writer, Method method) throws IOException {
        StringJoiner joiner = new StringJoiner(", ");
        Class<?>[] parameterTypes = method.getParameterTypes();
        int varNum = 1;
        for (Class<?> parameterType : parameterTypes) {
            joiner.add(parameterType.getSimpleName() + " var" + Integer.toString(varNum++));
        }
        writer.write(joiner.toString());
    }

    private static void writeReturnDefaultValue(Writer writer,  Class<?> someClass, int indent) throws IOException {
        String defaultValue;
        if (someClass.isPrimitive()) {
            if (someClass == boolean.class) {
                defaultValue = "false";
            } else {
                defaultValue = "0";
            }
        } else {
            defaultValue = "null";
        }
        writeIndents(writer, indent);
        writer.write("return " + defaultValue + ";\n");
    }

    private static void writeMethod(Writer writer, Method method, int indent, boolean inInterface) throws IOException {
        writeIndents(writer, indent);

        String modifiers = Modifier.toString(method.getModifiers());

        if (inInterface) {
            writer.write(modifiers.replace("abstract", ""));
        } else {
            writer.write(modifiers + " ");
        }

        writer.write(method.getReturnType().getSimpleName() + " ");
        writer.write(method.getName() + "(");
        writeParameters(writer, method);
        writer.write(")");

        Class<?>[] exceptions = method.getExceptionTypes();
        if (exceptions.length > 0) {
            writer.write(" throws ");
            StringJoiner joiner = new StringJoiner(", ");
            for (Class<?> exception : exceptions) {
                joiner.add(exception.getSimpleName());
            }
            writer.write(joiner.toString());
        }
        if (!Modifier.isAbstract(method.getModifiers())) {

            writer.write(" {\n");
            if (!method.getReturnType().equals(Void.TYPE)) {
                writeReturnDefaultValue(writer, method.getReturnType(), indent + 1);
            }
            writeIndents(writer, indent);
            writer.write("}");
        } else {
            writer.write(";");
        }

        writer.write("\n");
    }

    private static void writeMethods(Writer writer,  Class<?> someClass, int indent) throws IOException {
        Method[] methods = someClass.getDeclaredMethods();
        if (methods.length > 0) {
            writer.write("\n");
        }
        boolean inInterface = someClass.isInterface();
        for (Method method : methods) {
            writeMethod(writer, method, indent, inInterface);
            writer.write("\n");
        }
    }

    private static void writeClass(Writer writer, Class<?> someClass, int indent) throws IOException{
        writeIndents(writer, indent);
        writeCaption(writer, someClass, indent);

        writeFields(writer, someClass, indent + 1);

        writeClasses(writer, someClass, indent + 1);

        writeMethods(writer, someClass, indent + 1);

        writeIndents(writer, indent);
        writer.write("}");
    }


    public static void printStructure(Class<?> someClass) {
        String className = someClass.getSimpleName();
        try (Writer fileWriter = new FileWriter(className + ".java")) { //Writer fileWriter = new PrintWriter(System.out)/*
            writeClass(fileWriter, someClass, 0);

        }
        catch (IOException e) {
            System.out.println("Unable to open/write file!");
        }
    }


    public static void main(String[] args) {
        try {
            Class inter = Class.forName("ru.spbau.reflector.Interable");
            printStructure(inter);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
