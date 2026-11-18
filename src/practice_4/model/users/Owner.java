package practice_4.model.users;

import practice_4.model.Device;
import practice_4.model.Hub;
import practice_4.model.Room;
import practice_4.model.User;

public class Owner extends User {
    private final String address;

    public Owner(String id, String fullName, String email, String password, String address) {
        super(id, fullName, email, password);
        this.address = address;
    }

    @Override
    public void addDevice(Device device, Room room, Hub hub) {
        super.addDevice(device, room, hub);
        System.out.println("  Власник " + fullName + " додав " + device.getName() + " у кімнату " + room.getName());
    }

    @Override
    public String getPermissions() {
        return "повний доступ (додавання пристроїв, керування, налаштування)";
    }

    @Override
    protected String fieldsInfo() {
        return super.fieldsInfo() + ", address='" + address + "', notifications=" + notifications.size();
    }
}
