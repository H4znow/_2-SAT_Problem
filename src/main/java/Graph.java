/**
 * Interface qui permet d'implémenter les différents graphes du projet.
 */
public interface Graph {
    /**
     * Methode pour obtenir la matrice de graph qui represente les arcs du graph.
     * @return un tableau bidimensionnel d'entiers qui represente la matrice.
     */
    public int[][] getGraphMatrice();

    /**
     * Permet de retourner le litteral de la i-eme ligne/colonne.
     * La matrice est anti-symetrique. Ainsi, la lettre de la ligne i est la meme
     * que celle de la colonne i.
     * @param i le numero de la ligne/colonne dont on souhaite retourner le nom (le litteral).
     * @return le nom du litteral represente par la ieme ligne ou colonne.
     */
    public String nomCase(int i);
}