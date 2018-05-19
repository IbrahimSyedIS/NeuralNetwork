package network.activators;

public class ThresholdActivationStrategy implements ActivationStrategy {

    private double threshold;

    public ThresholdActivationStrategy(double t) {
        this.threshold = t;
    }

    @Override
    public double activate(double weightedSum) {
        return weightedSum > threshold ? 1 : 0;
    }

    @Override
    public double derivative(double weightedSum) {
        return 0;
    }

    public ThresholdActivationStrategy copy() {
        return new ThresholdActivationStrategy(threshold);
    }
}
