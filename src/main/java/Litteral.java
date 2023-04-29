/**
 * Represente un Litteral dans les clauses
 */
public class Litteral {

    // Les litteraux sont uniques. Chacun se differencie de l'autre via la lettre qu'il lui a ete attribue.
    private char idLettre;
    // Stocke le numéro de la variable
    private int idInt;

    // Pour déterminer si c'est un neg-litterale (précédé d'un non ou pas)
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
     * Pour déterminer la valeur retournée par le litteral lorsqu'il est évalué avec VRAI OU FAUX.
     * @param valeur la valeur que la méthode évalue (VRAI ou FAUX)
     * @return la {@code valeur} inverse si le litteral est précédé de {@code this.neg}. Autrement, elle retourne simplement
     * {@code valeur}.
     */
    public boolean evaluerLitteral(boolean valeur){
        if (neg)
            return !valeur;
        return valeur;
    }

    /**
     * Retourne la négation du litteral. Si on a x, on crée et retourne non(x)
     * @return litteral inverse de {@code this}
     */
    public Litteral negLitteral(){
        if (neg)
            return new Litteral(litteral.charAt(1)+"");
        return new Litteral("-"+litteral);
    }

    /**
     * Retourne l'ID du litteral
     * @return l'ID du litteral
     */
    public int getId() {
        return idInt;
    }

    /**
     * Retourne le litteral sous forme de string
     * @return String qui correspond au litteral. Ex : "!x" ou "x"
     */
    public String litteralToString(){
        if(neg)
            return "!"+idLettre;
        return idLettre+"";
    }

    /**
     * Convertit l'ID numérique du litteral en une lettre et stocke le résultat dans {@code this.idLettre}.
     */
    private void setIds(){
        int indice = 0;
        if(neg)
            indice = 1;
        // On convertit le numéro obtenu en int. Puis on l'ajoute à 'w' pour obtenir la ième lettre après 'w'.
        idInt = Character.getNumericValue(litteral.charAt(indice));
        idLettre= (char) ('w'+ idInt);
    }

    /**
     * Détermine si le litteral est précédé d'un signe négatif.
     */
    private void setNeg(){
        if(litteral.charAt(0) == '-') {
            neg = true;
            return;
        }
        neg = false;
    }

    /**
     * Retourne si le litteral est précédé d'un signe négatif.
     * @return true si le litteral est précédé d'un signe négatif, false sinon.
     */
    public boolean getNeg(){
        return neg;
    }
}