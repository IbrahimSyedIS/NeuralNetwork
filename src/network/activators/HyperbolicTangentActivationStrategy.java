package network.activators;

import java.io.Serializable;

public class HyperbolicTangentActivationStrategy implements ActivationStrategy, Serializable {

    @Override
    public double activate(double weightedSum) {
        double a = Math.exp(weightedSum);
        double b = Math.exp(-weightedSum);
        return ((a-b)/(a+b));
    }

    @Override
    public double derivative(double weightedSum) {
        return 1 - Math.pow(activate(weightedSum), 2.0);
    }

    @Override
    public HyperbolicTangentActivationStrategy copy() {
        return new HyperbolicTangentActivationStrategy();
    }
}