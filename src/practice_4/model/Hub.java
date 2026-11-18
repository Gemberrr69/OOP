package practice_4.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hub extends Device {
    int maxDevices = 15;

    private final String ipAddress;
    private final List<String> connectedDeviceIds = new ArrayList<>();

    public Hub(String id, String name, String ipAddress) {
        super(id, name);
        this.ipAddress = ipAddress;
    }

    void registerDevice(String deviceId) {
        if (!online) {
            throw new IllegalStateException("хаб не працює");
        }
        if (connectedDeviceIds.contains(deviceId)) {
            throw new IllegalArgumentException("пристрій " + deviceId + " вже підключено");
        }
        if (connectedDeviceIds.size() >= maxDevices) {
            throw new IllegalStateException("досягнуто ліміт пристроїв (" + maxDevices + ")");
        }
        connectedDeviceIds.add(deviceId);
    }

    public void disconnectDevice(String deviceId) {
        connectedDeviceIds.remove(deviceId);
    }

    @Override
    public void reboot() {
        online = false;
        log("Hub.reboot(): перезавантаження, відключено " + connectedDeviceIds.size() + " пристроїв");
        online = true;
        log("Hub.reboot(): пристрої підключено знову");
    }

    @Override
    public String getStatus() {
        return super.getStatus() + ", підключено " + connectedDeviceIds.size() + "/" + maxDevices;
    }

    @Override
    public double getPowerUsage() {
        return 5;
    }

    public List<String> getConnectedDeviceIds() {
        return Collections.unmodifiableList(connectedDeviceIds);
    }

    @Override
    protected String fieldsInfo() {
        return super.fieldsInfo() + ", ipAddress='" + ipAddress + "', connectedDeviceIds=" + connectedDeviceIds;
    }
}
