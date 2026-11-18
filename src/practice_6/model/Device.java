package practice_6.model;

public abstract class Device {
    private static int createdCount = 0;

    protected final String id;
    protected String name;
    protected String roomId;
    protected boolean online = true;

    protected Device(String id, String name) {
        if (!isValidId(id)) {
            throw new IllegalArgumentException("некоректний id: " + id);
        }
        this.id = id;
        this.name = name;
        createdCount++;
    }

    public abstract String getCategory();

    public abstract String getStatus();

    public abstract double getPowerUsage();

    public abstract void performMainAction();

    public static int getCreatedCount() {
        return createdCount;
    }

    public static boolean isValidId(String id) {
        return id != null && id.matches("[a-z]-\\d{2,}");
    }

    public static String kind() {
        return "пристрій (Device.kind)";
    }

    void assignRoom(String roomId) {
        this.roomId = roomId;
    }

    public boolean ping() {
        return online;
    }

    protected String connectionStatus() {
        return online ? "на зв'язку" : "не на зв'язку";
    }

    public void selfTest() {
        System.out.printf("  %s [%s]: зв'язок %s, споживання %.1f Вт%n", name, getCategory(), online ? "OK" : "FAIL", getPowerUsage());
    }

    public void reboot() {
        System.out.println("  Device.reboot() для " + name);
        online = true;
    }

    protected void log(String message) {
        System.out.println("  [" + name + "] " + message);
    }

    protected String fieldsInfo() {
        return "id='" + id + "', name='" + name + "', roomId='" + roomId + "', online=" + online;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + fieldsInfo() + "}";
    }
}
