package practice_3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import practice_3.model.*;

public class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final List<Room> rooms = new ArrayList<>();
    private static final List<SmartDevice> devices = new ArrayList<>();
    private static final List<Sensor> sensors = new ArrayList<>();
    private static final List<Actuator> actuators = new ArrayList<>();
    private static User user;
    private static Hub hub;

    public static void main(String[] args) {
        user = new User("u-001", "Ігор Потужний", "igor.potuzhnyi@gmail.com", "+380671112233", "власник", "potuzhno123");
        hub = new Hub("h-01", "192.168.0.10", "2.3.0");
        rooms.add(new Room("r-01", "Кухня", 1, 14.5, 22.0));
        rooms.add(new Room("r-02", "Спальня", 2, 18.0, 21.0));

        System.out.println("=== Система «Розумний дім» ===");
        while (!user.isLoggedIn()) {
            System.out.print("Пароль для " + user.getFullName() + ": ");
            if (!user.login(in.nextLine().trim())) {
                System.out.println("Невірний пароль, спробуйте ще раз.");
            }
        }
        System.out.println("Вітаємо, " + user.getFullName() + "!");

        boolean running = true;
        while (running) {
            printMenu();
            String choice = in.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> showHome();
                    case "2" -> addRoom();
                    case "3" -> addSmartDevice();
                    case "4" -> addSensor();
                    case "5" -> addActuator();
                    case "6" -> toggleDevice();
                    case "7" -> updateSensor();
                    case "8" -> changeActuatorLevel();
                    case "9" -> changeRoomTemperature();
                    case "10" -> showNotifications();
                    case "0" -> running = false;
                    default -> System.out.println("Невідомий пункт меню.");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Помилка: " + e.getMessage());
            }
        }
        user.logout();
        System.out.println("Ви вийшли з системи.");
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1. Показати стан дому");
        System.out.println("2. Додати кімнату");
        System.out.println("3. Додати розумний пристрій");
        System.out.println("4. Додати датчик");
        System.out.println("5. Додати виконавчий пристрій");
        System.out.println("6. Увімкнути / вимкнути пристрій");
        System.out.println("7. Оновити показник датчика");
        System.out.println("8. Змінити рівень виконавчого пристрою");
        System.out.println("9. Змінити температуру в кімнаті");
        System.out.println("10. Показати сповіщення");
        System.out.println("0. Вихід");
        System.out.print("Ваш вибір: ");
    }

    private static void showHome() {
        System.out.println(user);
        System.out.println(hub);
        rooms.forEach(System.out::println);
        devices.forEach(System.out::println);
        sensors.forEach(System.out::println);
        actuators.forEach(System.out::println);
    }

    private static void addRoom() {
        String name = ask("Назва кімнати: ");
        int floor = Integer.parseInt(ask("Поверх: "));
        double area = Double.parseDouble(ask("Площа (м²): "));
        double temp = Double.parseDouble(ask("Бажана температура: "));
        Room room = new Room("r-0" + (rooms.size() + 1), name, floor, area, temp);
        rooms.add(room);
        System.out.println("Додано: " + room);
    }

    private static void addSmartDevice() {
        String name = ask("Назва пристрою: ");
        String mac = ask("MAC-адреса: ");
        String protocol = ask("Протокол (Wi-Fi / Zigbee): ");
        Room room = chooseRoom();
        SmartDevice device = new SmartDevice("d-0" + (devices.size() + 1), name, mac, protocol, "1.0.0");
        user.addDevice(device, room, hub);
        devices.add(device);
        System.out.println("Додано: " + device);
    }

    private static void addSensor() {
        String name = ask("Назва датчика: ");
        String type = ask("Що вимірює: ");
        String unit = ask("Одиниця виміру: ");
        double threshold = Double.parseDouble(ask("Поріг для сповіщення: "));
        Room room = chooseRoom();
        Sensor sensor = new Sensor("s-0" + (sensors.size() + 1), name, type, unit, threshold);
        user.addDevice(sensor, room, hub);
        sensors.add(sensor);
        System.out.println("Додано: " + sensor);
    }

    private static void addActuator() {
        String name = ask("Назва пристрою: ");
        String type = ask("Тип (освітлення / клімат): ");
        int power = Integer.parseInt(ask("Потужність (Вт): "));
        Room room = chooseRoom();
        Actuator actuator = new Actuator("a-0" + (actuators.size() + 1), name, type, power);
        user.addDevice(actuator, room, hub);
        actuators.add(actuator);
        System.out.println("Додано: " + actuator);
    }

    private static void toggleDevice() {
        SmartDevice device = devices.get(chooseIndex("пристрій", devices.stream().map(SmartDevice::getName).toList()));
        if (device.isPoweredOn()) {
            device.turnOff();
        } else {
            device.turnOn();
        }
    }

    private static void updateSensor() {
        Sensor sensor = sensors.get(chooseIndex("датчик", sensors.stream().map(Sensor::getName).toList()));
        sensor.updateReading(Double.parseDouble(ask("Нове значення: ")));
        System.out.println("Оновлено: " + sensor);
    }

    private static void changeActuatorLevel() {
        Actuator actuator = actuators.get(chooseIndex("пристрій", actuators.stream().map(Actuator::getName).toList()));
        actuator.changeLevel(Integer.parseInt(ask("Рівень (0-100): ")));
        System.out.println("Оновлено: " + actuator + ", споживання " + actuator.estimatePowerUsage() + " Вт");
    }

    private static void changeRoomTemperature() {
        Room room = chooseRoom();
        room.changeTargetTemperature(Double.parseDouble(ask("Нова температура: ")));
        System.out.println("Оновлено: " + room);
    }

    private static void showNotifications() {
        List<String> list = user.getNotifications();
        if (list.isEmpty()) {
            System.out.println("Сповіщень немає.");
        }
        list.forEach(n -> System.out.println("! " + n));
    }

    private static Room chooseRoom() {
        return rooms.get(chooseIndex("кімнату", rooms.stream().map(Room::getName).toList()));
    }

    private static int chooseIndex(String what, List<String> names) {
        if (names.isEmpty()) {
            throw new IllegalStateException("список порожній");
        }
        for (int i = 0; i < names.size(); i++) {
            System.out.println("  " + (i + 1) + ") " + names.get(i));
        }
        int index = Integer.parseInt(ask("Оберіть " + what + ": ")) - 1;
        if (index < 0 || index >= names.size()) {
            throw new IllegalArgumentException("немає такого номера");
        }
        return index;
    }

    private static String ask(String prompt) {
        System.out.print(prompt);
        return in.nextLine().trim();
    }
}
