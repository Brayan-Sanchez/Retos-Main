package co.edu.taller.structural;

public class PaymentGatewayAdapter {

    private final LegacyGateway legacyGateway;

    public PaymentGatewayAdapter(LegacyGateway legacyGateway) {
        if (legacyGateway == null) {
            throw new IllegalArgumentException("LegacyGateway no puede ser nulo");
        }
        this.legacyGateway = legacyGateway;
    }

    public String charge(String orderId, int amountInPesos) {
        if (orderId == null || orderId.isBlank()) {
            throw new IllegalArgumentException("El ID de la orden no puede ser nulo ni estar vacío");
        }
        if (amountInPesos <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a cero");
        }

        long amountInCents = (long) amountInPesos * 100;

        return legacyGateway.payCents(orderId, amountInCents);
    }

    public static class LegacyGateway {
        public String payCents(String orderId, long amountInCents) {
            if (amountInCents <= 0) {
                throw new IllegalArgumentException("El monto en centavos debe ser mayor a cero");
            }
            return "LEGACY:" + orderId + ":" + amountInCents;
        }
    }
}