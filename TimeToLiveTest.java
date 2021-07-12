import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class TimeToLiveTest {
    @Test
    @DisplayName("Tests if you asked for the user's age.")
    public void testAgeQuestion() {
        var outputStream = InputOutput.getOutputStream();
        InputOutput.sendInput("38\n");
        TimeToLive.main(new String[] {});
        var output = outputStream.toString().toLowerCase();
        Assert.assertTrue("You didn't ask for my age! Make sure to ask 'What is your age?:'",
                output.contains("what") && output.contains("age"));
    }

    @Test
    @DisplayName("Tests the output of your program with the ages 56 and 38.")
    public void testHowMuchTimeLeftOutput() {
        var outputStream = InputOutput.getOutputStream();
        InputOutput.sendInput("38\n");
        TimeToLive.main(new String[] {});
        var output = outputStream.toString().toLowerCase();
        // You have 18980 days, weeks, or 624 months left.
        Assert.assertTrue("I think I should have 18980 days, weeks, or 624 months left. You say: " + output,
                output.contains("18980") && output.contains("2704") && output.contains("624"));

        outputStream = InputOutput.getOutputStream();
        InputOutput.sendInput("56\n");
        TimeToLive.main(new String[] {});
        output = outputStream.toString().toLowerCase();
        // You have 12410 days, 1768 weeks, or 408 months left.
        Assert.assertTrue("I think I should have 12410 days, 1768 weeks, or 408 months left.",
                output.contains("12410") && output.contains("1768") && output.contains("408"));
    }
}
