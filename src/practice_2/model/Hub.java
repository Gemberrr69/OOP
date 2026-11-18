package practice_2.model;

import java.util.ArrayList;
import java.util.List;

public class Hub {
    private String id;
    private String ipAddress;
    private String firmwareVersion;
    private boolean online;
    private List<String> connectedDeviceIds;

    public Hub() {
        this.connectedDeviceIds = new ArrayList<>();
    }

    public Hub(String id, String ipAddress, String firmwareVersion, boolean online, List<String> connectedDeviceIds) {
        this.id = id;
        this.ipAddress = ipAddress;
        this.firmwareVersion = firmwareVersion;
        this.online = online;
        this.connectedDeviceIds = connectedDeviceIds;
    }

    public void connectDevice(String deviceId) {
    }

    public void disconnectDevice(String deviceId) {
    }

    public void syncAll() {
    }

    public void reboot() {
    }

    @Override
    public String toString() {
        return "Hub{id='" + id + "', ipAddress='" + ipAddress + "', firmwareVersion='" + firmwareVersion
                + "', online=" + online + ", connectedDeviceIds=" + connectedDeviceIds + "}";
    }
}
