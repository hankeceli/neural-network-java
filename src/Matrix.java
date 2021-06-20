import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import static java.lang.Math.*;

public class Matrix {
    double [][]data;
    int rows,columns;

    public Matrix(int rows,int cols) {
        data= new double[rows][cols];
        this.rows=rows;
        this.columns=cols;

        for(var i = 0; i < rows; i++) {
            for(var j = 0; j < cols; j++)
                data[i][j] = (random() * 2) - 1;
        }
    }

    public void add(Matrix matrix) {
        if (columns != matrix.columns) {
            System.err.println("Columns aren't matched!");
            return;
        } else if (rows != matrix.rows) {
            System.err.println("Rows aren't matched!");
            return;
        }
        for(var i = 0; i < rows; i++) {
            for(var j = 0; j < columns; j++)
                this.data[i][j] += matrix.data[i][j];
        }
    }

    public static Matrix convArray(double[]cA) {
        var flag = new Matrix(cA.length,1);
        for(var i = 0; i < cA.length; i++)
            flag.data[i][0] = cA[i];
        return flag;
    }

    public List<Double> toArray() {
        var flag= new ArrayList<Double>();

        for(var i = 0; i < rows; i++) {
            for(var j = 0; j < columns; j++)
                flag.add(data[i][j]);
        }
        return flag;
    }

    public static Matrix subtracts(Matrix m1, Matrix m2) {
        var flag = new Matrix(m1.rows, m1.columns);

        for(var i = 0; i < m1.rows; i++) {
            for(var j = 0; j < m1.columns; j++) 
                flag.data[i][j] = m1.data[i][j] - m2.data[i][j];
        }
        return flag;
    }

    public static Matrix transpose(Matrix m) {
        var flag = new Matrix(m.columns,m.rows);

        for(var i = 0; i < m.rows; i++) {
            for(var j = 0; j < m.columns; j++)
                flag.data[j][i] = m.data[i][j];
        }
        return flag;
    }

    public static Matrix multiply(Matrix m1, Matrix m2) {
        var flag = new Matrix(m1.rows, m2.columns);

        for(var i = 0; i < flag.rows; i++) {
            for(var j = 0; j < flag.columns; j++) {
                var sum = 0.0;
                for(var k = 0; k < m1.columns; k++)
                    sum = sum + (m1.data[i][k] * m2.data[k][j]);
                flag.data[i][j] = sum;
            }
        }
        return flag;
    }

    public void multiply(Matrix m) {
        for(var i = 0; i < m.rows; i++) {
            for(var j = 0; j < m.columns; j++)
                this.data[i][j] = this.data[i][j] * m.data[i][j];
        }
    }

    public void multiply(double m) {
        for(var i = 0; i < rows; i++) {
            for(var j = 0; j < columns; j++)
                this.data[i][j] = this.data[i][j] * m;
        }
    }

    public void sigmoidFunc() {
        for(var i = 0; i < rows; i++) {
            for(var j = 0; j < columns; j++)
                this.data[i][j] = 1 / (1 + exp(-this.data[i][j]));
        }
    }

    public Matrix derivativeSigmoidFunc() {
        var flag = new Matrix(rows,columns);

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++)
                flag.data[i][j] = this.data[i][j] * 1 - this.data[i][j] * this.data[i][j];
        }
        return flag;

    }
}	