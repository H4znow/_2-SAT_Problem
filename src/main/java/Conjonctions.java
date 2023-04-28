import java.util.Iterator;
import java.util.LinkedList;

/**
 * Classe qui represente une conjonction de clauses. Autrement dit, elle contient toute les clauses du programmes
 */
public class Conjonctions {
    private LinkedList<Clause> clauses;
    private ReadingInFile readingInFile;
    private int maxLignes;
    private int nombreLitterauxTotaux;
    private int nombreClausesTotaux;

    public Conjonctions(ReadingInFile rf){
        this.readingInFile = rf;
        maxLignes = readingInFile.getNombreLignes();
        initialiserLesClauses();
        nombreLitterauxTotaux();
        nombreLitterauxTotaux();
    }

    public Clause[] getClauses(){
        return clauses.toArray(new Clause[0]);
    }
    public int getNombreLitterauxTotaux(){
        return nombreLitterauxTotaux;
    }

    public int getNombreClausesTotaux() {
        return nombreClausesTotaux;
    }

    private int nombreLitterauxTotaux(){
        String ligne2 = readingInFile.readingInFile(2);
        String[] parts = ligne2.split("\\s+");
        return Integer.parseInt(parts[2]);
    }
    private int nombreClauseTotaux(){
        String ligne2 = readingInFile.readingInFile(2);
        String[] parts = ligne2.split("\\s+");
        return Integer.parseInt(parts[3]);
    }
    private void initialiserLesClauses(){
        for (int i = 1; i <= maxLignes; i++) {
            clauses.add(new Clause(readingInFile.readingInFile(i)));
        }
    }
}
