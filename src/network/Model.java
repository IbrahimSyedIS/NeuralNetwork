package network;

import java.util.*;

import java.io.IOException;
import java.io.PrintWriter;

public class Model {

    // ArrayList for all the layers
    private ArrayList<Layer> layers;

    // Reserved value for the input layer
    private Layer inputLayer;

    private double currentEpoch;

    private double learningRate;
    private double momentum;
    private double characteristicTime;

    // Initializing the ArrayList
    public Model() {
        layers = new ArrayList<>();
    }

    // Adding a layer with the number of neurons as the parameter
    public void addLayer(int size) {
        if (layers.size() == 0) {
            Layer inLayer = new Layer(size, true);
            inputLayer = inLayer;
            layers.add(inLayer);
            return;
        }
        layers.add(new Layer(size, false));
    }

    public ArrayList<Layer> getLayers() {
        return layers;
    }

    // Connecting all the layers to the subsequent layers
    public void connectLayers() {
        forwardConnect();
        reverseConnect();
    }

    private void forwardConnect() {
        for (int i = 0; i < layers.size() - 1; i++) {
            layers.get(i).connectToLayer(layers.get(i + 1));
        }
    }

    private void reverseConnect() {
        for (int i = layers.size() - 1; i > 0; i--) {
            layers.get(i).inverseConnectToLayer(layers.get(i - 1));
        }
    }

    // Taking an array of values and sending them through the model
    public Double[] predict(Double[] data) {
        if (data.length != inputLayer.size()) {
            throw new InputMismatchException();
        }
        inputLayer.predict(data);
        forwardPropagate();
        return layers.get(layers.size() - 1).getPrediction();
    }

    // Facilitating the activation and firing of all the neurons in all the layers
    private void forwardPropagate() {
        for (int i = 1; i < layers.size(); i++) {
            layers.get(i).openFire();
        }
    }

    public void saveWeights(String directory) throws IOException {
        PrintWriter writer = new PrintWriter(directory, "UTF-8");
        ArrayList<ArrayList<Double>> allWeights = new ArrayList<>();
        for (int i = 0; i < layers.size() - 1; i++) {
            allWeights.add(layers.get(i).getWeights());
        }
       for (ArrayList<Double> weights : allWeights) {
            for (Double weight : weights) {
                writer.println(String.valueOf(weight));
            }
       }
       writer.close();
    }

    public void setWeights(Double[] weights) {
        ArrayList<Double> dynamicWeights = new ArrayList<>();
        Collections.addAll(dynamicWeights, weights);
        for (Layer layer : layers) {
            ArrayList<Double> currentWeightsToSend = new ArrayList<>();
            for (int i = 0; i < layer.getWeights().size(); i++) {
                currentWeightsToSend.add(dynamicWeights.remove(0));
            }
            layer.setWeights(currentWeightsToSend);
        }
    }

    // Training the Neural Network through back propagation
    public void train(ArrayList<Double[][]> dataset, Double errorThreshold, double lRate, double montum, double cTime) {
        this.learningRate = lRate;
        this.momentum = montum;
        this.characteristicTime = cTime;
        ArrayList<Double[]> inputs = new ArrayList<>();
        ArrayList<Double[]> expectedOutputs = new ArrayList<>();
        for (Double[][] value : dataset) {
            inputs.add(value[0]);
            expectedOutputs.add(value[1]);
        }

        double error;
        double sum = 0.0;
        double average = 25;
        int epoch = 1;
        int samples = 4;
        double[] errors = new double[samples];

        do {
            error = backPropagate(inputs, expectedOutputs);
            sum -= errors[epoch % samples];
            errors[epoch % samples] = error;
            sum += errors[epoch % samples];

            if (epoch > samples) {
                average = sum / samples;
            }

            System.out.println("Error for epoch " + epoch + ": " + error + ". Average: " + average + (characteristicTime > 0 ? " Learning rate: " + learningRate / (1 + (currentEpoch / characteristicTime)): ""));
            epoch++;
            currentEpoch = epoch;

        } while (average > errorThreshold);

//        ArrayList<Double[]> actualOutputs = new ArrayList<>();
//        ArrayList<Double[]> errors = new ArrayList<>();
//        for (int i = 0; i < expectedOutputs.size(); i++) {
//            Double[] error = new Double[expectedOutputs.get(i).length];
//            for (int j = 0; j < expectedOutputs.get(i).length; j++) {
//                error[j] = 0.5 * Math.pow((actualOutputs.get(i)[j] - expectedOutputs.get(i)[j]), 2);
//            }
//            errors.add(error);
//        }
//        for (Double[] error : errors) {
//            System.out.println(Arrays.toString(error));
//        }
    }

