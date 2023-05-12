import java.util.LinkedList;

/**
 * Classe qui represente une conjonction de clauses. Autrement dit, elle contient et represente toutes les clauses du programme.
 */
public class Conjonctions {
    // Liste chaine contenant toutes les clauses du programme.
    private LinkedList<Clause> clauses;
    // Objet ReadingInFile utilise pour lire le fichier d'entree.
    private ReadingInFile readingInFile;
    // Le nombre maximum de lignes dans le fichier d'entree.
    private int maxLignes;
    // Le nombre total de litteraux dans toutes les clauses.
    private int nombreLitterauxTotaux;
    // Le nombre total de clauses.
    private int nombreClausesTotaux;

    /**
     * Constructeur de la classe Conjonctions.
     * @param rf objet ReadingInFile utilise pour lire le fichier d'entree.
     */
    public Conjonctions(ReadingInFile rf){
        // Initialise l'objet ReadingInFile.
        this.readingInFile = rf;
        // Recupere le nombre maximum de lignes dans le fichier d'entree.
        maxLignes = readingInFile.getNombreLignes();
        // Initialise les clauses a partir du fichier d'entree.
        initialiserLesClauses();
        // Recupere le nombre total de litteraux dans toutes les clauses.
        nombreLitterauxTotaux = nombreLitterauxTotaux();
        // Recupere le nombre total de clauses.
        nombreClausesTotaux = nombreClauseTotaux();
    }

    /**
     * Retourne les clauses sous forme de tableau.
     * @return un tableau de clauses.
     */
    public Clause[] getClauses(){
        return clauses.toArray(new Clause[0]);
    }

    /**
     * Retourne le nombre total de litteraux dans toutes les clauses.
     * @return un entier representant le nombre total de litteraux.
     */
    public int getNombreLitterauxTotaux(){
        return nombreLitterauxTotaux;
    }

    /**
     * Retourne le nombre total de clauses.
     * @return un entier representant le nombre total de clauses.
     */
    public int getNombreClausesTotaux() {
        return nombreClausesTotaux;
    }

    /**
     * Recupere le nombre total de litteraux dans toutes les clauses a partir du fichier d'entree.
     * @return un entier representant le nombre total de litteraux.
     */
    private int nombreLitterauxTotaux(){
        String ligne2 = readingInFile.readingInFile(2);
        String[] parts = ligne2.split("\\s+");
        return Integer.parseInt(parts[2]);
    }

    /**
     * Recupere le nombre total de clauses a partir du fichier d'entree.
     * @return un entier representant le nombre total de clauses.
     */
    private int nombreClauseTotaux(){
        String ligne2 = readingInFile.readingInFile(2);
        String[] parts = ligne2.split("\\s+");
        return Integer.parseInt(parts[3]);
    }

    /**
     * Initialise les clauses a partir du fichier d'entree.
     */
    private void initialiserLesClauses(){
        clauses = new LinkedList<>();
        for (int i = 3; i <= maxLignes; i++) {//On commence a la ligne 3 (premiere clause)
            //On lit une ligne du programme.
            String arg = readingInFile.readingInFile(i);
            //On cree l'objet clause dans la ligne et on l'ajoute dans la conjonction.
            clauses.add(new Clause(arg));
        }
    }
}