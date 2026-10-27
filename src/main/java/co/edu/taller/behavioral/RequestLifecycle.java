package co.edu.taller.behavioral;

public class RequestLifecycle {

    public enum Status {
        RECEIVED, IN_REVIEW, APPROVED, REJECTED
    }

    private Status currentStatus;

    public RequestLifecycle() {
        this.currentStatus = Status.RECEIVED;
    }

    public Status status() {
        return currentStatus;
    }

    public Status getStatus() {
        return currentStatus;
    }

    public void review() {
        if (currentStatus != Status.RECEIVED) {
            throw new IllegalStateException("Solo se puede revisar desde RECEIVED");
        }
        this.currentStatus = Status.IN_REVIEW;
    }

    public void approve() {
        if (currentStatus != Status.IN_REVIEW) {
            throw new IllegalStateException("Solo se puede aprobar desde IN_REVIEW");
        }
        this.currentStatus = Status.APPROVED;
    }

    public void reject() {
        if (currentStatus != Status.RECEIVED && currentStatus != Status.IN_REVIEW) {
            throw new IllegalStateException("No se puede rechazar en el estado actual: " + currentStatus);
        }
        this.currentStatus = Status.REJECTED;
    }
}