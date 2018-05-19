import network.Backpropagator;
import network.Layer;
import network.NeuralNetwork;
import network.Neuron;
import network.activators.SigmoidActivationStrategy;
import network.activators.ThresholdActivationStrategy;
import network.generator.TrainingDataGenerator;
import network.xor.generator.XorTrainingDataGenerator;

public class Main {

    public static void main(String[] args) {
        NeuralNetwork xorNeuralNetwork = createXorNeuralNetwork();

        System.out.println("Testing XOR neural network");

        xorNeuralNetwork.setInputs(new double[]{0, 0});
        System.out.println("0 XOR 0: " + xorNeuralNetwork.getOutput()[0]);

        xorNeuralNetwork.setInputs(new double[]{0, 1});
        System.out.println("0 XOR 1: " + xorNeuralNetwork.getOutput()[0]);

        xorNeuralNetwork.setInputs(new double[]{1, 0});
        System.out.println("1 XOR 0: " + xorNeuralNetwork.getOutput()[0]);

        xorNeuralNetwork.setInputs(new double[]{1, 1});
        System.out.println("1 XOR 1: " + xorNeuralNetwork.getOutput()[0] + "\n");

//        NeuralNetwork untrained = createUntrainedXorNeuralNetwork();
//        TrainingDataGenerator xorTrainingDataGenerator = new XorTrainingDataGenerator();
//
//        Backpropagator backpropagator = new Backpropagator(untrained, 0.1, 0.9, 0);
//        backpropagator.train(xorTrainingDataGenerator, 0.0001);
//
//        System.out.println("Testing trained XOR neural network");
//
//        untrained.setInputs(new double[]{0, 0});
//        System.out.println("0 XOR 0: " + (untrained.getOutput()[0]));
//
//        untrained.setInputs(new double[]{0, 1});
//        System.out.println("0 XOR 1: " + (untrained.getOutput()[0]));
//
//        untrained.setInputs(new double[]{1, 0});
//        System.out.println("1 XOR 0: " + (untrained.getOutput()[0]));
//
//        untrained.setInputs(new double[]{1, 1});
//        System.out.println("1 XOR 1: " + (untrained.getOutput()[0]) + "\n");

//        untrained.persist();
    }

    private static NeuralNetwork createXorNeuralNetwork() {

        NeuralNetwork xorNeuralNetwork = new NeuralNetwork("XOR Network");

        Layer inputLayer = new Layer(null);

        Neuron a = new Neuron(new ThresholdActivationStrategy(1));
        a.setOutput(0);

        Neuron b = new Neuron(new ThresholdActivationStrategy(1));
        b.setOutput(0);

        inputLayer.addNeuron(a);
        inputLayer.addNeuron(b);

        Layer hiddenLayer = new Layer(inputLayer);

        Neuron hiddenA = new Neuron(new ThresholdActivationStrategy(1.5));
        Neuron hiddenB = new Neuron(new ThresholdActivationStrategy(0.5));

        hiddenLayer.addNeuron(hiddenA, new double[]{1, 1});
        hiddenLayer.addNeuron(hiddenB, new double[]{1, 1});

        Layer outputLayer = new Layer(hiddenLayer);
        Neuron xorNeuron = new Neuron(new ThresholdActivationStrategy(0.5));
        outputLayer.addNeuron(xorNeuron, new double[]{-1, 1});

        xorNeuralNetwork.addLayer(inputLayer);
        xorNeuralNetwork.addLayer(hiddenLayer);
        xorNeuralNetwork.addLayer(outputLayer);

        return xorNeuralNetwork;

    }

    private static NeuralNetwork createUntrainedXorNeuralNetwork() {
        NeuralNetwork xorNeuralNetwork = new NeuralNetwork("Trained XOR Network");

        Neuron inputBias = new Neuron(new SigmoidActivationStrategy());
        inputBias.setOutput(1);
        Layer inputLayer = new Layer(null, inputBias);

        Neuron a = new Neuron(new SigmoidActivationStrategy());
        a.setOutput(0);

        Neuron b = new Neuron(new SigmoidActivationStrategy());
        b.setOutput(0);

        inputLayer.addNeuron(a);
        inputLayer.addNeuron(b);

        Neuron bias = new Neuron(new SigmoidActivationStrategy());
        bias.setOutput(1);
        Layer hiddenLayer = new Layer(inputLayer, bias);

        Neuron hiddenA = new Neuron(new SigmoidActivationStrategy());
        Neuron hiddenB = new Neuron(new SigmoidActivationStrategy());

        hiddenLayer.addNeuron(hiddenA);
        hiddenLayer.addNeuron(hiddenB);

        Layer outputLayer = new Layer(hiddenLayer);
        Neuron xorNeuron = new Neuron(new SigmoidActivationStrategy());
        outputLayer.addNeuron(xorNeuron);

        xorNeuralNetwork.addLayer(inputLayer);
        xorNeuralNetwork.addLayer(hiddenLayer);
        xorNeuralNetwork.addLayer(outputLayer);

        return xorNeuralNetwork;
    }


}
