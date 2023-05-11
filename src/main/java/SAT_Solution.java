import java.util.ArrayList;

/**
 * La classe qui a appelle la classe {@link ComposantesFortementConnexes} pour déterminer les composantes connexes
 * et puis vérifie si la formule est insatisfiable ou pas.
 */
public class SAT_Solution {

    private ComposantesFortementConnexes cfc;
    private ArrayList<ComposanteConnexe> composanteConnexes;

    /**
     * Constructeur de la classe Solution
     *
     * @param graph  le graphe des implications
     * @param graphT le graphe transposé du graphe des implications
     * @param conj   la conjoncture du problème (l'ensemble des clauses)
     */
    public SAT_Solution(Graph graph, Graph graphT, Conjonctions conj) {
        cfc = new ComposantesFortementConnexes(graph, graphT, conj);
        cfc.determinerLesComposantesConnexes();
        composanteConnexes = cfc.getComposanteConnexes();
    }

    /**
     * Vérifie si l'une des clauses contient un littéral et son opposé. Si c'est le cas, alors elle retourne faux car la
     * fonction est insatisfiable.
     *
     * @return {@code true} si l'une des clauses contient un littéral ainsi que sa négation. Autrement, elle retourne {@code false}.
     */
    public boolean estSatisfiable() {
        for (int i = 0; i < composanteConnexes.size(); i++) {
            if (composanteConnexes.get(i).contient_Litteral_Et_Sa_Negation()) {
                // Si la clause est in
                return false;
            }
        }
        return true;
    }
}