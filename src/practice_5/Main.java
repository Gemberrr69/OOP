package practice_5;

import java.time.LocalDate;
import java.util.List;
import practice_5.factory.SmartHomeFactory;
import practice_5.model.*;
import practice_5.model.devices.*;
import practice_5.model.rooms.*;
import practice_5.model.users.*;
import practice_5.storage.HomeStorage;

public class Main {
    public static void main(String[] args) {
        section("1. Створення об'єктів через SmartHomeFactory");
        Owner owner = SmartHomeFactory.createOwner("Ігор", "Потужний", "igor.potuzhnyi@gmail.com", "potuzhno123", "Одеса, вул. Шкільна, 7");
        Guest guest = SmartHomeFactory.createGuest("Олена", "Гостьова", "olena@gmail.com", "guest1", LocalDate.of(2026, 12, 31));
        Room hall = SmartHomeFactory.createRoom("Коридор", 1, 6);
        Kitchen kitchen = SmartHomeFactory.createKitchen("Кухня", 1, 14.5, true);
        Bedroom bedroom = SmartHomeFactory.createBedroom("Спальня", 2, 18);
        Hub hub = SmartHomeFactory.createHub("Центральний хаб", "192.168.0.10");
        SmartPlug plug = SmartHomeFactory.createSmartPlug("Розетка для чайника", "3C:71:BF:0A:12:7E", 2000);
        TemperatureSensor temp = SmartHomeFactory.createTemperatureSensor("Датчик температури", 28, 10);
        HumiditySensor humidity = SmartHomeFactory.createHumiditySensor("Датчик вологості", 70);
        SmartLamp lamp = SmartHomeFactory.createSmartLamp("Розумна лампа", 9);
        AirConditioner ac = SmartHomeFactory.createAirConditioner("Кондиціонер", 900);
        Device speaker = SmartHomeFactory.createDevice("speaker", "Розумна колонка");
        Device smoke = SmartHomeFactory.createDevice("sensor", "Датчик диму");
        try {
            SmartHomeFactory.createDevice("toaster", "Тостер");
        } catch (IllegalArgumentException e) {
            System.out.println("  Помилка: " + e.getMessage());
        }

        section("2. Вкладений клас User.NameInfo");
        User.NameInfo name = owner.getNameInfo();
        System.out.println(name);
        System.out.println("Повне ім'я: " + name.getFullName() + ", ініціали: " + name.getInitials());
        User.NameInfo other = new User.NameInfo("Артьом", "Дулгер-Шевченко");
        System.out.println("Окремий об'єкт NameInfo: " + other.getInitials());
        try {
            new User.NameInfo("", "Тест");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        }

        section("3. Статичні методи базових класів (виклик через назву класу)");
        System.out.println("Device.isValidId(\"d-01\") = " + Device.isValidId("d-01"));
        System.out.println("Device.isValidId(\"lamp1\") = " + Device.isValidId("lamp1"));
        System.out.println("Device.getCreatedCount() = " + Device.getCreatedCount());
        System.out.println("SmartHomeFactory.getCreatedDevicesCount() = " + SmartHomeFactory.getCreatedDevicesCount());
        System.out.println("User.isValidEmail(\"igor@gmail.com\") = " + User.isValidEmail("igor@gmail.com"));
        System.out.println("User.isValidEmail(\"igor.gmail\") = " + User.isValidEmail("igor.gmail"));
        System.out.println("Room.isComfortable(22) = " + Room.isComfortable(22) + ", Room.isComfortable(27) = " + Room.isComfortable(27));
        System.out.println("Room.toFahrenheit(22) = " + Room.toFahrenheit(22));
        System.out.println("Hub.DEFAULT_MAX_DEVICES = " + Hub.DEFAULT_MAX_DEVICES);

        section("4. Додавання пристроїв у дім");
        owner.login("potuzhno123");
        guest.login("guest1");
        owner.addDevice(plug, kitchen, hub);
        owner.addDevice(smoke, kitchen, hub);
        owner.addDevice(humidity, kitchen, hub);
        owner.addDevice(speaker, hall, hub);
        owner.addDevice(temp, bedroom, hub);
        owner.addDevice(lamp, bedroom, hub);
        owner.addDevice(ac, bedroom, hub);
        try {
            guest.addDevice(lamp, hall, hub);
        } catch (IllegalStateException e) {
            System.out.println("  Помилка: " + e.getMessage());
        }

        section("5. Показники датчика і вкладений клас Sensor.Reading");
        for (double v : new double[]{21.5, 22.0, 23.4, 8.0}) {
            temp.updateReading(v);
        }
        List<Sensor.Reading> history = temp.getHistory();
        history.forEach(r -> System.out.println("  " + r));
        System.out.printf("Sensor.average(history) = %.2f°C%n", Sensor.average(history));
        Sensor.Reading last = history.get(history.size() - 1);
        System.out.println("Останнє значення: " + last.getValue() + " о " + last.getTime().toLocalTime()
                + ", комфортно: " + Room.isComfortable(last.getValue()));
        owner.getNotifications().forEach(n -> System.out.println("! " + n));

        section("6. Робота зі сховищем HomeStorage");
        plug.plugIn(1500);
        plug.turnOn();
        lamp.changeLevel(70);
        ac.changeLevel(50);
        ac.activate();
        HomeStorage.printSummary();
        System.out.println("HomeStorage.findDevice(\"a-05\") -> " + HomeStorage.findDevice("a-05").getName());
        System.out.println("HomeStorage.findRoom(\"спальня\") -> " + HomeStorage.findRoom("спальня"));
        System.out.println("Усі датчики: " + HomeStorage.getDevicesOfType(Sensor.class).stream().map(Device::getName).toList());
        System.out.println("Усі виконавчі пристрої: " + HomeStorage.getDevicesOfType(Actuator.class).stream().map(Device::getName).toList());
        System.out.printf("HomeStorage.getTotalPowerUsage() = %.1f Вт%n", HomeStorage.getTotalPowerUsage());
        try {
            HomeStorage.findDevice("x-99");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        }

        section("7. Однакові методи для всіх пристроїв зі сховища");
        for (Device d : HomeStorage.getDevices()) {
            System.out.printf("%-18s %-20s | %s%n", d.getClass().getSimpleName(), d.getName(), d.getStatus());
        }
        for (Room r : HomeStorage.getRooms()) {
            r.prepareForNight();
        }

        section("8. Стан усіх об'єктів у сховищі");
        HomeStorage.getUsers().forEach(System.out::println);
        HomeStorage.getRooms().forEach(System.out::println);
        HomeStorage.getDevices().forEach(System.out::println);
    }

    private static void section(String title) {
        System.out.println();
        System.out.println("=== " + title + " ===");
    }
}
