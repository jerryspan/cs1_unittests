import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class ArrayAssignmentTest {

    int[][][] diagInputs = { { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } } };
    String[] diagOutputs = { "1 5 2 9 6 3 10 7 4 11 8 12" };

    @Test
    @DisplayName("Tests your diagonal printing method.")
    public void testDiagonalPrint() {
        var message = "Testing for matrix %s:\nUnfortunately, your output: %s does not match the expected output: %s";
        for (int i = 0; i < diagInputs.length; i++) {
            var stream = InputOutput.getOutputStream();
            var matrix = diagInputs[i];
            ArrayAssignment.diagonalPrint(matrix);
            var outputString = stream.toString().trim();
            StringBuilder sb = new StringBuilder();
            for (int[] row : matrix) {
                sb.append(Arrays.toString(row));
                sb.append("\n");
            }

            Assert.assertTrue(String.format(message, sb.toString(), outputString, diagOutputs[i]),
                    outputString.contains(diagOutputs[i]));
        }
    }

    int[][] allNumsInputs = { { 1, 2, 3, 209, 12, 45, 1, 0, 1, 1, 1, 1, 0, 1, 1, 2, 2, 3, 4 },
            { 0, 2, 0, 2, 0, 2, 2, 1, 1, 0 }, { 0, 1, 1, 4, 0, 2, 0, 1, 0, 2 },
            { 0, 1, 2, 2, 2, 5, 2, 2, 5, 5, 6, 5 } };
    int[] allNumsKs = { 4, 3, 6, 3 };
    int[] allNumsOutputs = { 6, 4, 0, 3 };

    @Test
    @DisplayName("Tests the output of your allNums method.")
    public void testAllNumsWithin() {
        var message = "Testing for input %s with k=%d\nUnfortunately, your output: %d does not match the expected output: %d";

        for (int i = 0; i < diagInputs.length; i++) {
            var n = ArrayAssignment.allNumsWithin(allNumsInputs[i], allNumsKs[i]);
            Assert.assertEquals(
                    String.format(message, Arrays.toString(allNumsInputs[i]), allNumsKs[i], n, allNumsOutputs[i]),
                    allNumsOutputs[i], n);
        }
    }
}
