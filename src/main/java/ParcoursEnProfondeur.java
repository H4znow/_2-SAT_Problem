public interface ParcoursEnProfondeur {
    /**
     * Algorithme du parcours en profondeur itérée récursive pour calculer les composantes connexes.
     * @param i Le sommet de départ du parcours.
     */
    public void parcoursEnProfondeur(int i);

    /**
     * Explore l'arc (i,j) et détermine s'il a été parcouru ou pas. S'il ne l'a pas été, il explore ensuite les
     * arcs sortants de j, etc.
     * Fonction utilisée dans le parcours en profondeur.
     * @param i Le sommet de départ.
     * @param j Le sommet de destination.
     */
    public void explorer(int i, int j);

    /**
     * Dessine le tableau prédécesseur dans le terminal.
     */
    public void printParcoursEnProfondeur();
}