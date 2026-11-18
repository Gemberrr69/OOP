package practice_6.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class User {
    protected final String id;
    protected final NameInfo nameInfo;
    protected String email;
    protected boolean loggedIn;
    protected final List<String> notifications = new ArrayList<>();
    private final String password;

    protected User(String id, NameInfo nameInfo, String email, String password) {
        this.id = id;
        this.nameInfo = nameInfo;
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
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("некоректний email: " + email);
        }
        this.email = email;
    }

    public abstract String getRoleName();

    public abstract String getPermissions();

    public abstract boolean canAddDevices();

    public void addDevice(Device device, Room room, Hub hub) {
        checkLoggedIn();
        if (!canAddDevices()) {
            throw new IllegalStateException(getRoleName() + " " + getFullName() + " не може додавати пристрої");
        }
        hub.registerDevice(device.getId());
        room.attachDevice(device.getId());
        device.assignRoom(room.getId());
        if (device instanceof Sensor sensor) {
            sensor.bindOwner(this);
        }
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.matches("[\\w.]+@[\\w.]+\\.[a-z]{2,}");
    }

    void receiveNotification(String message) {
        notifications.add(message);
    }

    protected void checkLoggedIn() {
        if (!loggedIn) {
            throw new IllegalStateException(getFullName() + " не увійшов у систему");
        }
    }

    public List<String> getNotifications() {
        return Collections.unmodifiableList(notifications);
    }

    public String getFullName() {
        return nameInfo.getFullName();
    }

    public NameInfo getNameInfo() {
        return nameInfo;
    }

    protected String fieldsInfo() {
        return "id='" + id + "', name=" + nameInfo + ", email='" + email + "', loggedIn=" + loggedIn;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + fieldsInfo() + "}";
    }

    public static class NameInfo {
        private final String firstName;
        private final String lastName;

        public NameInfo(String firstName, String lastName) {
            if (firstName.isBlank() || lastName.isBlank()) {
                throw new IllegalArgumentException("ім'я та прізвище не можуть бути порожніми");
            }
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFullName() {
            return firstName + " " + lastName;
        }

        public String getInitials() {
            return lastName + " " + firstName.charAt(0) + ".";
        }

        @Override
        public String toString() {
            return "NameInfo{firstName='" + firstName + "', lastName='" + lastName + "'}";
        }
    }
}
