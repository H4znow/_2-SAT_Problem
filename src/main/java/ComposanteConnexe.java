import java.util.ArrayList;

/**
 * Classe qui represente les composantes connexes : les litteraux qu'elle contient.
 */
public class ComposanteConnexe {

    // Litteraux dans la composante connexe
    private ArrayList<Litteral> litteraux;

    /**
     * Constructeur de la classe {@link ComposanteConnexe}.
     */
    public ComposanteConnexe() {
        litteraux = new ArrayList<>();
    }

    /**
     * Methode pour ajouter un litteral dans la composante connexe.
     * @param litteral nouveau {@link Litteral} qu'on ajoute.
     */
    public void add(Litteral litteral) {
        litteraux.add(litteral);
    }

    /**
     * Verifie si la clause contient un litteral ainsi que sa negation. Si oui, elle retourne vrai.
     * @return {@code true} si la clause contient un litteral ainsi que sa negation. Autrement, elle retourne {@code false}.
     */
    public boolean contient_Litteral_Et_Sa_Negation() {
        for (int i = 0; i < litteraux.size(); i++) {
            Litteral negationLitteral = litteraux.get(i).negLitteral(); //On creer la negation du litteral i
            for (int j = 0; j < litteraux.size(); j++) {
                if ((negationLitteral.getId() == litteraux.get(j).getId()) && (negationLitteral.getNeg() == litteraux.get(j).getNeg())) {
                    //Si les deux litteraux ont le meme ID et les deux ont le meme "signe"
                    return true; // Alors la clause contient un litteral et son oppose.
                }
            }
        }
        return false;
    }
}