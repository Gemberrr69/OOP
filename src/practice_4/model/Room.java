package practice_4.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Room {
    protected final String id;
    protected String name;
    protected int floor;
    protected double area;
    protected double targetTemperature;
    protected double minTemperature = 16;
    protected double maxTemperature = 30;
    private final List<String> deviceIds = new ArrayList<>();

    public Room(String id, String name, int floor, double area) {
        this.id = id;
        this.name = name;
        this.floor = floor;
        this.area = area;
        this.targetTemperature = 21;
    }

    public void changeTargetTemperature(double temperature) {
        if (temperature < minTemperature || temperature > maxTemperature) {
            throw new IllegalArgumentException(name + ": температура має бути від " + minTemperature + " до " + maxTemperature);
        }
        targetTemperature = temperature;
        System.out.println("  " + name + ": температуру змінено на " + temperature);
    }

    void attachDevice(String deviceId) {
        if (!deviceIds.contains(deviceId)) {
            deviceIds.add(deviceId);
        }
    }

    void detachDevice(String deviceId) {
        deviceIds.remove(deviceId);
    }

    public String getPurpose() {
        return "звичайна кімната";
    }

    public void prepareForNight() {
        System.out.println("  Room.prepareForNight() - " + name);
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

    protected String fieldsInfo() {
        return "id='" + id + "', name='" + name + "', floor=" + floor + ", area=" + area
                + ", targetTemperature=" + targetTemperature + ", deviceIds=" + deviceIds;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + fieldsInfo() + "}";
    }
}
