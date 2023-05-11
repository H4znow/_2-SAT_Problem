import java.io.File;

/**
 * La classe Programme représente le programme qui permet de résoudre le problème 2-SAT.
 * Elle crée les objets nécessaires au fonctionnement du programme : le lecteur, les conjonctions, et les graphes.
 */
public class Programme {
    private ReadingInFile rf;

    /**
     * Constructeur de la classe Programme.
     * @param file le fichier qui contient le problème 2-SAT
     */
    Programme(File file){
        // Initialisation du lecteur de fichier
        rf = new ReadingInFile(file);
        // Initialisation des conjonctions à partir du fichier lu
        Conjonctions conj = new Conjonctions(rf);
        // Création du graphe d'implication à partir des conjonctions
        Graph graphImplication = new GraphImplication(conj);
        // Affichage du graphe d'implication
        graphImplication.printGraph();
        // Création du graphe transposé
        Graph graphTransposee = new GraphTransposee(conj, graphImplication);
        // Affichage du graphe transposé
        graphTransposee.printGraph();

        // Génération du code DOT et des images des deux graphes
        DessinerGraph dg;
        dg = new DessinerGraph(graphImplication, "Graphe Implication");
        dg.genererGraph();
        //dg.genererLImage();
        dg = new DessinerGraph(graphTransposee, "Graphe Tranposee");
        dg.genererGraph();
        //dg.genererLImage();


        //Resolution du probleme 2-SAT
        SAT_Solution ss = new SAT_Solution(graphImplication, graphTransposee, conj);
        if (ss.estSatisfiable())
            System.out.println("------------la formule est SATISFIABLE-----------------");
        else
            System.out.println("------------la formule est INSATISFIABLE-----------------");
    }
}