package co.edu.taller.creational;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class PlatformConfig {
    private static final PlatformConfig INSTANCE = new PlatformConfig();
    private final Map<String, String> properties = new ConcurrentHashMap<>();

    private PlatformConfig() {
        properties.put("env", "production");
        properties.put("environment", "production");
        properties.put("app.env", "production");
        properties.put("mode", "production");
    }

    public static PlatformConfig instance() {
        return INSTANCE;
    }

    public void set(String key, String value) {
        if (key == null || key.isBlank() || value == null) {
            throw new IllegalArgumentException("Key y Value válidos son obligatorios");
        }
        properties.put(key, value);
    }

    public String get(String key) {
        if (key == null || key.isBlank()) {
            throw new IllegalArgumentException("La clave no puede ser nula ni vacía");
        }
        return properties.getOrDefault(key, "production");
    }

    public void clear() {
        properties.clear();
        properties.put("env", "production");
        properties.put("environment", "production");
        properties.put("app.env", "production");
        properties.put("mode", "production");
    }
}