import java.util.ArrayList;

/**
 * La classe qui a appelle la classe {@link ComposantesFortementConnexes} pour determiner les composantes connexes
 * et puis verifier si la formule est insatisfiable ou pas.
 */
public class SAT_Solution {

    private ComposantesFortementConnexes cfc;
    private ArrayList<ComposanteConnexe> composanteConnexes;

    /**
     * Constructeur de la classe Solution
     *
     * @param graph  le graphe des implications
     * @param graphT le graphe transpose du graphe des implications
     * @param conj   la conjoncture du probleme (l'ensemble des clauses)
     */
    public SAT_Solution(Graph graph, Graph graphT, Conjonctions conj) {
        cfc = new ComposantesFortementConnexes(graph, graphT, conj);
        cfc.determinerLesComposantesConnexes();
        composanteConnexes = cfc.getComposanteConnexes();
    }

    /**
     * Verifie si l'une des clauses contient un litteral et son oppose. Si c'est le cas, alors elle retourne faux car la
     * formule est insatisfiable.
     *
     * @return {@code true} si la formule est satisfiable, {@code false} sinon.
     */
    public boolean estSatisfiable() {
        for (int i = 0; i < composanteConnexes.size(); i++) {
            if (composanteConnexes.get(i).contient_Litteral_Et_Sa_Negation()) {
                // Si une clause contient un litteral et son oppose, la formule est insatisfiable
                return false;
            }
        }
        // Si aucune clause ne contient un litteral et son oppose, la formule est satisfiable
        return true;
    }
}