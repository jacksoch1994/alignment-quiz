import java.util.ArrayList;
import java.util.List;

public class Scene {

    /*
    ########################################   Attributes   ##########################################
     */

    private String description = "";

    private List<Choice> choices = new ArrayList<>();
    private List<Scene> childScenes = new ArrayList<>();

    boolean isScenarioStart;


    /*
    ######################################## Getter Methods ##########################################
     */

    public String getDescription() {
        return description;
    }

    public List<Choice> getChoices() {
        return choices;
    }


    /*
    ########################################  Other Methods  ##########################################
     */

    public void addChoice(Choice choice) {
        choices.add(choice);
    }
    public void addChildScene(Scene scene) {
        this.childScenes.add(scene);
    }

    //Returns score from the choice
    public Choice getChoice(int choiceIndex) {
        return choices.get(choiceIndex);
    }

    public Scene nextScene(int choiceIndex) {
        return childScenes.get(choiceIndex);
    }

    /*
    ########################################   Constructor   ##########################################
     */


    public Scene(String description, boolean isStartingScenario) {
        this.isScenarioStart = isStartingScenario;
        this.description = description;
    }

    public Scene(String description) {
        this(description, false);
    }



}
