package practice_2;

import java.time.LocalDateTime;
import java.util.List;
import practice_2.model.*;

public class Main {
    public static void main(String[] args) {
        User user = new User("u-001", "Ігор Потужний", "igor.potuzhnyi@gmail.com", "+380671112233",
                "власник", LocalDateTime.of(2026, 11, 1, 10, 30));

        Room room = new Room("r-01", "Кухня", 1, 14.5, 22.0, List.of("d-01", "s-01", "a-01"));

        SmartDevice plug = new SmartDevice("d-01", "Розумна розетка", "3C:71:BF:0A:12:7E", "Wi-Fi",
                "r-01", true, 100, "1.4.2");

        Sensor sensor = new Sensor("s-01", "Датчик вологості", "r-01", "вологість", 47.0, "%", 70.0,
                LocalDateTime.of(2026, 11, 17, 21, 0));

        Actuator lamp = new Actuator("a-01", "Розумна лампа", "r-01", "освітлення", 9, 80, true);

        Hub hub = new Hub("h-01", "192.168.0.10", "2.3.0", true, List.of("d-01", "s-01", "a-01"));

        System.out.println(user);
        System.out.println(room);
        System.out.println(plug);
        System.out.println(sensor);
        System.out.println(lamp);
        System.out.println(hub);
    }
}
