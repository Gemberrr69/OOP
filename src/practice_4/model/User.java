package practice_4.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {
    protected final String id;
    protected String fullName;
    protected String email;
    protected boolean loggedIn;
    protected final List<String> notifications = new ArrayList<>();
    private final String password;

    public User(String id, String fullName, String email, String password) {
        this.id = id;
        this.fullName = fullName;
        changeEmail(email);
        this.password = password;
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

    public void addDevice(Device device, Room room, Hub hub) {
        checkLoggedIn();
        hub.registerDevice(device.getId());
        room.attachDevice(device.getId());
        device.assignRoom(room.getId());
        if (device instanceof Sensor sensor) {
            sensor.bindOwner(this);
        }
    }

    public String getPermissions() {
        return "базовий доступ";
    }

    void receiveNotification(String message) {
        notifications.add(message);
    }

    protected void checkLoggedIn() {
        if (!loggedIn) {
            throw new IllegalStateException(fullName + " не увійшов у систему");
        }
    }

    public List<String> getNotifications() {
        return Collections.unmodifiableList(notifications);
    }

    public String getFullName() {
        return fullName;
    }

    protected String fieldsInfo() {
        return "id='" + id + "', fullName='" + fullName + "', email='" + email + "', loggedIn=" + loggedIn;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + fieldsInfo() + "}";
    }
}
