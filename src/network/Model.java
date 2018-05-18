package network;

import java.util.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Model {

    // ArrayList for all the layers
    private ArrayList<Layer> layers;

    // Reserved value for the input layer
    private Layer inputLayer;

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

    // Connecting all the layers to the subsequent layers
    public void connectLayers() {
        for (int i = 0; i < layers.size() - 1; i++) {
            layers.get(i).connectToLayer(layers.get(i + 1));
        }
    }

    // Taking an array of values and sending them through the model
    public double[] predict(double[] data) {
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
    public void train(ArrayList<Double[][]> dataset) {

    }
}
