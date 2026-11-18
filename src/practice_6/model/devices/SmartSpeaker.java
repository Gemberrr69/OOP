package practice_6.model.devices;

import practice_6.model.SmartDevice;

public class SmartSpeaker extends SmartDevice {
    private int volume = 30;

    public SmartSpeaker(String id, String name, String macAddress) {
        super(id, name, macAddress, "Wi-Fi", "3.1.0");
    }

    public void changeVolume(int volume) {
        if (volume < 0 || volume > 100) {
            throw new IllegalArgumentException("гучність має бути від 0 до 100");
        }
        this.volume = volume;
        log("гучність " + volume + "%");
    }

    @Override
    public String getCategory() {
        return "мультимедіа";
    }

    // основна дія колонки: увімкнути музику
    @Override
    public void performMainAction() {
        if (!poweredOn) {
            turnOn();
        }
        log("грає музика, гучність " + volume + "%");
    }

    @Override
    public double getPowerUsage() {
        return poweredOn ? 5 + volume / 10.0 : 0;
    }

    @Override
    protected String fieldsInfo() {
        return super.fieldsInfo() + ", volume=" + volume;
    }
}
