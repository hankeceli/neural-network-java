import java.util.Collections;
import java.util.List;

import static java.lang.Math.*;

public class NNet {

    public Matrix weights_iHiddenLayer, weights_oHiddenLayer , bias_hLayer,bias_oLayer;
    double learning_rate=0.01;

    public NNet(int input, int hidden, int output) {
        weights_iHiddenLayer = new Matrix(hidden, input);
        weights_oHiddenLayer = new Matrix(output,hidden);

        bias_hLayer= new Matrix(hidden,1);
        bias_oLayer= new Matrix(output,1);

    }

    public List<Double> predict(double[] pred) {
        var inputLayer = Matrix.convArray(pred);

        var hiddenLayer = Matrix.multiply(weights_iHiddenLayer, inputLayer);
        hiddenLayer.add(bias_hLayer);
        hiddenLayer.sigmoidFunc();

        var outputLayer = Matrix.multiply(weights_oHiddenLayer,hiddenLayer);
        outputLayer.add(bias_oLayer);
        outputLayer.sigmoidFunc();

        return Collections.unmodifiableList(outputLayer.toArray());
    }


    public void fit(double[][]X, double[][]Y, int epoch) {
        for(var i = 0; i < epoch; i++) {
            var randomDP =  (int)(random() * X.length );
            this.train(X[randomDP], Y[randomDP]);
        }
    }

    public void train(double [] X,double [] Y) {
        var inputLayer = Matrix.convArray(X);
        var hiddenLayer = Matrix.multiply(weights_iHiddenLayer, inputLayer);
        hiddenLayer.add(bias_hLayer);
        hiddenLayer.sigmoidFunc();

        var outputLayer = Matrix.multiply(weights_oHiddenLayer,hiddenLayer);
        outputLayer.add(bias_oLayer);
        outputLayer.sigmoidFunc();

        var target = Matrix.convArray(Y);

        var error = Matrix.subtracts(target, outputLayer);
        var gradient = outputLayer.derivativeSigmoidFunc();
        gradient.multiply(error);
        gradient.multiply(learning_rate);

        var hidden_T = Matrix.transpose(hiddenLayer);
        var who_delta =  Matrix.multiply(gradient, hidden_T);

        weights_oHiddenLayer.add(who_delta);
        bias_oLayer.add(gradient);

        var who_T = Matrix.transpose(weights_oHiddenLayer);
        var hidden_errors = Matrix.multiply(who_T, error);

        var h_gradient = hiddenLayer.derivativeSigmoidFunc();
        h_gradient.multiply(hidden_errors);
        h_gradient.multiply(learning_rate);

        var i_T = Matrix.transpose(inputLayer);
        var wih_delta = Matrix.multiply(h_gradient, i_T);

        weights_iHiddenLayer.add(wih_delta);
        bias_hLayer.add(h_gradient);

    }


}