import java.util.ArrayList;
import java.util.InputMismatchException;

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

    public void train() {
        System.out.println("boop");
    }

//    private double[] getFinalValues() {
//        double[] prediction = new double[layers.get(layers.size() - 1).size()];
//    }
}
