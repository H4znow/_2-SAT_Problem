/**
 * Classe qui represente le graphe transpose Gt de G.
 */
public class GraphTransposee implements Graph{
    private Graph graph;
    private int[][] graphMatrice;
    private String[] litterauxDuTableau;

    /**
     * Constructeur pour initialiser les attributs.
     * @param conj La {@link Conjonctions} du probleme.
     * @param graph Le {@link GraphImplication} dont on va determiner la transposee.
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
    public String nomCase(int i) {
        return litterauxDuTableau[i];
    }

    /**
     * Creer la matrice transposee (pour inverser les arcs) a partir de la matrice de {@link GraphImplication}.
     * @param matrice La matrice {@link GraphImplication}.
     * @return Le tableau bidimensionnel representant le graphe transpose.
     */
    private int[][] creerMatriceTransposee(int[][] matrice){
        int[][] matriceTransposee = new int[matrice.length][matrice.length];

        // Initialise le tableau a 0.
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice.length; j++) {
                matriceTransposee[i][j] = 0;
            }
        }

        // Determine les arcs du graphe. Si (i,j) = 1 alors dans le nouveau graphe, on a (j,i) = 1.
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice.length; j++) {
                if (matrice[i][j] == 1)
                    matriceTransposee[j][i] = 1;
            }
        }
        return matriceTransposee;
    }
}