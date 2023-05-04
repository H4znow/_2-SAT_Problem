import java.util.ArrayList;

/**
 * Une classe pour effectuer le parcours en profondeur d'un graphe.
 */
public class ParcoursEnProfondeurGraphe implements ParcoursEnProfondeur {

    // Les conjonctions à utiliser
    private Conjonctions conj;
    // La matrice représentant le graphe
    private final int[][] matriceGraph;
    // Tableau qui permet de stocker les sommets déjà parcourus durant l'algorithme
    private ArrayList<Integer> parcouru;
    // Tableau qui permet de déterminer l'ordre de sortie du programme
    private ArrayList<Integer> ordreDeSortie;
    // Tableau qui permet de connaître les chemins parcourus à la fin de l'algo
    int[] predecesseur;

    /**
     * Constructeur de la classe.
     * @param graph le graphe à parcourir
     * @param conj les conjonctions à utiliser
     * @param i le sommet initial à partir duquel on démarre le parcours
     */
    public ParcoursEnProfondeurGraphe(Graph graph, Conjonctions conj, int i) {
        this.conj = conj;
        matriceGraph = graph.getGraphMatrice();
        parcouru = new ArrayList<>();
        predecesseur = new int[2 * conj.getNombreLitterauxTotaux()];
        for (int j = 0; j < predecesseur.length; j++) {
            predecesseur[j] = -1;
        }
        ordreDeSortie = new ArrayList<>();
        parcoursEnProfondeur(i);
    }

    /**
     * Implémentation de la méthode parcoursEnProfondeur de l'interface ParcoursEnProfondeur.
     * @param i le sommet initial à partir duquel on démarre le parcours
     */
    @Override
    public void parcoursEnProfondeur(int i) {
        int n = 2 * conj.getNombreLitterauxTotaux(); // max de sommets dans l'algo

        // Parcours depuis le sommet i choisi
        parcouru.add(i);
        for (int k = 0; k < n; k++) { // Parcours tous les arcs sortants de i
            if (matriceGraph[k][i] == 1) { // Si (i,k) est un arc alors
                explorer(i, k);
            }
        }
        // Quand on a fini de parcourir tous les sommets sortants du sommet initial,
        // on l'ajoute dans l'ordre de sortie
        ordreDeSortie.add(i);
        // La partie itérée de l'algo, on parcourt les autres sommets
        for (int j = 0; j < n; j++) { // On parcourt tous les littéraux un par un
            if (!parcouru.contains(j)) {
                parcouru.add(j);
                for (int k = 0; k < n; k++) { // Parcours tous les arcs sortants de j
                    if (matriceGraph[k][j] == 1) { // Si (j,k) est un arc alors
                        explorer(j, k);
                    }
                }
                // On a fini d'explorer les sommets sortants de j, alors on l'ajoute dans l'ordre de sortie
                ordreDeSortie.add(j);
            }
        }
    }

    /**
     * Implémentation de la méthode explorer de l'interface ParcoursEnProfondeur.
     * @param i le sommet actuel
     * @param j le sommet suivant à explorer
     */
    @Override
    public void explorer(int i, int j) {
        int v = j;
        if (!parcouru.contains(v)) {
            parcouru.add(v);
            predecesseur[v] = i; // le sommet v est donc précédé du sommet i dans le parcours
            for (int k = 0; k < matriceGraph.length; k++) { // Parcours tous les arcs sortants de v
                if (matriceGraph[k][v] == 1) { // Si (v,k) est un arc alors
                    explorer(v, k);
                }
            }
            // Ajoute v dans l'ordre des sorties car il a été entièrement exploré
            ordreDeSortie.add(v);
        }
    }

    /**
     * Méthode pour afficher le tableau de parcours enprofondeur.
     */
    @Override
    public void printParcoursEnProfondeur() {
        System.out.println("---- TABLEAU PARCOURS EN PROFONDEUR ----");

        for (int i = 0; i < predecesseur.length; i++) {
            if (i >= conj.getNombreLitterauxTotaux())
                System.out.print("!" + (char) ('a' + i - conj.getNombreLitterauxTotaux()) + " |");
            else
                System.out.print((char) ('a' + i) + "  |");
        }
        System.out.println();
        System.out.print(predecesseur[0] + " |");
        for (int i = 1; i < predecesseur.length; i++) {
            System.out.print(predecesseur[i] + "  |");
        }
        System.out.println();
    }
}