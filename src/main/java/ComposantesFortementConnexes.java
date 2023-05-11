import java.util.ArrayList;
import java.util.Random;

/**
 * Classe pour déterminer les composantes connexes fortes du graphe G.
 * Dans un premier temps, elle instancie la classe {@link OrdreDeSortieDuGraphImplication} pour déterminer l'ordre de
 * sortie des sommets lors du parcours en profondeur du graphe.
 * Puis, dans cet ordre en sens décroissant (donc en partant du dernier sommet trouvé par la
 * première étape), nous exécutons un deuxième parcours itéré en profondeur, mais dans G^T.
 */
public class ComposantesFortementConnexes {
    private Graph graph;
    private Conjonctions conj;
    // Matrice représentant le graphe transposé
    private final int[][] matriceGraphTransposee;
    // Liste qui permet de déterminer l'ordre de sortie du programme. Fonctionnera comme une pile.
    private ArrayList<Integer> parcouru;
    // Liste qui permet de déterminer l'ordre de sortie du programme. Fonctionnera comme une pile.
    private ArrayList<Integer> ordreDeSortie;
    // Liste des composantes connexes du graphe transposé (et donc celles du graphe G également).
    ArrayList<ComposanteConnexe> composanteConnexes;
    // Tableau qui permet de connaître les chemins parcourus à la fin de l'algorithme.
    int[] predecesseur;

    /**
     * Constructeur de la classe
     * @param graph le graph des implications qu'on étudie
     * @param graphT la transposée du graph
     * @param conj l'ensemble des clauses
     */
    public ComposantesFortementConnexes(Graph graph, Graph graphT, Conjonctions conj){
        this.graph = graph;
        this.conj = conj;
        matriceGraphTransposee = graphT.getGraphMatrice();
        ordreDeSortie = determinerOrdreDeSortieDansG();
        composanteConnexes = new ArrayList<>();
        parcouru = new ArrayList<>();
        predecesseur = new int[2*conj.getNombreLitterauxTotaux()];
    }

    /**
     * Une version de l'algorithme du parcours en profondeur itérée récursive.
     * À chaque fois qu'on débute un nouveau chemin, on crée une nouvelle composante connexe.
     * On parcourt les sommets accessibles via le parcours en profondeur et on les ajoute dans la composante connexe.
     * L'ordre des sommets explorés est déterminé par la liste d'ordre de sortie. On commence à la parcourir depuis la
     * fin.
     */
    public void determinerLesComposantesConnexes() {
        int n = 2 * conj.getNombreLitterauxTotaux(); // maximum de sommets dans l'algorithme
        int i;
        for (int j = ordreDeSortie.size()-1; j >= 0; j--) {
            i = ordreDeSortie.get(j); // l'élément de la liste d'ordre de sortie
            if (!parcouru.contains(i)) {
                parcouru.add(i);
                composanteConnexes.add(new ComposanteConnexe()); // On a exploré (et donc défini) tous les sommets de la composante connexe.
                ComposanteConnexe cc = composanteConnexes.get(composanteConnexes.size()-1);
                cc.add(creerLitteral(i)); // On a parcouru le sommet i. On l'ajoute dans la composante connexe.
                // Composante connexe
                for (int k = 0; k < n; k++) { // Parcours de tous les arcs sortants de j.
                    if (matriceGraphTransposee[k][i] == 1) { // Si (j,k) est un arc, alors...
                        explorer(i, k, cc);
                    }
                }
                // On l'ajoute donc à la liste.
            }
        }
    }

    /**
     * Explore l'arc (i,j) et détermine s'il a été parcouru ou pas. S'il ne l'a pas été, il explore ensuite les
     * arcs sortants de j, etc.
     * Fonction utilisée dans le parcours en profondeur.
     * @param i Le sommet de départ.
     * @param j Le sommet de destination.
     */
    private void explorer(int i, int j, ComposanteConnexe cc) {
        int v = j;
        if (!parcouru.contains(v)) {
            parcouru.add(v);
            predecesseur[v] = i; // Le sommet v est donc précédé du sommet i dans le parcours.
            for (int k = 0; k < matriceGraphTransposee.length; k++) { // Parcours de tous les arcs sortants de v.
                if (matriceGraphTransposee[k][v] == 1) { // Si (v,k) est un arc, alors...
                    explorer(v, k,cc);
                }
            }
            cc.add(creerLitteral(i)); // On a parcouru le sommet i. On l'ajoute dans la composante connexe.
        }
    }

    /**
     * Méthode qui appelle la classe {@link OrdreDeSortieDuGraphImplication} pour calculer l'ordre de sortie
     * des sommets lors d'un parcours en profondeur de {@code this.graph}.
     * @return une {@link ArrayList} d'entiers représentant l'ordre de sortie des sommets.
     */
    private ArrayList<Integer> determinerOrdreDeSortieDansG(){
        // Génère un entier aléatoire qui correspondra au sommet de départ du parcours de profondeur.
        int i = new Random().nextInt(2*conj.getNombreLitterauxTotaux());
        // Calcul en profondeur.
        OrdreDeSortieDuGraphImplication pf = new OrdreDeSortieDuGraphImplication(graph,conj,1);
        // Calcul de l'ordre de sortie.
        return pf.determinerOrdreSortieDuGraph(0);
    }

    /**
     * Méthode qui simplifie la création des littéraux.
     * Elle est appelée pour ajouter un nouveau littéral dans une composante connexe.
     * @param i le {@link Litteral} {@code .IntID - 1}  du littéral.
     * @return le nouvel {@link Litteral} créé.
     */
    private Litteral creerLitteral(int i){
        if(i >= conj.getNombreLitterauxTotaux()){
            return new Litteral("-"+ (i+1-conj.getNombreLitterauxTotaux()));
        }
        return new Litteral(""+(i+1));
    }

    /**
     * Méthode qui retourne la liste des composantes fortement connexes du graphe.
     * @return la liste {@link ArrayList} des {@link ComposanteConnexe} composantes connexes.
     */
    public ArrayList<ComposanteConnexe> getComposanteConnexes(){
        return composanteConnexes;
    }
}