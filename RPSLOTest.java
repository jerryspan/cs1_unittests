import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class RPSLOTest {
    // Rock = 0, Paper = 1, Scissors = 2, Lizzard = 3, Spock = 4
    // Scissors cut Paper
    // Paper covers Rock
    // Rock crushes Lizard
    // Lizard poisons Spock
    // Spock smashes Scissors
    // Scissors decapitate Lizard
    // Lizard eats Paper
    // Paper disproves Spock
    // Spock vaporizes Rock
    // Rock crushes Scissors

    protected static final String[] choiceArray = { "r", "p", "s", "l", "o" };

    protected static final Integer[][] winMatrix = { { 3, 2 }, // Rock
            { 0, 4 }, // Paper
            { 1, 3 }, // Scissors
            { 4, 1 }, // Lizzard
            { 0, 2 }, // Spock
    };

    protected static final String computerPlayString = "Computer play is ";

    protected static <T> int indexOf(T needle, T[] haystack) {
        for (var i = 0; i < haystack.length; i++) {
            if (haystack[i] != null && haystack[i].equals(needle) || needle == null && haystack[i] == null)
                return i;
        }

        return -1;
    }

    @Test
    @DisplayName("Testing if each option gives the correct response!")
    public void testRPSLOIGame() {
        String[] choices = { "R", "P", "S", "L", "O" };

        for (var choice : choices) {

            var stream = InputOutput.getOutputStream();
            InputOutput.sendInput(choice + "\n");
            RPSLO.main(new String[] {});

            var outputString = stream.toString().trim();
            var lastIndex = outputString.lastIndexOf(computerPlayString) + computerPlayString.length();
            var computerPlay = outputString.substring(lastIndex, lastIndex + 1);

            Assert.assertTrue(
                    "Your computer play does not match what I expect. (I got: " + computerPlay
                            + ") but it should be one of: " + Arrays.toString(choiceArray) + " (lower or uppercase)",
                    indexOf(computerPlay.toLowerCase(), choiceArray) > -1);

            int playerIndex = indexOf(choice.toLowerCase(), choiceArray);
            int computerIndex = indexOf(computerPlay.toLowerCase(), choiceArray);

            if (computerIndex == playerIndex) {
                Assert.assertTrue(outputString.toLowerCase().contains("tie"));
            } else {
                if (indexOf(new Integer(computerIndex), winMatrix[playerIndex]) > -1) {
                    Assert.assertTrue(outputString.toLowerCase().contains("you win"));
                } else {
                    Assert.assertTrue(outputString.toLowerCase().contains("computer win"));
                }
            }
        }

    }
}
