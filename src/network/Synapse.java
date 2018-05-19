package network;

public class Synapse {

    private Neuron sourceNeuron;
    private double weight;

    public Synapse(Neuron srcNeuron, double wgt) {
        this.sourceNeuron = srcNeuron;
        this.weight = wgt;
    }

    public Neuron getSourceNeuron() {
        return this.sourceNeuron;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double n) {
        this.weight = n;
    }

}
