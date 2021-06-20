import java.util.List;

public class Main {

    //independent var
    public static double [][] independent = {
            {0,0},
            {1,0},
            {0,1},
            {1,1}
    };

    //dependent var
    public static double [][] dependent = {
            {1},{0},{0},{1}
    };

    public static void main(String args[]) {

        var ANN = new NNet(2,12,1);

        List<Double> out;

        ANN.fit(independent, dependent, 50000);
        double [][] inputLayer = {
                {0,0},{0,1},{1,0},{1,1}
        };

        var i = 0;
        while (i < inputLayer.length) {
            var val = inputLayer[i];
            out = ANN.predict(val);
            System.out.print(out.toString() + "\n");
            i++;
        }
    }
}
