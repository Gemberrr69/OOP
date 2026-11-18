package practice_4.model.devices;

import practice_4.model.SmartDevice;

public class SmartPlug extends SmartDevice {
    private final int maxLoadWatts;
    private int loadWatts;

    public SmartPlug(String id, String name, String macAddress, int maxLoadWatts) {
        super(id, name, macAddress, "Wi-Fi", "1.0.0");
        this.maxLoadWatts = maxLoadWatts;
    }

    public void plugIn(int watts) {
        loadWatts = watts;
        log("підключено навантаження " + watts + " Вт");
        if (poweredOn && loadWatts > maxLoadWatts) {
            poweredOn = false;
            log("перевантаження! розетку вимкнено (максимум " + maxLoadWatts + " Вт)");
        }
    }

    @Override
    public void turnOn() {
        super.turnOn();
        if (loadWatts > maxLoadWatts) {
            poweredOn = false;
            log("перевантаження! розетку вимкнено (максимум " + maxLoadWatts + " Вт)");
        }
    }

    @Override
    public double getPowerUsage() {
        return poweredOn ? loadWatts + 1 : 0;
    }

    @Override
    public String getStatus() {
        return super.getStatus() + ", навантаження " + loadWatts + " Вт";
    }

    @Override
    protected String fieldsInfo() {
        return super.fieldsInfo() + ", loadWatts=" + loadWatts + ", maxLoadWatts=" + maxLoadWatts;
    }
}
