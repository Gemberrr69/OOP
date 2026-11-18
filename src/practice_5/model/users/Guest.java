package practice_5.model.users;

import java.time.LocalDate;
import practice_5.model.Device;
import practice_5.model.Hub;
import practice_5.model.Room;
import practice_5.model.User;

public class Guest extends User {
    private final LocalDate accessUntil;

    public Guest(String id, User.NameInfo nameInfo, String email, String password, LocalDate accessUntil) {
        super(id, nameInfo, email, password);
        this.accessUntil = accessUntil;
    }

    @Override
    public void addDevice(Device device, Room room, Hub hub) {
        checkLoggedIn();
        throw new IllegalStateException("гість " + getFullName() + " не може додавати пристрої");
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
