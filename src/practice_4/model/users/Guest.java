package practice_4.model.users;

import java.time.LocalDate;
import practice_4.model.Device;
import practice_4.model.Hub;
import practice_4.model.Room;
import practice_4.model.User;

public class Guest extends User {
    private final LocalDate accessUntil;

    public Guest(String id, String fullName, String email, String password, LocalDate accessUntil) {
        super(id, fullName, email, password);
        this.accessUntil = accessUntil;
    }

    @Override
    public void addDevice(Device device, Room room, Hub hub) {
        checkLoggedIn();
        throw new IllegalStateException("гість " + fullName + " не може додавати пристрої");
    }

    @Override
    public String getPermissions() {
        return "тільки перегляд, доступ до " + accessUntil;
    }

    @Override
    protected String fieldsInfo() {
        return super.fieldsInfo() + ", accessUntil=" + accessUntil;
    }
}
