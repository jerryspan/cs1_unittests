import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

public class GameLabWeek3Test extends GameLabWeek2Test {

	@Test
	@Tag("lab3")
	@DisplayName("Test your takeAction function for the correct state transitions.")
	public void gameLab3TakeActionTest() {
		Assert.assertEquals(Game.takeAction("open the door", 0, stateMatrix), 1);
		Assert.assertEquals(Game.takeAction("take item", 0, stateMatrix), 2);
		Assert.assertEquals(Game.takeAction("go east", 1, stateMatrix), 3);
	}

	@Test
	@Tag("lab3")
	@DisplayName("Test your getStory function for the correct states.")
	public void gameLab3GetStory() {
		for (var i = 0; i < 4; i++) {
			String story = Game.getStory(i, stories);
			Assert.assertTrue(
					"Check your output for getStory(" + i + ") it is not what I expected.. \n - you start with: "
							+ story.substring(0, 20) + "...\n - expected: " + stories[i].substring(0, 20) + "...",
					story.startsWith(stories[i].substring(0, 20)));
		}
	}
}
