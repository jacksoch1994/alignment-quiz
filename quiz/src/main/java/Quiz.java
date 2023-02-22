import java.util.*;

public class Quiz {

    private UserInterface UI = new UserInterface();
    private int MIN_ALIGNMENT_VAL = 1;

    /*
    ########################################   Attributes   ##########################################
     */

    private Scanner in = new Scanner(System.in);
    private List<Scenario> scenarios;
    private Map<String, Alignment> alignments;
    private Queue<Integer> choiceHistory = new LinkedList<>();
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
        UI.display(scenario.getDescription());
        UI.promptWithAcknowledgement("\nPress (ENTER) to continue...");

        Scene currentScene =  scenario.getStartScene();

        while (currentScene != null) {

            UI.banner();

            //End of Scenario if current scene has no choices
            if (currentScene.getChoices().size() == 0) {
                UI.display("SCENARIO END\n");
                UI.display(currentScene.getDescription());
                UI.promptWithAcknowledgement("\nPress (ENTER) to continue...");
                return;
            }

            UI.display(currentScene.getDescription());
            UI.display("\nWhat do you do?\n");

            //If scene has no choices, end of scenario.


            int selectedChoiceIndex = UI.getUserChoice(currentScene);
            updateScore(currentScene.getChoice(selectedChoiceIndex));
            currentScene = currentScene.nextScene(selectedChoiceIndex);
            choiceHistory.add(selectedChoiceIndex);
        }
    }

    //Check results and return appropriate alignment object
    private Alignment getAlignment(int lawChaos, int goodEvil) {
        StringBuilder alignmentID = new StringBuilder();

        //Law-Chaos Prefix
        if (lawChaos <= -1 * MIN_ALIGNMENT_VAL) {
            alignmentID.append("C");
        } else if (lawChaos >= MIN_ALIGNMENT_VAL) {
            alignmentID.append("L");
        } else {
            alignmentID.append("N");
        }

        //Good-Evil Suffix
        if (goodEvil <= -1 * MIN_ALIGNMENT_VAL) {
            alignmentID.append("E");
        } else if (goodEvil >= MIN_ALIGNMENT_VAL) {
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

        Alignment result = getAlignment(playerLawChaosScore, playerGoodEvilScore);
        UI.display("Final Results...\n");
        UI.display(String.format("Your alignment is: %s.\n", result.getName().toUpperCase()));

        UI.display("Description: ");
        UI.display(result.getDescription());

        UI.display(String.format("\nThe Outer Plane (i.e. Afterlife) for characters of your alignment is: \n-- %s\n", result.getOuterPlane()));
        UI.display("Extraplanar beings of this alignment you might find in the Outer Planes: ");
        for (String outsider : result.getOutsiders()) {
            UI.display("-- " + outsider);
        }

        UI.display("");
        UI.display("Gods of Golarion that characters of your alignment might worship: ");
        for (String deity : result.getDeities()) {
            UI.display("-- " + deity);
        }

        UI.promptWithAcknowledgement("\nPress (ENTER) to continue...");

        UI.banner();


        displayChoiceTree();

        UI.display("THANKS FOR PLAYING!!!!");

    }

    private void displayChoiceTree() {

        for (Scenario scenario : scenarios) {
            UI.display("CHOICE HISTORY FOR SCENARIO: \n");
            UI.display(scenario.getDescription() + " \n");

            Scene currentScene = scenario.getStartScene();
            do {

                int choiceIndex = choiceHistory.remove();
                Choice choice = currentScene.getChoice(choiceIndex);
                Alignment choiceAlignment = getAlignment(choice.getLawChaosModifier(), choice.getGoodEvilModifier());

                UI.display(currentScene.getDescription());
                UI.printBranch(choiceAlignment.getName(), choice.getDescription());

                currentScene = currentScene.nextScene(choiceIndex);
            } while (currentScene.getChoices().size() != 0);

            UI.display(currentScene.getDescription());

            UI.promptWithAcknowledgement("\nPress (ENTER) to continue...");

        }
        
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
