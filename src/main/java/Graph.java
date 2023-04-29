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

    /**
     * Permet de retourner le litterale de la i-eme ligne/colonne
     * La matrice est anti-symetrique. Ainsi la lettre de la ligne i est la meme que celle de la colonne A
     * @param i le numero de la ligne/colonne dont on souhaite retourner le nom (le litteral)
     * @return le nom du litteral represente par la ieme ligne ou colonne
     */
    public String nomCase(int i);
}
