package network.activators;

import java.io.Serializable;

public class SigmoidActivationStrategy implements ActivationStrategy, Serializable {

    @Override
    public double activate(double weightedSum) {
        return 1.0 / (1 + Math.exp(-1.0 * weightedSum));
    }

    @Override
    public double derivative(double weightedSum) {
//        return weightedSum * (1.0 - weightedSum);
        return activate(weightedSum) * (1.0 - activate(weightedSum));
    }

    public SigmoidActivationStrategy copy() {
        return new SigmoidActivationStrategy();
    }
}
