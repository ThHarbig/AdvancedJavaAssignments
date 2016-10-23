/**
 * Created by theresa on 22.10.16.
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

class FastaTool {
    private List<Sequence> sequences=new ArrayList<>();

    /**
     * reads a fastA file with multiple entries
     * @param filename path to file
     * @throws IOException
     */
    void readFile(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            boolean newSeq=true;
            String line;
            while ((line = br.readLine()) != null) {
                if(line.startsWith(">")){
                    newSeq=true;
                    sequences.add(new Sequence(new ArrayList<>(),line));
                }
                else{
                    if(!line.startsWith(";")){
                        Sequence seq= sequences.get(sequences.size()-1);
                        seq.appendNucleotides(line);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return number of sequences
     */
    int numSeq(){
        return sequences.size();
    }

    /**
     *
     * @return length of shortest sequence
     */
    int shortest(){
        int shortest=Integer.MAX_VALUE;
        for (Sequence seq: sequences) {
            int currSize=seq.getSize();
            if(currSize<shortest){
                shortest=currSize;
            }
        }
        return shortest;
    }

    /**
     *
     * @return length of longest sequence
     */
    int longest() {
        int longest = 0;
        for (Sequence seq : sequences) {
            int currSize = seq.getSize();
            if (currSize > longest) {
                longest = currSize;
            }
        }
        return longest;
    }

    /**
     *
     * @return average sequence length
     */
    int average(){
        int sum=0;
        int count=0;
        for (Sequence seq : sequences) {
            sum+= seq.getSize();
            count+=1;
        }
        return sum/count;
    }
}
