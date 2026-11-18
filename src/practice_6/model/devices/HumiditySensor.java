package practice_6.model.devices;

import practice_6.model.Sensor;

public class HumiditySensor extends Sensor {

    public HumiditySensor(String id, String name, double threshold) {
        super(id, name, "вологість", "%", threshold);
    }

    @Override
    public String getCategory() {
        return "клімат";
    }

    @Override
    protected double readRawValue() {
        return 55;
    }

    // вологість не може бути меншою за 0% або більшою за 100%
    @Override
    protected double normalize(double rawValue) {
        double value = super.normalize(rawValue);
        return Math.max(0, Math.min(100, value));
    }
}
