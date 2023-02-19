import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ResultsLoader {

    private final File ALIGNMENT_SOURCE = new File("AlignmentInfo.txt");
    private final Map<String, Alignment> ALIGNMENTS = new HashMap<>();

    private Scanner alignmentReader;


    public Map<String, Alignment> createResults() {
        if (loadAlignments()) {
            return ALIGNMENTS;
        }
        return new HashMap<String, Alignment>();
    }


    private boolean loadAlignments() {
        if (alignmentReader == null) return false;

        while (alignmentReader.hasNextLine()) {
            String[] line = alignmentReader.nextLine().split("--");
            String[] identificationInfo = line[0].split("\\|");

            try {
                String identifier = identificationInfo[0];

                Alignment alignment = new Alignment(identificationInfo[1],
                        identificationInfo[2],
                        identificationInfo[3],
                        line[1].split("\\|"),
                        line[2].split("\\|"));
                ALIGNMENTS.put(identifier, alignment);

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Alignment source file incorrectly formatted. Failed to add all alignments.");
                return false;
            }

        }

        alignmentReader.close();
        return true;
    }

    public ResultsLoader() {
        try {
            alignmentReader = new Scanner(ALIGNMENT_SOURCE);
        } catch (FileNotFoundException e) {
            System.out.printf("%s not found. Failed to load file.", ALIGNMENT_SOURCE.getName());
        }
    }
}
