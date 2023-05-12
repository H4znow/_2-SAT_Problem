import java.io.*;

/**
 * Une classe pour lire dans le fichier donne en argument au programme.
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
     * @param file le fichier a lire
     */
    public ReadingInFile(File file){
        this.file = file;
        initiateReader();
        setNombreLigne();
    }

    /**
     * Acceder au nombre maximum de lignes dans le fichier
     * @return le nombre de lignes maximum dans le fichier
     */
    public int getNombreLignes(){
        return nombreLignes;
    }

    /*
     * Cette methode permet d'initialiser le lecteur de fichier.
     * Elle est utile pour initialiser < BufferedReader br >.
     * Elle est egalement utile pour re-"pointer" sur le debut du fichier (premiere ligne).
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
     * Methode pour determiner le nombre de lignes dans le fichier.
     * Elle est utilisee dans les autres documents.
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
     * Une methode pour lire la n-eme ligne. Elle retourne une chaine de caracteres qui contient la ligne.
     * @param n le numero de la ligne a lire (doit etre compris entre 1 et le nombre total de lignes).
     * @return la ligne n lue dans le fichier sous forme de chaine de caracteres.
     * @throws IllegalArgumentException si n est inferieur ou egal a zero ou superieur au nombre total de lignes du fichier.
     * @throws RuntimeException si une erreur d'entree/sortie se produit lors de la lecture du fichier.
     */
    public String readingInFile(int n){
        initiateReader(); // On re-initialise le lecteur pour etre sur qu'il lit depuis le debut du fichier
        if(n<=0 || n > nombreLignes)
            throw new IllegalArgumentException("L'argument n : " + n + " doit etre compris entre [1,"+nombreLignes+"].");
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