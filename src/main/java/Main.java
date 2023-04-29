import java.io.File;

/**
 * Class Main qui contient la methode main, point d'acces du programme.
 * Elle a deux missions : Determiner s'il y a un argument
 */
public class Main {

    // L'objet file qui represente le fichier en argument du programme
    private static File file;

    /**
     * Verifie que le programme est bien lance avec un argument
     * @param args taleau string qui represente les arguments du programme
     * @throws RuntimeException s'il y a un probleme avec l'argument
     */
    private static void thereIsAnArgument(String[] args){
        if (args.length == 0) {
            throw new RuntimeException("Aucun argument n'a été passé.");
        } else {
            System.out.println("Le programme a reçu " + args.length + " argument(s).");
            try {
                file = new File(args[0]);
            } catch (Throwable e) {
                throw new RuntimeException("Erreur dans l'adresse du fichier donnee", e);
            }
            System.out.println("L'adresse du fichier est "+ file.getAbsolutePath());
        }
    }

    /**
     * Methode main qui est le point d'acces au programme
     * @param args taleau string qui represente les arguments du programme
     */
    public static void main(String[] args) {
        thereIsAnArgument(args);
        Programme programme = new Programme(file);
    }
}
