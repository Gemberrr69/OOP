package practice_6.model.devices;

import practice_6.model.Sensor;

public class SmokeSensor extends Sensor {

    public SmokeSensor(String id, String name, double threshold) {
        super(id, name, "дим", " ppm", threshold);
    }

    @Override
    public String getCategory() {
        return "безпека";
    }

    @Override
    protected double readRawValue() {
        return 15;
    }

    @Override
    protected String checkAlarm() {
        if (currentValue > threshold) {
            return "ДИМ " + currentValue + unit + "! можлива пожежа";
        }
        return null;
    }
}
