package processing;

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
        } catch (IOException e) {
            System.out.println("IO Exception");
            e.printStackTrace();
        }
        return dataset;
    }

    private static Double[][] getData(String directory) throws IOException {
        ArrayList<Double[]> inputs = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(directory));
        while (reader.ready()) {
            String[] values = reader.readLine().split(" ");
            Double[] input = new Double[values.length];
            for (int i = 0; i < values.length; i++) {
                input[i] = Double.parseDouble(values[i]);
            }
            inputs.add(input);
        }
        Double[][] returnValues = new Double[inputs.size()][inputs.get(0).length];
        for (int i = 0; i < inputs.size(); i++) {
            returnValues[i] = inputs.get(i);
        }
        return returnValues;
    }

    public static Double[] getSavedWeights(String directory) throws IOException {
        ArrayList<Double> weights = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(directory));
        while (reader.ready()) {
            weights.add(Double.parseDouble(reader.readLine()));
        }
        Double[] convertedWeights = new Double[weights.size()];
        for (int i = 0; i < weights.size(); i++) {
            convertedWeights[i] = weights.get(i);
        }
        return convertedWeights;
    }

}
