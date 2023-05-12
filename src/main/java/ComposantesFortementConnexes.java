import java.util.ArrayList;
import java.util.Random;

/**
 * Classe pour determiner les composantes connexes fortes du graphe G.
 * Dans un premier temps, elle instancie la classe {@link OrdreDeSortieDuGraphImplication} pour determiner l'ordre de
 * sortie des sommets lors du parcours en profondeur du graphe.
 * Puis, dans cet ordre en sens decroissant (donc en partant du dernier sommet trouve par la
 * premiere etape), nous executons un deuxieme parcours itere en profondeur, mais dans G^T.
 */
public class ComposantesFortementConnexes {
    private Graph graph;
    private Conjonctions conj;
    // Matrice representant le graphe transpose
    private final int[][] matriceGraphTransposee;
    // Liste qui permet de determiner l'ordre de sortie du programme. Fonctionnera comme une pile.
    private ArrayList<Integer> parcouru;
    // Liste qui permet de determiner l'ordre de sortie du programme. Fonctionnera comme une pile.
    private ArrayList<Integer> ordreDeSortie;
    // Liste des composantes connexes du graphe transpose (et donc celles du graphe G egalement).
    ArrayList<ComposanteConnexe> composanteConnexes;
    // Tableau qui permet de connaitre les chemins parcourus a la fin de l'algorithme.
    int[] predecesseur;

    /**
     * Constructeur de la classe
     * @param graph le graph des implications qu'on etudie
     * @param graphT la transposee du graph
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
     * Une version de l'algorithme du parcours en profondeur iteree recursive.
     * A chaque fois qu'on debute un nouveau chemin, on cree une nouvelle composante connexe.
     * On parcourt les sommets accessibles via le parcours en profondeur et on les ajoute dans la composante connexe.
     * L'ordre des sommets explores est determine par la liste d'ordre de sortie. On commence a la parcourir depuis la
     * fin.
     */
    public void determinerLesComposantesConnexes() {
        int n = 2 * conj.getNombreLitterauxTotaux(); // maximum de sommets dans l'algorithme
        int i;
        for (int j = ordreDeSortie.size()-1; j >= 0; j--) {
            i = ordreDeSortie.get(j); // l'element de la liste d'ordre de sortie
            if (!parcouru.contains(i)) {
                parcouru.add(i);
                composanteConnexes.add(new ComposanteConnexe()); // On a explore (et donc defini) tous les sommets de la composante connexe.
                ComposanteConnexe cc = composanteConnexes.get(composanteConnexes.size()-1);
                cc.add(creerLitteral(i)); // On a parcouru le sommet i. On l'ajoute dans la composante connexe.
                // Composante connexe
                for (int k = 0; k < n; k++) { // Parcours de tous les arcs sortants de j.
                    if (matriceGraphTransposee[k][i] == 1) { // Si (j,k) est un arc, alors...
                        explorer(i, k, cc);
                    }
                }
                // On l'ajoute donc a la liste.
            }
        }
    }

    /**
     * Explore l'arc (i,j) et determine s'il a ete parcouru ou pas. S'il ne l'a pas ete, il explore ensuite les
     * arcs sortants de j, etc.
     * Fonction utilisee dans le parcours en profondeur.
     * @param i Le sommet de depart.
     * @param j Le sommet de destination.
     */
    private void explorer(int i, int j, ComposanteConnexe cc) {
        int v = j;
        if (!parcouru.contains(v)) {
            parcouru.add(v);
            predecesseur[v] = i; // Le sommet v est donc precede du sommet i dans le parcours.
            for (int k = 0; k < matriceGraphTransposee.length; k++) { // Parcours de tous les arcs sortants de v.
                if (matriceGraphTransposee[k][v] == 1) { // Si (v,k) est un arc, alors...
                    explorer(v, k,cc);
                }
            }
            cc.add(creerLitteral(i)); // On a parcouru le sommet i. On l'ajoute dans la composante connexe.
        }
    }

    /**
     * Methode qui appelle la classe {@link OrdreDeSortieDuGraphImplication} pour calculer l'ordre de sortie
     * des sommets lors d'un parcours en profondeur de {@code this.graph}.
     * @return une {@link ArrayList} d'entiers representant l'ordre de sortie des sommets.
     */
    private ArrayList<Integer> determinerOrdreDeSortieDansG(){
        // Genere un entier aleatoire qui correspondra au sommet de depart du parcours de profondeur.
        int i = new Random().nextInt(2*conj.getNombreLitterauxTotaux());
        // Calcul en profondeur.
        OrdreDeSortieDuGraphImplication pf = new OrdreDeSortieDuGraphImplication(graph,conj,1);
        // Calcul de l'ordre de sortie.
        return pf.determinerOrdreSortieDuGraph(0);
    }

    /**
     * Methode qui simplifie la creation des litteraux.
     * Elle est appelee pour ajouter un nouveau litteral dans une composante connexe.
     * @param i le {@link Litteral} {@code .IntID - 1}  du litteral.
     * @return le nouvel {@link Litteral} cree.
     */
    private Litteral creerLitteral(int i){
        if(i >= conj.getNombreLitterauxTotaux()){
            return new Litteral("-"+ (i+1-conj.getNombreLitterauxTotaux()));
        }
        return new Litteral(""+(i+1));
    }

    /**
     * Methode qui retourne la liste des composantes fortement connexes du graphe.
     * @return la liste {@link ArrayList} des {@link ComposanteConnexe} composantes connexes.
     */
    public ArrayList<ComposanteConnexe> getComposanteConnexes(){
        return composanteConnexes;
    }
}