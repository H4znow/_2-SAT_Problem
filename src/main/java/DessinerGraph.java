import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Classe pour dessiner les graphes grace a Graphviz.
 * On ecrit le code DOT dans le fichier correspondant dans {@code resources/dot}
 * Puis on le compile et on sauvegarde le graph dans {@code resources/graph}
 */
public class DessinerGraph {

    private final Graph graph;
    //Le string contient
    private String codeDotDuGraph;
    private final int[][] matriceGraph;
    private final int matriceLength;
    private String titreGraph;
    private final String fichierDot;
    private final String fichierImage;

    public DessinerGraph(Graph graph, String titreGraph) {
        this.graph = graph;
        this.titreGraph = "label=\"" + titreGraph+ "\";\n";
        matriceGraph = graph.getGraphMatrice();
        matriceLength = graph.getGraphMatrice().length;
        codeDotDuGraph = "digraph {\n" + this.titreGraph +"\n";
        fichierDot = "src/main/resources/dot/codeGraphDOT.DOT";
        fichierImage = "imageGraph.png";
    }

    /**
     * Methode pour generer le code du graph, l'enregistrer dans fichier DOT pour enfin le compiler et ouvrir l'image
     */
    public void genererGraph(){
        creerCodeGraphe();
        genererCodeDOT();
        genererLImage();
    }

    /**
     * Methode pour creer le code graphe du Code. Elle parcours la matrice et si (i,j) = 1 alors elle creer un arc de i
     * vers j.
     * Enfin elle ajoute '}' pour fermer correctement le code DOT.
     */
    private void creerCodeGraphe() {
        int n = matriceGraph.length; // nombre de lignes
        int m = matriceGraph[0].length; // nombre de colonnes

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(matriceGraph[i][j] == 1) //Si == 1, c'est qu'il y a un arc de j vers i
                    creerUnArc(j,i); // j ici represente le numero de colonne et i le numero de ligne
            }
        }

        codeDotDuGraph += "}"; //Pour finir le code DOT
    }

    /**
     * Methode qui ajoute le code Dot pour creer un arc de {@code i} vers {@code j}
     * Elle recupere les noms de i et j grace a {@code graph.nomCase(i)} et {@code graph.nomCase(j)}
     * @param i int representant la ieme colonne de la matrice. Point du depart de l'arc
     * @param j int representant la ieme ligne de la mtrice. Fin de l'arc
     */
    private void creerUnArc(int j, int i){
        codeDotDuGraph += "\"" + graph.nomCase(j) + "\" " +" -> " + "\"" + graph.nomCase(i) + "\";" + "\n";
    }

    /**
     * Methode pour generer le code DOT dans {@link String}{@code codeDotDuGraph}.
     * Elle va permettre de sauvegarder le code dans le fichier du projet :
     * @file src/main/resources/dot/codeGraphDOT.DOT
     */
    private void genererCodeDOT(){
        try {
            File fichier = new File("src/main/resources/dot/codeGraphDOT.DOT");
            FileWriter fw = new FileWriter(fichier, false); // false pour ecraser le contenu de la classe
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(codeDotDuGraph);

            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Methode pour compiler le fichier DOT precedemment remplis en un fichier PNG.
     * L'image est contenu dans le dossier **src/main/resources/graph**
     */
    public void genererLImage() {
        String command = "dot -Tpng " + fichierDot + " -o " + fichierImage;
        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", command);
        pb.directory(new File("src/main/resources/graph"));
        pb.redirectErrorStream(true);
        try {
            Process process = pb.start();
            process.waitFor(); // attendre la fin de la compilation de l'image

            // ouvrir l'image avec le programme par défaut
            File imageFile = new File(fichierImage);
            Desktop.getDesktop().open(imageFile);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

