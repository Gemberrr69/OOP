package practice_4.model;

import java.time.LocalDateTime;

public class Sensor extends Device {
    protected final String sensorType;
    protected final String unit;
    protected double currentValue;
    protected double threshold;
    protected double calibrationOffset;
    protected LocalDateTime lastUpdate;
    private User owner;

    public Sensor(String id, String name, String sensorType, String unit, double threshold) {
        super(id, name);
        this.sensorType = sensorType;
        this.unit = unit;
        setAlarmThreshold(threshold);
    }

    public void updateReading(double rawValue) {
        currentValue = normalize(rawValue);
        lastUpdate = LocalDateTime.now().withNano(0);
        String alarm = checkAlarm();
        if (alarm != null && owner != null) {
            owner.receiveNotification(name + ": " + alarm);
        }
    }

    protected String checkAlarm() {
        if (currentValue > threshold) {
            return sensorType + " " + currentValue + unit + " перевищує поріг " + threshold + unit;
        }
        return null;
    }

    protected double normalize(double rawValue) {
        return rawValue + calibrationOffset;
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

    @Override
    public String getStatus() {
        return super.getStatus() + ", " + sensorType + " = " + currentValue + unit;
    }

    @Override
    public double getPowerUsage() {
        return 0.5;
    }

    @Override
    public void selfTest() {
        super.selfTest();
        System.out.println("  " + name + ": Sensor.selfTest() - останнє зчитування " + (lastUpdate == null ? "відсутнє" : lastUpdate));
    }

    public double getCurrentValue() {
        return currentValue;
    }

    @Override
    protected String fieldsInfo() {
        return super.fieldsInfo() + ", sensorType='" + sensorType + "', currentValue=" + currentValue + ", unit='" + unit
                + "', threshold=" + threshold + ", lastUpdate=" + lastUpdate;
    }
}
