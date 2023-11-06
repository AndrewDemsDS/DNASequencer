import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TestCase {
    String[] inputArray;
    String expectedOutput;

    TestCase(String input, String expectedOutput) {
        this.inputArray = input.split(" ");
        this.expectedOutput = expectedOutput;
    }
}

public class TestRunnerV2 {
    private static final String TEST_CASES_FILE_PATH = "/Users/andreasdemosthenous/Library/CloudStorage/OneDrive-UniversityofCyprus/IdeaProjects/HM0/src/testcases.txt";

    public static void main(String[] args) {
        List<TestCase> testCases = readTestCasesFromFile(TEST_CASES_FILE_PATH);
        runTestCases(testCases);
    }

    public static List<TestCase> readTestCasesFromFile(String filePath) {
        List<TestCase> testCases = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 2) {
                    String input = parts[0];
                    String expectedOutput = parts[1];
                    testCases.add(new TestCase(input, expectedOutput)); // Pass input as a single string
                } else {
                    System.err.println("Invalid test case format: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading test cases file: " + e.getMessage());
        }
        return testCases;
    }

    public static void runTestCases(List<TestCase> testCases) {
        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            TestCase testCase = testCases.get(i);
            try {
                List<String> command = new ArrayList<>();
                command.add("java");
                command.add("/Users/andreasdemosthenous/Library/CloudStorage/OneDrive-UniversityofCyprus/IdeaProjects/HM0/src/DNASequencer.java");
                command.addAll(Arrays.asList(testCase.inputArray));

                ProcessBuilder processBuilder = new ProcessBuilder(command);
                Process process = processBuilder.start();

                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String output = reader.readLine();
                process.waitFor();

                if (output != null && output.equals(testCase.expectedOutput)) {
                    System.out.println("Test Case " + (i + 1) + " Passed");
                    passed++;
                } else {
                    System.out.println("Test Case " + (i + 1) + " Failed");
                    System.out.println("Input: " + Arrays.toString(testCase.inputArray));
                    System.out.println("Expected Output: " + testCase.expectedOutput);
                    System.out.println("Actual Output: " + output);
                }
            } catch (IOException | InterruptedException e) {
                System.err.println("Error running test cases: " + e.getMessage());
            }
        }
        System.out.println(passed + " out of " + testCases.size() + " test cases passed.");
    }
}
