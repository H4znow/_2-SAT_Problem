import java.io.File;


public class Main {

    // L'objet file qui represente le fichier en argument du programme
    private static File file;

    private static void thereIsAnArgument(String[] args){
        if (args.length == 0) {
            System.out.println("Aucun argument n'a été passé.");
            System.exit(1);
        } else {
            System.out.println("Le programme a reçu " + args.length + " argument(s).");
            file = new File(args[0]);
            System.out.println("L'adresse du fichier est "+ file.getAbsolutePath());
        }
    }

    public static void main(String[] args) {
        thereIsAnArgument(args);
        Programme programme = new Programme(file);
    }
}
