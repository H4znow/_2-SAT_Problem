import java.io.*;

/**
 * Une classe pour lire dans le fichier donne en argument au programme.
 */
public class ReadingInFile {

    //Le fichier en argument
    private File file;
    // Nombre de ligne
    private int nombreLignes;
    //BufferedReader permet de lire dans le fichier
    private BufferedReader br;
    public ReadingInFile(File file){
        this.file = file;
        initiateReader();
        setNombreLigne();
    }
    /*
    Cette methode permet d'initialiser le reader du fichier.
    Utile pour initialiser < BufferedReader br >.
    Egalement utile pour re-"pointer" sur le debut du fichier (1ere ligne).
     */
    private void initiateReader(){
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    /*
    Methode pour determiner le nombre de ligne dans le fichier.
    Donne qui sera utilise dans les autres documents.
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
     * Une methode pour lire la n-eme ligne. Elle retourne un String qui contient la ligne.
     * @param n le numéro de la ligne à lire (doit être compris entre 1 et le nombre total de lignes).
     * @return la ligne n lue dans le fichier sous forme de chaîne de caractères.
     * @throws IllegalArgumentException si n est inférieur ou égal à zéro ou supérieur au nombre total de lignes du fichier.
     * @throws RuntimeException si une erreur d'entrée/sortie se produit lors de la lecture du fichier.
     */
    public String readingInFile(int n){
        if(n<=0 || n > nombreLignes)
            throw new IllegalArgumentException("L'argument n : " + n + "doit etre compris entre [1,"+nombreLignes+"].");
        String line = "";
        for (int i = 0; i < n; i++) {
            try {
                line = br.readLine();
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }
        return line;
    }
}
