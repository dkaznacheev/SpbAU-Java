package ru.spbau.serialization;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static ru.spbau.serialization.Serialization.deserialize;
import static ru.spbau.serialization.Serialization.serialize;

public class SerializationTest {
    public static class SimpleClass {
        public boolean booleanField2;
        public int intField;
        public boolean booleanField1;

        @Override
        public boolean equals(Object o) {
            if (o == null || o.getClass() != getClass())
                return false;
            SimpleClass o1 = (SimpleClass) o;
            return     o1.intField == intField
                    && o1.booleanField1 == booleanField1
                    && o1.booleanField2 == booleanField2;
        }
    }

    public static class StringFieldClass {
        public String stringField;

        @Override
        public boolean equals(Object o) {
            if (o == null || o.getClass() != getClass())
                return false;
            StringFieldClass o1 = (StringFieldClass) o;
            return o1.stringField.equals(stringField);
        }
    }

    public class UnsupportedFieldClass {
        public UnsupportedFieldClass unsupportedField;
    }

    private class PrivateFieldClass {
        private int privateIntField = 239;
    }

    public static class PrimitiveClass {
        public boolean booleanField;
        public int intField;
        public float floatField;
        public double doubleField;
        public long longField;
        public short shortField;
        public char charField;

        @Override
        public boolean equals(Object o) {
            if (o == null || o.getClass() != getClass())
                return false;
            PrimitiveClass o1 = (PrimitiveClass) o;
            return     o1.intField == intField
                    && o1.booleanField == booleanField
                    && o1.floatField == floatField
                    && o1.doubleField == doubleField
                    && o1.longField == longField
                    && o1.shortField == shortField
                    && o1.charField == charField;
        }
    }

    @Test
    public void testSimpleInstanceSerializes() throws IOException, InstantiationException, IllegalAccessException, UnsupportedTypeException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        SimpleClass test1 = new SimpleClass();
        test1.booleanField1 = false;
        test1.booleanField2 = true;
        test1.intField = 239;
        serialize(test1, os);
        InputStream is = new ByteArrayInputStream(os.toByteArray());
        SimpleClass test2 = deserialize(is, SimpleClass.class);
        assertEquals(test1, test2);
    }

    @Test
    public void testNullInstanceSerializes() throws IOException, InstantiationException, IllegalAccessException, UnsupportedTypeException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        SimpleClass test1 = null;
        serialize(test1, os);
        InputStream is = new ByteArrayInputStream(os.toByteArray());
        SimpleClass test2 = deserialize(is, SimpleClass.class);
        assertEquals(test1, test2);
    }

    @Test
    public void testStringFieldInstanceSerializes() throws IOException, InstantiationException, IllegalAccessException, UnsupportedTypeException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        StringFieldClass test1 = new StringFieldClass();
        test1.stringField = "test";
        serialize(test1, os);
        InputStream is = new ByteArrayInputStream(os.toByteArray());
        StringFieldClass test2 = deserialize(is, StringFieldClass.class);
        assertEquals(test1, test2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testStringNullFieldInstanceThrows() throws IOException, InstantiationException, IllegalAccessException, UnsupportedTypeException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        StringFieldClass test1 = new StringFieldClass();
        test1.stringField = null;
        serialize(test1, os);
    }

    @Test (expected = UnsupportedTypeException.class)
    public void testUnsupportedFieldTypeInstanceThrows() throws IOException, InstantiationException, IllegalAccessException, UnsupportedTypeException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        UnsupportedFieldClass test1 = new UnsupportedFieldClass();
        test1.unsupportedField = test1;
        serialize(test1, os);
    }

    @Test
    public void testPrimitiveInstanceSerializes() throws IOException, InstantiationException, IllegalAccessException, UnsupportedTypeException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrimitiveClass test1 = new PrimitiveClass();
        test1.booleanField = true;
        test1.intField = 1;
        test1.floatField = 1;
        test1.doubleField = 1;
        test1.longField = 1;
        test1.shortField = 1;
        test1.charField = 1;
        serialize(test1, os);

        InputStream is = new ByteArrayInputStream(os.toByteArray());
        PrimitiveClass test2 = deserialize(is, PrimitiveClass.class);
        assertEquals(test1, test2);
    }

    @Test (expected = InstantiationException.class)
    public void testPrivateFieldInstanceThrows() throws IOException, InstantiationException, IllegalAccessException, UnsupportedTypeException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrivateFieldClass test1 = new PrivateFieldClass();
        serialize(test1, os);
        InputStream is = new ByteArrayInputStream(os.toByteArray());
        PrivateFieldClass test2 = deserialize(is, PrivateFieldClass.class);
        assertEquals(test1, test2);
    }
}
