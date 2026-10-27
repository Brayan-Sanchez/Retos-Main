package co.edu.taller.behavioral;

public class ShippingRateStrategy {

    public enum Mode {
        STANDARD, EXPRESS, NIGHT
    }

    public int quote(Mode mode, int baseRate) {
        if (mode == null) {
            throw new IllegalArgumentException("El modo de envío no puede ser nulo");
        }
        if (baseRate <= 0) {
            throw new IllegalArgumentException("La tarifa base debe ser mayor a cero");
        }

        return switch (mode) {
            case STANDARD -> baseRate;
            case EXPRESS -> (int) (baseRate * 1.8);
            case NIGHT -> (int) (baseRate * 2.0);
        };
    }
}