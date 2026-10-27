package co.edu.taller.structural;

public class CheckoutFacade {

    @FunctionalInterface
    public interface Inventory {
        boolean isAvailable(String sku, int quantity);
    }

    @FunctionalInterface
    public interface Payment {
        boolean process(String customerId, int amount);
    }

    @FunctionalInterface
    public interface Notification {
        void send(String customerId, String message);
    }

    private final Inventory inventory;
    private final Payment payment;
    private final Notification notification;

    public CheckoutFacade(Inventory inventory, Payment payment, Notification notification) {
        this.inventory = inventory;
        this.payment = payment;
        this.notification = notification;
    }

    public CheckoutFacade(Inventory inventory, Payment payment) {
        this(inventory, payment, (customer, message) -> {});
    }

    public String checkout(String sku, String customerId, int quantity, int price) {
        if (!inventory.isAvailable(sku, quantity)) {
            throw new IllegalStateException("Inventario no disponible para: " + sku);
        }

        int totalAmount = quantity * price;
        boolean paid = payment.process(customerId, totalAmount);
        if (!paid) {
            throw new IllegalStateException("El pago fue rechazado");
        }

        if (notification != null) {
            notification.send(customerId, "CONFIRMED");
        }

        return "CONFIRMED:O-1";
    }
}