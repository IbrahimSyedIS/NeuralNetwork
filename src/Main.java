import java.awt.datatransfer.SystemFlavorMap;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Model XORGate = new Model();
        XORGate.addLayer(2);
        XORGate.addLayer(3);
        XORGate.addLayer(1);
        XORGate.connectLayers();
        double[] values = {1.0, 0.0};
        double[] val = {0.0, 1.0};
        System.out.println(Arrays.toString(XORGate.predict(values)));
        System.out.println(Arrays.toString(XORGate.predict(val)));
    }

    
}
