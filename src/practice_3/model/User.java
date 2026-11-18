package practice_3.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {
    private final String id;
    private String fullName;
    private String email;
    private String phone;
    private String role;
    private final LocalDateTime registeredAt;
    private final String password;
    private boolean loggedIn;
    private final List<String> notifications = new ArrayList<>();

    public User(String id, String fullName, String email, String phone, String role, String password) {
        this.id = id;
        this.fullName = fullName;
        changeEmail(email);
        this.phone = phone;
        this.role = role;
        this.password = password;
        this.registeredAt = LocalDateTime.now();
    }

    public boolean login(String password) {
        loggedIn = this.password.equals(password);
        return loggedIn;
    }

    public void logout() {
        loggedIn = false;
    }

    public void changeEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("некоректний email: " + email);
        }
        this.email = email;
    }

    public void addDevice(SmartDevice device, Room room, Hub hub) {
        checkLoggedIn();
        hub.registerDevice(device.getId());
        room.attachDevice(device.getId());
        device.assignRoom(room.getId());
    }

    public void addDevice(Sensor sensor, Room room, Hub hub) {
        checkLoggedIn();
        hub.registerDevice(sensor.getId());
        room.attachDevice(sensor.getId());
        sensor.assignRoom(room.getId());
        sensor.bindOwner(this);
    }

    public void addDevice(Actuator actuator, Room room, Hub hub) {
        checkLoggedIn();
        hub.registerDevice(actuator.getId());
        room.attachDevice(actuator.getId());
        actuator.assignRoom(room.getId());
    }

    void receiveNotification(String message) {
        notifications.add(message);
    }

    private void checkLoggedIn() {
        if (!loggedIn) {
            throw new IllegalStateException("спочатку потрібно увійти в систему");
        }
    }

    public List<String> getNotifications() {
        return Collections.unmodifiableList(notifications);
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    @Override
    public String toString() {
        return "User{id='" + id + "', fullName='" + fullName + "', email='" + email + "', phone='" + phone
                + "', role='" + role + "', registeredAt=" + registeredAt.withNano(0) + ", loggedIn=" + loggedIn + "}";
    }
}
