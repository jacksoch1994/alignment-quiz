public class Scenario {

    private Scene startScene;
    private String description;

    public Scene getStartScene() {
        return startScene;
    }

    public String getDescription() {
        return description;
    }


    public Scenario(String description, Scene startScene) {
        this.description = description;
        this.startScene = startScene;
    }
}
