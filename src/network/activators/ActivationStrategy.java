package network.activators;

public interface ActivationStrategy {

    double activate(double weightedSum);
    double derivative(double weightedSum);

    ActivationStrategy copy();

}
