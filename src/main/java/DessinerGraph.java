import java.io.File;
import java.io.IOException;

/**
 * Classe pour dessiner les graphes grace a Graphviz
 */
public class DessinerGraph {












    String command = "dot -Tpng input.dot -o output.png";
    ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", command);
    private void test(){
        pb.directory(new File("resources/graph"));
        pb.redirectErrorStream(true);
        try {
            Process process = pb.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
