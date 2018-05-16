import java.io.File;
import java.util.ArrayList;

public class Model {

    private ArrayList<Layer> layers; // Like onions, Donkey... they have LAYERS
    private InputLayer inputLayer;

    private boolean isInitialized;

    public Model(int ofInputSize) {
        isInitialized = false;
        layers = new ArrayList<>();
        inputLayer = new InputLayer();
        for (int i = 0; i < ofInputSize; i++) {
            inputLayer.addNeuron(new InputNeuron());
        }
    }

    public void addLayer(Layer layer) {
        layers.add(layer);
    }

    public void initialize() {
        if (isInitialized) return;
        inputLayer.connectLayer(layers.get(0));
        for (int i = 0; i < layers.size() - 1; i++) {
            layers.get(i).connectLayer(layers.get(i + 1));
        }
        isInitialized = true;
    }

    public double[] predict(double[] data) {
        inputLayer.initialValues(data);
        return layers.get(layers.size() - 1).computeValues();
    }

}
