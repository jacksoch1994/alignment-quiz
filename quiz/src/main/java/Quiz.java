import java.util.Map;
import java.util.Scanner;

public class Quiz {

    private UserInterface UI = new UserInterface();

    /*
    ########################################   Attributes   ##########################################
     */

    private Scanner in = new Scanner(System.in);
    private Map<String, Scenario> scenarios;
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

        for (String key : scenarios.keySet()) {
            Scenario scenario = scenarios.get(key);
            if (scenario.isStartingScenario) {

                System.out.println(scenario.getDescription());
                System.out.println("What do you do?\n");
                UI.getUserChoice(scenario);

            }
        }
    }





    /*
    ########################################   Constructor   ##########################################
     */

    public Quiz() {
        QuizLoader loader = new QuizLoader();
        scenarios = loader.createScenes();
    }


}
