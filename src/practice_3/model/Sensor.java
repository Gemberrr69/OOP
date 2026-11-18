package practice_3.model;

import java.time.LocalDateTime;

public class Sensor {
    private final String id;
    private String name;
    private String roomId;
    private final String sensorType;
    private double currentValue;
    private final String unit;
    private double threshold;
    private double calibrationOffset;
    private LocalDateTime lastUpdate;
    private User owner;

    public Sensor(String id, String name, String sensorType, String unit, double threshold) {
        this.id = id;
        this.name = name;
        this.sensorType = sensorType;
        this.unit = unit;
        setAlarmThreshold(threshold);
    }

    public void updateReading(double rawValue) {
        currentValue = rawValue + calibrationOffset;
        lastUpdate = LocalDateTime.now().withNano(0);
        if (currentValue > threshold && owner != null) {
            owner.receiveNotification(name + ": " + sensorType + " " + currentValue + unit
                    + " перевищує поріг " + threshold + unit);
        }
    }

    public void calibrate(double offset) {
        if (Math.abs(offset) > 10) {
            throw new IllegalArgumentException("поправка калібрування занадто велика");
        }
        calibrationOffset = offset;
    }

    public void setAlarmThreshold(double threshold) {
        if (threshold <= 0) {
            throw new IllegalArgumentException("поріг має бути більшим за 0");
        }
        this.threshold = threshold;
    }

    void bindOwner(User owner) {
        this.owner = owner;
    }

    void assignRoom(String roomId) {
        this.roomId = roomId;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Sensor{id='" + id + "', name='" + name + "', roomId='" + roomId + "', sensorType='" + sensorType
                + "', currentValue=" + currentValue + ", unit='" + unit + "', threshold=" + threshold
                + ", lastUpdate=" + lastUpdate + "}";
    }
}
