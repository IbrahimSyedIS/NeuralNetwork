import java.util.ArrayList;

public class Layer {

    private ArrayList<Neuron> neurons;

    public Layer() {
        neurons = new ArrayList<>();
    }

    public void addNeuron(Neuron neuron) {
        neurons.add(neuron);
    }

    public void connectLayer(Layer layer) {
        for (Neuron neuron : neurons) {
            for (Neuron otherNeuron : layer.neurons) {
                otherNeuron.connectNeuron(neuron);
            }
        }
    }

    public double[] computeValues() {
        double[] values = new double[neurons.size()];
        for (int i = 0; i < neurons.size(); i++) {
            values[i] = neurons.get(i).computeValue();
        }
        return values;
    }

}
