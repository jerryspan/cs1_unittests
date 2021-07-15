import java.util.Scanner;

public class Game {

	private static String[] moves = { "open the door", "go north", "go east", "go south", "go west", "take item",
			"drop item", "use item", "quit" };
	private static String[] stories = {
			"You are standing in an abandoned university office. There are neither students nor teachers around you. There’s a table in front of you with various papers, pens, a small puzzle toy, and a calculator. A large window shows an empty office building; there are no Zombies in the empty building (as far as you can tell). Behind you is a dark and mysterious door that leads to a well-lit corridor with a fireproof ceiling and floor. You feel a sense of Wi-Fi around you, the grinding of an LCD operated coffee machine can be heard in the distance. You are not thirsty, but you rather have a craving for justice.",
			"You are in a long hallway. There’s a man wearing glasses at the end of it, he looks harmless. West is a wall, east is the man, to the north is nothing but empty offices, a desperate sight. The carpeting in the hallway feels soft, you hear the clicking of a mouse in the distance. Your office is south (behind you).",
			"You take the calculator from your desk. It’s a Casio FX-85gt Plus. The display shows the number 0.1134. You turn it upside down; now the Casio greets you with a friendly “hello”, nice. You hold the calculator in your hand.",
			"The man greets you and starts endlessly talking to you about his children and his holiday to Benidorm. You die of boredom.",
			"You enter the hallway with the Casio FX-85gt stand-by. Having this small device greet you puts you in a good mood, somehow the building feels less lonely than before. West is a wall, looking east you stare into the darkness, the corridor is too long to see the end. To the north you see an office with what looks like a small creature in a corner. The carpeting in the hallway feels soft, you hear someone explaining algorithms to your north.",
			"You enter the office. To your surprise a small dog is sitting in the corner. Surely this breaks any number of university regulations! In a high-pitched voice the dog tells you how to write even more cool words on your Casio FX-85gt, good boi! To the north you see an open window, a ladder hangs down from it, it looks so dangerous! An LCD display shows a youtube video about developing algorithms." };
	private static String[][] stateMatrix;

	private static String asciiArt = "	         __                    _          ___       __                 __                 \n"
			+ "		/ /__  ____________  _( )_____   /   | ____/ /   _____  ____  / /___  __________  \n"
			+ "	   __  / / _ \\/ ___/ ___/ / / /// ___/  / /| |/ __  / | / / _ \\/ __ \\/ __/ / / / ___/ _ \\\n"
			+ "	  / /_/ /  __/ /  / /  / /_/ / (__  )  / ___ / /_/ /| |/ /  __/ / / / /_/ /_/ / /  /  __/ \n"
			+ "	  \\____/\\___/_/  /_/   \\__, / /____/  /_/  |_\\__,_/ |___/\\___/_/ /_/\\__/\\__,_/_/   \\___/  \n"
			+ "			      /____/  	\n\nby: Student\n";

	private static Scanner scanner;

	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		System.out.println(asciiArt);
		System.out.print("What is your name?: ");
		String name = scanner.nextLine();
		System.out.println("Hello " + name);
		System.out.print("When were you born?: ");
		int birthYear = scanner.nextInt();
		scanner.nextLine();
		System.out.println("You are " + calculateAge(birthYear) + " years old.");

		buildStateMatrix();

		int stateId = 0;

		while (stateId != 666) {
			String story = getStory(stateId, stories);
			System.out.println(story);
			String action = getInput();
			stateId = takeAction(action, stateId, stateMatrix);
		}
	}

	private static void buildStateMatrix() {
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

	public static int calculateAge(int birthYear) {
		return 2021 - birthYear;
	}

	public static String getInput() {
		boolean validInput = false;
		String action = "";

		while (!validInput) {
			System.out.print("What would you like to do?: ");
			action = scanner.nextLine();
			validInput = contains(moves, action.toLowerCase());

			if (validInput)
				System.out.println(action);
			else
				System.out.println("Invalid Action");
		}
		return action;
	}

	public static boolean contains(final String[] array, final String v) {
		for (String i : array)
			if (v.equals(i))
				return true;
		return false;
	}

	public static int takeAction(String action, int stateId, String[][] stateMatrix) {
		action = action.toLowerCase();
		if (action.equals("quit"))
			return 666;

		for (int i = 0; i < stateMatrix[stateId].length; i++) {
			if (stateMatrix[stateId][i].equals(action)) {
				return i;
			}
		}
		return stateId;
	}

	public static String getStory(int stateId, String[] storyArray) {
		return storyArray[stateId];
	}
}
