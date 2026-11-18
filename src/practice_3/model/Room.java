package practice_3.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Room {
    private static final double MIN_TEMPERATURE = 16.0;
    private static final double MAX_TEMPERATURE = 30.0;

    private final String id;
    private String name;
    private int floor;
    private double area;
    private double targetTemperature;
    private final List<String> deviceIds = new ArrayList<>();

    public Room(String id, String name, int floor, double area, double targetTemperature) {
        this.id = id;
        this.name = name;
        this.floor = floor;
        this.area = area;
        changeTargetTemperature(targetTemperature);
    }

    public void changeTargetTemperature(double temperature) {
        if (temperature < MIN_TEMPERATURE || temperature > MAX_TEMPERATURE) {
            throw new IllegalArgumentException("температура має бути від " + MIN_TEMPERATURE + " до " + MAX_TEMPERATURE);
        }
        this.targetTemperature = temperature;
    }

    void attachDevice(String deviceId) {
        if (!deviceIds.contains(deviceId)) {
            deviceIds.add(deviceId);
        }
    }

    void detachDevice(String deviceId) {
        deviceIds.remove(deviceId);
    }

    protected String formatArea() {
        return String.format("%.1f м²", area);
    }

    public List<String> getDeviceIds() {
        return Collections.unmodifiableList(deviceIds);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getTargetTemperature() {
        return targetTemperature;
    }

    @Override
    public String toString() {
        return "Room{id='" + id + "', name='" + name + "', floor=" + floor + ", area=" + formatArea()
                + ", targetTemperature=" + targetTemperature + ", deviceIds=" + deviceIds + "}";
    }
}
