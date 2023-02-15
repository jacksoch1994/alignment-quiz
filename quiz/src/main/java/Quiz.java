import java.util.List;
import java.util.Scanner;

public class Quiz {

    private UserInterface UI = new UserInterface();

    /*
    ########################################   Attributes   ##########################################
     */

    private Scanner in = new Scanner(System.in);
    private List<Scenario> scenarios;
    private int playerLawChaosScore = 0;
    private int playerGoodEvilScore = 0;

    /*
    ##########################################    Main    ############################################
     */

    public static void main(String[] args) {
        Quiz quiz = new Quiz();
        quiz.run();
    }

    /*
    ########################################  Other Methods  ##########################################
     */


    public void run() {

        for (Scenario scenario : scenarios) {
            UI.prompt("Next scenario: \n");
            UI.prompt(scenario.getDescription() + "\n");
            UI.promptWithAcknowledgement("Press (ENTER) to continue...");
            UI.prompt("\n");

            Scene currentScene =  scenario.getStartScene();

            while (currentScene != null) {

                UI.prompt(currentScene.getDescription());
                UI.prompt("What do you do?");

                int selectedChoiceIndex = UI.getUserChoice(currentScene);
                updateScore(currentScene.getChoice(selectedChoiceIndex));
                currentScene = currentScene.nextScene(selectedChoiceIndex);
            }
        }
    }

    private void updateScore(Choice choice) {

    }



    /*
    ########################################   Constructor   ##########################################
     */

    public Quiz() {
        QuizLoader loader = new QuizLoader();
        scenarios = loader.createScenes();
    }


}
