import java.util.ArrayList;
import java.util.List;

/**
 * Created by theresa on 22.10.16.
 */
class Sequence {
    private List<Nucleotide> seq=new ArrayList<>();
    private String description;
    private int size;
    Sequence(List<Nucleotide> seq,String description){
        this.seq=seq;
        this.description=description;
        this.size=0;
    }

    /**
     *
     * @return length of sequence
     */
    int getSize(){
        return size;
    }

    /**
     *
     * @return sequence as a string
     */
    String getSeq(){
        return seq.toString();
    }

    /**
     *
     * @return description of sequence
     */
    String getDescription(){
        return description;
    }

    /**
     * appends nucleotides to the sequence
     * @param nucleotides String containing nucleotides
     */
    void appendNucleotides(String nucleotides){
        for(int i=0;i<nucleotides.length();i++){
            Nucleotide x=new Nucleotide(nucleotides.charAt(i));
            seq.add(x);
            if(x.getNucleotide()!='-'){
                size+=1;
            }
        }
    }
}
