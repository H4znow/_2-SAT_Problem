import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GraphImplication implements Graph {

    /*
    Talbeau de taille (2*le nombre de litteraux)x(2*le nombre de litteraux).
    Une case prend 3 valeurs :
    si graphMatrice[i][j] = 0 : aucun arc
    si graphMatrice[i][j] = 1 : arc de i vers j
    Les i-eme premiers cases contiennent les litteraux "positifs" et les j-ieme restantes contient leurs inverse
     */

    private int[][] graphMatrice;
    private List<Litteral> litteraux;
    private Clause[] clauses;
    private Conjonctions conj;

    GraphImplication(Conjonctions conj){
        this.conj= conj;
        clauses = conj.getClauses();
        setLitteraux();
        initialiserGraph();
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
        for (int i = 0; i < conj.getNombreLitterauxTotaux(); i++) {
            System.out.print( " " + (char) ('x'+i) + " |");
        }
        //dessiner la seconde partie de la 1ere ligne pour indiquer les litteraux negatifs
        for (int i = 0; i < conj.getNombreLitterauxTotaux(); i++) {
            System.out.print( "!" + (char) ('x'+i) + " |");
        }
        System.out.println("");

        // parcours du tableau à l'aide de deux boucles for
        for (int i = 0; i < graphMatrice.length; i++) {
            for (int j = 0; j < graphMatrice[i].length; j++) {
                if (j == 0 && i < conj.getNombreLitterauxTotaux()){
                    //dessiner la premiere partie de la 1ere colonne pour indiquer les litteraux positifs
                    System.out.print( " " + (char) ('x'+i) + " |");
                } else if (j == 0 && i >= conj.getNombreLitterauxTotaux()) {
                    //dessiner la seconde partie de la 1ere colonne pour indiquer les litteraux negatifs
                    System.out.print( "!" + (char) ('x'+i-conj.getNombreLitterauxTotaux()) + " |");
                }
                System.out.print(" " + graphMatrice[i][j] + " |");
            }
            System.out.println(); // saut de ligne pour passer à la ligne suivante du tableau
        }
    }

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
            graphMatrice[ligneIndice][colIndice] += 1; //On ajoute 1 dans la case du graphe pour representer la presence de l'arc

            //Determiner l'implication non(y) => x

            colonneLitt = litterauxDeLaClause[1].negLitteral(); //On creer !x [ non(x) ]
            ligneLitt = litterauxDeLaClause[0]; //On garde y
            colIndice = colonneLitt.getId()-1; // Definit l'indice du 1er litteral dans le graph
            ligneIndice = ligneLitt.getId()-1;
            if (colonneLitt.getNeg())
                colIndice += n; //car s'il est negatif, il est dans la seconde partie du tableau
            if(ligneLitt.getNeg())
                ligneIndice += n;
            graphMatrice[ligneIndice][colIndice ] += 1; //On ajoute 1 dans la case du graphe pour representer la presence de l'arc
        }
    }
    //recuperer les litteraux des clauses
    private void setLitteraux(){
        litteraux = new ArrayList<Litteral>();
        for (int i = 0; i < clauses.length; i++) {
            litteraux.add(clauses[i].getLitterauxDeLaClause()[0]); //Recupere le premier litterale de la clause
            litteraux.add(clauses[i].getLitterauxDeLaClause()[1]); //Recupere le second litterale de la clause
        }
    }












    String command = "dot -Tpng input.dot -o output.png";
    ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", command);
    private void test(){
        pb.directory(new File("resources/graph"));
        pb.redirectErrorStream(true);
        try {
            Process process = pb.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}







