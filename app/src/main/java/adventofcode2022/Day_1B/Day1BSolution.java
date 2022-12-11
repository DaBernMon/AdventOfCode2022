package adventofcode2022.Day_1B;

import java.util.List;

import adventofcode2022.AoCRunner;

/**
 * This is the solution for part 2 of day 1's problem.
 * 
 * The strategy is to loop over all the inputs keeping track of the max calories
 * we have seen so far by the top M (M=3) elves and a local max, the amount of calories
 * carried by the current elf. Once we are done processing a single elf's calories
 * we see if it exceeded the previous max of any of M elves and update the max if so.
 * 
 * It runs in O(NM) runtime, meaning if there are N entries in the list,m
 * and we want to track the top M elves, we only have,
 * in the worst case (which is every case for this solution), N*M iterations.
 * 
 * If you ignore the cost of loading the input list, it uses O(M) additional storage.
 * We need a list of M to track the top M elves. Currently a constant of 3, but if we wanted more
 * the list would grow linearly.
 * 
 * Could possibly optimize runtime further by using a PriorityQueue,
 * but want to keep things simple to start out.
 * Maybe you can figure out how it might be used?
 */
public class Day1BSolution {
    private static String INPUT_NAME = "Day_1B/input.txt";
    private static String PROMPT = """
        By the time you calculate the answer to the Elves' question,
        they've already realized that the Elf carrying the most Calories
        of food might eventually run out of snacks.

        To avoid this unacceptable situation, the Elves would instead like to know
        the total Calories carried by the top three Elves carrying the most Calories.
        That way, even if one of those Elves runs out of snacks, they still have two backups.
        
        In the example above, the top three Elves are the fourth Elf (with 24000 Calories),
        then the third Elf (with 11000 Calories), then the fifth Elf (with 10000 Calories).
        The sum of the Calories carried by these three elves is 45000.
        
        Find the top three Elves carrying the most Calories.
        How many Calories are those Elves carrying in total?
            """;

    /// This is the class default constructor, in order for the setup to work,
    /// you MUST change its name to match the class name (DayXSolution) and make sure
    /// it takes no input arguments.
    /// You do not need to change any of the code contained within the method.
    public Day1BSolution() {
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
        int M = 3; // Number of top elves to track
        int[] maxTotals = new int[M]; // Track the total max of top M elves
        int currentElf = 0; // Current elf's total calories

        // Loop over total input one line at a time
        for (String line: getInputData()) {
            // A blank line means we're done with the current elf's total
            if (line.length() == 0) {
                // Is this elf's total greater than any of the highest we've seen?
                insertElfIfHigher(maxTotals, currentElf);

                // Now starting on the next elf
                currentElf = 0;
                continue;
            }

            currentElf += Integer.parseInt(line);
        }

        // If the final line is not an empty line, the final elf's value
        // has not yet been checked. So check it here.
        insertElfIfHigher(maxTotals, currentElf);

        // Finally, add up all of the top M elves
        int total = 0;
        for (int singleElf: maxTotals) {
            total += singleElf;
        }
        System.out.println("Solution for problem 1, part 2: " + total);
    }

    private void insertElfIfHigher(int[] highestElves, int elfTotal) {
        int currentElf = elfTotal;

        // Need to loop over all top elves so the lowest will be pushed out
        for (int i = 0; i < highestElves.length; i++) {
            // If current elf is higher than current position, swap it out with that elf total
            // But need to track that elf's total for next iteration so it can pushed down
            // the chain.
            if (currentElf > highestElves[i]) {
                int temp = highestElves[i];
                highestElves[i] = currentElf;
                currentElf = temp;
            }
        }
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
