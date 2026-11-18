package practice_2.model;

import java.time.LocalDateTime;

public class User {
    private String id;
    private String fullName;
    private String email;
    private String phone;
    private String role;
    private LocalDateTime registeredAt;

    public User() {
    }

    public User(String id, String fullName, String email, String phone, String role, LocalDateTime registeredAt) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.registeredAt = registeredAt;
    }

    public void login(String password) {
    }

    public void logout() {
    }

    public void addDevice(SmartDevice device) {
    }

    public void receiveNotification(String message) {
    }

    @Override
    public String toString() {
        return "User{id='" + id + "', fullName='" + fullName + "', email='" + email + "', phone='" + phone
                + "', role='" + role + "', registeredAt=" + registeredAt + "}";
    }
}
