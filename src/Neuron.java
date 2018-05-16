import java.util.LinkedHashMap;
import java.util.Map;

public class Neuron {

    private LinkedHashMap<Neuron, Double> inputs;

    public Neuron() {
        inputs = new LinkedHashMap<>();
    }

    private double sigmoid(double x) {
        return 1d / (1 + Math.exp(-x));
    }

    public void connectNeuron(Neuron incomingNeuron) {
        inputs.put(incomingNeuron, Math.random());
    }

    public double computeValue() {
        double weightedSum = 0.0;
        for (Map.Entry<Neuron, Double> entry : inputs.entrySet()) {
            weightedSum += entry.getKey().computeValue() * entry.getValue();
        }
        return sigmoid(weightedSum);
    }

}
