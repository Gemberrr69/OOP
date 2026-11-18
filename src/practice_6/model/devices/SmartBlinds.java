package practice_6.model.devices;

import practice_6.model.Actuator;

public class SmartBlinds extends Actuator {

    public SmartBlinds(String id, String name, int powerWatts) {
        super(id, name, "штори", powerWatts);
    }

    @Override
    public String getCategory() {
        return "комфорт";
    }

    public void openTo(int percent) {
        changeLevel(percent);
        log("жалюзі відкрито на " + percent + "%");
    }

    // жалюзі споживають енергію тільки під час руху тому в спокої 0
    @Override
    public double getPowerUsage() {
        return 0;
    }

    @Override
    public String getStatus() {
        return connectionStatus() + ", відкрито на " + (active ? level : 0) + "%";
    }
}
