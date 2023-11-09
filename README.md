# DNASequencer
DNA sequencer written in java for a university project with a input-output test added

Compilation command: javac DNASequencer.java

Execution command: java DNASequencer <Strand1> <Strand2/> ...

This program takes DNA strands as input from the command line arguments (args) and performs the following tasks:
1. Validate the input DNA strands for correct characters ('c', 't', 'g', 'a').
2. Select the first valid strand as the base strand (A1).
3. Check for common characters in other input strands and merge them with the base strand at position A1.
4. Output the concatenated DNA strand.
