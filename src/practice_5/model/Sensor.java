package practice_5.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sensor extends Device {
    protected final String sensorType;
    protected final String unit;
    protected double currentValue;
    protected double threshold;
    protected double calibrationOffset;
    protected LocalDateTime lastUpdate;
    private User owner;
    private final List<Reading> history = new ArrayList<>();

    public Sensor(String id, String name, String sensorType, String unit, double threshold) {
        super(id, name);
        this.sensorType = sensorType;
        this.unit = unit;
        setAlarmThreshold(threshold);
    }

    public void updateReading(double rawValue) {
        currentValue = normalize(rawValue);
        lastUpdate = LocalDateTime.now().withNano(0);
        history.add(new Reading(currentValue, lastUpdate));
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

    public List<Reading> getHistory() {
        return Collections.unmodifiableList(history);
    }

    public static double average(List<Reading> readings) {
        double sum = 0;
        for (Reading r : readings) {
            sum += r.getValue();
        }
        return readings.isEmpty() ? 0 : sum / readings.size();
    }

    public double getCurrentValue() {
        return currentValue;
    }

    @Override
    protected String fieldsInfo() {
        return super.fieldsInfo() + ", sensorType='" + sensorType + "', currentValue=" + currentValue + ", unit='" + unit
                + "', threshold=" + threshold + ", lastUpdate=" + lastUpdate;
    }

    public static class Reading {
        private final double value;
        private final LocalDateTime time;

        public Reading(double value, LocalDateTime time) {
            this.value = value;
            this.time = time;
        }

        public double getValue() {
            return value;
        }

        public LocalDateTime getTime() {
            return time;
        }

        @Override
        public String toString() {
            return time.toLocalTime() + " -> " + value;
        }
    }
}
