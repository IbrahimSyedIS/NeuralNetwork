package network;

import java.util.ArrayList;
import java.util.Map;

public class Layer {

    // ArrayList representing all the neurons in this instantiated layer
    private ArrayList<Neuron> neurons;

    private Layer nextLayer;
    private Layer previousLayer;

    private boolean isInput;

    // Initializing the neurons ArrayList
    public Layer(boolean isInputLayer) {
        neurons = new ArrayList<>();
        isInput = isInputLayer;
    }

    public Layer(int size, boolean isInputLayer) {
        this(isInputLayer);
        for (int i = 0; i < size; i++) {
            addNeuron(new Neuron());
        }
    }

    // Adding a neuron to this layer
    public void addNeuron(Neuron n) {
        neurons.add(n);
    }

    public Neuron getNeuron(int n) {
        return neurons.get(n);
    }

    public boolean isInputLayer() {
        return isInput;
    }

    public boolean isOutputLayer() {
        return neurons.get(0).sizeOfNextLayer() == 0;
    }

    public Layer getNextLayer() {
        return nextLayer;
    }

    public ArrayList<Neuron> getNeurons() {
        return this.neurons;
    }
    // Connecting all the neurons in this layer to the neurons in the next one
    public void connectToLayer(Layer layer) {
        this.nextLayer = layer;
        for (Neuron n1 : neurons) {
            for (Neuron n2 : layer.neurons) {
                n1.connectWeight(n2);
            }
        }
    }

    public void inverseConnectToLayer(Layer layer) {
        this.previousLayer = layer;
        for (Neuron currentNeuron : neurons) {
            for (Neuron prevNeuron : previousLayer.neurons) {
                currentNeuron.inverseConnectWeight(prevNeuron);
            }
        }
    }

    // Returning the number of neurons in this layer
    public int size() {
        return neurons.size();
    }

    // Applying the activation function and firing all the neurons in this layer
    public void openFire() {
        if (isInput) return;
        for (Neuron n : neurons) {
            n.activate();
            n.fire();
        }
    }

    // Taking an array of values to be sent through this layer to the next
    public void predict(Double[] data) {
        if (!isInput) {
            return;
        }
        for (int i = 0; i < neurons.size(); i++) {
            neurons.get(i).addToWeightedSum(data[i], true);
        }
        for (Neuron n : neurons) {
            n.fire();
        }
    }

    public Double[] getPrediction() {
        if (neurons.get(0).sizeOfNextLayer() != 0) {
            return null;
        }
        Double[] prediction = new Double[neurons.size()];
        for (int i = 0; i < neurons.size(); i++) {
            prediction[i] = neurons.get(i).getPrediction();
        }
        return prediction;
    }

    public ArrayList<Double> getWeights() {
        ArrayList<Double> weights = new ArrayList<>();
        for (Neuron neuron : neurons) {
            for (Map.Entry<Neuron, Double> entry : neuron.getNextLayer().entrySet()) {
                weights.add(entry.getValue());
            }
        }
        return weights;
    }

    public void setWeights(ArrayList<Double> weights) {
        for (Neuron neuron : neurons) {
            for (Map.Entry<Neuron, Double> entry : neuron.getNextLayer().entrySet()) {
                entry.setValue(weights.remove(0));
            }
        }
    }

}