    public Double backPropagate(ArrayList<Double[]> inputs, ArrayList<Double[]> expectedOutputs) {
        Double error = 0d;

        Map<Neuron, Double> synapseNeuronDeltaMap = new LinkedHashMap<>();


        for (int i = 0; i < inputs.size(); i++) {
            Double[] output = predict(inputs.get(i));
            Double[] expectedOutput = expectedOutputs.get(i);
            for (int j = layers.size() - 1; j > 0; j--) {
                Layer layer = layers.get(j);
                for (int k = 0; k < layer.size(); k++) {
                    Neuron neuron = layer.getNeuron(k);
                    Double neuronError = 0d;

                    if (layer.isOutputLayer()) {
                        neuronError = neuron.getDerivative() * (output[k] - expectedOutput[k]);
                    } else {
                        neuronError = neuron.getDerivative();

                        Double sum = 0d;
                        ArrayList<Neuron> downstreamNeurons = layer.getNextLayer().getNeurons();

                        for (Neuron downstreamNeuron : downstreamNeurons) {
                            int l = 0;
                            boolean found = false;
                            List<Neuron> downstreamNeuronsInputs = new ArrayList<>(downstreamNeuron.getInputs().keySet());
                            while (l < downstreamNeuronsInputs.size() && !found) {
                                Neuron srcNeuron = downstreamNeuronsInputs.get(l);
                                if (neuron == srcNeuron) {
                                    sum += (downstreamNeuron.getInputs().get(srcNeuron) * downstreamNeuron.getError());
                                    found = true;
                                }
                                l++;
                            }
                        }
                        neuronError *= sum;
                    }
                    neuron.setError(neuronError);
                }
            }

            for (int j = layers.size() - 1; j > 0; j--) {
                Layer layer = layers.get(j);

                for (Neuron neuron : layer.getNeurons()) {

                    for (Map.Entry<Neuron, Double> entry : neuron.getInputs().entrySet()) {

                        double newLearningRate = characteristicTime > 0 ? learningRate / (1 + (currentEpoch / characteristicTime)) : learningRate;
                        double delta = newLearningRate * neuron.getError() * entry.getKey().getPrediction();

                        if (synapseNeuronDeltaMap.get(entry.getKey()) != null) {
                            double previousDelta = synapseNeuronDeltaMap.get(entry.getKey());
                            delta += momentum * previousDelta;
                        }

                        synapseNeuronDeltaMap.put(entry.getKey(), delta);
                        entry.setValue(entry.getValue() - delta);
                        neuron.updateIncomingWeights();
                    }
                }
            }

            output = predict(inputs.get(i));

            error += error(output, expectedOutput);

//            actualOutputs.add(output);
        }


        return error;
    }

    private double error(Double[] actual, Double[] expected) {

        if (actual.length != expected.length) {
            throw new IllegalArgumentException("The lengths of the actual and expected value arrays must be equal");
        }

        double sum = 0;

        for (int i = 0; i < expected.length; i++) {
            sum += Math.pow(expected[i] - actual[i], 2);
        }
        return sum / 2;

    }

}
