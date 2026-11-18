package practice_3.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hub {
    int maxDevices = 10;

    private final String id;
    private final String ipAddress;
    private String firmwareVersion;
    private boolean online;
    private final List<String> connectedDeviceIds = new ArrayList<>();

    public Hub(String id, String ipAddress, String firmwareVersion) {
        this.id = id;
        this.ipAddress = ipAddress;
        this.firmwareVersion = firmwareVersion;
        this.online = true;
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

    public void reboot() {
        online = false;
        online = true;
    }

    public int getConnectedCount() {
        return connectedDeviceIds.size();
    }

    public List<String> getConnectedDeviceIds() {
        return Collections.unmodifiableList(connectedDeviceIds);
    }

    @Override
    public String toString() {
        return "Hub{id='" + id + "', ipAddress='" + ipAddress + "', firmwareVersion='" + firmwareVersion
                + "', online=" + online + ", connectedDevices=" + connectedDeviceIds.size() + "/" + maxDevices
                + ", connectedDeviceIds=" + connectedDeviceIds + "}";
    }
}
