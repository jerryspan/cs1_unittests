import java.nio.charset.StandardCharsets;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class GameLabWeek2Test extends GameLabWeek1Test {

	@Test
	@DisplayName("Test if you print the story after my move.")
	public void gameLab2SecondStoryTest() {
		runGameWithInputs(GOOD_INPUTS, true);
		boolean test = output.contains(stories[2].toLowerCase());
		Assert.assertTrue(
				"The progression of the story was not what I expected after doing the move 'take item'. Make sure you print the correct story, and that your print the story exactly like the assignment.",
				test);
	}

	@Test
	@DisplayName("Test your takeAction function for the correct state transitions.")
	public void gameLab2TakeActionTest() {
		Assert.assertEquals(Game.takeAction("open the door", 0), 1);
		Assert.assertEquals(Game.takeAction("take item", 0), 2);
		Assert.assertEquals(Game.takeAction("go east", 1), 3);
	}

	@Test
	@DisplayName("Test your printState function for the correct states.")
	public void gameLab2PrintStateTest() {
		for (var i = 0; i < 4; i++) {
			var outputStream = InputOutput.getOutputStream();
			Game.printState(i);
			String outp = outputStream.toString(StandardCharsets.UTF_8).toLowerCase();

			Assert.assertTrue("Check your output for printState(" + i + ") it is not what I expected.. \n - you: "
					+ outp.substring(0, 20) + "...\n - expected: " + stories[i].substring(0, 20).toLowerCase() + "...",
					outp.startsWith(stories[i].substring(0, 20).toLowerCase()));
		}
	}
}
