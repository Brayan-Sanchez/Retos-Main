package co.edu.taller.creational;

public class NotificationFactory {

    public enum Channel {
        EMAIL, SMS
    }

    public interface Notifier {
        String send(String destination, String message);
    }

    public static class EmailNotifier implements Notifier {
        @Override
        public String send(String destination, String message) {
            if (destination == null || !destination.contains("@")) {
                throw new IllegalArgumentException("Dirección de email inválida");
            }
            return "EMAIL:" + destination + ":" + message;
        }
    }

    public static class SmsNotifier implements Notifier {
        @Override
        public String send(String destination, String message) {
            if (destination == null || destination.isBlank()) {
                throw new IllegalArgumentException("Número de teléfono inválido");
            }
            return "SMS:" + destination + ":" + message;
        }
    }

    public Notifier create(Channel channel) {
        if (channel == null) {
            throw new IllegalArgumentException("El canal no puede ser nulo");
        }
        return switch (channel) {
            case EMAIL -> new EmailNotifier();
            case SMS -> new SmsNotifier();
        };
    }
}