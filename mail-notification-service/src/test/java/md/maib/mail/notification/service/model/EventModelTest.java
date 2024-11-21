package md.maib.mail.notification.service.model;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class EventModelTest {

    @Test
    void testRecordFields() {
        // Arrange
        String name = "John";
        String surname = "Doe";
        int age = 30;
        String gender = "Male";

        // Act
        EventModel model = new EventModel(name, surname, age, gender);

        // Assert
        assertEquals(name, model.name(), "Name should match");
        assertEquals(surname, model.surname(), "Surname should match");
        assertEquals(age, model.age(), "Age should match");
        assertEquals(gender, model.gender(), "Gender should match");
    }

    @Test
    void testEqualsAndHashCode() {
        // Arrange
        EventModel model1 = new EventModel("Jane", "Doe", 25, "Female");
        EventModel model2 = new EventModel("Jane", "Doe", 25, "Female");

        // Assert
        assertEquals(model1, model2, "Objects with same data should be equal");
        assertEquals(model1.hashCode(), model2.hashCode(), "Hash codes should match for equal objects");
    }

    @Test
    void testToString() {
        // Arrange
        EventModel model = new EventModel("Alice", "Brown", 22, "Female");

        // Act
        String modelString = model.toString();

        // Assert
        assertTrue(modelString.contains("Alice"), "String representation should contain the name");
        assertTrue(modelString.contains("Brown"), "String representation should contain the surname");
        assertTrue(modelString.contains("22"), "String representation should contain the age");
        assertTrue(modelString.contains("Female"), "String representation should contain the gender");
    }

    @Test
    void testSerialization() throws IOException, ClassNotFoundException {
        // Arrange
        EventModel model = new EventModel("John", "Smith", 40, "Male");

        // Serialize
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream objectOut = new ObjectOutputStream(byteOut);
        objectOut.writeObject(model);

        // Deserialize
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream objectIn = new ObjectInputStream(byteIn);
        EventModel deserializedModel = (EventModel) objectIn.readObject();

        // Assert
        assertEquals(model, deserializedModel, "Deserialized object should equal the original");
    }

}
