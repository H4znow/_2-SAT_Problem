import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Classe permettant de dessiner les graphes grâce à Graphviz.
 * Le code DOT est écrit dans le fichier correspondant dans {@code resources/dot}.
 * Ensuite, il est compilé et le graphe est sauvegardé dans {@code resources/graph}.
 */
public class DessinerGraph {

    private final Graph graph;
    private String codeDotDuGraph;
    private final int[][] matriceGraph;
    private final int matriceLength;
    private String titreGraph;
    private String fichierDot;
    private String fichierImage;

    /**
     * Constructeur pour initialiser les attributs.
     * @param graph Le graphe que l'on va dessiner.
     * @param titreGraph Le titre du graphe qui apparaîtra dans la représentation graphique du graphe.
     */
    public DessinerGraph(Graph graph, String titreGraph) {
        this.graph = graph;
        this.titreGraph = "label=\"" + titreGraph+ "\";\n";
        matriceGraph = graph.getGraphMatrice();
        matriceLength = graph.getGraphMatrice().length;
        codeDotDuGraph = "digraph {\n" + this.titreGraph +"\n";

        if(titreGraph.equals("Graphe Implication")){
            fichierImage = "graphImpilication.png";
            fichierDot = "src/main/resources/dot/graphImpilication.DOT";
        }else{
            fichierImage = "graphTranspose.png";
            fichierDot = "src/main/resources/dot/graphTranspose.DOT";
        }

    }

    /**
     * Méthode pour générer le graphe.
     * On crée le code du graphe, on l'enregistre dans le fichier DOT, puis on le compile et on ouvre l'image.
     */
    public void genererGraph(){
        creerCodeGraphe();
        genererCodeDOT();
    }

    /**
     * Méthode pour créer le code du graphe.
     * On parcourt la matrice et si (i,j) = 1 alors on crée un arc de i vers j.
     * Enfin on ajoute '}' pour fermer correctement le code DOT.
     */
    private void creerCodeGraphe() {
        int n = matriceGraph.length; // nombre de lignes
        int m = matriceGraph[0].length; // nombre de colonnes

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(matriceGraph[i][j] == 1) //Si == 1, il y a un arc de j vers i.
                    creerUnArc(j, i); // j ici représente le numéro de colonne et i le numéro de ligne.
            }
        }

        codeDotDuGraph += "}"; //Pour finir le code DOT.
    }

    /**
     * Méthode qui ajoute le code DOT pour créer un arc de {@code i} vers {@code j}.
     * On récupère les noms de i et j grâce à {@code graph.nomCase(i)} et {@code graph.nomCase(j)}.
     * @param i Un entier représentant la ième colonne de la matrice, point de départ de l'arc.
     * @param j Un entier représentant la ième ligne de la matrice, fin de l'arc.
     */
    private void creerUnArc(int i, int j){
        codeDotDuGraph += "\"" + graph.nomCase(i) + "\" " +" -> " + "\"" + graph.nomCase(j) + "\";" + "\n";
    }

    /**
     * Méthode pour générer le code DOT dans {@link String}{@code codeDotDuGraph}.
     * Elle permet de sauvegarder le code dans le fichier du projet :
     * @file src/main/resources/dot/graphTranspose.DOT
     */
    private void genererCodeDOT(){
        try {
            File fichier = new File(fichierDot);
            FileWriter fw = new FileWriter(fichier, false); // false pour écraser le contenu de la classe.
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(codeDotDuGraph);

            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode pour compiler le fichier DOT rempli en un fichier PNG.
     * L'image est contenue dans le dossier **src/main/resources/graph**.
     */
    public void genererLImage() {
        String chemin = fichierDot.substring(fichierDot.indexOf("dot/"));
        String command = "dot -Tpng ../" + chemin + " -o " + fichierImage;
        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", command);
        pb.directory(new File("src/main/resources/graph"));
        pb.redirectErrorStream(true);
        try {
            Process process = pb.start();
            process.waitFor(); // attendre la fin de la compilation de l'image.

            // ouvrir l'image avec le programme par défaut.
            File imageFile = new File("src/main/resources/graph/"+fichierImage);
            Desktop.getDesktop().open(imageFile);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}