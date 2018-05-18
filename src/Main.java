import network.Model;
import processing.DataInterpreter;

import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Model xorGate = new Model();
        xorGate.addLayer(2);
        xorGate.addLayer(3);
        xorGate.addLayer(1);
        xorGate.connectLayers();
        ArrayList<Double[][]> dataSet = DataInterpreter.getDataSet("res/dataset.txt", "res/dataset_labels.txt");
        Double[] weights;
        try {
            weights = DataInterpreter.getSavedWeights("res/weights.txt");
            xorGate.setWeights(weights);
            xorGate.saveWeights("res/weightsSaved.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        double[] prediction = xorGate.predict(new double[] {1.0, 0.0});
        System.out.println(Arrays.toString(prediction));
    }


}
