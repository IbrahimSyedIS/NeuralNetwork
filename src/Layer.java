import java.util.ArrayList;

public class Layer {

    // ArrayList representing all the neurons in this instantiated layer
    private ArrayList<Neuron> neurons;

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

    // Connecting all the neurons in this layer to the neurons in the next one
    public void connectToLayer(Layer layer) {
        for (Neuron n1 : neurons) {
            for (Neuron n2 : layer.neurons) {
                n1.connectWeight(n2);
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
    public void predict(double[] data) {
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

}
