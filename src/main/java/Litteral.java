/**
 * Represente un Litteral dans les clauses
 */
public class Litteral {

    //Les litteraux sont uniques. Chacun se differencie de l'autre via la lettre qu'il lui a ete attribue.
   private char idLettre;
   //Stock le numero de la variable
   private int idInt;

   //Pour determiner si c'est un neg-litterale (precede d'un non ou pas)
    private boolean neg;

    //Le litteral entier (avec neg s'il y est)
    private String litteral;

    /**
     * Constructeur du litteral. Initie le litteral en entier puis determine son {@code this.id} (le numero) et si il est precede
     * de {@code this.neg}.
     * @param litteral
     */
    public Litteral(String litteral){
        this.litteral =litteral;
        setNeg();
        setIds();
    }

    /**
     * Pour determiner la valeur retourner par le litteral lorsqu'il est evalue avec VRAI OU FAUX.
     * @param valeur la valeur que la methode evalue (VRAI ou FAUX)
     * @return la {@code valeur} inverse si le litteral est precede de {@code this.neg}. Autrement, elle retourne simplement
     * {@code valeur}.
     */
    public boolean evaluerLitteral(boolean valeur){
        if (neg)
            return !valeur;
        return valeur;
    }

    public Litteral negLitteral(){
        if (neg)
            return new Litteral(litteral.charAt(1)+"");
        return new Litteral("-"+litteral);
    }

    public int getId() {
        return idInt;
    }

    /**
     * Renvoie le litterale sous forme de string
     * @return String qui correspond au litterale. Ex : "!x" ou "x"
     */
    public String litteralToString(){
        if(neg)
            return "!"+idLettre;
        return idLettre+"";
    }
    private void setIds(){
        int indice = 0;
        if(neg)
            indice = 1;
        //On converti le numero obtenu en int. Puis on l'ajoute a y pour obtenir la i lettre apres y.
        idInt = Character.getNumericValue(litteral.charAt(indice));
        idLettre= (char) ('w'+(int) litteral.charAt(indice));
    }
    private void setNeg(){
        if(litteral.charAt(0) == '-') {
            neg = true;
            return;
        }
        neg = false;
    }
    public boolean getNeg(){
        return neg;
    }

}
