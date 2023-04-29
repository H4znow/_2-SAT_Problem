import java.util.LinkedList;

/**
 * Classe qui represente une conjonction de clauses. Autrement dit, elle contient et represente toute les clauses du programmes
 */
public class Conjonctions {
    // Liste chaînée contenant toutes les clauses du programme.
    private LinkedList<Clause> clauses;
    // Objet ReadingInFile utilisé pour lire le fichier d'entrée.
    private ReadingInFile readingInFile;
    // Le nombre maximum de lignes dans le fichier d'entrée.
    private int maxLignes;
    // Le nombre total de littéraux dans toutes les clauses.
    private int nombreLitterauxTotaux;
    // Le nombre total de clauses.
    private int nombreClausesTotaux;

    /**
     * Constructeur de la classe Conjonctions.
     * @param rf objet ReadingInFile utilisé pour lire le fichier d'entrée.
     */
    public Conjonctions(ReadingInFile rf){
        // Initialise l'objet ReadingInFile.
        this.readingInFile = rf;
        // Récupère le nombre maximum de lignes dans le fichier d'entrée.
        maxLignes = readingInFile.getNombreLignes();
        // Initialise les clauses à partir du fichier d'entrée.
        initialiserLesClauses();
        // Récupère le nombre total de littéraux dans toutes les clauses.
        nombreLitterauxTotaux = nombreLitterauxTotaux();
        // Récupère le nombre total de clauses.
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
     * Retourne le nombre total de littéraux dans toutes les clauses.
     * @return un entier représentant le nombre total de littéraux.
     */
    public int getNombreLitterauxTotaux(){
        return nombreLitterauxTotaux;
    }

    /**
     * Retourne le nombre total de clauses.
     * @return un entier représentant le nombre total de clauses.
     */
    public int getNombreClausesTotaux() {
        return nombreClausesTotaux;
    }

    /**
     * Récupère le nombre total de littéraux dans toutes les clauses à partir du fichier d'entrée.
     * @return un entier représentant le nombre total de littéraux.
     */
    private int nombreLitterauxTotaux(){
        String ligne2 = readingInFile.readingInFile(2);
        String[] parts = ligne2.split("\\s+");
        return Integer.parseInt(parts[2]);
    }

    /**
     * Récupère le nombre total de clauses à partir du fichier d'entrée.
     * @return un entier représentant le nombre total de clauses.
     */
    private int nombreClauseTotaux(){
        String ligne2 = readingInFile.readingInFile(2);
        String[] parts = ligne2.split("\\s+");
        return Integer.parseInt(parts[3]);
    }

    /**
     * Initialise les clauses à partir du fichier d'entrée.
     */
    private void initialiserLesClauses(){
        clauses = new LinkedList<Clause>();
        for (int i = 3; i <= maxLignes; i++) {
            String arg = readingInFile.readingInFile(i);
            clauses.add(new Clause(arg));
        }
    }
}