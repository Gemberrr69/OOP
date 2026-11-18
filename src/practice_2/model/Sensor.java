package practice_2.model;

import java.time.LocalDateTime;

public class Sensor {
    private String id;
    private String name;
    private String roomId;
    private String sensorType;
    private double currentValue;
    private String unit;
    private double threshold;
    private LocalDateTime lastUpdate;

    public Sensor() {
    }

    public Sensor(String id, String name, String roomId, String sensorType, double currentValue,
                  String unit, double threshold, LocalDateTime lastUpdate) {
        this.id = id;
        this.name = name;
        this.roomId = roomId;
        this.sensorType = sensorType;
        this.currentValue = currentValue;
        this.unit = unit;
        this.threshold = threshold;
        this.lastUpdate = lastUpdate;
    }

    public void readValue() {
    }

    public void calibrate(double offset) {
    }

    public void setAlarmThreshold(double threshold) {
    }

    @Override
    public String toString() {
        return "Sensor{id='" + id + "', name='" + name + "', roomId='" + roomId + "', sensorType='" + sensorType
                + "', currentValue=" + currentValue + ", unit='" + unit + "', threshold=" + threshold
                + ", lastUpdate=" + lastUpdate + "}";
    }
}
