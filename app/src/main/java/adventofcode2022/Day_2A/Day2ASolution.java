package adventofcode2022.Day_2A;

import java.util.List;

import adventofcode2022.AoCRunner;

/**
 * This is the solution for part 1 of day 2's problem.
 * 
 * The strategy is to loop over all the inputs and add the projected score
 * to the total score. The tricky bit here is properly calculating a round score,
 * the conveting characters to an integer value. I just used a big 'if' block for this
 * you could probably simplify this with char manipulation, but it didn't seem like it
 * would lead to more efficient runtime code.
 * 
 * It runs in O(N) runtime, meaning if there are N entries in the list, we only have,
 * in the worst case (which is every case for this solution), N iterations.
 * 
 * If you ignore the cost of loading the input list, it uses O(1) storage.
 */
public class Day2ASolution {
    private static String INPUT_NAME = "Day_2A/input.txt";
    private static String PROMPT = """
        What would your total score be if everything goes exactly according to your strategy guide?
            """;

    /// This is the class default constructor, in order for the setup to work,
    /// you MUST change its name to match the class name (DayXSolution) and make sure
    /// it takes no input arguments.
    /// You do not need to change any of the code contained within the method.
    public Day2ASolution() {
        if (INPUT_NAME == null) {
            System.out.println("Warning: INPUT_NAME has not been set for '"
                + this.getClass().getName()
                + "' redo Step 8 and try again.");
            System.exit(0);
        }

        if (!PROMPT.strip().equals("Put the prompt for this here.")) {
            System.out.println(PROMPT);
        }
    }

    /// Put your solution here!
    /// Note, for simplicity it does not return anything, so you may want to end the method
    /// with some sort of print so you can see your result. For example, if your solved answer
    /// will be contained within the variable 'solution', maybe make the last line of the method:
    ///   System.out.println("My answer is: " + solution);
    public void Solve() {
        int totalScore = 0;

        // Loop over total input one line at a time
        for (String line: getInputData()) {
            totalScore += calculateScoreForRound(line);
        }
        System.out.println("Solution for problem 2, part 1: " + totalScore);
    }

    // Calculates the score for a single line of input
    // First add points for the choice you made (rock / paper / scissors)
    // Then add to that the round result
    private int calculateScoreForRound(String roundInput) {
        char opponentChoice = roundInput.charAt(0);
        char yourChoice = roundInput.charAt(2);

        int score = 0;

        // First, award points for your choice
        // 'W' is one less than 'X', so this converts
        // X -> 1, Y -> 2, Z -> 3
        score = yourChoice - 'W';

        // Now add the round result to it
        if (opponentChoice == 'A') {
            if (yourChoice == 'X') {
                score += 3;
            } else if (yourChoice == 'Y') {
                score += 6;
            } else {
                score += 0;
            }
        } else if (opponentChoice == 'B') {
            if (yourChoice == 'X') {
                score += 0;
            } else if (yourChoice == 'Y') {
                score += 3;
            } else {
                score += 6;
            }
        } else { // Opponent chose 'C'
            if (yourChoice == 'X') {
                score += 6;
            } else if (yourChoice == 'Y') {
                score += 0;
            } else {
                score += 3;
            }
        }

        return score;
    }


    ///
    /// The methods below this line are helper methods provided for you.
    /// If you want to use one of them, simply call the method within Solve.
    ///
    protected List<String> getInputData() {
        List<String> data = null;
        try {
            data = AoCRunner.AoCHelper.convertFileToStringList(INPUT_NAME);
        } catch (NullPointerException e) {
            System.out.println("Loading the input data failed. "
                + "Make sure that you set INPUT_NAME properly. "
                + "It needs the folder name, file name, and file extension.");
            System.exit(0);
        }
        return data;
    }
}
