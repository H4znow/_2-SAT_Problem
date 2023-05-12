import java.io.File;

/**
 * La classe Programme represente le programme qui permet de resoudre le probleme 2-SAT.
 * Elle crée les objets necessaires au fonctionnement du programme : le lecteur, les conjonctions, et les graphes.
 */
public class Programme {
    private ReadingInFile rf;

    /**
     * Constructeur de la classe Programme.
     * @param file le fichier qui contient le problème 2-SAT
     */
    public Programme(File file){
        // Initialisation du lecteur de fichier
        rf = new ReadingInFile(file);
        // Initialisation des conjonctions à partir du fichier lu
        Conjonctions conj = new Conjonctions(rf);
        // Création du graphe d'implication à partir des conjonctions
        Graph graphImplication = new GraphImplication(conj);
        // Création du graphe transposé
        Graph graphTransposee = new GraphTransposee(conj, graphImplication);

        //Resolution du probleme 2-SAT
        SAT_Solution ss = new SAT_Solution(graphImplication, graphTransposee, conj);
        if (ss.estSatisfiable())
            System.out.println("---- SAT ----");
        else
            System.out.println("--- NON-SAT ---");
    }
}