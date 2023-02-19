public class Alignment {

    private String name;
    private String description;

    private String outerPlane;
    private String[] deities;
    private String[] outsiders;


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getOuterPlane() {
        return outerPlane;
    }

    public String[] getDeities() {
        return deities;
    }

    public String[] getOutsiders() {
        return outsiders;
    }


    public Alignment(String name, String description, String outerPlane, String[] deities, String[] outsiders) {
        this.name = name;
        this.description = description;
        this.outerPlane = outerPlane;
        this.deities = deities;
        this.outsiders = outsiders;
    }
}
