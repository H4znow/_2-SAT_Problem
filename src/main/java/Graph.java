/**
 * Interface qui permet d'implémenter les différents graphes du projet.
 */
public interface Graph {
    /**
     * Méthode pour obtenir la matrice de graph qui représente les arcs du graph.
     * @return un tableau bidimensionnel d'entiers qui représente la matrice.
     */
    public int[][] getGraphMatrice();

    /**
     * Méthode pour afficher la matrice de graph dans la console afin de permettre
     * à l'utilisateur de la visualiser.
     */
    public void printGraph();

    /**
     * Permet de retourner le littéral de la i-ème ligne/colonne.
     * La matrice est anti-symétrique. Ainsi, la lettre de la ligne i est la même
     * que celle de la colonne i.
     * @param i le numéro de la ligne/colonne dont on souhaite retourner le nom (le littéral).
     * @return le nom du littéral représenté par la ième ligne ou colonne.
     */
    public String nomCase(int i);
}