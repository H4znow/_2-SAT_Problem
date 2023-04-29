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
         rf = new ReadingInFile(file);
         Conjonctions conj = new Conjonctions(rf);
         Graph graphImplication = new GraphImplication(conj);
         graphImplication.printGraph();
         DessinerGraph dg = new DessinerGraph(graphImplication, "Graphe Implication");
         dg.genererGraph();
    }
}
