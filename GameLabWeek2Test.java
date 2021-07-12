import java.nio.charset.StandardCharsets;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

public class GameLabWeek2Test extends GameLabWeek1Test {

	@Test
	@Tag("lab2")
	@DisplayName("Test if you print the story after my move.")
	public void gameLab2SecondStoryTest() {
		runGameWithInputs(GOOD_INPUTS);
		boolean test = output.contains(stories[2]);
		Assert.assertTrue(
				"The progression of the story was not what I expected after doing the move 'take item'. Make sure you print the correct story, and that your print the story exactly like the assignment.",
				test);
	}

	@Test
	@Tag("lab2")
	@DisplayName("Test your takeAction function for the correct state transitions.")
	public void gameLab2TakeActionTest() {
		Assert.assertEquals(Game.takeAction("open the door", 0), 1);
		Assert.assertEquals(Game.takeAction("take item", 0), 2);
		Assert.assertEquals(Game.takeAction("go east", 1), 3);
	}

	@Test
	@Tag("lab2")
	@DisplayName("Test your printState function for the correct states.")
	public void gameLab2PrintStateTest() {
		for (var i = 0; i < 4; i++) {
			var outputStream = getOutputStream();
			Game.printState(i);
			String outp = outputStream.toString(StandardCharsets.UTF_8);

			Assert.assertTrue(
					"Check your output for printState(" + i + ") it is not what I expected.. \n - you: "
							+ outp.substring(0, 20) + "...\n - expected: " + stories[i].substring(0, 20) + "...",
					outp.startsWith(stories[i].substring(0, 20)));
		}
	}

	// @Test
	// @Tag("lab3")
	// @DisplayName("Test your takeAction function for the correct state
	// transitions.")
	// public void gameLab3TakeActionTest() {

	// Assert.assertEquals(Game.takeAction("open the door", 0, stateMatrix), 1);
	// Assert.assertEquals(Game.takeAction("take item", 0, stateMatrix), 2);
	// Assert.assertEquals(Game.takeAction("go east", 1, stateMatrix), 3);
	// }

	// @Test
	// @Tag("lab3")
	// @DisplayName("Test your getStory function for the correct states.")
	// public void gameLab3GetStory() {
	// for (var i = 0; i < 4; i++) {
	// String story = Game.getStory(i, stories);
	// Assert.assertTrue(
	// "Check your output for getStory(" + i + ") it is not what I expected.. \n -
	// you start with: "
	// + story.substring(0, 20) + "...\n - expected: " + stories[i].substring(0, 20)
	// + "...",
	// story.startsWith(stories[i].substring(0, 20)));
	// }
	// }

	// String[] endings = { "eloping algorithms.", "You die of boredom.", "eloping
	// algorithms.", "eloping algorithms.",
	// "eloping algorithms." };

	// @Test
	// @Tag("lab4")
	// @DisplayName("Test your tellStory function for the correct output.")
	// public void gameLab4tellStory() {
	// for (var i = 0; i < 5; i++) {
	// var outputStream = getOutputStream();
	// Game.tellStory(i, stateMatrix, stories, new boolean[stories.length]);
	// String outp = outputStream.toString(StandardCharsets.UTF_8);

	// Assert.assertTrue(
	// "Check the start of your output for tellStory(" + i
	// + ") it is not what I expected.. \n - you start with: " + outp.substring(0,
	// 22)
	// + "...\n - but I expected: " + stories[i].substring(0, 22) + "...",
	// outp.startsWith(stories[i].substring(0, 20)));

	// var n = outp.length();
	// var end = outp.substring(n - 25, n);
	// Assert.assertTrue("Check the end of your output for tellStory(" + i
	// + ") it is not what I expected.. \n - you end with: " + end + "...\n - but I
	// expected: "
	// + endings[i], end.replace("\n", "").endsWith(endings[i]));
	// }
	// }
}
