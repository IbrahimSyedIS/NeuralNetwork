package network;

import java.util.LinkedHashMap;
import java.util.Map;

public class Neuron {

    // Linked Hash Map representing the next layer of neurons and the associated weights
    private LinkedHashMap<Neuron, Double> nextLayer;

    // The sum accumulated from the neurons in the last layer
    private double weightedSum;

    // The weighted sum but with the activation function applied
    private double valueToSend;

    // Initializing all the variables
    public Neuron() {
        nextLayer = new LinkedHashMap<>();
        weightedSum = 0;
        valueToSend = 0;
    }

    // Adding a neuron from the next layer to this neurons connections
    public void connectWeight(Neuron n) {
        nextLayer.put(n, Math.random());
    }

    // Taking a value from the previous layer and adding it to the weighted sum
    public void addToWeightedSum(double value, boolean isInput) {
        if (isInput) {
            valueToSend = value;
        } else {
            weightedSum += value;
        }
    }

    // Applying the activation function to the weighted sum and then resetting it
    public void activate() {
        valueToSend = sigmoid(weightedSum);
        weightedSum = 0;
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

    public double getPrediction() {
        if (nextLayer.size() != 0) return 0;
        return valueToSend;
    }

    public LinkedHashMap<Neuron, Double> getNextLayer() {
        return nextLayer;
    }

    // Sigmoid activation function
    private double sigmoid(double x) {
        return 1d / (1 + Math.exp(-x));
    }

}
