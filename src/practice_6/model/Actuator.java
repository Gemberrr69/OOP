package practice_6.model;

public abstract class Actuator extends Device {
    protected final String actuatorType;
    protected final int powerWatts;
    protected int level = 100;
    protected boolean active;

    protected Actuator(String id, String name, String actuatorType, int powerWatts) {
        super(id, name);
        this.actuatorType = actuatorType;
        this.powerWatts = powerWatts;
    }

    public void activate() {
        active = true;
        log("Actuator.activate()");
    }

    public void deactivate() {
        active = false;
        log("Actuator.deactivate()");
    }

    @Override
    public void performMainAction() {
        if (active) {
            deactivate();
        } else {
            activate();
        }
    }

    public void changeLevel(int level) {
        if (level < 0 || level > 100) {
            throw new IllegalArgumentException("рівень має бути від 0 до 100");
        }
        this.level = level;
        active = level > 0;
    }

    @Override
    public String getStatus() {
        return connectionStatus() + ", " + (active ? "працює на " + level + "%" : "не працює");
    }

    @Override
    public double getPowerUsage() {
        return active ? powerWatts * level / 100.0 : 0;
    }

    @Override
    protected String fieldsInfo() {
        return super.fieldsInfo() + ", actuatorType='" + actuatorType + "', powerWatts=" + powerWatts
                + ", level=" + level + ", active=" + active;
    }
}
