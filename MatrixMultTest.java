import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class MatrixMultTest {
    static double[][] firstMatrix = { new double[] { 1d, 5d }, new double[] { 2d, 3d }, new double[] { 1d, 7d } };

    static double[][] secondMatrix = { new double[] { 1d, 2d, 3d, 7d }, new double[] { 5d, 2d, 8d, 1d } };

    static double[][] expected = { new double[] { 26d, 12d, 43d, 12d }, new double[] { 17d, 10d, 30d, 17d },
            new double[] { 36d, 16d, 59d, 14d } };

    @Test
    @DisplayName("Tests your matrix multiplication method when an incorrect input is given. This should print a warning.")
    public void testIncorrectMatrix() {
        double[][] matrix1 = { new double[] { 1d, 5d }, new double[] { 2d, 3d }, new double[] { 1d, 7d } };
        double[][] matrix2 = { new double[] { 1d, 2d, 3d, 7d }, new double[] { 5d, 2d, 8d, 1d },
                new double[] { 5d, 2d, 8d, 1d }, new double[] { 5d, 2d, 8d, 1d } };

        var stream = InputOutput.getOutputStream();
        MatrixMult.multiplyMatrices(matrix1, matrix2);
        var outputString = stream.toString().trim();
        Assert.assertFalse("You should print a message to System.out when the matrices' sizes don't match.",
                outputString.equals(""));
    }

    @Test
    @DisplayName("Tests your matrix multiplication method when a correct input is provided.")
    public void testCorrectMatrix() {
        double[][] actual = MatrixMult.multiplyMatrices(firstMatrix, secondMatrix);

        String firstMatrixString = matrixToString(firstMatrix);
        String secondMatrixString = matrixToString(secondMatrix);
        String expectedMatrixString = matrixToString(expected);

        String message = "The output of your array multiplication does not match the expected output. A\n%s\nX\nB\n%s\n!=\n%s\nShould be:\n%s";

        for (int j = 0; j < expected.length; j++) {
            var row = actual[j];
            Assert.assertArrayEquals(String.format(message, firstMatrixString, secondMatrixString,
                    matrixToString(actual), expectedMatrixString), row, expected[j], 0.00001);
        }
    }

    public String matrixToString(double[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (double[] row : matrix) {
            sb.append(Arrays.toString(row));
            sb.append("\n");
        }
        return sb.toString();
    }

    static double[][] multiplyMatrices(double[][] firstMatrix, double[][] secondMatrix) {

        double[][] result = new double[firstMatrix.length][secondMatrix[0].length];
        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                result[row][col] = multiplyMatricesCell(firstMatrix, secondMatrix, row, col);
            }
        }
        return result;
    }

    static double multiplyMatricesCell(double[][] firstMatrix, double[][] secondMatrix, int row, int col) {
        double cell = 0;
        for (int i = 0; i < secondMatrix.length; i++) {
            cell += firstMatrix[row][i] * secondMatrix[i][col];
        }
        return cell;
    }
}
