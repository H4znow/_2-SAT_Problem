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
    si graphMatrice[i][j] = 2 : arc de j vers i
    si graphMatrice[i][j] = 3 : deux arcs
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
        // parcours du tableau à l'aide de deux boucles for
        for (int i = 0; i < graphMatrice.length; i++) {
            for (int j = 0; j < graphMatrice[i].length; j++) {
                System.out.print(graphMatrice[i][j] + " ");
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
        remplirLaMatrice();
    }
    private void remplirLaMatrice(){
        for (int i = 0; i < clauses.length; i++) {
            Litteral[] litterauxDeLaClause = clauses[i].getLitterauxDeLaClause(); //On a x v y

            //Determiner l'implication non(x) => y

            Litteral premLitt = litterauxDeLaClause[0].negLitteral(); //On creer !x [ non(x) ]
            Litteral secLitt = litterauxDeLaClause[1]; //On garde y
            int premIndice = premLitt.getId()-1; // Definit l'indice du 1er litteral dans le graph
            //-1 parceque le tableau commence a 0
            int secIndice = premLitt.getId()-1;
            if (premLitt.getNeg())
                premIndice += conj.getNombreClausesTotaux()-1; //car s'il est negatif, il est dans la seconde partie du tableau
            if(secLitt.getNeg())
                secIndice += conj.getNombreClausesTotaux()-1;
            graphMatrice[premIndice][secIndice] += 1; //On ajoute 1 dans la case du graphe pour representer la presence de l'arc

            //Determiner l'implication non(y) => x

            premLitt = litterauxDeLaClause[1].negLitteral(); //On creer !x [ non(x) ]
            secLitt = litterauxDeLaClause[0]; //On garde y
            premIndice = premLitt.getId()-1; // Definit l'indice du 1er litteral dans le graph
            secIndice = premLitt.getId()-1;
            if (premLitt.getNeg())
                premIndice += conj.getNombreClausesTotaux()-1; //car s'il est negatif, il est dans la seconde partie du tableau
            if(secLitt.getNeg())
                secIndice += conj.getNombreClausesTotaux()-1;
            graphMatrice[premIndice][secIndice] += 1; //On ajoute 1 dans la case du graphe pour representer la presence de l'arc
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







