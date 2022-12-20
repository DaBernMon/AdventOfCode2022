package adventofcode2022.Day_6B;

import java.util.LinkedList;
import java.util.List;

import adventofcode2022.AoCRunner;

/**
 * This is the solution for part 2 of day 6's problem.
 * 
 * Using L as the start-of-signal length, will just loop over the input
 * keeping a LinkedList (as a queue) of characters which can only be in 3 states:
 * 1. Less than L entries, no repeats, get next character add to back of queue
 * 2. Repeats - remove front of queue until no repeats
 * 3. L entries, no repeats, done!
 * 
 * Same as part 1, but L=14 instead of 4.
 * 
 * It runs in O(NL) runtime, using N characters in the input signal,
 * and the start-of-signal length is L.
 * 
 * If you ignore the cost of loading the input list, it uses O(L) storage,
 * where L is the start-of-signal length.
 */
public class Day6BSolution {
    private static String INPUT_NAME = "Day_6B/input.txt";
    private static String PROMPT = """
        How many characters need to be processed before the first start-of-message marker is detected?
        """;

    /// This is the class default constructor, in order for the setup to work,
    /// you MUST change its name to match the class name (DayXSolution) and make sure
    /// it takes no input arguments.
    /// You do not need to change any of the code contained within the method.
    public Day6BSolution() {
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

    // Public Solver method that prints the solution once complete.
    public void Solve() {
        int answer = 0;
        int L = 14;
        LinkedList<Character> signalSequence = new LinkedList<>();
        // Should only be one line, but keep format the same as other problems
        for (String line: getInputData()) {
            // Iterate over characters
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);

                while (charIsInList(signalSequence, c)) {
                    signalSequence.pop();
                }
                signalSequence.offer(c);

                if (signalSequence.size() == L) {
                    answer = i + 1;
                    break;
                }
            }            
        }
        System.out.println("Solution for problem 6, part 2: " + answer);
    }

    // Returns true if the provided character is in the list
    private static boolean charIsInList(LinkedList<Character> list, char character) {
        for (char c : list) {
            if (c == character) {
                return true;
            }
        }
        
        return false;
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