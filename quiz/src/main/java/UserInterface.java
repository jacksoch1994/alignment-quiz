import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private final int MAX_LINE_CHARACTER_COUNT = 152;

    Scanner in = new Scanner(System.in);


    public void display(String message) {
        if (message.length() > MAX_LINE_CHARACTER_COUNT) {
            displayParagraph(message);
        } else {
            System.out.println(message);
        }

    }

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

    public void promptWithAcknowledgement(String prompt) {
        System.out.print(prompt);
        in.nextLine();
    }

    private void displayChoices(List<Choice> choices) {
        int choiceCount = 1;
        for (Choice choice : choices) {
            System.out.printf("(%d) %s\n", choiceCount++, choice.getDescription());
        }
    }

    public void banner() {
        System.out.println("\n**************************************************************************\n");
    }

    public void displayParagraph(String para) {
        StringBuilder output = new StringBuilder();
        String[] words = para.split(" ");

        int lineCount = output.length();

        for (String word : words) {

            if (word.length() + lineCount > MAX_LINE_CHARACTER_COUNT) {
                output.append("\n");
                lineCount = word.length() + 1;
            }

            output.append(word);
            output.append(" ");
            lineCount += word.length() + 1;

        }

        System.out.println(output);
    }


    public void printBranch(String leftDesc, String rightDesc) {
        StringBuilder output = new StringBuilder();

        String[] words = rightDesc.split(" ");
        String leftBuffer = "           ";
        String pipe = "||            ";

        int lineCount = output.length();

        output.append(leftBuffer + pipe + "\n");
        output.append(leftBuffer + pipe + "\n");
        output.append(leftBuffer + pipe + "[" + leftDesc + "]" + "\n");
        output.append(leftBuffer + pipe);


        for (String word : words) {

            if (word.length() + lineCount > MAX_LINE_CHARACTER_COUNT - leftBuffer.length() - pipe.length()) {
                output.append("\n" + leftBuffer + pipe);
                lineCount = word.length() + 1;
            }

            output.append(word);
            output.append(" ");
            lineCount += word.length() + 1;

        }

        output.append("\n" + leftBuffer + pipe + "\n");
        output.append("          \\  / \n");
        output.append(leftBuffer + "\\/");

        System.out.println(output);

    }




    /*
    #####################################  Validation Methods  #######################################
     */

    //	Tests to see if String can be converted by Integer.parseInt()
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
