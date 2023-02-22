import java.util.List;
import java.util.Scanner;

/**
 * UserInterface provides methods for displaying information to the console and returning valid input from the user.
 */
public class UserInterface {

    private final int MAX_LINE_CHARACTER_COUNT = 152;
    private final int BRANCH_LEFT_SPACE_COUNT = 12;
    private final int BRANCH_RIGHT_SPACE_COUNT = 12;

    Scanner in = new Scanner(System.in);

    /**
     * Displays a message to the console. If the message is longer than the pre-determined limit described by
     * MAX_LINE_CHARACTER_COUNT, then the string is passed into the "display paragraph" function to be processed
     * into multiple lines.
     *
     * @param message message to be displayed to the console.
     */
    public void display(String message) {
        if (message.length() > MAX_LINE_CHARACTER_COUNT) {
            System.out.println(displayParagraph(message));
        } else {
            System.out.println(message);
        }
    }

    /**
     * Obtains the index of the choice picked by the user from the list of choices available in the provided Scene
     * object. If the user provides an invalid input, displays an error message to the console and once again prompts
     * them for an input.
     *
     * @param scene Scene containing Choices to prompt the user for
     * @return the index of the user's selected choice from the Scene's list of Choices
     */
    public int getUserChoice(Scene scene) {
        List<Choice> choices = scene.getChoices();

        while (true) {
            //Display User Choices
            displayChoices(choices);

            //Prompt for input
            System.out.print("\nPlease make your selection from the options above: ");
            String userResponse = in.nextLine();

            //See if input can be converted to integer. If not, continue to the next loop iteration and prompt again.
            if (!isValidIntegerInput(userResponse)) {
                banner();
                display("Invalid input. Please provide an integer to select your choice.\n");
                display(scene.getDescription());
                display("\nWhat do you do?\n");
                continue;
            }

            //Convert to integer and check if the integer matches one of the choices presented on screen.
            int choiceIndex = Integer.parseInt(userResponse);
            if (choiceIndex < 1 || choiceIndex > choices.size()) {
                banner();
                display("Invalid input. Please provide the number of the option you wish to pick.\n");
                display(scene.getDescription());
                display("\nWhat do you do?\n");
                continue;
            }

            //Input validated, return the **Index** of the choice by subtracting one
            return choiceIndex - 1;
        }

    }


    /**
     * Displays a prompt to the console. Requires the user to provide an input, but does not return the value.
     *
     * @param prompt message to be displayed to the console.
     */
    public void promptWithAcknowledgement(String prompt) {
        System.out.print(prompt);
        in.nextLine();
    }

    /**
     * Helper method. Displays a numbered list of Choice objects' descriptions from the provided List<Choice>, numbering
     * starting at 1.
     *
     * @param choices
     */
    private void displayChoices(List<Choice> choices) {
        int choiceCount = 1;
        for (Choice choice : choices) {
            display(String.format("(%d) %s", choiceCount++, choice.getDescription()));
        }
    }

    /**
     * Displays a line consisting of asterisks surrounded by newLine characters to the console.
     */
    public void banner() {
        display("\n**************************************************************************\n");
    }

    /**
     * Takes a String, and returns a formatted string where newLine characters break the string up into lines of no
     * more than MAX_LINE_CHARACTER_COUNT characters, and each line starts with the header String. Strings are broken
     * up in such a way that individual words are never split between lines.
     *
     * @param para the String to format as a paragraph
     * @param header the string to append to the front of each line of the paragraph
     * @return the formatted string
     */
    public String displayParagraph(String para, String header) {

        //Create new StringBuilder to append each word to
        StringBuilder output = new StringBuilder();
        //Split string on each space to get an array of the individual words
        String[] words = para.split("(?<!\\s)(\\s)(?!\\s)"); //Only split on SINGLE spaces

        //Counter variable of the number of characters in the current line
        int lineCount = header.length();
        output.append(header);

        for (String word : words) {

            //If a newline already exists in the word, and adding the contents of this word does not result in a line
            //longer than MAX_LINE_CHARACTER_COUNT, add the character and move to the next word
            if (word.contains("\n") && word.length() + lineCount < MAX_LINE_CHARACTER_COUNT - header.length()) {
                output.append(word);
                output.append(header);
                lineCount = header.length();

            } else  {

                //If adding this word would result in the line being longer than MAX_LINE_CHARACTER_COUNT, add a newline
                //character to the output and reset lineCount to 0.
                if (word.length() + lineCount > MAX_LINE_CHARACTER_COUNT - header.length()) {
                    output.append("\n");
                    output.append(header);
                    lineCount = header.length();
                }

                output.append(word);
                output.append(" ");
                lineCount += word.length() + 1;
            }

        }

        return output.toString();
    }

    /**
     * Takes a String, and returns a formatted string where newLine characters break the string up into lines of no
     * more than MAX_LINE_CHARACTER_COUNT characters. Strings are broken up in such a way that individual words are
     * never split between lines.
     *
     * @param para the String to format as a paragraph
     * @return the formatted string
     */
    public String displayParagraph(String para) {
        return this.displayParagraph(para, "");
    }

    private String padString(int leftPadding, int rightPadding, String str) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < leftPadding; i++) {
            output.append(" ");
        }
        output.append(str);
        for (int i = 0; i < rightPadding; i++) {
            output.append(" ");
        }
        return output.toString();
    }



    /**
     * Prints an arrow on the left side of the console, and two strings on the right. The header string is printed above
     * the content string passed in. The header is surrounded by square brackets.
     *
     * @return the formatted string
     */
    public void printBranch(String header, String content) {

        String pipe = padString(BRANCH_LEFT_SPACE_COUNT, BRANCH_RIGHT_SPACE_COUNT, "||");

        display(pipe);
        display(pipe);
        display(pipe + "[" + header + "]");
        System.out.println(displayParagraph(content, pipe));
        display(padString(BRANCH_LEFT_SPACE_COUNT - 1, BRANCH_RIGHT_SPACE_COUNT - 1, "\\  /"));
        display(padString(BRANCH_LEFT_SPACE_COUNT, BRANCH_RIGHT_SPACE_COUNT, "\\/"));

    }


    /*
    #####################################  Validation Methods  #######################################
     */

    /**
     * Tests to see if String can be converted by Integer.parseInt()
     *
     * @param input String to test
     * @return boolean indicating if the String can be parsed to an Integer
     */
    private static boolean isValidIntegerInput(String input) {
        //	Tries to convert the provided string to an integer value. If an invalid input is provided, catches the exception and returns false.
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }



}
