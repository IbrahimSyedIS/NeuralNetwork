import java.awt.datatransfer.SystemFlavorMap;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Model xorGate = new Model();
        xorGate.addLayer(2);
        xorGate.addLayer(3);
        xorGate.addLayer(1);
        xorGate.connectLayers();
        double[] values = {1.0, 0.0};
        double[] val = {0.0, 1.0};
        System.out.println(Arrays.toString(xorGate.predict(values)));
        System.out.println(Arrays.toString(xorGate.predict(val)));


        System.out.println("====================================");
        ArrayList<Double[][]> thingy = DataInterpreter.getDataSet("res/dataset.txt", "res/dataset_labels.txt");
        for (Double[][] data : thingy) {
            System.out.println("The Values to be inputted are " + data[0][0] + " and " + data[0][1] + " and the theoretical result is " + data[1][0]);
        }
    }


}
