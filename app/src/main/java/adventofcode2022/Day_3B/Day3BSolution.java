package adventofcode2022.Day_3B;

import java.util.Arrays;
import java.util.List;

import adventofcode2022.AoCRunner;

/**
 * This is the solution for part 1 of day 3's problem.
 * 
 * The strategy is to convert each character to an int,
 * add it to an array at the value position of its priority (so a goes in 0, A goes in 26).
 * Then, repeat this for two more rows, so there are 3 total.
 * Then, logically 'and' the three lists and return the value where they match.
 * Repeat this process for every set of 3 lines of input.
 * 
 * It runs in O(NMI) runtime, meaning if there are N entries in the list,
 * M is the length of a single line, and I is the number of unique possible
 * items in the bag (so in this case 52, a-z and A-Z.
 * We only have, in the worst case N * M * I iterations.
 * 
 * If you ignore the cost of loading the input list, it uses O(IG) storage as we need
 * arrays of size I and G (G=3 for this) is the number of grouped elves in one team.
 */
public class Day3BSolution {
    private static String INPUT_NAME = "Day_3B/input.txt";
    private static String PROMPT = """
        Find the item type that appears in both compartments of each rucksack.
        What is the sum of the priorities of those item types?
            """;

    /// This is the class default constructor, in order for the setup to work,
    /// you MUST change its name to match the class name (DayXSolution) and make sure
    /// it takes no input arguments.
    /// You do not need to change any of the code contained within the method.
    public Day3BSolution() {
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
        int G = 3; // Group size for a group of elves
        int listIdx = 0; // Keep track of which elf in the group this is
        int total = 0;
        boolean[][] groupList = new boolean[G][I];

        // Loop over total input one line at a time
        for (String line: getInputData()) {
            populateList(groupList[listIdx], line);
            listIdx++;

            // Finished processing a group of 'G' elves
            if (listIdx == G) {
                total += calculateOverlapPriority(groupList, G);

                // Clear the lists for the next batch of 'G' elves
                for (int i = 0; i < G; i++) {
                    Arrays.fill(groupList[i], false);
                }

                // Start back at the first list
                listIdx = 0;
            }
        }
        System.out.println("Solution for problem 3, part 2: " + total);
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

    // Loop over the 'G' lists looking for an index where all of the lists have the same item
    // Return that item's index (+1 to get to 1-based indexing)
    private int calculateOverlapPriority(boolean[][] list, int G) {
        for (int i = 0; i < list[0].length; i++) {
            int foundCount = 0;
            for (int g = 0; g < G; g++) {
                if (list[g][i]) {
                    foundCount++;
                }
            }

            // If, after checking each list, found count == G, then this is the badge item
            if (foundCount == G) {
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