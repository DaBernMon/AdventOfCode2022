package adventofcode2022.Day_1A;

import java.util.List;

import adventofcode2022.AoCRunner;

/**
 * This is the solution for part 1 of day 1's problem.
 * 
 * The strategy is to loop over all the inputs keeping track of the max calories
 * we have seen so far by a single elf and the amount of calories
 * carried by the current elf. Once we are done processing a single elf's calories
 * we see if it exceeded the previous max and update the max if so.
 * 
 * It runs in O(N) runtime, meaning if there are N entries in the list, we only have,
 * in the worst case (which is every case for this solution), N iterations.
 * 
 * If you ignore the cost of loading the input list, it uses O(1) or constant additional storage.
 * This is because there is no storage used that grows based on the size of the calorie list.
 */
public class Day1ASolution {
    private static String INPUT_NAME = "Day_1A/input.txt";
    private static String PROMPT = """
        This list represents the Calories of the food carried by five Elves:

        The first Elf is carrying food with 1000, 2000, and 3000 Calories, a total of 6000 Calories.
        The second Elf is carrying one food item with 4000 Calories.
        The third Elf is carrying food with 5000 and 6000 Calories, a total of 11000 Calories.
        The fourth Elf is carrying food with 7000, 8000, and 9000 Calories, a total of 24000 Calories.
        The fifth Elf is carrying one food item with 10000 Calories.
        In case the Elves get hungry and need extra snacks, they need to know which Elf to ask: they'd like to know how many Calories are being carried by the Elf carrying the most Calories. In the example above, this is 24000 (carried by the fourth Elf).
        
        Find the Elf carrying the most Calories. How many total Calories is that Elf carrying?
            """;

    /// This is the class default constructor, in order for the setup to work,
    /// you MUST change its name to match the class name (DayXSolution) and make sure
    /// it takes no input arguments.
    /// You do not need to change any of the code contained within the method.
    public Day1ASolution() {
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
        int max = -1; // Track the total max
        int currentElf = 0; // Current elf's total calories

        // Loop over total input one line at a time
        for (String line: getInputData()) {
            // A blank line means we're done with the current elf's total
            if (line.length() == 0) {
                // Is this elf's total greater than the highest we've seen?
                if (max < currentElf) {
                    max = currentElf;
                }

                // Now starting on the next elf
                currentElf = 0;
                continue;
            }

            currentElf += Integer.parseInt(line);
        }

        // If the final line is not an empty line, the final elf's value
        // has not yet been checked. So check it here.
        if (max < currentElf) {
            max = currentElf;
        }
        System.out.println("Solution for problem 1, part 1: " + max);
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
