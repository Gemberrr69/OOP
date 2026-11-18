package practice_6.model.rooms;

import practice_6.model.Room;

public class Bedroom extends Room {
    private boolean nightMode;

    public Bedroom(String id, String name, int floor, double area) {
        super(id, name, floor, area);
        minTemperature = 18;
        maxTemperature = 24;
    }

    @Override
    public String getPurpose() {
        return "спальня, комфортна температура для сну 18-24";
    }

    @Override
    public void prepareForNight() {
        nightMode = true;
        targetTemperature = 19;
        System.out.println("  Bedroom.prepareForNight() - нічний режим, температура 19");
    }

    @Override
    public int getRecommendedLux() {
        return 150;
    }

    @Override
    protected String fieldsInfo() {
        return super.fieldsInfo() + ", nightMode=" + nightMode;
    }
}
