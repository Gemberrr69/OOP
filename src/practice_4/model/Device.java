package practice_4.model;

public class Device {
    protected final String id;
    protected String name;
    protected String roomId;
    protected boolean online = true;

    public Device(String id, String name) {
        this.id = id;
        this.name = name;
    }

    void assignRoom(String roomId) {
        this.roomId = roomId;
    }

    public boolean ping() {
        return online;
    }

    public String getStatus() {
        return online ? "на зв'язку" : "не на зв'язку";
    }

    public double getPowerUsage() {
        return 0;
    }

    public void selfTest() {
        System.out.println("  " + name + ": Device.selfTest() - зв'язок " + (online ? "OK" : "FAIL"));
    }

    public void reboot() {
        System.out.println("  Device.reboot() для " + name);
        online = true;
    }

    protected void log(String message) {
        System.out.println("  [" + name + "] " + message);
    }

    protected String fieldsInfo() {
        return "id='" + id + "', name='" + name + "', roomId='" + roomId + "', online=" + online;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + fieldsInfo() + "}";
    }
}
