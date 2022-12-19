package adventofcode2022.Day_5B;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import adventofcode2022.AoCRunner;

/**
 * This is the solution for part 1 of day 5's problem.
 * 
 * This solution will use doubly-linked lists, meaning they can act
 * as both a queue and a stack. During phase 1, loading the initial state of the crates,
 * the lists will be used as queues, each added element will be added to the back,
 * so the items encountered first will be removed first in phase 2.
 * In phase 2, following the instructions, the lists will be used as a stack,
 * removing the front item and adding it to the front of the next list.
 * Phase 3 will just loop over all the lists and get the front item for the answer.
 * 
 * It runs in O(NM) runtime, meaning if there are N entries in the instruction list,
 * and an instruction at worst moves M objects,
 * we only have, in the worst case, N iterations.
 * 
 * If you ignore the cost of loading the input list, it uses O(C) storage,
 * where C is the total number of crates on all stacks.
 */
public class Day5BSolution {
    private static String INPUT_NAME = "Day_5A/input.txt";
    private static String PROMPT = """
        After the rearrangement procedure completes, what crate ends up on top of each stack?
        """;

    /// This is the class default constructor, in order for the setup to work,
    /// you MUST change its name to match the class name (DayXSolution) and make sure
    /// it takes no input arguments.
    /// You do not need to change any of the code contained within the method.
    public Day5BSolution() {
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
        // Phase 0: Find out how many lists there need to be
        int stackCount = 0;
        for (String line: getInputData()) {
            // The first line that doesn't contain '[' should be
            // the line that lists the container numbers.
            if (line.contains("[")) {
                continue;
            }

            String[] stacks = line.strip().split(" ");
            // Last item will be stack count
            stackCount = Integer.parseInt(stacks[stacks.length - 1]);
            break;
        }

        // Create lists for each stack
        List<LinkedList<String>> stacks = new ArrayList<LinkedList<String>>();
        for (int i = 0; i < stackCount; i++) {
            stacks.add(new LinkedList<>());
        }

        // Loop over total input one line at a time
        boolean inPhase1 = true;
        for (String line: getInputData()) {
            if (inPhase1) {
                // Empty line separates phase 1 and 2
                if (line.length() == 0) {
                    inPhase1 = false;
                    continue;
                }

                // Last line of phase 1 is just the stack numbers, do nothing
                if (!line.contains("[")) {
                    continue;
                }

                String noBrackets = line.replaceAll("\\[", "").replaceAll("\\]", "");
                // 4 spaces means missing item, so replace with " _"
                // Will end with something like A _ B _ C if there were 5 stacks
                // and stacks 2 and 4 had no item at that height.
                noBrackets = noBrackets.replaceAll("    ", " _");

                String[] stackItems = noBrackets.trim().split(" ");
                for (int i = 0; i < stackItems.length; i++) {
                    // Skip the empty stacks
                    if (stackItems[i].equals("_")) {
                        continue;
                    }

                    stacks.get(i).offer(stackItems[i]);
                }

                // Skip any phase 2 logic
                continue;
            }

            // Phase 2: Process the move "X" from "Y" to "Z" lines
            // Convert "move X from Y to Z" to "X Y Z"
            String instruction = line.replace("move ", "").replace(" from", "").replace(" to", "");
            String[] splitInstructions = instruction.split(" ");
            int count = Integer.parseInt(splitInstructions[0]);
            // -1 to get to 0-based index
            int fromStack = Integer.parseInt(splitInstructions[1]) - 1;
            int toStack = Integer.parseInt(splitInstructions[2]) - 1;

            // For X, remove one from top of Y, add to top of Z
            for (int i = 0; i < count; i++) {
                String temp = stacks.get(fromStack).pop();
                stacks.get(toStack).push(temp);
            }
        }

        String answer = "";
        for (int i = 0; i < stackCount; i++) {
            answer += stacks.get(i).pop();
        }
        System.out.println("Solution for problem 5, part 1: " + answer);
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