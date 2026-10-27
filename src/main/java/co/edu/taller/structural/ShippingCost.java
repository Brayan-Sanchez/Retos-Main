package co.edu.taller.structural;

public interface ShippingCost {

    int cost();

    class Standard implements ShippingCost {
        @Override
        public int cost() {
            return 12000;
        }
    }

    abstract class Decorator implements ShippingCost {
        protected final ShippingCost delegate;

        public Decorator(ShippingCost delegate) {
            if (delegate == null) {
                throw new IllegalArgumentException("El delegado de envío no puede ser nulo");
            }
            this.delegate = delegate;
        }
    }

    class Insurance extends Decorator {
        private final int insuranceCost;

        public Insurance(ShippingCost delegate) {
            this(delegate, 4500);
        }

        public Insurance(ShippingCost delegate, int insuranceCost) {
            super(delegate);
            this.insuranceCost = insuranceCost;
        }

        @Override
        public int cost() {
            return delegate.cost() + insuranceCost;
        }
    }

    class Priority extends Decorator {
        private final int priorityCost;

        public Priority(ShippingCost delegate) {
            this(delegate, 5000);
        }

        public Priority(ShippingCost delegate, int priorityCost) {
            super(delegate);
            this.priorityCost = priorityCost;
        }

        @Override
        public int cost() {
            return delegate.cost() + priorityCost;
        }
    }
}