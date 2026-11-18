package practice_2.model;

public class SmartDevice {
    private String id;
    private String name;
    private String macAddress;
    private String protocol;
    private String roomId;
    private boolean online;
    private int batteryLevel;
    private String firmwareVersion;

    public SmartDevice() {
    }

    public SmartDevice(String id, String name, String macAddress, String protocol, String roomId,
                       boolean online, int batteryLevel, String firmwareVersion) {
        this.id = id;
        this.name = name;
        this.macAddress = macAddress;
        this.protocol = protocol;
        this.roomId = roomId;
        this.online = online;
        this.batteryLevel = batteryLevel;
        this.firmwareVersion = firmwareVersion;
    }

    public void turnOn() {
    }

    public void turnOff() {
    }

    public void ping() {
    }

    public void updateFirmware(String version) {
    }

    @Override
    public String toString() {
        return "SmartDevice{id='" + id + "', name='" + name + "', macAddress='" + macAddress + "', protocol='" + protocol
                + "', roomId='" + roomId + "', online=" + online + ", batteryLevel=" + batteryLevel
                + ", firmwareVersion='" + firmwareVersion + "'}";
    }
}
