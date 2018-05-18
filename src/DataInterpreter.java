import java.io.*;
import java.util.ArrayList;

public class DataInterpreter {

    public static ArrayList<Double[][]> getDataSet(String data, String dataLabels) {
        ArrayList<Double[][]> dataset = new ArrayList<>();
        try {
            Double[][] inputValues = getData(data);
            Double[][] expectedOutputs = getData(dataLabels);
            for (int i = 0; i < inputValues.length; i++) {
                dataset.add(new Double[][] {inputValues[i], expectedOutputs[i]});
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO Exception");
            e.printStackTrace();
        }
        return dataset;
    }

    private static Double[][] getData(String data) throws FileNotFoundException, IOException {
        ArrayList<Double[]> inputs = new ArrayList<>();
        BufferedReader breader = new BufferedReader(new FileReader(data));
        while (breader.ready()) {
            String[] values = breader.readLine().split(" ");
            Double[] doubles = new Double[values.length];
            for (int i = 0; i < values.length; i++) {
                doubles[i] = Double.parseDouble(values[i]);
            }
            inputs.add(doubles);
        }
        Double[][] returnValues = new Double[inputs.size()][inputs.get(0).length];
        for (int i = 0; i < inputs.size(); i++) {
            returnValues[i] = inputs.get(i);
        }
        return returnValues;
    }

}
