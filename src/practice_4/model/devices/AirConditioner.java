package practice_4.model.devices;

import practice_4.model.Actuator;

public class AirConditioner extends Actuator {
    private String mode = "охолодження";

    public AirConditioner(String id, String name, int powerWatts) {
        super(id, name, "клімат", powerWatts);
    }

    public void switchMode(String mode) {
        if (!mode.equals("охолодження") && !mode.equals("обігрів")) {
            throw new IllegalArgumentException("невідомий режим: " + mode);
        }
        this.mode = mode;
        log("режим: " + mode);
    }

    @Override
    public void activate() {
        super.activate();
        log("AirConditioner.activate() - працює в режимі " + mode);
    }

    // в режимі обігріву кондиціонер споживає більше
    @Override
    public double getPowerUsage() {
        double base = super.getPowerUsage();
        return mode.equals("обігрів") ? base * 1.2 : base;
    }

    @Override
    protected String fieldsInfo() {
        return super.fieldsInfo() + ", mode='" + mode + "'";
    }
}
