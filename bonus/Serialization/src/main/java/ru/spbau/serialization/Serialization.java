package ru.spbau.serialization;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Class with two static methods for object serialization.
 * Supports classes with primitive type fields and without parametrized constructors.
 */
public class Serialization {

    /**
     * Sorts the array of fields by their names.
     * Is necessary because getFields() method of Class does not guarantee the order of the fields.
     * @param fields array of fields
     * @return sorted array of fields
     */
    private static Field[] sortedFields(Field[] fields) {
        return Arrays.stream(fields).sorted(Comparator.comparing(Field::getName)).toArray(Field[]::new);
    }

    /**
     * Serializes the object and writes it to the output stream.
     * If the class has a null String field, this method throws RuntimeException.
     * @param object object to serialize
     * @param outputStream stream to write object to
     */
    public static void serialize(Object object, OutputStream outputStream) throws IllegalAccessException, IOException, UnsupportedTypeException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        if (object == null) {
            dataOutputStream.writeBoolean(true);
            return;
        }

        dataOutputStream.writeBoolean(false);

        Field[] fields = sortedFields(object.getClass().getFields());

        for (Field field : fields) {
            Class<?> type = field.getType();

            if (type == boolean.class) {
                dataOutputStream.writeBoolean((boolean) field.get(object));
            } else if (type == int.class) {
                dataOutputStream.writeInt((int) field.get(object));
            } else if (type == float.class) {
                dataOutputStream.writeFloat((float) field.get(object));
            } else if (type == double.class) {
                dataOutputStream.writeDouble((double) field.get(object));
            } else if (type == long.class) {
                dataOutputStream.writeLong((long) field.get(object));
            } else if (type == short.class) {
                dataOutputStream.writeShort((short) field.get(object));
            } else if (type == char.class) {
                dataOutputStream.writeChar((char) field.get(object));
            } else if (type == String.class) {
                dataOutputStream.writeUTF((String) field.get(object));
            } else {
                throw new UnsupportedTypeException();
            }
        }
    }

    /**
     * Deserializes the object from an InputStream and returns an instance of it.
     * @param inputStream InputStream to deserialize from
     * @param clazz class we get
     * @param <T> type of the class we get
     * @return instance of the class with fields filled with values from the stream
     */
    public static <T> T deserialize(InputStream inputStream, Class<T> clazz) throws IllegalAccessException, InstantiationException, IOException, UnsupportedTypeException {
        DataInputStream dataInputStream = new DataInputStream(inputStream);

        boolean isNull = dataInputStream.readBoolean();
        if (isNull) {
            return null;
        }

        T instance  = clazz.newInstance();
        Field[] fields = sortedFields(clazz.getFields());

        for (Field field : fields) {
            Object fieldValue;
            Class<?> type = field.getType();

            if (type == boolean.class) {
                fieldValue = dataInputStream.readBoolean();
            } else if (type == int.class) {
                fieldValue = dataInputStream.readInt();
            } else if (type == float.class) {
                fieldValue = dataInputStream.readFloat();
            } else if (type == double.class) {
                fieldValue = dataInputStream.readDouble();
            } else if (type == long.class) {
                fieldValue = dataInputStream.readLong();
            } else if (type == short.class) {
                fieldValue = dataInputStream.readShort();
            } else if (type == char.class) {
                fieldValue = dataInputStream.readChar();
            } else if (type == String.class) {
                fieldValue = dataInputStream.readUTF();
            } else {
                throw new UnsupportedTypeException();
            }

            field.set(instance, fieldValue);
        }

        return instance;
    }
}
