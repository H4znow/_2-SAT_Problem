import java.io.File;

/**
 * Classe Main qui contient la méthode main, point d'accès du programme.
 * Elle a deux missions : déterminer s'il y a un argument.
 */
public class Main {

    // L'objet file qui représente le fichier en argument du programme.
    private static File file;

    /**
     * Vérifie que le programme est bien lancé avec un argument.
     * @param args Le tableau de string qui représente les arguments du programme.
     * @throws RuntimeException S'il y a un problème avec l'argument.
     */
    private static void thereIsAnArgument(String[] args){
        if (args.length == 0) {
            throw new RuntimeException("Aucun argument n'a été passé.");
        } else {
            System.out.println("Le programme a reçu " + args.length + " argument(s).");
            try {
                file = new File(args[0]);
            } catch (Throwable e) {
                throw new RuntimeException("Erreur dans l'adresse du fichier donnée", e);
            }
            System.out.println("L'adresse du fichier est "+ file.getAbsolutePath());
        }
    }

    /**
     * Méthode main qui est le point d'accès au programme.
     * @param args Le tableau de string qui représente les arguments du programme.
     */
    public static void main(String[] args) {
        thereIsAnArgument(args);
        Programme programme = new Programme(file);
    }
}