import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class HanoiTest {
    String[][] expected = { {},
            { "Move the top disc from the first stack to the second stack",
                    "Move the top disc from the first stack to the third stack",
                    "Move the top disc from the second stack to the third stack" },
            { "Move the top disc from the first stack to the third stack",
                    "Move the top disc from the first stack to the second stack",
                    "Move the top disc from the third stack to the second stack",
                    "Move the top disc from the first stack to the third stack",
                    "Move the top disc from the second stack to the first stack",
                    "Move the top disc from the second stack to the third stack",
                    "Move the top disc from the first stack to the third stack" },
            { "Move the top disc from the first stack to the second stack",
                    "Move the top disc from the first stack to the third stack",
                    "Move the top disc from the second stack to the third stack",
                    "Move the top disc from the first stack to the second stack",
                    "Move the top disc from the third stack to the first stack",
                    "Move the top disc from the third stack to the second stack",
                    "Move the top disc from the first stack to the second stack",
                    "Move the top disc from the first stack to the third stack",
                    "Move the top disc from the second stack to the third stack",
                    "Move the top disc from the second stack to the first stack",
                    "Move the top disc from the third stack to the first stack",
                    "Move the top disc from the second stack to the third stack",
                    "Move the top disc from the first stack to the second stack",
                    "Move the top disc from the first stack to the third stack",
                    "Move the top disc from the second stack to the third stack" } };

    @Test
    @DisplayName("Tests your hanoi towers outputs between 2 and 4. Make sure your output matches the output in the assignment!")
    public void testHanoiTowers() {
        for (var t = 1; t < expected.length; t++) {

            var stream = InputOutput.getOutputStream();
            HanoiTowers.hanoiRecursion(t + 1, "first", "second", "third");
            var outputString = stream.toString().trim();

            String[] lines = outputString.split("\n");
            String message = "Testing for height %d:\nLine %d of your output (%s) does not match the expected: %s";

            for (int i = 0; i < expected[t].length; i++) {
                var check = lines[i].contains(expected[t][i]);
                Assert.assertTrue(String.format(message, t + 1, i, lines[i], expected[t][i]), check);
            }
        }
    }
}
