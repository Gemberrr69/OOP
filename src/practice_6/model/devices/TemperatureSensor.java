package practice_6.model.devices;

import practice_6.model.Sensor;

public class TemperatureSensor extends Sensor {
    private final double frostLimit;

    public TemperatureSensor(String id, String name, double threshold, double frostLimit) {
        super(id, name, "температура", "°C", threshold);
        this.frostLimit = frostLimit;
    }

    @Override
    public String getCategory() {
        return "клімат";
    }

    @Override
    protected double readRawValue() {
        return 22.4;
    }

    @Override
    protected String checkAlarm() {
        if (currentValue < frostLimit) {
            return "температура " + currentValue + unit + " нижча за " + frostLimit + unit + ", ризик замерзання труб";
        }
        return super.checkAlarm();
    }

    @Override
    public void selfTest() {
        super.selfTest();
        boolean ok = currentValue > -40 && currentValue < 80;
        System.out.println("  " + name + ": діапазон температури " + (ok ? "OK" : "FAIL"));
    }

    @Override
    protected String fieldsInfo() {
        return super.fieldsInfo() + ", frostLimit=" + frostLimit;
    }
}
