/**
 * Interface qui permet d'implementer les differents graph du projet
 */
public interface Graph {
    /**
     * Methode pour obtenir la matrice du graph qui represente arcs du
     * graph
     * @return un tableau bi-dimensionnel d'entier qui represente la matrice
     */
    public int[][] getGraphMatrice();

    /**
     * Methode pour afficher la matrice du graph dans la console afin de
     * permettre a l'utilisateur de la visualiser.
     */
    public void printGraph();
}
