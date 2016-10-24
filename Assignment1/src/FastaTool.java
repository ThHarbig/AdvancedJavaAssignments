/**
 * Created by theresa on 22.10.16.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class FastaTool {
    private List<Sequence> sequences;
    private String filename;

    FastaTool(String filename) {
        this.filename = filename;
        this.sequences = new ArrayList<>();
    }

    /**
     * reads a fastA file with multiple entries
     *
     * @throws IOException
     */
    void readFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(">")) {
                    sequences.add(new Sequence(new ArrayList<>(), line.replace(">", "")));
                } else {
                    if (!line.startsWith(";")) {
                        Sequence seq = sequences.get(sequences.size() - 1);
                        seq.appendNucleotides(line);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return number of sequences
     */
    private int numSeq() {
        return sequences.size();
    }

    /**
     * @return length of shortest sequence without gaps
     */
    private int shortestWOgaps() {
        int shortest = Integer.MAX_VALUE;
        for (Sequence seq : sequences) {
            int currSize = seq.getSizeWOgaps();
            if (currSize < shortest) {
                shortest = currSize;
            }
        }
        return shortest;
    }

    /**
     * @return length of longest sequence without gaps
     */
    private int longestWOgaps() {
        int longest = 0;
        for (Sequence seq : sequences) {
            int currSize = seq.getSizeWOgaps();
            if (currSize > longest) {
                longest = currSize;
            }
        }
        return longest;
    }

    /**
     * @return average sequence length without gaps
     */
    private int averageWOgaps() {
        int sum = 0;
        int count = 0;
        for (Sequence seq : sequences) {
            sum += seq.getSizeWOgaps();
            count += 1;
        }
        return sum / count;
    }

    /**
     * @return length of shortest sequence with gaps
     */
    private int shortest() {
        int shortest = Integer.MAX_VALUE;
        for (Sequence seq : sequences) {
            int currSize = seq.getTotalSize();
            if (currSize < shortest) {
                shortest = currSize;
            }
        }
        return shortest;
    }

    /**
     * @return length of longest sequence with gaps
     */
    private int longest() {
        int longest = 0;
        for (Sequence seq : sequences) {
            int currSize = seq.getTotalSize();
            if (currSize > longest) {
                longest = currSize;
            }
        }
        return longest;
    }

    /**
     * @return average sequence length with gaps
     */
    private int average() {
        int sum = 0;
        int count = 0;
        for (Sequence seq : sequences) {
            sum += seq.getTotalSize();
            count += 1;
        }
        return sum / count;
    }

    private int[] counts() {
        int as = 0, gs = 0, us = 0, cs = 0, gaps = 0;
        for (Sequence seq :
                sequences) {
            as += seq.numAs();
            gs += seq.numGs();
            us += seq.numUs();
            cs += seq.numCs();
            gaps += seq.numGaps();
        }
        return new int[]{as, cs, gs, us, gaps};
    }

    /**
     * writes Sequences into well formatted output
     */
    void formattedOutput() {
        int[] counts = counts();
        int i = 1;
        while (i < sequences.get(0).getSeq().length()) {
            if(i+59>longest()){
                System.out.format("%32d %"+(longest()-i-2)+"d\n", i, longest());
            }
            else{
                System.out.format("%32d %57d\n", i, i+59);
            }
            for (Sequence seq :
                    sequences) {
                if (i + 60 > seq.getSeq().length()) {
                    System.out.format("%-30s %-60s\n", seq.getDescription(), seq.getSeq().substring(i, seq.getSeq().length()));

                } else {
                    System.out.format("%-30s %-60s\n", seq.getDescription(), seq.getSeq().substring(i, i + 59));

                }
            }
            System.out.println("");
            i += 60;
        }
        System.out.println("Number of sequences: " + numSeq());
        System.out.println("Shortest length: \t" + shortest() + " (excluding '-'s: " + shortestWOgaps() + ")");
        System.out.println("Average length: \t" + average() + " (excluding '-'s: " + averageWOgaps() + ")");
        System.out.println("Longest length: \t" + longest() + " (excluding '-'s: " + longestWOgaps() + ")");
        System.out.println("Counts: A: " + counts[0] + ", C: " + counts[1] + ", G: " + counts[2] + ", U: " + counts[3] + ", -: " + counts[4]);
    }
}
