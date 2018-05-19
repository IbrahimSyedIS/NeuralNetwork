package network.activators;

public class LinearActivationStrategy implements ActivationStrategy {

    @Override
    public double activate(double weightedSum) {
        return weightedSum;
    }

    @Override
    public double derivative(double weightedSum) {
        return 1;
    }

    @Override
    public LinearActivationStrategy copy() {
        return new LinearActivationStrategy();
    }
}
