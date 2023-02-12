import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class QuizLoader {

    private final File SCENARIO_SOURCE = new File("Scenarios.txt");
    private final File CHOICE_SOURCE = new File("Choices.txt");

    private final Map<String, Scenario> scenarios = new HashMap<>();

    private Scanner scenarioReader;
    private Scanner choiceReader;


    public Map<String, Scenario> createScenes() {
        if (readScenarios()) readChoices();
        return scenarios;
    }

    private boolean readScenarios() {
        if (scenarioReader == null) return false;

        while (scenarioReader.hasNextLine()) {
            String[] line = scenarioReader.nextLine().split("\\|");

            Scenario scenario = new Scenario(line[0], Boolean.parseBoolean(line[1]), line[2]);
            scenarios.put(scenario.getIdentifier(), scenario);
        }

        scenarioReader.close();
        return true;
    }

    private boolean readChoices() {
        if (choiceReader == null) return false;

        while (choiceReader.hasNextLine()) {
            String[] line = choiceReader.nextLine().split("\\|");

            Choice choice = new Choice(line[0], line[1], line[2], Integer.parseInt(line[3]), Integer.parseInt(line[4]));
            if (scenarios.containsKey(choice.getParentScenario())) {
                scenarios.get(choice.getParentScenario()).addChoice(choice);
            }
        }
        choiceReader.close();

        return true;
    }


    public QuizLoader() {
        try  {
            scenarioReader = new Scanner(SCENARIO_SOURCE);
        } catch (FileNotFoundException e) {
            System.out.printf("%s could not be found. Cannot load file.\n", SCENARIO_SOURCE.getName());
        }

        try  {
            choiceReader = new Scanner(CHOICE_SOURCE);
        } catch (FileNotFoundException e) {
            System.out.printf("%s could not be found. Cannot load file.\n", CHOICE_SOURCE.getName());
        }
    }




}
