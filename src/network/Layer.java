package network;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Layer implements Serializable {

    // ArrayList representing all the neurons in this instantiated layer
    private List<Neuron> neurons;
    private Layer previousLayer;
    private Layer nextLayer;
    private Neuron bias;

    // Initializing the neurons ArrayList
    public Layer() {
        neurons = new ArrayList<>();
        previousLayer = null;
    }

    public Layer(Layer previousLayer) {
        this();
        this.previousLayer = previousLayer;
    }

    public Layer(Layer previousLayer, Neuron bias) {
        this(previousLayer);
        this.bias = bias;
        neurons.add(bias);
    }

    public List<Neuron> getNeurons() {
        return this.neurons;
    }

    public void addNeuron(Neuron n) {
        neurons.add(n);

        if (previousLayer != null) {
            for (Neuron previousLayerNeuron : previousLayer.getNeurons()) {
                n.addInput(new Synapse(previousLayerNeuron, (Math.random() * 1) - 0.5));
            }
        }
    }

    public void addNeuron(Neuron n, double[] weights) {
        neurons.add(n);
        if (previousLayer != null) {
            if (previousLayer.getNeurons().size() != weights.length) {
                throw new IllegalArgumentException();
            } else {
                List<Neuron> previousLayerNeurons = previousLayer.getNeurons();
                for (int i = 0; i < previousLayerNeurons.size(); i++) {
                    n.addInput(new Synapse(previousLayerNeurons.get(i), weights[i]));
                }
            }
        }
    }

    public void feedForward() {
        int biasCount = hasBias() ? 1 : 0;
        for (int i = biasCount; i < neurons.size(); i++) {
            neurons.get(i).activate();
        }
    }

    public Layer getPreviousLayer() {
        return this.previousLayer;
    }

    public void setPreviousLayer(Layer layer) {
        this.previousLayer = layer;
    }

    public Layer getNextLayer() {
        return this.nextLayer;
    }

    public void setNextLayer(Layer layer) {
        this.nextLayer = layer;
    }

    public boolean isOutputLayer() {
        return nextLayer == null;
    }

    public boolean hasBias() {
        return bias != null;
    }

}
