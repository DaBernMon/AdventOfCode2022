package adventofcode2022;

import java.util.List;

/**
 * This is a template for the DayXSolution class.
 * In these instructions, X will be used as an example day.
 * If you are working on day 5, for example, replace all of the 'X' in this example
 * With the value 5. So Day5Solution class, Day_5 directory, etc.
 * 
 * When you are going to create a solution for Day X:
 * 1. Create a directory named Day_X
 * 2. Copy this file, move it under the Day_X directory).
 *    Note: Must contain the '_'
 * 3. Rename the copied file to DayXSolution.java
 * 4. Rename the class below, change:
 *    From: public class Template {
 *    To: public class DayXSolution {
 * 5. Rename constructor below, change:
 *    From: public Template() {
 *    To: public DayXSolution() {
 * 6. Create a file under Day_X for the input from the prompt.
 * 7. On the Advent of Code page, click the input, copy it all (CTRL + A, then CTRL + C),
 *    and paste it into the file from step 6.
 * 8. Whatever you named the file in steps 6 and 7, put its name (including the file extention)
 *    in INPUT_NAME below. So, if you created a file named 'exampleName.txt', change:
 *    From: private static String INPUT_NAME = null;
 *    To: private static String INPUT_NAME = "exampleName.txt";
 * 9. (Optional) Copy Day X's prompt in between the two sets of """ after PROMPT
 *    So:
 *    private static String PROMPT = """
            <Put the prompt for Day X here below>
            """;
 * 10. (Optional) In the copied file, delete all of these instructions and maybe replace them
 *     with a summary of how you are going to solve the problem.
 */
public class Template {
    /// This will store the name of the input file, if there was one for the problem of the day.
    /// You can name the input file you create whatever you want, just make sure this is set
    /// to EXACTLY match the file name (including the file extentions, like '.txt').
    private static String INPUT_NAME = null;

    /// If you want, change the text here to match the instructions from Advent of Code
    /// and then it will print out when you run the file!
    private static String PROMPT = """
            Put the prompt for this here.
            """;

    /// This is the class default constructor, in order for the setup to work,
    /// you MUST change its name to match the class name (DayXSolution) and make sure
    /// it takes no input arguments.
    /// You do not need to change any of the code contained within the method.
    public Template() {
        if (INPUT_NAME == null) {
            System.out.println("Warning: INPUT_NAME has not been set for '"
                + this.getClass().getName()
                + "'' redo Step 8 and try again.");
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
        System.out.println("Your Solution here.");
    }


    ///
    /// The methods below this line are helper methods provided for you.
    /// If you want to use one of them, simply call the method within Solve.
    ///
    protected List<String> getInputData() {
        return AoCRunner.AoCHelper.convertFileToStringList(INPUT_NAME);
    }
}
