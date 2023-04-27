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

    private void creerLitterauxDeLaClause(){

    }
}
