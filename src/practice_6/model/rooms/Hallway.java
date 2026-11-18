package practice_6.model.rooms;

import practice_6.model.Room;

public class Hallway extends Room {
    private final boolean hasEntranceDoor;

    public Hallway(String id, String name, int floor, double area, boolean hasEntranceDoor) {
        super(id, name, floor, area);
        this.hasEntranceDoor = hasEntranceDoor;
    }

    @Override
    public String getPurpose() {
        return "коридор" + (hasEntranceDoor ? ", вхідні двері" : "");
    }

    @Override
    public void prepareForNight() {
        System.out.println("  Hallway.prepareForNight() - " + (hasEntranceDoor ? "перевірено, що вхідні двері зачинено" : "вимкнено світло"));
    }

    @Override
    public int getRecommendedLux() {
        return 100;
    }

    @Override
    protected String fieldsInfo() {
        return super.fieldsInfo() + ", hasEntranceDoor=" + hasEntranceDoor;
    }
}
