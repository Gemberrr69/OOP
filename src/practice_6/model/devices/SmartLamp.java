package practice_6.model.devices;

import practice_6.model.Actuator;

public class SmartLamp extends Actuator {
    private int colorTemperature = 4000;

    public SmartLamp(String id, String name, int powerWatts) {
        super(id, name, "освітлення", powerWatts);
    }

    public static String kind() {
        return "лампа (SmartLamp.kind)";
    }

    @Override
    public String getCategory() {
        return "освітлення";
    }

    public void changeColor(int kelvin) {
        if (kelvin < 2700 || kelvin > 6500) {
            throw new IllegalArgumentException("колір лампи має бути від 2700K до 6500K");
        }
        colorTemperature = kelvin;
        log("колір змінено на " + kelvin + "K");
    }

    @Override
    public String getStatus() {
        return super.getStatus() + ", колір " + colorTemperature + "K";
    }

    @Override
    protected String fieldsInfo() {
        return super.fieldsInfo() + ", colorTemperature=" + colorTemperature;
    }
}
