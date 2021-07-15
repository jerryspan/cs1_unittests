import java.nio.charset.StandardCharsets;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class GameLabWeek4Test extends GameLabWeek1Test {
	// In week 3 the takeAction method is adjusted, so we cannot inherit from week 2

	String[] endings = { "eloping algorithms.", "You die of boredom.", "eloping algorithms.", "eloping algorithms.",
			"eloping algorithms." };

	@Test
	@DisplayName("Test your tellStory function for the correct output.")
	public void gameLab4tellStory() {
		for (var i = 0; i < 5; i++) {
			var outputStream = InputOutput.getOutputStream();
			Game.tellStory(i, stateMatrix, stories, new boolean[stories.length]);
			String outp = outputStream.toString(StandardCharsets.UTF_8).toLowerCase();

			Assert.assertTrue(
					"Check the start of your output for tellStory(" + i
							+ ") it is not what I expected.. \n - you start with: " + outp.substring(0, 22)
							+ "...\n - but I expected: " + stories[i].substring(0, 22).toLowerCase() + "...",
					outp.startsWith(stories[i].substring(0, 20).toLowerCase()));

			var n = outp.length();
			var end = outp.substring(n - 25, n);
			Assert.assertTrue(
					"Check the end of your output for tellStory(" + i
							+ ") it is not what I expected.. \n - you end with: " + end.toLowerCase()
							+ "...\n - but I expected: " + endings[i].toLowerCase(),
					end.toLowerCase().replace("\n", "").endsWith(endings[i].toLowerCase()));
		}
	}
}
