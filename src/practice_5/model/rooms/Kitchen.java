package practice_5.model.rooms;

import practice_5.model.Room;

public class Kitchen extends Room {
    private final boolean hasGasStove;

    public Kitchen(String id, String name, int floor, double area, boolean hasGasStove) {
        super(id, name, floor, area);
        this.hasGasStove = hasGasStove;
    }

    @Override
    public String getPurpose() {
        return "кухня, приготування їжі" + (hasGasStove ? ", є газова плита" : "");
    }

    @Override
    public void prepareForNight() {
        super.prepareForNight();
        System.out.println("  Kitchen.prepareForNight() - " + (hasGasStove ? "перевірено, що газ перекрито" : "вимкнено плиту"));
    }

    @Override
    protected String fieldsInfo() {
        return super.fieldsInfo() + ", hasGasStove=" + hasGasStove;
    }
}
