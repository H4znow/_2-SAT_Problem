import java.io.File;

public class Programme {
    private ReadingInFile rf;
    Programme(File file){
         rf = new ReadingInFile(file);
         Conjonctions conj = new Conjonctions(rf);
         GraphImplication graphImplication = new GraphImplication(conj);
         graphImplication.printGraph();
    }
}
