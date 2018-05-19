package network;

import java.util.LinkedHashMap;
import java.util.Map;

public class Neuron {

    // Linked Hash Map representing the next layer of neurons and the associated weights
    private LinkedHashMap<Neuron, Double> nextLayer;
    private LinkedHashMap<Neuron, Double> lastLayer;

    // The sum accumulated from the neurons in the last layer
    private Double weightedSum;

    private Double error;

    // The weighted sum but with the activation function applied
    private Double valueToSend;

    private Double derivative;

    // Initializing all the variables
    public Neuron() {
        nextLayer = new LinkedHashMap<>();
        lastLayer = new LinkedHashMap<>();
        weightedSum = 0d;
        valueToSend = 0d;
    }

    // Adding a neuron from the next layer to this neurons connections
    public void connectWeight(Neuron n) {
        nextLayer.put(n, Math.random());
    }

    public void updateIncomingWeights() {
        for (Map.Entry<Neuron, Double> incoming : lastLayer.entrySet()) {
            for (Map.Entry<Neuron, Double> entry : incoming.getKey().nextLayer.entrySet()) {
                if (entry.getKey() == this) {
                    System.out.println("Found a match");
                    entry.setValue(incoming.getValue());
                }
            }
        }
    }

    public void inverseConnectWeight(Neuron n) {
        lastLayer.put(n, n.nextLayer.get(this));
    }

    public LinkedHashMap<Neuron, Double> getInputs() {
        return lastLayer;
    }

    // Taking a value from the previous layer and adding it to the weighted sum
    public void addToWeightedSum(Double value, boolean isInput) {
        if (isInput) {
            valueToSend = value;
        } else {
            weightedSum += value;
        }
    }

    // Applying the activation function to the weighted sum and then resetting it
    public void activate() {
        valueToSend = sigmoid(weightedSum);
        derivative = sigmoidDerivative(weightedSum);
        weightedSum = 0d;
    }

    public Double getDerivative() {
        return derivative;
    }

    public void setError(Double x) {
        error = x;
    }

    public Double getError() {
        return error;
    }

    // Sending the activated value to the neurons in the next layer multiplied by the associated weights
    public void fire() {
        for (Map.Entry<Neuron, Double> entry : nextLayer.entrySet()) {
            entry.getKey().addToWeightedSum(valueToSend * entry.getValue(), false);
        }
    }

    public int sizeOfNextLayer() {
        return nextLayer.size();
    }

    public Double getPrediction() {
        if (nextLayer.size() != 0) return 0d;
        return valueToSend;
    }

    public LinkedHashMap<Neuron, Double> getNextLayer() {
        return nextLayer;
    }

    // Sigmoid activation function
    private Double sigmoid(Double x) {
        return 1d / (1 + Math.exp(-x));
    }

    private Double sigmoidDerivative(Double x) {
        return sigmoid(x) * (1 - sigmoid(x));
    }

}
