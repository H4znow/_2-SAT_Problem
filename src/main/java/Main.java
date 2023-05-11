import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * Classe Main qui contient la méthode main, point d'accès du programme.
 * Elle a deux missions : déterminer s'il y a un argument.
 */
public class Main {

    /**
     * Méthode main qui est le point d'accès au programme.
     * @param args Le tableau de string qui représente les arguments du programme.
     */
    public static void main(String[] args) {
        executerProgrammeSurRepertoire();
    }

    /**
     * Exécute le programme sur tous les fichiers d'un répertoire "./exemples" depuis la racine du projet.
     */
    private static void executerProgrammeSurRepertoire(){
        String directoryPath = "exemples"; // Chemin vers le répertoire à parcourir
        File directory = new File(directoryPath); // Création d'un objet File pour le répertoire
        File[] files = directory.listFiles(); // Liste de tous les fichiers dans le répertoire
        Programme programme; // Notre programme qui va déterminer si la formule est SAT ou pas
        for (File file : files) { // Boucle sur tous les fichiers
            if (file.isFile()) { // Vérification que le fichier est bien un fichier (et pas un sous-répertoire)
                programme = new Programme(file);
            }
        }
    }
}

