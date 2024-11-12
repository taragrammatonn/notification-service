package md.maib.integration.notification.service.model;


import java.io.Serializable;

public record EventModel(String name, String surname, int age, String gender) implements Serializable {
}
