/**
 * Une classe qui représente les clauses du problème 2-SAT. Elle est composée de plusieurs littéraux.
 */
public class Clause {
    private Litteral[] litterauxDeLaClause;
    private String clauseString;

    /**
     * Constructeur de la clause.
     * @param clause la clause complète sous forme de string. Ex : "x -y"
     */
    public Clause(String clause){
        clauseString = clause;
        creerLitterauxDeLaClause();
    }

    /**
     * Retourne la clause sous forme de string.
     * @return String qui correspond à la clause. Ex : "!x v y v !z"
     */
    public String clauseToString(){
        return litterauxDeLaClause[0].litteralToString() + " v " + litterauxDeLaClause[1].litteralToString();
    }

    /**
     * Retourne les littéraux de la clause.
     * @return un tableau de littéraux.
     */
    public Litteral[] getLitterauxDeLaClause() {
        return litterauxDeLaClause;
    }

    /**
     * Crée les littéraux de la clause en séparant la chaîne de caractères de la clause en deux parties.
     */
    private void creerLitterauxDeLaClause(){
        String[] parts = clauseString.split("\\s+");
        litterauxDeLaClause = new Litteral[2];
        // Crée les littéraux à partir des deux parties de la chaîne de caractères de la clause.
        litterauxDeLaClause[0] = new Litteral(parts[0]);
        litterauxDeLaClause[1] = new Litteral(parts[1]);
    }
}