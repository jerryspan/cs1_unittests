import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

public class GameLabTest {

	private static String[] stories = {
			"You are standing in an abandoned university office. There are neither students nor teachers around you. There’s a table in front of you with various papers, pens, a small puzzle toy, and a calculator. A large window shows an empty office building; there are no Zombies in the empty building (as far as you can tell). Behind you is a dark and mysterious door that leads to a well-lit corridor with a fireproof ceiling and floor. You feel a sense of Wi-Fi around you, the grinding of an LCD operated coffee machine can be heard in the distance. You are not thirsty, but you rather have a craving for justice.",
			"You are in a long hallway. There’s a man wearing glasses at the end of it, he looks harmless. West is a wall, east is the man, to the north is nothing but empty offices, a desperate sight. The carpeting in the hallway feels soft, you hear the clicking of a mouse in the distance. Your office is south (behind you).",
			"You take the calculator from your desk. It’s a Casio FX-85gt Plus. The display shows the number 0.1134. You turn it upside down; now the Casio greets you with a friendly “hello”, nice. You hold the calculator in your hand.",
			"The man greets you and starts endlessly talking to you about his children and his holiday to Benidorm. You die of boredom.",
			"You enter the hallway with the Casio FX-85gt stand-by. Having this small device greet you puts you in a good mood, somehow the building feels less lonely than before. West is a wall, looking east you stare into the darkness, the corridor is too long to see the end. To the north you see an office with what looks like a small creature in a corner. The carpeting in the hallway feels soft, you hear someone explaining algorithms to your north.",
			"You enter the office. To your surprise a small dog is sitting in the corner. Surely this breaks any number of university regulations! In a high-pitched voice the dog tells you how to write even more cool words on your Casio FX-85gt, good boi! To the north you see an open window, a ladder hangs down from it, it looks so dangerous! An LCD display shows a youtube video about developing algorithms." };

	String output;
	String GOOD_INPUTS = "Tom Pepels\n1983\ntake item\ngo east\nquit\n";
	String DIE_INPUTS = "Tom Pepels\n1983\nopen the door\ngo east\nquit\n";
	String INVALID_INPUTS = "Tom Pepels\n1983\nnever let me go\nquit\n";

	String[][] stateMatrix;

	@Before
	public void setupGame() {
		stateMatrix = new String[stories.length][stories.length];
		for (int i = 0; i < stories.length; i++) {
			for (int j = 0; j < stories.length; j++) {
				stateMatrix[i][j] = "";
			}
		}
		stateMatrix[0][1] = "open the door";
		stateMatrix[0][2] = "take item";
		stateMatrix[1][0] = "go south";
		stateMatrix[1][3] = "go east";
		stateMatrix[2][4] = "open the door";
		stateMatrix[4][1] = "drop item";
		stateMatrix[4][5] = "go north";
		stateMatrix[5][1] = "go south";
	}

	public void runGameWithInputs(String inputs) {
		var outputStream = getOutputStream();
		System.out.println("Running game with inputs: " + inputs);
		sendInput(inputs);
		Game.main(new String[] {});
		this.output = outputStream.toString();
	}

	@Test
	@Tag("lab1")
	@DisplayName("Test if you mention your name in the game title.")
	public void gameLab1CreditsTest() {
		runGameWithInputs(GOOD_INPUTS);
		boolean test = output.contains("by: ");
		Assert.assertTrue(
				"Hey! You want credit for your work right?? Make sure to include by: your name in your output!", test);

	}

	@Test
	@Tag("lab1")
	@DisplayName("Test if your title includes the ASCII art.")
	public void gameLab1ASCIITest() {
		runGameWithInputs(GOOD_INPUTS);
		boolean test = output
				.contains("/ /_/ /  __/ /  / /  / /_/ / (__  )  / ___ / /_/ /| |/ /  __/ / / / /_/ /_/ / /  /  __/");
		Assert.assertTrue("I think your ASCII Art is not ready for the Bonnefanten Museum yet!", test);
	}

	@Test
	@Tag("lab1")
	@DisplayName("Test if you print the player's name.")
	public void gameLab1PlayerNameTest() {
		runGameWithInputs(GOOD_INPUTS);
		boolean test = output.contains("Pepels");
		Assert.assertTrue("Do you know who I am? Make sure to ask and print the player's name after the title.", test);
	}

	@Test
	@Tag("lab1")
	@DisplayName("Test if you print the first part of the story.")
	public void gameLab1StoryTest() {
		runGameWithInputs(GOOD_INPUTS);
		boolean test = output.contains("of Wi-Fi around you, the grinding of an LCD operated coffee machine can be");
		Assert.assertTrue(
				"What is this game about? Did you print the story (make sure it is exactlty the same as the assignment text).",
				test);
	}

	@Test
	@Tag("lab1")
	@DisplayName("Test if you print the chosen move.")
	public void gameLab1MoveTest() {
		runGameWithInputs(GOOD_INPUTS);
		boolean test = output.contains("take item");
		Assert.assertTrue("Did you print my move? Make sure to ask for a move and print it after printing the story",
				test);
	}

	@Test
	@Tag("lab1")
	@DisplayName("Test if your calculateAge method returns the correct result.")
	public void gameLab1AgeTest() {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int age = Game.calculateAge(1983);
		boolean test = age == (year - 1983);
		Assert.assertTrue("I'm pretty sure that I'm not " + age + " years old, I was born in 1983!", test);
	}

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

	String[] endings = { "eloping algorithms.", "You die of boredom.", "eloping algorithms.", "eloping algorithms.",
			"eloping algorithms." };

	@Test
	@Tag("lab4")
	@DisplayName("Test your tellStory function for the correct output.")
	public void gameLab4tellStory() {
		for (var i = 0; i < 5; i++) {
			var outputStream = getOutputStream();
			Game.tellStory(i, stateMatrix, stories, new boolean[stories.length]);
			String outp = outputStream.toString(StandardCharsets.UTF_8);

			Assert.assertTrue(
					"Check the start of your output for tellStory(" + i
							+ ") it is not what I expected.. \n - you start with: " + outp.substring(0, 22)
							+ "...\n - but I expected: " + stories[i].substring(0, 22) + "...",
					outp.startsWith(stories[i].substring(0, 20)));

			var n = outp.length();
			var end = outp.substring(n - 25, n);
			Assert.assertTrue("Check the end of your output for tellStory(" + i
					+ ") it is not what I expected.. \n - you end with: " + end + "...\n - but I expected: "
					+ endings[i], end.replace("\n", "").endsWith(endings[i]));
		}
	}

	private ByteArrayOutputStream getOutputStream() {
		var baos = new ByteArrayOutputStream();
		var ps = new PrintStream(baos);
		System.setOut(ps);
		return baos;
	}

	private void sendInput(String input) {
		System.setIn(new ByteArrayInputStream(input.getBytes()));
	}

}
