import java.util.ArrayList;
import java.util.InputMismatchException;

public class InputLayer extends Layer {

    private ArrayList<InputNeuron> inputNeurons;

    public InputLayer() {
        inputNeurons = new ArrayList<>();
    }

    public void addNeuron(InputNeuron neuron) {
        inputNeurons.add(neuron);
    }

    public void initialValues(double[] values) {
        if (values.length != inputNeurons.size()) {
            throw new InputMismatchException();
        }
        for (int i = 0; i < inputNeurons.size(); i++) {
            inputNeurons.get(i).setGivenValue(values[i]);
        }
    }
}
