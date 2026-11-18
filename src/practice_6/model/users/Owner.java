package practice_6.model.users;

import practice_6.model.Device;
import practice_6.model.Hub;
import practice_6.model.Room;
import practice_6.model.User;

public class Owner extends User {
    private final String address;

    public Owner(String id, User.NameInfo nameInfo, String email, String password, String address) {
        super(id, nameInfo, email, password);
        this.address = address;
    }

    @Override
    public void addDevice(Device device, Room room, Hub hub) {
        super.addDevice(device, room, hub);
        System.out.println("  Власник " + getFullName() + " додав " + device.getName() + " у кімнату " + room.getName());
    }

    @Override
    public String getRoleName() {
        return "власник";
    }

    @Override
    public boolean canAddDevices() {
        return true;
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
