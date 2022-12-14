package adventofcode2022.Day_4A;

import java.util.List;

import adventofcode2022.AoCRunner;

/**
 * This is the solution for part 1 of day 4's problem.
 * 
 * The steps are as follows:
 * 1. Convert the two ranges into a min and a length
 * 2a. If mins match, must overlap, increment total, go to next line
 * 2b. If mins don't match, find the smaller of the two mins
 * 3. Determine difference between two mins
 * 4. Subtract difference from 3 from smaller min's length
 * 5a. If remaining length >= other range's length, full overlap, increment total, go to next line
 * 5b. If remaining length < other range's length, not full overlap, go to next line
 * 
 * It runs in O(N) runtime, meaning if there are N entries in the list,
 * we only have, in the worst case, N iterations.
 * 
 * If you ignore the cost of loading the input list, it uses O(1) storage.
 * No extra lists are created based on any potential growing sizes.
 */
public class Day4ASolution {
    private static String INPUT_NAME = "Day_4A/input.txt";
    private static String PROMPT = """
        In how many assignment pairs does one range fully contain the other?
            """;

    /// This is the class default constructor, in order for the setup to work,
    /// you MUST change its name to match the class name (DayXSolution) and make sure
    /// it takes no input arguments.
    /// You do not need to change any of the code contained within the method.
    public Day4ASolution() {
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
        int total = 0;

        // Loop over total input one line at a time
        for (String line: getInputData()) {
            Range range1 = new Range(line.split(",")[0]);
            Range range2 = new Range(line.split(",")[1]);

            // If the minimums match, must be full overlap
            if (range1.minimum == range2.minimum) {
                total++;
                continue;
            }

            // If minimum 2 is smaller, swap them so minimum 1 is always the smallest
            if (range2.minimum < range1.minimum) {
                Range temp = range1;
                range1 = range2;
                range2 = temp;
            }

            int minDiff = range2.minimum - range1.minimum;
            if (range1.length - minDiff >= range2.length) {
                total++;
            }
        }
        System.out.println("Solution for problem 4, part 1: " + total);
    }

    // Creating this internal class to handle the range string processing
    // Converts "X-Y" to a minimum and a length
    class Range {
        public Range(String inputLine) {
            // Convert the number before the '-' in an int, it is the minimum value in the range
            minimum = Integer.parseInt(inputLine.split("-")[0]);
            // Convert the number after the '-' in an int, it is the maximum value in the range
            int max = Integer.parseInt(inputLine.split("-")[1]);

            // Add 1 because if the range were 6:6, still want one value.
            length = (max - minimum) + 1;
        }

        public int minimum;
        public int length;
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