package practice_6;

import java.time.LocalDate;
import practice_6.factory.SmartHomeFactory;
import practice_6.model.*;
import practice_6.model.devices.SmartLamp;
import practice_6.storage.HomeStorage;

public class Main {
    public static void main(String[] args) {
        section("1. Створення об'єктів (змінні мають абстрактний тип)");
        User owner = SmartHomeFactory.createOwner("Ігор", "Потужний", "igor.potuzhnyi@gmail.com", "potuzhno123", "Одеса, вул. Шкільна, 7");
        User guest = SmartHomeFactory.createGuest("Олена", "Гостьова", "olena@gmail.com", "guest1", LocalDate.of(2026, 12, 31));
        Room hall = SmartHomeFactory.createHallway("Коридор", 1, 6, true);
        Room kitchen = SmartHomeFactory.createKitchen("Кухня", 1, 14.5, true);
        Room bedroom = SmartHomeFactory.createBedroom("Спальня", 2, 18);
        Hub hub = SmartHomeFactory.createHub("Центральний хаб", "192.168.0.10");
        Device speaker = SmartHomeFactory.createDevice("speaker", "Розумна колонка");
        Device plug = SmartHomeFactory.createDevice("plug", "Розетка для чайника");
        Device smoke = SmartHomeFactory.createDevice("smoke", "Датчик диму");
        Device temp = SmartHomeFactory.createDevice("temperature", "Датчик температури");
        Device humidity = SmartHomeFactory.createDevice("humidity", "Датчик вологості");
        Device blinds = SmartHomeFactory.createDevice("blinds", "Жалюзі");
        Device lamp = SmartHomeFactory.createDevice("lamp", "Розумна лампа");
        Device ac = SmartHomeFactory.createDevice("ac", "Кондиціонер");

        section("2. Користувачі: одна функція showUser(User)");
        owner.login("potuzhno123");
        guest.login("guest1");
        for (User u : HomeStorage.getUsers()) {
            showUser(u);
        }

        section("3. Додавання пристроїв");
        addToRoom(owner, speaker, hall, hub);
        addToRoom(owner, plug, kitchen, hub);
        addToRoom(owner, smoke, kitchen, hub);
        addToRoom(owner, humidity, kitchen, hub);
        addToRoom(owner, temp, bedroom, hub);
        addToRoom(owner, blinds, bedroom, hub);
        addToRoom(owner, lamp, bedroom, hub);
        addToRoom(owner, ac, bedroom, hub);
        addToRoom(guest, lamp, hall, hub);

        section("4. Одна функція runDevice(Device) для всіх пристроїв");
        for (Device d : HomeStorage.getDevices()) {
            runDevice(d);
        }
        System.out.printf("Загальне споживання: %.1f Вт%n", HomeStorage.getTotalPowerUsage());

        section("5. selfTest() викликає абстрактні методи всередині");
        for (Device d : HomeStorage.getDevices()) {
            d.selfTest();
        }

        section("6. Одна функція nightRoutine(Room) для всіх кімнат");
        for (Room r : HomeStorage.getRooms()) {
            nightRoutine(r);
        }

        section("7. Дослідження: пряме посилання і посилання на абстрактний клас");
        SmartLamp direct = SmartHomeFactory.createSmartLamp("Тестова лампа", 12);
        Device abstractRef = direct;
        System.out.println("Це той самий об'єкт: " + (direct == abstractRef));
        direct.changeLevel(40);
        System.out.println("direct.getCategory()       = " + direct.getCategory());
        System.out.println("abstractRef.getCategory()  = " + abstractRef.getCategory());
        System.out.println("direct.getStatus()         = " + direct.getStatus());
        System.out.println("abstractRef.getStatus()    = " + abstractRef.getStatus());
        System.out.println("direct.getPowerUsage()     = " + direct.getPowerUsage());
        System.out.println("abstractRef.getPowerUsage()= " + abstractRef.getPowerUsage());
        System.out.println("abstractRef.getClass()     = " + abstractRef.getClass().getSimpleName());
        direct.changeColor(3000);
        if (abstractRef instanceof SmartLamp lampRef) {
            System.out.println("Після перевірки instanceof метод SmartLamp доступний:");
            lampRef.changeColor(6000);
        }
        System.out.println("Статичний метод залежить від типу посилання, а не об'єкта:");
        System.out.println("direct.kind()      = " + direct.kind());
        System.out.println("abstractRef.kind() = " + abstractRef.kind());

        section("8. Підсумок");
        HomeStorage.printSummary();
        System.out.println("Device.getCreatedCount() = " + Device.getCreatedCount());
        HomeStorage.getDevices().forEach(System.out::println);
    }

    private static void showUser(User user) {
        System.out.println(user.getFullName() + " (" + user.getRoleName() + "): " + user.getPermissions()
                + ", може додавати пристрої: " + (user.canAddDevices() ? "так" : "ні"));
    }

    private static void addToRoom(User user, Device device, Room room, Hub hub) {
        try {
            user.addDevice(device, room, hub);
        } catch (IllegalStateException e) {
            System.out.println("  Помилка: " + e.getMessage());
        }
    }

    private static void runDevice(Device device) {
        System.out.println(device.getName() + " [" + device.getCategory() + "]");
        device.performMainAction();
        System.out.printf("  статус: %s; споживання: %.1f Вт%n", device.getStatus(), device.getPowerUsage());
    }

    private static void nightRoutine(Room room) {
        System.out.println(room.getName() + ": " + room.getPurpose() + ", рекомендоване освітлення " + room.getRecommendedLux() + " лк");
        room.prepareForNight();
    }

    private static void section(String title) {
        System.out.println();
        System.out.println("=== " + title + " ===");
    }
}
