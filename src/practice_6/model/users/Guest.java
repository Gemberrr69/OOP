package practice_6.model.users;

import java.time.LocalDate;
import practice_6.model.User;

public class Guest extends User {
    private final LocalDate accessUntil;

    public Guest(String id, User.NameInfo nameInfo, String email, String password, LocalDate accessUntil) {
        super(id, nameInfo, email, password);
        this.accessUntil = accessUntil;
    }

    @Override
    public String getRoleName() {
        return "гість";
    }

    @Override
    public boolean canAddDevices() {
        return false;
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
