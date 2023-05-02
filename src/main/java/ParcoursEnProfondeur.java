import java.util.ArrayList;

public class ParcoursEnProfondeur {

    private Conjonctions conj;
    private final int [][] matriceGraph;
    private ArrayList<Integer> parcouru;
    int[] predecesseur;
    public ParcoursEnProfondeur(Graph graph, Conjonctions conj, int i){
        this.conj = conj;
        matriceGraph = graph.getGraphMatrice();
        parcouru =  new ArrayList<>();
        predecesseur = new int[2*conj.getNombreLitterauxTotaux()];
        for (int j = 0; j < predecesseur.length; j++) {
            predecesseur[j] = -1;
        }
        //parcouru.add(i); //On ajoute le sommet de depart dans le tableau parcouru
        //predecesseur[i] = -1;
        parcoursEnProfondeur(i);
    }

    /**
     * Algorithme du parcours en profondeur pour calculer les composantes connexes.
     * @param i le sommet de depart du parcours
     */
    public void parcoursEnProfondeur(int i){
        int n = 2*conj.getNombreLitterauxTotaux(); //max de sommets dans l'algo
        for (int j = -1; j < n; j++) {//On parcours tout les litteraux un par un
            if(j==-1)
                j=i; //algo commence par le sommet donne en argument.
            if(!parcouru.contains(j)){//
                parcouru.add(j);
                for (int k = 0; k < n; k++) {//Parcours tout les ars sortants de j
                    if(matriceGraph[k][j] == 1){//Si (j,i) est un arc alors
                        explorer(j,k);
                    }
                }
            }
        }
    }

    /**
     * Explore l'arc (i,j) et determine s'il a ete parcouru ou pas. S'il ne l'a pas ete, il explore ensuite les
     * arc sortants de j, etc
     * @param i le sommet de depart
     * @param j le sommet de destination
     */
    private void explorer(int i, int j){
        int v =  j;
        if(!parcouru.contains(v)){
            parcouru.add(v);
            predecesseur[v] = i;
            for (int k = 0; k < matriceGraph.length; k++) {//Parcours tout les ars sortants de v
                if(matriceGraph[k][v] == 1){//Si (v,i) est un arc alors
                    explorer(v,k);
                }
            }
        }
    }

    public void printParcoursEnProfn(){
        System.out.println("---- TABLEAU PARCOURS EN PROFONDEUR ----");

        for (int i = 0; i < predecesseur.length; i++) {
            if(i >= conj.getNombreLitterauxTotaux())
                System.out.print( "!"+(char) ('x'+i-conj.getNombreLitterauxTotaux()) + " |");
            else
                System.out.print((char) ('x'+i) + "  |");
        }
        System.out.println();
        System.out.print(predecesseur[0] + " |");
        for (int i = 1; i < predecesseur.length; i++) {
            System.out.print(predecesseur[i] + "  |");
        }

    }


}
