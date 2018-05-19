package network.activators;

import java.io.Serializable;

public class LinearActivationStrategy implements ActivationStrategy, Serializable {

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
