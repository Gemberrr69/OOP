package practice_6.factory;

import java.time.LocalDate;
import practice_6.model.*;
import practice_6.model.devices.*;
import practice_6.model.rooms.*;
import practice_6.model.users.*;
import practice_6.storage.HomeStorage;

public final class SmartHomeFactory {
    private static int userCounter = 0;
    private static int roomCounter = 0;
    private static int deviceCounter = 0;

    private SmartHomeFactory() {
    }

    private static String nextId(char prefix, int number) {
        return String.format("%c-%02d", prefix, number);
    }

    private static <T extends Device> T register(T device) {
        HomeStorage.addDevice(device);
        System.out.println("  SmartHomeFactory: створено " + device.getClass().getSimpleName() + " " + device.getId() + " (" + device.getName() + ")");
        return device;
    }

    private static <T extends Room> T register(T room) {
        HomeStorage.addRoom(room);
        System.out.println("  SmartHomeFactory: створено " + room.getClass().getSimpleName() + " " + room.getId() + " (" + room.getName() + ")");
        return room;
    }

    private static <T extends User> T register(T user) {
        HomeStorage.addUser(user);
        System.out.println("  SmartHomeFactory: створено " + user.getClass().getSimpleName() + " " + user.getFullName());
        return user;
    }

    public static Owner createOwner(String firstName, String lastName, String email, String password, String address) {
        return register(new Owner(nextId('u', ++userCounter), new User.NameInfo(firstName, lastName), email, password, address));
    }

    public static Guest createGuest(String firstName, String lastName, String email, String password, LocalDate accessUntil) {
        return register(new Guest(nextId('u', ++userCounter), new User.NameInfo(firstName, lastName), email, password, accessUntil));
    }

    public static Hallway createHallway(String name, int floor, double area, boolean hasEntranceDoor) {
        return register(new Hallway(nextId('r', ++roomCounter), name, floor, area, hasEntranceDoor));
    }

    public static Kitchen createKitchen(String name, int floor, double area, boolean hasGasStove) {
        return register(new Kitchen(nextId('r', ++roomCounter), name, floor, area, hasGasStove));
    }

    public static Bedroom createBedroom(String name, int floor, double area) {
        return register(new Bedroom(nextId('r', ++roomCounter), name, floor, area));
    }

    public static Hub createHub(String name, String ipAddress) {
        return register(new Hub(nextId('h', ++deviceCounter), name, ipAddress));
    }

    public static SmartPlug createSmartPlug(String name, String macAddress, int maxLoadWatts) {
        return register(new SmartPlug(nextId('d', ++deviceCounter), name, macAddress, maxLoadWatts));
    }

    public static SmartSpeaker createSmartSpeaker(String name, String macAddress) {
        return register(new SmartSpeaker(nextId('d', ++deviceCounter), name, macAddress));
    }

    public static SmokeSensor createSmokeSensor(String name, double threshold) {
        return register(new SmokeSensor(nextId('s', ++deviceCounter), name, threshold));
    }

    public static SmartBlinds createSmartBlinds(String name, int powerWatts) {
        return register(new SmartBlinds(nextId('a', ++deviceCounter), name, powerWatts));
    }

    public static TemperatureSensor createTemperatureSensor(String name, double threshold, double frostLimit) {
        return register(new TemperatureSensor(nextId('s', ++deviceCounter), name, threshold, frostLimit));
    }

    public static HumiditySensor createHumiditySensor(String name, double threshold) {
        return register(new HumiditySensor(nextId('s', ++deviceCounter), name, threshold));
    }

    public static SmartLamp createSmartLamp(String name, int powerWatts) {
        return register(new SmartLamp(nextId('a', ++deviceCounter), name, powerWatts));
    }

    public static AirConditioner createAirConditioner(String name, int powerWatts) {
        return register(new AirConditioner(nextId('a', ++deviceCounter), name, powerWatts));
    }

    public static Device createDevice(String type, String name) {
        return switch (type) {
            case "plug" -> createSmartPlug(name, "00:00:00:00:00:00", 2000);
            case "temperature" -> createTemperatureSensor(name, 28, 10);
            case "humidity" -> createHumiditySensor(name, 70);
            case "lamp" -> createSmartLamp(name, 9);
            case "ac" -> createAirConditioner(name, 900);
            case "speaker" -> createSmartSpeaker(name, "A4:C1:38:00:00:00");
            case "smoke" -> createSmokeSensor(name, 300);
            case "blinds" -> createSmartBlinds(name, 15);
            default -> throw new IllegalArgumentException("невідомий тип пристрою: " + type);
        };
    }

    public static int getCreatedDevicesCount() {
        return deviceCounter;
    }
}
