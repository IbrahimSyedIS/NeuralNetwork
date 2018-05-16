public class Main {

    public static void main(String[] args) {
        Model xorGate = new Model(2);
        Layer modelOutput = new Layer();
        Neuron outputNeuron = new Neuron();
        modelOutput.addNeuron(outputNeuron);
        xorGate.addLayer(modelOutput);
        xorGate.initialize();
        double[] data = {1.0, 1.0};
        for (int i = 0; i < xorGate.predict(data).length; i++) {
            System.out.println(xorGate.predict(data)[i]);
        }
    }


}
