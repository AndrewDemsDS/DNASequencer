/**
 * Author: Andreas Demosthenous
 * Written: 26/09/2023
 * Last updated: 08/20/2023
 * <p>
 * Compilation command: javac DNASequencer.java
 * Execution command: java DNASequencer <Strand1> <Strand2/> ...
 * <p>
 * This program takes DNA strands as input from the command line arguments (args) and performs the following tasks:
 * 1. Validate the input DNA strands for correct characters ('c', 't', 'g', 'a').
 * 2. Select the first valid strand as the base strand (A1).
 * 3. Check for common characters in other input strands and merge them with the base strand at position A1.
 * 4. Output the concatenated DNA strand.
 */

public class DNASequencer {
    public static void main(String[] args) {
        if (args.length == 0) {
            // Check if values have been provided in the args array.
            // If not, display an error message and terminate the program.
            System.out.println("Wrong input!");
            System.out.println("Expected input: <strand1> <strand2> ...");
            return;
        }

        String A1 = "", B1;
        for (int i = 0; i <= args.length - 1; i++) {
            boolean wrongCharCheck, ifFullOverlap = false;
            wrongCharCheck = false;
            for (int j = 0; j <= args[i].length() - 1; j++) {
                // Validate the characters in the input strand to ensure they are 'c', 't', 'g', or 'a'.
                if (args[i].charAt(j) != 'c' && args[i].charAt(j) != 't' && args[i].charAt(j) != 'g' && args[i].charAt(j) != 'a') {
                    wrongCharCheck = true;
                    break;
                }
            }
            if (wrongCharCheck) {
                continue; // Skip the current strand if it contains invalid characters.
            } else if (A1.isBlank()) {
                A1 = args[i];
                continue;
            }

            B1 = args[i];
            int overlap = 0, minLength;
            minLength = Math.min(A1.length(), B1.length());

            if (A1.length() > B1.length())
                for (int l = 0; l <= A1.length() - B1.length() - 1; l++) {
                    ifFullOverlap = true;
                    for (int m = 0; m < B1.length(); m++) {
                        // Check for full overlap by comparing characters in both strands.
                        if (A1.charAt(l + m) != B1.charAt(m)) {
                            ifFullOverlap = false;
                            break;
                        }
                    }
                    if (ifFullOverlap) {
                        break;
                    }
                }

            if (ifFullOverlap) {
                continue; // Skip the current strand if it fully overlaps with the base strand.
            }

            for (int j = 4; j <= minLength; j++) {
                boolean ifOverlap = true;
                for (int k = 0; k < j; k++) {
                    // Check for partial overlap by comparing characters in the specified region.
                    if (A1.charAt(A1.length() - j + k) != B1.charAt(k)) {
                        ifOverlap = false;
                        break;
                    }
                }
                if (ifOverlap) {
                    overlap = j; // Store the overlap position.
                }
            }

            if (overlap >= 4) {
                // Merge the two strands at the overlapping position.
                StringBuilder merged = new StringBuilder(A1);
                for (int n = overlap; n < B1.length(); n++) {
                    merged.append(B1.charAt(n));
                }
                A1 = merged.toString();
            }
        }

        if (A1.isBlank()) {
            // If the base strand is still blank, no valid input strands were provided.
            System.out.println("Wrong input!");
            return;
        }

        // Output the concatenated DNA strand.
        System.out.println("DNA molecule : " + A1);
    }
}
