package network;

import network.activators.ActivationStrategy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Neuron implements Serializable {

    private List<Synapse> inputs;
    private ActivationStrategy activationStrategy;
    private double output;
    private double derivative;
    private double weightedSum;
    private double error;

    // Initializing all the variables
    public Neuron(ActivationStrategy strategy) {
        inputs = new ArrayList<>();
        this.activationStrategy = strategy;
        error = 0;
    }

    public void addInput(Synapse in) {
        inputs.add(in);
    }

    public List<Synapse> getInputs() {
        return inputs;
    }

    public double[] getWeights() {
        double[] weights = new double[inputs.size()];

        for (int i = 0; i < weights.length; i++) {
            weights[i] = inputs.get(i).getWeight();
        }

        return weights;
    }

    public void calculateWeightedSum() {
        weightedSum = 0;
        for (Synapse synapse : inputs) {
            weightedSum += synapse.getWeight() * synapse.getSourceNeuron().getOutput();
        }
    }

    // Applying the activation function to the weighted sum and then resetting it
    public void activate() {
        calculateWeightedSum();
        output = activationStrategy.activate(weightedSum);
        derivative = activationStrategy.derivative(weightedSum);
    }

    public double getOutput() {
        return this.output;
    }

    public void setOutput(double out) {
        this.output = out;
    }

    public Double getDerivative() {
        return derivative;
    }

    public ActivationStrategy getActivationStrategy() {
        return this.activationStrategy;
    }

    public Double getError() {
        return error;
    }

    public void setError(Double x) {
        error = x;
    }

}
