package co.edu.taller.behavioral;

import java.util.ArrayList;
import java.util.List;

public class InventoryAlerts {

    @FunctionalInterface
    public interface Listener {
        void onAlert(String sku, int currentQuantity);
    }

    private final int threshold;
    private final List<Listener> listeners = new ArrayList<>();

    public InventoryAlerts(int threshold) {
        if (threshold < 0) {
            throw new IllegalArgumentException("El umbral no puede ser negativo");
        }
        this.threshold = threshold;
    }

    public void subscribe(Listener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("El suscriptor no puede ser nulo");
        }
        listeners.add(listener);
    }

    public void unsubscribe(Listener listener) {
        listeners.remove(listener);
    }

    public void update(String sku, int currentQuantity) {
        if (currentQuantity <= threshold) {
            for (Listener listener : listeners) {
                listener.onAlert(sku, currentQuantity);
            }
        }
    }
}