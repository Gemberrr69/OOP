package practice_6.storage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import practice_6.model.Device;
import practice_6.model.Room;
import practice_6.model.User;

public final class HomeStorage {
    private static final List<User> users = new ArrayList<>();
    private static final List<Room> rooms = new ArrayList<>();
    private static final List<Device> devices = new ArrayList<>();

    private HomeStorage() {
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public static void addRoom(Room room) {
        rooms.add(room);
    }

    public static void addDevice(Device device) {
        devices.add(device);
    }

    public static List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }

    public static List<Room> getRooms() {
        return Collections.unmodifiableList(rooms);
    }

    public static List<Device> getDevices() {
        return Collections.unmodifiableList(devices);
    }

    public static Device findDevice(String id) {
        for (Device d : devices) {
            if (d.getId().equals(id)) {
                return d;
            }
        }
        throw new IllegalArgumentException("пристрій " + id + " не знайдено");
    }

    public static Room findRoom(String name) {
        for (Room r : rooms) {
            if (r.getName().equalsIgnoreCase(name)) {
                return r;
            }
        }
        throw new IllegalArgumentException("кімнату " + name + " не знайдено");
    }

    public static <T extends Device> List<T> getDevicesOfType(Class<T> type) {
        List<T> result = new ArrayList<>();
        for (Device d : devices) {
            if (type.isInstance(d)) {
                result.add(type.cast(d));
            }
        }
        return result;
    }

    public static double getTotalPowerUsage() {
        double total = 0;
        for (Device d : devices) {
            total += d.getPowerUsage();
        }
        return total;
    }

    public static int getTotalCount() {
        return users.size() + rooms.size() + devices.size();
    }

    public static void printSummary() {
        System.out.println("HomeStorage.printSummary(): користувачів " + users.size() + ", кімнат " + rooms.size()
                + ", пристроїв " + devices.size() + ", усього об'єктів " + getTotalCount());
    }

    public static void clear() {
        users.clear();
        rooms.clear();
        devices.clear();
        System.out.println("HomeStorage.clear(): сховище очищено");
    }
}
