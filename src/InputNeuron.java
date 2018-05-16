public class InputNeuron extends Neuron {

    private double givenValue;

    public void setGivenValue(double value) {
        givenValue = value;
    }

    public double computeValue() {
        return givenValue;
    }

}
