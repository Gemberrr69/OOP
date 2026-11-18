package practice_2.model;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String id;
    private String name;
    private int floor;
    private double area;
    private double targetTemperature;
    private List<String> deviceIds;

    public Room() {
        this.deviceIds = new ArrayList<>();
    }

    public Room(String id, String name, int floor, double area, double targetTemperature, List<String> deviceIds) {
        this.id = id;
        this.name = name;
        this.floor = floor;
        this.area = area;
        this.targetTemperature = targetTemperature;
        this.deviceIds = deviceIds;
    }

    public void addDevice(String deviceId) {
    }

    public void removeDevice(String deviceId) {
    }

    public void turnAllOff() {
    }

    public void setClimate(double temperature) {
    }

    @Override
    public String toString() {
        return "Room{id='" + id + "', name='" + name + "', floor=" + floor + ", area=" + area
                + ", targetTemperature=" + targetTemperature + ", deviceIds=" + deviceIds + "}";
    }
}
