import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class GameLabWeek3Test extends GameLabWeek1Test {
	// In week 3 the takeAction method is adjusted, so we cannot inherit from week 2

	@Test
	@DisplayName("Test if you print the story after my move.")
	public void gameLab3SecondStoryTest() {
		runGameWithInputs(GOOD_INPUTS, true);
		boolean test = output.contains(stories[2].toLowerCase());
		Assert.assertTrue(
				"The progression of the story was not what I expected after doing the move 'take item'. Make sure you print the correct story, and that your print the story exactly like the assignment.",
				test);
	}

	@Test
	@DisplayName("Test your takeAction function for the correct state transitions.")
	public void gameLab3TakeActionTest() {
		Assert.assertEquals(Game.takeAction("open the door", 0, stateMatrix), 1);
		Assert.assertEquals(Game.takeAction("take item", 0, stateMatrix), 2);
		Assert.assertEquals(Game.takeAction("go east", 1, stateMatrix), 3);
	}

	@Test
	@DisplayName("Test your getStory function for the correct states.")
	public void gameLab3GetStory() {
		for (var i = 0; i < 4; i++) {

			String story = Game.getStory(i, stories);

			Assert.assertTrue(
					"Check your output for getStory(" + i + ") it is not what I expected.. \n - you start with: "
							+ story.substring(0, 20).toLowerCase() + "...\n - expected: "
							+ stories[i].substring(0, 20).toLowerCase() + "...",
					story.startsWith(stories[i].substring(0, 20).toLowerCase()));
		}
	}
}
