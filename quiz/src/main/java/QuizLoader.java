import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class QuizLoader {

    private final File SCENE_SOURCE = new File("Scenes.txt");
    private final File CHOICE_SOURCE = new File("Choices.txt");
    private final File SCENARIO_SOURCE = new File("Scenarios.txt");


    private final Map<String, Scene> scenes = new HashMap<>();
    private final List<Scenario> scenarios = new ArrayList<>();

    private Scanner sceneReader;
    private Scanner choiceReader;
    private Scanner scenarioReader;


    public List<Scenario> createScenes() {
        if (readScenes() && readChoices() && readScenarios()) {
            return scenarios;
        };
        return null;
    }

    private boolean readScenes() {
        if (sceneReader == null) return false;

        while (sceneReader.hasNextLine()) {
            String[] line = sceneReader.nextLine().split("\\|");



            try {
                String identifier = line[0];

                Scene newScene = new Scene(line[1], Boolean.parseBoolean(line[2]));
                scenes.put(identifier, newScene);

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Scene source file incorrectly formatted. Failed to add all scenes.");
                return false;
            }

        }

        sceneReader.close();
        return true;
    }

    private boolean readChoices() {
        if (choiceReader == null) return false;

        while (choiceReader.hasNextLine()) {
            String[] line = choiceReader.nextLine().split("\\|");
            try {
                String parentScene = line[0];
                String nextScene = line[1];

                Choice choice = new Choice(line[2], Integer.parseInt(line[3]), Integer.parseInt(line[4]));

                if (scenes.containsKey(parentScene)) {
                    Scene parent = scenes.get(parentScene);
                    parent.addChoice(choice);

                    if (scenes.containsKey(nextScene)) {
                        parent.addChildScene(scenes.get(nextScene));
                    } else {
                        parent.addChildScene(null);
                    }

                }

            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Choice source file incorrectly formatted. Failed to add all choices.");
                return false;
            }

        }
        choiceReader.close();

        return true;
    }

    private boolean readScenarios() {
        if (scenarioReader == null) return false;

        while (scenarioReader.hasNextLine()) {
            String[] line = scenarioReader.nextLine().split("\\|");
            try {
                String startingSceneID = line[0];
                String description = line[1];

                if (scenes.containsKey(startingSceneID)) {
                    Scenario scenario = new Scenario(description, scenes.get(startingSceneID));
                    scenarios.add(scenario);
                }


            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Scenario source file incorrectly formatted. Failed to add all scenarios.");
                return false;
            }

        }
        scenarioReader.close();

        return true;
    }


    public QuizLoader() {
        try {
            sceneReader = new Scanner(SCENE_SOURCE);
        } catch (FileNotFoundException e) {
            System.out.printf("%s could not be found. Cannot load file.\n", SCENE_SOURCE.getName());
        }

        try {
            choiceReader = new Scanner(CHOICE_SOURCE);
        } catch (FileNotFoundException e) {
            System.out.printf("%s could not be found. Cannot load file.\n", CHOICE_SOURCE.getName());
        }

        try {
            scenarioReader = new Scanner(SCENARIO_SOURCE);
        } catch (FileNotFoundException e) {
            System.out.printf("%s could not be found. Cannot load file.\n", SCENARIO_SOURCE.getName());
        }
    }


}
