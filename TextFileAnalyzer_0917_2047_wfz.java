// 代码生成时间: 2025-09-17 20:47:59
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * TextFileAnalyzer is a Java class that analyzes the content of a text file.
 * It provides methods to count characters, words, and lines.
 */
@ManagedBean
@RequestScoped
public class TextFileAnalyzer {

    private String filePath;
    private long totalCharacters;
    private long totalWords;
    private long totalLines;

    /**
     * Default constructor
     */
    public TextFileAnalyzer() {
    }

    /**
     * Analyzes the content of the text file specified by the filePath.
     * @return A list of strings containing the analysis results.
     */
    public List<String> analyzeFileContent() {
        List<String> results = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int charCount = 0;
            int wordCount = 0;
            while ((line = reader.readLine()) != null) {
                charCount += line.length();
                String[] words = line.split("\s+");
                wordCount += words.length;
                totalLines++;
            }
            totalCharacters = charCount;
            totalWords = wordCount;
            totalLines = reader.lines().count();

            results.add("Total Characters: " + totalCharacters);
            results.add("Total Words: " + totalWords);
            results.add("Total Lines: " + totalLines);

        } catch (IOException e) {
            results.add("Error reading file: " + e.getMessage());
        }
        return results;
    }

    // Getters and setters
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getTotalCharacters() {
        return totalCharacters;
    }

    public long getTotalWords() {
        return totalWords;
    }

    public long getTotalLines() {
        return totalLines;
    }
}
