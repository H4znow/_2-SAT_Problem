import java.io.*;

/**
 * Une classe pour lire dans le fichier donné en argument au programme.
 */
public class ReadingInFile {

    // Le fichier en argument
    private File file;
    // Nombre de lignes
    private int nombreLignes;
    // BufferedReader permet de lire dans le fichier
    private BufferedReader br;

    /**
     * Constructeur de la classe
     * @param file le fichier à lire
     */
    public ReadingInFile(File file){
        this.file = file;
        initiateReader();
        setNombreLigne();
    }

    /**
     * Accéder au nombre maximum de lignes dans le fichier
     * @return le nombre de lignes maximum dans le fichier
     */
    public int getNombreLignes(){
        return nombreLignes;
    }

    /*
     * Cette méthode permet d'initialiser le lecteur de fichier.
     * Elle est utile pour initialiser < BufferedReader br >.
     * Elle est également utile pour re-"pointer" sur le début du fichier (première ligne).
     */
    private void initiateReader(){
        if(br!=null) {
            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * Méthode pour déterminer le nombre de lignes dans le fichier.
     * Elle est utilisée dans les autres documents.
     */
    private void setNombreLigne(){
        nombreLignes = 0;
        while (true){
            try {
                if (br.readLine()== null) break;
                nombreLignes++;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Une méthode pour lire la n-ème ligne. Elle retourne une chaîne de caractères qui contient la ligne.
     * @param n le numéro de la ligne à lire (doit être compris entre 1 et le nombre total de lignes).
     * @return la ligne n lue dans le fichier sous forme de chaîne de caractères.
     * @throws IllegalArgumentException si n est inférieur ou égal à zéro ou supérieur au nombre total de lignes du fichier.
     * @throws RuntimeException si une erreur d'entrée/sortie se produit lors de la lecture du fichier.
     */
    public String readingInFile(int n){
        initiateReader(); // On ré-initialise le lecteur pour être sûr qu'il lit depuis le début du fichier
        if(n<=0 || n > nombreLignes)
            throw new IllegalArgumentException("L'argument n : " + n + " doit être compris entre [1,"+nombreLignes+"].");
        String line = "";
        for (int i = 0; i < n; i++) {
            try {
                line = br.readLine();
            }catch (IOException e){
                throw new RuntimeException("Erreur dans la lecture de la ligne "+i);
            }
        }
        return line;
    }
}