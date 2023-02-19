import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Quiz {

    private UserInterface UI = new UserInterface();
    private int MIN_ALIGNMENT_VAL = 1;

    /*
    ########################################   Attributes   ##########################################
     */

    private Scanner in = new Scanner(System.in);
    private List<Scenario> scenarios;
    private Map<String, Alignment> alignments;
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
        if (scenarios == null) {
            System.exit(1);
        }
        for (Scenario scenario : scenarios) {
            runScenario(scenario);
        }

        results();

    }

    private void updateScore(Choice choice) {
        this.playerLawChaosScore += choice.getLawChaosModifier();
        this.playerGoodEvilScore += choice.getGoodEvilModifier();
    }

    private void runScenario(Scenario scenario) {
        UI.display("Next scenario: \n");
        UI.display(scenario.getDescription() + "\n");
        UI.promptWithAcknowledgement("Press (ENTER) to continue...");

        Scene currentScene =  scenario.getStartScene();

        while (currentScene != null) {

            UI.banner();

            UI.display(currentScene.getDescription());
            UI.display("\nWhat do you do?\n");

            int selectedChoiceIndex = UI.getUserChoice(currentScene);
            updateScore(currentScene.getChoice(selectedChoiceIndex));
            currentScene = currentScene.nextScene(selectedChoiceIndex);
        }
    }

    //Check results and return appropriate alignment object
    private Alignment getAlignment() {
        StringBuilder alignmentID = new StringBuilder();

        //Law-Chaos Prefix
        if (playerLawChaosScore <= -1 * MIN_ALIGNMENT_VAL) {
            alignmentID.append("C");
        } else if (playerLawChaosScore >= MIN_ALIGNMENT_VAL) {
            alignmentID.append("L");
        } else {
            alignmentID.append("N");
        }

        //Good-Evil Suffix
        if (playerGoodEvilScore <= -1 * MIN_ALIGNMENT_VAL) {
            alignmentID.append("E");
        } else if (playerGoodEvilScore >= MIN_ALIGNMENT_VAL) {
            alignmentID.append("G");
        } else {
            if (!alignmentID.toString().equals("N")) alignmentID.append("N");
        }

        return alignments.get(alignmentID.toString());

    }

    private void results() {
        if (alignments.isEmpty()) {
            UI.display("An error has occurred. Final results unavailable.");
        }

        UI.banner();

        Alignment result = getAlignment();
        UI.display("Final Results...\n");
        UI.display(String.format("Your alignment is: %s.\n", result.getName().toUpperCase()));

        UI.display("Description: ");
        UI.displayParagraph(result.getDescription() + "\n");

        UI.display(String.format("The Outer Plane (i.e. Afterlife) for characters of your alignment is: \n-- %s\n", result.getOuterPlane()));
        UI.display("Extraplanar beings of this alignment you might find in the Outer Planes: ");
        for (String outsider : result.getOutsiders()) {
            UI.display("-- " + outsider);
        }

        UI.display("");
        UI.display("Gods of Golarion that characters of your alignment might worship: ");
        for (String deity : result.getDeities()) {
            UI.display("-- " + deity);
        }

        UI.banner();

        UI.display("THANKS FOR PLAYING!!!!");

    }



    /*
    ########################################   Constructor   ##########################################
     */

    public Quiz() {
        QuizLoader loader = new QuizLoader();
        scenarios = loader.createScenes();
        ResultsLoader resultsLoader = new ResultsLoader();
        alignments = resultsLoader.createResults();
    }


}
