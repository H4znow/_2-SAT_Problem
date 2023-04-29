import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe représente une implémentation de l'interface Graph qui permet de représenter
 * les implications entre les littéraux d'une conjonction.
 */
public class GraphImplication implements Graph {

    /*
    Talbeau de taille (2*le nombre de litteraux)x(2*le nombre de litteraux).
    Une case prend 3 valeurs :
    si graphMatrice[i][j] = 0 : aucun arc
    si graphMatrice[i][j] = 1 : arc de i vers j
    Les i-eme premiers cases contiennent les litteraux "positifs" et les j-ieme restantes contient leurs inverse
     */

    private int[][] graphMatrice;
    private Clause[] clauses;
    private Conjonctions conj;
    private String[] litterauxDuTableau;

    /**
     * Constructeur de la class
     * @param conj la conjonction (ensemble des clauses) qu'il utilise pour deduire lles implications entre litteraux
     */
    public GraphImplication(Conjonctions conj){
        this.conj= conj;
        clauses = conj.getClauses();
        initialiserGraph();
        litterauxDuTableau = new String[2*conj.getNombreLitterauxTotaux()];
        for (int i = 0; i < litterauxDuTableau.length; i++) {
            if(i < conj.getNombreLitterauxTotaux())
                litterauxDuTableau[i] = " " + (char) ('x'+i);
            else
                litterauxDuTableau[i] = "!" + (char) ('x'+i-litterauxDuTableau.length/2);
        }
    }
    @Override
    public String nomCase(int i){
        return litterauxDuTableau[i];
    }

    @Override
    public int[][] getGraphMatrice() {
        return graphMatrice;
    }

    @Override
    public void printGraph() {
        System.out.println("---- MATRICE GRAPH DES IMPLICATIONS ----");
        //dessiner la premiere partie de la 1ere ligne pour indiquer les litteraux positifs
        System.out.print("___|");
        for (int i = 0; i < litterauxDuTableau.length; i++) {
            System.out.print(litterauxDuTableau[i] + " |");
        }
        System.out.println();

        // parcours du tableau à l'aide de deux boucles for
        for (int i = 0; i < graphMatrice.length; i++) {
            for (int j = 0; j < graphMatrice[i].length; j++) {
                if (j == 0){
                    //dessiner la premiere partie de la 1ere colonne pour indiquer les litteraux positifs
                    System.out.print(litterauxDuTableau[i] + " |");
                }
                System.out.print(" " + graphMatrice[i][j] + " |");
            }
            System.out.println(); // saut de ligne pour passer à la ligne suivante du tableau
        }
    }

    /**
     * Methode pour initier dans un premier temps le tableau a 0. Elle determine ensuite les arc en remplissant
     * le tableau via {@code this.determinerLesArcDuGraph}
     */
    private void initialiserGraph(){
        graphMatrice = new int[2*conj.getNombreLitterauxTotaux()][2*conj.getNombreLitterauxTotaux()]; //2 fois car les
        //les litteraux sont en double (x et !x)
        for (int i = 0; i < graphMatrice.length; i++) {
            for (int j = 0; j < graphMatrice.length; j++) {
                graphMatrice[i][j] = 0;
            }
        }
        determinerLesArcDuGraph();
    }

    /**
     * Methode pour determiner les arcs.
     * Fonctionnement :
     * A) Parcours les clauses une par une.
     *  Pour la clause une (l1 v l2)
     *  1) elle ajoute l'arc !l1 vers l2
     *  2) elle ajoute l'arc !l2 vers l1
     *  -- Elle recupere la negation d'un litteral grace a {@link Litteral}.{@code negLitteral()} --
     * B) Recommencer avec les autres clauses
     */
    private void determinerLesArcDuGraph(){
        int n = conj.getNombreLitterauxTotaux(); //Nombre de litteral dans la clause
        for (int i = 0; i < clauses.length; i++) {
            Litteral[] litterauxDeLaClause = clauses[i].getLitterauxDeLaClause(); //On a x v y par exemple

            //Determiner l'implication non(x) => y

            Litteral colonneLitt = litterauxDeLaClause[0].negLitteral(); //On creer !x [ non(x) ]
            Litteral ligneLitt = litterauxDeLaClause[1]; //On garde y
            int colIndice = colonneLitt.getId()-1; // Definit l'indice du 1er litteral dans le graph
            //-1 parceque le tableau commence a 0
            int ligneIndice = ligneLitt.getId()-1;
            if (colonneLitt.getNeg())
                colIndice += n; //car s'il est negatif, il est dans la seconde partie du tableau
            if(ligneLitt.getNeg())
                ligneIndice += n;
            System.out.println(colonneLitt.litteralToString() + ", id : " + colonneLitt.getId() + ", Indice NUMERO 1 : " + colIndice);
            System.out.println(ligneLitt.litteralToString() + ", id : " + ligneLitt.getId() + ", Indice : " + ligneIndice);
            graphMatrice[ligneIndice][colIndice] = 1; //On ajoute 1 dans la case du graphe pour representer la presence de l'arc

            //Determiner l'implication non(y) => x

            colonneLitt = litterauxDeLaClause[1].negLitteral(); //On creer !x [ non(x) ]
            ligneLitt = litterauxDeLaClause[0]; //On garde y
            colIndice = colonneLitt.getId()-1; // Definit l'indice du 1er litteral dans le graph
            ligneIndice = ligneLitt.getId()-1;
            if (colonneLitt.getNeg())
                colIndice += n; //car s'il est negatif, il est dans la seconde partie du tableau
            if(ligneLitt.getNeg())
                ligneIndice += n;
            graphMatrice[ligneIndice][colIndice ] = 1; //On ajoute 1 dans la case du graphe pour representer la presence de l'arc
        }
    }
}
