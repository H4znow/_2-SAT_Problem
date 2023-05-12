/**
 * Une classe qui represente les clauses du probleme 2-SAT. Elle est composee de plusieurs litteraux.
 */
public class Clause {
    private Litteral[] litterauxDeLaClause;
    private String clauseString;

    /**
     * Constructeur de la clause.
     * @param clause la clause complete sous forme de string. Ex : "x -y"
     */
    public Clause(String clause){
        clauseString = clause;
        creerLitterauxDeLaClause();
    }

    /**
     * Retourne la clause sous forme de string.
     * @return String qui correspond a la clause. Ex : "!x v y v !z"
     */
    public String clauseToString(){
        return litterauxDeLaClause[0].litteralToString() + " v " + litterauxDeLaClause[1].litteralToString();
    }

    /**
     * Retourne les litteraux de la clause.
     * @return un tableau de litteraux.
     */
    public Litteral[] getLitterauxDeLaClause() {
        return litterauxDeLaClause;
    }

    /**
     * Cree les litteraux de la clause en separant la chaine de caracteres de la clause en deux parties.
     */
    private void creerLitterauxDeLaClause(){
        String[] parts = clauseString.split("\\s+");
        litterauxDeLaClause = new Litteral[2];
        // Cree les litteraux a partir des deux parties de la chaine de caracteres de la clause.
        litterauxDeLaClause[0] = new Litteral(parts[0]);
        litterauxDeLaClause[1] = new Litteral(parts[1]);
    }
}