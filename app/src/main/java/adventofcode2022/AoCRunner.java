package adventofcode2022;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This is the main runner class for the Advent of Code.
 * When you run the project (for instance the 'Play' button in VS Code)
 * this is the file that runs first.
 * 
 * For anyone following along, this class should be setup such that
 * you should not have to make any changes yourself as long as you follow this format:
 * 
 * 1. Each new AoC problem should be in its own folder named 'Day_XA' where 'X' is the day number,
 *    and 'A' is the part of that day's problem.
 *    So for example, on day 5, you create a new folder named 'Day_5A' and
 *    put all your contents for day 5 there. If day 5 happens to have multiple parts, when you
 *    complete part A, you can copy the entire 5A directory, and rename everything to 5B.
 *    That way you'll have a reference of the previous solution as well.
 *    Note: The "_" is needed here, if it is not finding your folder, ensure it has the "_"!
 * 2. When you create the new Java class for the day, create a file 'DayXASolution.java'
 *    In the 'DayXA' folder created in step 1. Again, 'X' should be the day number,
 *    and 'A' should be the part of that day's problem.
 * 3. The class 'DayXASolution' should have one public method named 'Solve' with no inputs.
 *    So your solution should be contained within:
 *    public void Soltion() {
 *      < Your solution here >
 *    }
 * 4. The class 'DayXASolution' should be contained within the appropriate package.
 *    If using 'adventofcode2022' for this class (top line of file), then it should be:
 *    package adventofcode2022.Day_XA;
 */
public class AoCRunner {
    public static Helper AoCHelper = new Helper();

    public static void main(String[] args) {
        System.out.println("\nWelcome to Advent of Code 2022 Runner!");
        String chosenProblem = getProblemNumber();
        System.out.println("Chosen: " + chosenProblem + ".\n");

        runChosenProblem(chosenProblem);
    }


    /**
     * Below this line are private helper methods used in this class to help
     * generate neat formatting and processing when you run the project.
     */
    // Looks in the current directory for all subdirectories,
    // and finds the problem numbers that exist.
    private static String[] getProblemList() {
        String[] directories = AoCHelper.listDirectoriesInWorkingDirectory();

        String[] problemList = new String[directories.length];
        for (int i = 0; i < directories.length; i++) {
            try {
                problemList[i] = directories[i].split("_")[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Warning: Directory '" + directories[i]
                    + "' does not have a '_' in its name. It will be skipped.");
            }
        }

        return problemList;
    }

    // Asks for user input until the user selects one of the available problems
    private static String getProblemNumber() {
        String[] problemList = getProblemList();
        if (problemList.length == 0) {
            System.out.println("\nThere were no solution directories found.");
            System.out.println("Follow the instructions in the README and rerun this.");
            System.exit(0);
        }

        String chosenProblem = null;
        
        Scanner userInput = new Scanner(System.in);
        while (chosenProblem == null) {
            System.out.println("Which problem do you want to solve? " + Arrays.toString(problemList));

            String input = userInput.nextLine().toUpperCase();
            if (input == null || input.length() == 0) {
                System.out.println();
                continue;
            }

            for (String problem: problemList) {
                if (input.equals(problem.toUpperCase())) {
                    chosenProblem = input;
                    break;
                }
            }

            if (chosenProblem == null) {
                System.out.println("'" + input + "' is not an option.\n");
            }
        }
        userInput.close();

        return chosenProblem;
    }

    // Runs the chosen problem, handing the various potential failures by
    // providing a helpful message to determine what went wrong.
    private static void runChosenProblem(String chosenProblem) {
        try {
            Class<?> clazz = Class.forName("adventofcode2022.Day_" + chosenProblem
                + ".Day" + chosenProblem + "Solution");
            Method method = clazz.getDeclaredMethod("Solve");
            Object classObj = clazz.getDeclaredConstructor().newInstance();
            method.invoke(classObj);
        } catch (ClassNotFoundException e) {
            System.out.println("The class 'Day" + chosenProblem + "Solution' does not exist. "
                + " It should be in 'Day_" + chosenProblem + "/Day" + chosenProblem
                + "Solution.java'");
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            System.out.println("Make sure you create a method 'public void Solve()' in class Day"
                + chosenProblem + "Solution with no input arguments.");
        } catch (SecurityException e) {
            System.out.println("Something went wrong. See below:");
            e.printStackTrace();
        } catch (InstantiationException e) {
            System.out.println("class 'Day" + chosenProblem
                + "Solution' needs a public constructor (Add: "
                + "public Day" + chosenProblem + "Solution() {} to the class).");
        } catch (IllegalAccessException e) {
            System.out.println("Make sure the Solve method is public: 'public void Solve()'"
                + " in class Day" + chosenProblem + "Solution.");
        } catch (IllegalArgumentException e) {
            System.out.println("Make sure the Solve method takes no input arguments: 'public void Solve()'"
                + " in class Day" + chosenProblem + "Solution.");
        } catch (InvocationTargetException e) {
            System.out.println("Your solution had an error.\n"
                + "The java.lang.reflect.InvocationTargetException is unrelated.\n"
                + "Look for the 'Caused by:' line to debug the error.\n");
            e.printStackTrace();
        }
    }
}
