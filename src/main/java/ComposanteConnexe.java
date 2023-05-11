import java.util.ArrayList;

/**
 * Classe qui représente les composantes connexes : les littéraux qu'elle contient.
 */
public class ComposanteConnexe {

    // Littéraux dans la composante connexe
    private ArrayList<Litteral> litteraux;

    /**
     * Constructeur de la classe {@link ComposanteConnexe}.
     */
    public ComposanteConnexe() {
        litteraux = new ArrayList<>();
    }

    /**
     * Méthode pour ajouter un littéral dans la composante connexe.
     * @param litteral nouveau {@link Litteral} qu'on ajoute.
     */
    public void add(Litteral litteral) {
        litteraux.add(litteral);
    }

    /**
     * Vérifie si la clause contient un littéral ainsi que sa négation. Si oui, elle retourne vrai.
     * @return {@code true} si la clause contient un littéral ainsi que sa négation. Autrement, elle retourne {@code false}.
     */
    public boolean contient_Litteral_Et_Sa_Negation() {
        for (int i = 0; i < litteraux.size(); i++) {
            Litteral negationLitteral = litteraux.get(i).negLitteral(); //On creer la negation du litteral i
            for (int j = 0; j < litteraux.size(); j++) {
                if ((negationLitteral.getId() == litteraux.get(j).getId()) && (negationLitteral.getNeg() == litteraux.get(j).getNeg())) {
                    //Si les deux litteraux ont le meme ID et les deux ont le meme "signe"
                    return true; // Alors la clause contient un littéral et son opposé.
                }
            }
        }
        return false;
    }
}