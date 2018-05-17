import java.util.ArrayList;

public class Layer {

    // ArrayList representing all the neurons in this instantiated layer
    ArrayList<Neuron> neurons;

    // Initializing the neurons ArrayList
    public Layer() {
        neurons = new ArrayList<>();
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

}
