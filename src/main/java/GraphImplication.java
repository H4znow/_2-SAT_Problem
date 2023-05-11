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
    Tableau de taille (2*le nombre de littéraux)x(2*le nombre de littéraux).
    Une case prend 3 valeurs :
    si graphMatrice[i][j] = 0 : aucun arc
    si graphMatrice[i][j] = 1 : arc de i vers j
    Les i-ème premières cases contiennent les littéraux "positifs" et les j-ième restantes contiennent leurs inverses
     */

    private int[][] graphMatrice;
    private Clause[] clauses;
    private Conjonctions conj;
    private String[] litterauxDuTableau;

    /**
     * Constructeur de la classe
     * @param conj la conjonction (ensemble des clauses) qu'il utilise pour déduire les implications entre littéraux
     */
    public GraphImplication(Conjonctions conj){
        this.conj= conj;
        clauses = conj.getClauses();
        initialiserGraph();
        litterauxDuTableau = new String[2*conj.getNombreLitterauxTotaux()];
        for (int i = 0; i < litterauxDuTableau.length; i++) {
            if(i < conj.getNombreLitterauxTotaux())
                litterauxDuTableau[i] = " " + (char) ('a'+i);
            else
                litterauxDuTableau[i] = "!" + (char) ('a'+i-litterauxDuTableau.length/2);
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

    /**
     * Méthode pour initialiser dans un premier temps le tableau à 0. Elle détermine ensuite les arcs en remplissant
     * le tableau via {@code this.determinerLesArcDuGraph}
     */
    private void initialiserGraph(){
        graphMatrice = new int[2*conj.getNombreLitterauxTotaux()][2*conj.getNombreLitterauxTotaux()]; //2 fois car les
        //les littéraux sont en double (x et !x)
        for (int i = 0; i < graphMatrice.length; i++) {
            for (int j = 0; j < graphMatrice.length; j++) {
                graphMatrice[i][j] = 0;
            }
        }
        determinerLesArcDuGraph();
    }

    /**
     * Méthode pour déterminer les arcs.
     * Fonctionnement :
     * A) Parcours les clauses une par une.
     *  Pour la clause une (l1 v l2)
     *  1) elle ajoute l'arc !l1 vers l2
     *  2) elle ajoute
     *  -- Elle recupere la negation d'un litteral grace a {@link Litteral}.{@code negLitteral()} --
     * B) Recommencer avec les autres clauses
     */
    private void determinerLesArcDuGraph(){
        int n = conj.getNombreLitterauxTotaux(); //Nombre de litteral dans la clause
        for (int i = 0; i < clauses.length; i++) {
            Litteral[] litterauxDeLaClause = clauses[i].getLitterauxDeLaClause(); //On a x v y par exemple

            //Determiner l'implication non(x) => y

            Litteral colonneLitt = litterauxDeLaClause[0].negLitteral(); //On creer l'inverse du litteral x [ non(x) ]
            Litteral ligneLitt = litterauxDeLaClause[1]; //On garde y
            int colIndice = colonneLitt.getId()-1; // Definit l'indice du 1er litteral dans le graph
            //-1 parceque le tableau commence a 0
            int ligneIndice = ligneLitt.getId()-1;
            if (colonneLitt.getNeg())
                colIndice += n; //car s'il est negatif, il est dans la seconde partie du tableau
            if(ligneLitt.getNeg())
                ligneIndice += n;
            graphMatrice[ligneIndice][colIndice] = 1; //On ajoute 1 dans la case du graphe pour representer la presence de l'arc

            //Determiner l'implication non(y) => x

            colonneLitt = litterauxDeLaClause[1].negLitteral(); //On creer l'inverse du litteral x [ non(x) ]
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
