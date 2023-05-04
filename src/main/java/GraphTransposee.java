/**
 * Classe qui représente le graphe transpose Gt de G.
 */
public class GraphTransposee implements Graph{
    private Graph graph;
    private int[][] graphMatrice;
    private String[] litterauxDuTableau;

    /**
     * Constructeur pour initialiser les attributs.
     * @param conj La {@link Conjonctions} du problème.
     * @param graph Le {@link GraphImplication} dont on va déterminer la transposee.
     */
    public GraphTransposee(Conjonctions conj, Graph graph){
        this.graph = graph;
        graphMatrice = creerMatriceTransposee(graph.getGraphMatrice());
        litterauxDuTableau = new String[2*conj.getNombreLitterauxTotaux()];
        for (int i = 0; i < litterauxDuTableau.length; i++) {
            if(i < conj.getNombreLitterauxTotaux())
                litterauxDuTableau[i] = " " + (char) ('a'+i);
            else
                litterauxDuTableau[i] = "!" + (char) ('a'+i-litterauxDuTableau.length/2);
        }
    }

    @Override
    public int[][] getGraphMatrice() {
        return graphMatrice;
    }

    @Override
    public void printGraph() {
        System.out.println("---- MATRICE GRAPH TRANSPOSEE ----");
        // Dessine la première partie de la 1ère ligne pour indiquer les littéraux positifs.
        System.out.print("___|");
        for (int i = 0; i < litterauxDuTableau.length; i++) {
            System.out.print(litterauxDuTableau[i] + " |");
        }
        System.out.println();

        // Parcours du tableau à l'aide de deux boucles for.
        for (int i = 0; i < graphMatrice.length; i++) {
            for (int j = 0; j < graphMatrice[i].length; j++) {
                if (j == 0){
                    // Dessine la première partie de la 1ère colonne pour indiquer les littéraux positifs.
                    System.out.print(litterauxDuTableau[i] + " |");
                }
                System.out.print(" " + graphMatrice[i][j] + " |");
            }
            System.out.println(); // Saut de ligne pour passer à la ligne suivante du tableau.
        }
        System.out.println();
        System.out.println();
        // Pour la mise en page.
    }

    @Override
    public String nomCase(int i) {
        return litterauxDuTableau[i];
    }

    /**
     * Créer la matrice transposee (pour inverser les arcs) à partir de la matrice de {@link GraphImplication}.
     * @param matrice La matrice {@link GraphImplication}.
     * @return Le tableau bidimensionnel représentant le graphe transpose.
     */
    private int[][] creerMatriceTransposee(int[][] matrice){
        int[][] matriceTransposee = new int[matrice.length][matrice.length];

        // Initialise le tableau à 0.
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice.length; j++) {
                matriceTransposee[i][j] = 0;
            }
        }

        // Détermine les arcs du graphe. Si (i,j) = 1 alors dans le nouveau graphe, on a (j,i) = 1.
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice.length; j++) {
                if (matrice[i][j] == 1)
                    matriceTransposee[j][i] = 1;
            }
        }
        return matriceTransposee;
    }
}