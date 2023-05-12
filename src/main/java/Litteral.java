/**
 * Represente un Litteral dans les clauses.
 */
public class Litteral {

    // Les litteraux sont uniques. Chacun se differencie de l'autre via la lettre qu'il lui a ete attribue.
    private char idLettre;
    // Stocke le numero de la variable
    private int idInt;

    // Pour determiner si c'est un neg-litterale (precede d'un non ou pas)
    private boolean neg;

    // Le litteral entier (avec neg s'il y est)
    private String litteral;

    /**
     * Constructeur du litteral. Initie le litteral en entier puis determine son {@code this.id} (le numero) et si il est precede
     * de {@code this.neg}.
     * @param litteral le litteral complet sous forme de string. Ex : "x" ou "-x"
     */
    public Litteral(String litteral){
        this.litteral =litteral;
        setNeg();
        setIds();
    }

    /**
     * Retourne la negation du litteral. Si on a x, on cree et retourne non(x).
     * @return litteral inverse de {@code this}.
     */
    public Litteral negLitteral(){
        if (neg)
            return new Litteral(litteral.substring(1));
        return new Litteral("-"+litteral);
    }

    /**
     * Retourne l'ID du litteral.
     * @return l'ID du litteral.
     */
    public int getId() {
        return idInt;
    }

    /**
     * Retourne le litteral sous forme de string.
     * @return String qui correspond au litteral. Ex : "!x" ou "x".
     */
    public String litteralToString(){
        if(neg)
            return "!"+idLettre;
        return idLettre+"";
    }

    /**
     * Convertit l'ID numerique du litteral en une lettre et stocke le resultat dans {@code this.idLettre}.
     */
    private void setIds(){
        int indice = 0;
        if(neg)
            indice = 1;
        // On convertit le numero obtenu en int. Puis on l'ajoute a 'w' pour obtenir la ieme lettre apres 'w'.
        if(indice==1)
            idInt = Integer.parseInt(litteral.substring(1)); // extrait la sous-chaine a partir de l'indice 1
        else
            idInt = Integer.parseInt(litteral);
        idLettre= (char) ('a'-1+ idInt);
    }

    /**
     * Determine si le litteral est precede d'un signe negatif.
     */
    private void setNeg(){
        if(litteral.charAt(0) == '-') {
            neg = true;
            return;
        }
        neg = false;
    }

    /**
     * Retourne si le litteral est precede d'un signe negatif.
     * @return true si le litteral est precede d'un signe negatif, false sinon.
     */
    public boolean getNeg(){
        return neg;
    }
}