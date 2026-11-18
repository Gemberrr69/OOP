package practice_3.model;

public class Actuator {
    private final String id;
    private String name;
    private String roomId;
    private final String actuatorType;
    private final int powerWatts;
    private int level;
    private boolean active;

    public Actuator(String id, String name, String actuatorType, int powerWatts) {
        this.id = id;
        this.name = name;
        this.actuatorType = actuatorType;
        this.powerWatts = powerWatts;
        this.level = 100;
    }

    public void activate() {
        active = true;
    }

    public void deactivate() {
        active = false;
    }

    public void changeLevel(int level) {
        if (level < 0 || level > 100) {
            throw new IllegalArgumentException("рівень має бути від 0 до 100");
        }
        this.level = level;
        active = level > 0;
    }

    public double estimatePowerUsage() {
        return active ? powerWatts * level / 100.0 : 0;
    }

    void assignRoom(String roomId) {
        this.roomId = roomId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Actuator{id='" + id + "', name='" + name + "', roomId='" + roomId + "', actuatorType='" + actuatorType
                + "', powerWatts=" + powerWatts + ", level=" + level + ", active=" + active + "}";
    }
}
