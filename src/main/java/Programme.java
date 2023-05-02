import java.io.File;

/**
 * Class programme qui represente le programme. Elle creer les objets
 * necessaire au fonctionnement : rader, conjonctions et les graphs.
 */
public class Programme {
    private ReadingInFile rf;

    /**
     * Constructeur de la class
     * @param file le fichier qui contient le 2-SAT probleme
     */
    Programme(File file){
        //Initier le lecteur
         rf = new ReadingInFile(file);
         //Initier la conjection (toute les clauses)
         Conjonctions conj = new Conjonctions(rf);
         //Creer le graph des implications
         Graph graphImplication = new GraphImplication(conj);
         graphImplication.printGraph();
         //Creer le graph transposee
         Graph graphTransposee = new GraphTransposee(conj, graphImplication);
         graphTransposee.printGraph();

         //Generer le code DOT puis les images des deux graphes
         DessinerGraph dg;
         dg = new DessinerGraph(graphImplication, "Graphe Implication");
         dg.genererGraph();
         //dg.genererLImage();
         dg = new DessinerGraph(graphTransposee, "Graphe Tranposee");
         dg.genererGraph();
         //dg.genererLImage();

         //Calcul en Profondeur
        ParcoursEnProfondeur pf = new ParcoursEnProfondeur(graphImplication,conj,0);
        pf.printParcoursEnProfn();


    }
}
