import java.util.List;
import java.util.Scanner;

public class UserInterface {

    Scanner in = new Scanner(System.in);


    public void display(String message) {
        System.out.println(message);
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
                display("\nInvalid input. Please provide an integer to select your choice.\n");
                continue;
            }

            //Convert to integer and check if the integer matches one of the choices presented on screen.
            int choiceIndex = Integer.parseInt(userResponse);
            if (choiceIndex < 1 || choiceIndex > choices.size()) {
                display("\nInvalid input. Please provide the number of the option you wish to pick.\n");
                continue;
            }

            //Input validated, return the **Index** of the choice by subtracting one
            return choiceIndex - 1;
        }

    }

    public void prompt(String prompt) {
        System.out.println(prompt);
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
