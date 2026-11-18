package practice_2.model;

public class Actuator {
    private String id;
    private String name;
    private String roomId;
    private String actuatorType;
    private int powerWatts;
    private int level;
    private boolean active;

    public Actuator() {
    }

    public Actuator(String id, String name, String roomId, String actuatorType, int powerWatts, int level, boolean active) {
        this.id = id;
        this.name = name;
        this.roomId = roomId;
        this.actuatorType = actuatorType;
        this.powerWatts = powerWatts;
        this.level = level;
        this.active = active;
    }

    public void activate() {
    }

    public void deactivate() {
    }

    public void setLevel(int level) {
    }

    @Override
    public String toString() {
        return "Actuator{id='" + id + "', name='" + name + "', roomId='" + roomId + "', actuatorType='" + actuatorType
                + "', powerWatts=" + powerWatts + ", level=" + level + ", active=" + active + "}";
    }
}
