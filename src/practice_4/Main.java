package practice_4;

import java.time.LocalDate;
import java.util.List;
import practice_4.model.*;
import practice_4.model.devices.*;
import practice_4.model.rooms.*;
import practice_4.model.users.*;

public class Main {
    public static void main(String[] args) {
        section("1. Користувачі (ієрархія User)");
        User user = new User("u-000", "Тестовий користувач", "test@gmail.com", "1111");
        Owner owner = new Owner("u-001", "Ігор Потужний", "igor.potuzhnyi@gmail.com", "potuzhno123", "Одеса, вул. Шкільна, 7");
        Guest guest = new Guest("u-002", "Олена Гостьова", "olena@gmail.com", "guest1", LocalDate.of(2026, 12, 31));
        List<User> users = List.of(user, owner, guest);
        for (User u : users) {
            u.login(u == user ? "1111" : u == owner ? "potuzhno123" : "guest1");
            System.out.println(u.getClass().getSimpleName() + " " + u.getFullName() + ": " + u.getPermissions());
        }

        section("2. Кімнати (ієрархія Room)");
        Room hall = new Room("r-01", "Коридор", 1, 6);
        Kitchen kitchen = new Kitchen("r-02", "Кухня", 1, 14.5, true);
        Bedroom bedroom = new Bedroom("r-03", "Спальня", 2, 18);
        List<Room> rooms = List.of(hall, kitchen, bedroom);
        for (Room r : rooms) {
            System.out.println(r.getClass().getSimpleName() + " " + r.getName() + ": " + r.getPurpose());
        }
        for (Room r : rooms) {
            try {
                r.changeTargetTemperature(26);
            } catch (IllegalArgumentException e) {
                System.out.println("  Помилка: " + e.getMessage());
            }
        }

        section("3. Додавання пристроїв (ієрархія Device)");
        Hub hub = new Hub("h-01", "Центральний хаб", "192.168.0.10");
        Device unknown = new Device("x-01", "Невідомий пристрій");
        SmartDevice speaker = new SmartDevice("d-01", "Розумна колонка", "A4:C1:38:22:11:01", "Wi-Fi", "3.1.0");
        SmartPlug plug = new SmartPlug("d-02", "Розетка для чайника", "3C:71:BF:0A:12:7E", 2000);
        Sensor smoke = new Sensor("s-01", "Датчик диму", "дим", " ppm", 300);
        TemperatureSensor temp = new TemperatureSensor("s-02", "Датчик температури", 28, 10);
        HumiditySensor humidity = new HumiditySensor("s-03", "Датчик вологості", 70);
        Actuator blinds = new Actuator("a-01", "Жалюзі", "штори", 15);
        SmartLamp lamp = new SmartLamp("a-02", "Розумна лампа", 9);
        AirConditioner ac = new AirConditioner("a-03", "Кондиціонер", 900);

        owner.addDevice(unknown, hall, hub);
        owner.addDevice(speaker, bedroom, hub);
        owner.addDevice(plug, kitchen, hub);
        owner.addDevice(smoke, kitchen, hub);
        owner.addDevice(temp, bedroom, hub);
        owner.addDevice(humidity, kitchen, hub);
        user.addDevice(blinds, bedroom, hub);
        System.out.println("  Тестовий користувач додав " + blinds.getName() + " (без додаткового повідомлення)");
        owner.addDevice(lamp, bedroom, hub);
        owner.addDevice(ac, bedroom, hub);
        try {
            guest.addDevice(new SmartLamp("a-04", "Лампа гостя", 7), hall, hub);
        } catch (IllegalStateException e) {
            System.out.println("  Помилка: " + e.getMessage());
        }

        section("4. Керування пристроями");
        speaker.turnOn();
        plug.plugIn(1800);
        plug.turnOn();
        plug.plugIn(2400);
        lamp.changeLevel(70);
        lamp.changeColor(3000);
        blinds.activate();
        ac.switchMode("обігрів");
        ac.changeLevel(50);
        ac.activate();
        smoke.updateReading(120);
        temp.updateReading(7.5);
        humidity.calibrate(5);
        humidity.updateReading(98);

        List<Device> devices = List.of(unknown, speaker, plug, smoke, temp, humidity, blinds, lamp, ac, hub);
        section("5. Однакові методи для різних рівнів ієрархії: getStatus() і getPowerUsage()");
        double total = 0;
        for (Device d : devices) {
            System.out.printf("%-18s %-20s %6.1f Вт | %s%n", d.getClass().getSimpleName(), d.getName(), d.getPowerUsage(), d.getStatus());
            total += d.getPowerUsage();
        }
        System.out.printf("Загальне споживання: %.1f Вт%n", total);

        section("6. Однакові методи для різних рівнів ієрархії: selfTest() і reboot()");
        for (Device d : List.of(unknown, speaker, smoke, temp)) {
            d.selfTest();
        }
        unknown.reboot();
        hub.reboot();

        section("7. Нічний режим у кімнатах: prepareForNight()");
        for (Room r : rooms) {
            r.prepareForNight();
        }

        section("8. Сповіщення власника");
        owner.getNotifications().forEach(n -> System.out.println("! " + n));

        section("9. Стан усіх об'єктів (toString)");
        users.forEach(System.out::println);
        rooms.forEach(System.out::println);
        devices.forEach(System.out::println);
    }

    private static void section(String title) {
        System.out.println();
        System.out.println("=== " + title + " ===");
    }
}
