package practice_3.model;

public class SmartDevice {
    private final String id;
    private String name;
    private final String macAddress;
    private String protocol;
    private String roomId;
    private boolean online;
    private boolean poweredOn;
    private int batteryLevel;
    private String firmwareVersion;

    public SmartDevice(String id, String name, String macAddress, String protocol, String firmwareVersion) {
        this.id = id;
        this.name = name;
        this.macAddress = macAddress;
        this.protocol = protocol;
        this.firmwareVersion = firmwareVersion;
        this.online = true;
        this.batteryLevel = 100;
    }

    public void turnOn() {
        if (!online) {
            throw new IllegalStateException("пристрій " + name + " не на зв'язку");
        }
        if (batteryLevel == 0) {
            throw new IllegalStateException("батарея пристрою " + name + " розряджена");
        }
        poweredOn = true;
        log("увімкнено");
    }

    public void turnOff() {
        poweredOn = false;
        log("вимкнено");
    }

    public boolean ping() {
        return online;
    }

    public void consumeBattery(int percent) {
        if (percent < 0) {
            throw new IllegalArgumentException("відсоток не може бути від'ємним");
        }
        batteryLevel = Math.max(0, batteryLevel - percent);
        if (batteryLevel == 0) {
            poweredOn = false;
        }
    }

    public void charge() {
        batteryLevel = 100;
    }

    public void updateFirmware(String version) {
        if (version.compareTo(firmwareVersion) <= 0) {
            throw new IllegalArgumentException("версія " + version + " не новіша за " + firmwareVersion);
        }
        firmwareVersion = version;
        log("прошивку оновлено до " + version);
    }

    void assignRoom(String roomId) {
        this.roomId = roomId;
    }

    protected void log(String message) {
        System.out.println("  [" + name + "] " + message);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isPoweredOn() {
        return poweredOn;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    @Override
    public String toString() {
        return "SmartDevice{id='" + id + "', name='" + name + "', macAddress='" + macAddress + "', protocol='" + protocol
                + "', roomId='" + roomId + "', online=" + online + ", poweredOn=" + poweredOn
                + ", batteryLevel=" + batteryLevel + ", firmwareVersion='" + firmwareVersion + "'}";
    }
}
