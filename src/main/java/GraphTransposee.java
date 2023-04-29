public class GraphTransposee implements Graph{
    private Graph graph;
    private int[][] graphMatrice;
    private String[] litterauxDuTableau;
    public GraphTransposee(Conjonctions conj, Graph graph){
        this.graph = graph;
        graphMatrice = creerMatriceTransposee(graph.getGraphMatrice());
        litterauxDuTableau = new String[2*conj.getNombreLitterauxTotaux()];
        for (int i = 0; i < litterauxDuTableau.length; i++) {
            if(i < conj.getNombreLitterauxTotaux())
                litterauxDuTableau[i] = " " + (char) ('x'+i);
            else
                litterauxDuTableau[i] = "!" + (char) ('x'+i-litterauxDuTableau.length/2);
        }
    }
    @Override
    public int[][] getGraphMatrice() {
        return graphMatrice;
    }

    @Override
    public void printGraph() {
        System.out.println("---- MATRICE GRAPH TRANSPOSEE ----");
        //dessiner la premiere partie de la 1ere ligne pour indiquer les litteraux positifs
        System.out.print("___|");
        for (int i = 0; i < litterauxDuTableau.length; i++) {
            System.out.print(litterauxDuTableau[i] + " |");
        }
        System.out.println();

        // parcours du tableau à l'aide de deux boucles for
        for (int i = 0; i < graphMatrice.length; i++) {
            for (int j = 0; j < graphMatrice[i].length; j++) {
                if (j == 0){
                    //dessiner la premiere partie de la 1ere colonne pour indiquer les litteraux positifs
                    System.out.print(litterauxDuTableau[i] + " |");
                }
                System.out.print(" " + graphMatrice[i][j] + " |");
            }
            System.out.println(); // saut de ligne pour passer à la ligne suivante du tableau
        }
    }

    @Override
    public String nomCase(int i) {
        return litterauxDuTableau[i];
    }

    /**
     * Creer la matrice transposee (pour inverser les arcs) a partir de la matrice de {@link GraphImplication}
     * @param matrice la matrice {@link GraphImplication}
     * @return le tableau bidimensionnel representant le graph transposee
     */
    private int[][] creerMatriceTransposee(int[][] matrice){
        int[][] matriceTransposee = new int[matrice.length][matrice.length];

        //Initialise le tableau a 0
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice.length; j++) {
                matriceTransposee[i][j] = 0;
            }
        }

        //Determine les arcs du graph. Si (i,j) = 1 alors dans le nouveau graph, on a (j,i) = 1
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice.length; j++) {
                if (matrice[i][j] == 1)
                    matriceTransposee[j][i] = 1;
            }
        }
        return matriceTransposee;
    }
}
