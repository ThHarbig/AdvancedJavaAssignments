import java.util.ArrayList;
import java.util.List;

/**
 * Created by theresa on 22.10.16.
 */
class Sequence {
    private List<Nucleotide> seq = new ArrayList<>();
    private String description;
    private int sizeWOgaps;
    private int totalSize;
    private int as, cs, gs, us, gaps;

    Sequence(List<Nucleotide> seq, String description) {
        this.seq = seq;
        this.description = description;
        this.sizeWOgaps = 0;
        this.totalSize = 0;
        this.as = 0;
        this.cs = 0;
        this.gs = 0;
        this.us = 0;
        this.gaps = 0;
    }

    /**
     * appends nucleotides to the sequence
     *
     * @param nucleotides String containing nucleotides
     */
    void appendNucleotides(String nucleotides) {
        for (int i = 0; i < nucleotides.length(); i++) {
            Nucleotide x = new Nucleotide(nucleotides.charAt(i));
            seq.add(x);
            totalSize += 1;
            if (x.getNucleotide() != '-') {
                sizeWOgaps += 1;
            }
            if (x.getNucleotide() == 'A') {
                as += 1;
            }
            if (x.getNucleotide() == 'C') {
                cs += 1;
            }
            if (x.getNucleotide() == 'G') {
                gs += 1;
            }
            if (x.getNucleotide() == 'U') {
                us += 1;
            }
            if (x.getNucleotide() == '-') {
                gaps += 1;
            }
        }
    }

    /**
     * @return length of sequence with gaps
     */
    int getTotalSize() {
        return totalSize;
    }

    /**
     * @return length of sequence without gaps
     */
    int getSizeWOgaps() {
        return sizeWOgaps;
    }

    /**
     * @return sequence as a string
     */
    String getSeq() {
        String completeSequence = "";
        for (Nucleotide nuc :
                seq) {
            completeSequence += nuc.getNucleotide();
        }
        return completeSequence;
    }

    /**
     * @return description of sequence
     */
    String getDescription() {
        return description;
    }

    int numAs() {
        return as;
    }

    int numCs() {
        return cs;
    }

    int numUs() {
        return us;
    }

    int numGs() {
        return gs;
    }

    int numGaps() {
        return gaps;
    }

}
