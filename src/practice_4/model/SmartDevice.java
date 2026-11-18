package practice_4.model;

public class SmartDevice extends Device {
    private final String macAddress;
    private final String protocol;
    protected boolean poweredOn;
    protected int batteryLevel = 100;
    protected String firmwareVersion;

    public SmartDevice(String id, String name, String macAddress, String protocol, String firmwareVersion) {
        super(id, name);
        this.macAddress = macAddress;
        this.protocol = protocol;
        this.firmwareVersion = firmwareVersion;
    }

    @Override
    void assignRoom(String roomId) {
        super.assignRoom(roomId);
        log("SmartDevice.assignRoom(): кімната " + roomId);
    }

    public void turnOn() {
        if (!online) {
            throw new IllegalStateException(name + " не на зв'язку");
        }
        if (batteryLevel == 0) {
            throw new IllegalStateException("батарея " + name + " розряджена");
        }
        poweredOn = true;
        log("увімкнено");
    }

    public void turnOff() {
        poweredOn = false;
        log("вимкнено");
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

    @Override
    public String getStatus() {
        return super.getStatus() + ", " + (poweredOn ? "увімкнено" : "вимкнено") + ", батарея " + batteryLevel + "%";
    }

    @Override
    public double getPowerUsage() {
        return poweredOn ? 2 : 0;
    }

    @Override
    public void selfTest() {
        super.selfTest();
        System.out.println("  " + name + ": SmartDevice.selfTest() - батарея " + (batteryLevel > 20 ? "OK" : "LOW"));
    }

    public boolean isPoweredOn() {
        return poweredOn;
    }

    @Override
    protected String fieldsInfo() {
        return super.fieldsInfo() + ", macAddress='" + macAddress + "', protocol='" + protocol + "', poweredOn="
                + poweredOn + ", batteryLevel=" + batteryLevel + ", firmwareVersion='" + firmwareVersion + "'";
    }
}
