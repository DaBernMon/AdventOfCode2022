package adventofcode2022.Day_3A;

import java.util.Arrays;
import java.util.List;

import adventofcode2022.AoCRunner;

/**
 * This is the solution for part 1 of day 3's problem.
 * 
 * The strategy is to convert each character to an int,
 * add it to an array at the value position of its priority (so a goes in 0, A goes in 26),
 * first half of the line goes in one array, second half goes in another.
 * Then, locally and the two lists and return the value where they match.
 * Repeat this process for each line of input.
 * 
 * It runs in O(NMI) runtime, meaning if there are N entries in the list,
 * M is the length of a single line, and I is the number of unique possible
 * items in the bag (so in this case 52, a-z and A-Z.
 * We only have, in the worst case N * M * I iterations.
 * 
 * If you ignore the cost of loading the input list, it uses O(I) storage as we need
 * arrays of size I.
 */
public class Day3ASolution {
    private static String INPUT_NAME = "Day_3A/input.txt";
    private static String PROMPT = """
        Find the item type that appears in both compartments of each rucksack.
        What is the sum of the priorities of those item types?
            """;

    /// This is the class default constructor, in order for the setup to work,
    /// you MUST change its name to match the class name (DayXSolution) and make sure
    /// it takes no input arguments.
    /// You do not need to change any of the code contained within the method.
    public Day3ASolution() {
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
        int I = 52; // a through z + A through Z
        int total = 0;
        boolean[] firstHalfList = new boolean[I];
        boolean[] secondHalfList = new boolean[I];

        // Loop over total input one line at a time
        for (String line: getInputData()) {
            // Clear the lists from the previous iteration
            Arrays.fill(firstHalfList, false);
            Arrays.fill(secondHalfList, false);

            String firstHalf = line.substring(0, line.length() / 2);
            String secondHalf = line.substring(line.length() / 2);

            populateList(firstHalfList, firstHalf);
            populateList(secondHalfList, secondHalf);

            total += calculateOverlapPriority(firstHalfList, secondHalfList);
        }
        System.out.println("Solution for problem 3, part 1: " + total);
    }

    // Fill the list with values using index 0 = a, index 26 = A
    private void populateList(boolean[] list, String items) {
        for (int i = 0; i < items.length(); i++) {
            char c = items.charAt(i);

            // Convert the char to an int and use it as the list index
            // Set it to 'true' meaning an object of that type was found in this set of items
            if (c - 'a' >= 0 && c - 'z' < 26) {
                list[c - 'a'] = true;
            } else if (c - 'A' >= 0 && c - 'Z' < 26) {
                list[c + 26 - 'A'] = true;
            }
        }
    }

    // Loop over both lists looking for an index where both have the same item
    // Return that item's index (+1 to get to 1-based indexing)
    private int calculateOverlapPriority(boolean[] listA, boolean[] listB) {
        for (int i = 0; i < listA.length; i++) {
            if (listA[i] && listB[i]) {
                return i + 1;
            }
        }

        System.out.println("Warning: Somehow there was no match found!");
        return -1;
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