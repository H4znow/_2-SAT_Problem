/**
 * Une classe qui represente les clauses du 2-SAT probleme. Elle est compose de plusieurs litteraux.
 */
public class Clause {
    private Litteral[] litterauxDeLaClause;
    private String clauseString;
    public Clause(String clause){
        clauseString = clause;
        creerLitterauxDeLaClause();
    }

    /**
     * Renvoie la clause sous forme de string
     * @return String qui correspond a la clause. Ex : "!x v y v !z"
     */
    public String clauseToString(){
        String clauseInString ="";
        int i;
        for (i = 0; i < litterauxDeLaClause.length-1; i++) {
            clauseInString += litterauxDeLaClause[i].litteralToString()+" v ";
        }
        clauseInString += litterauxDeLaClause[i];
        return clauseInString;
    }

    private void creerLitterauxDeLaClause(){

    }
}
