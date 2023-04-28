import java.io.File;
import java.io.IOException;

public class GraphImplication {

    private String[][] graphMatrice;

    GraphImplication(){

    }
    String command = "dot -Tpng input.dot -o output.png";
    ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", command);
    private void test(){
        pb.directory(new File("C:\\chemin\\vers\\le\\dossier\\du\\fichier\\"));
        pb.redirectErrorStream(true);
        try {
            Process process = pb.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }}
