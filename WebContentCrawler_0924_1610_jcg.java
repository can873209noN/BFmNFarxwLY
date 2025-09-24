// 代码生成时间: 2025-09-24 16:10:27
 * It includes error handling and is structured for easy understanding and maintenance.
 */

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.IOException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@ManagedBean
@SessionScoped
public class WebContentCrawler {

    // The URL of the web page to crawl
    private String url;

    // The content of the web page
    private String content;

    // Setter for the URL
    public void setUrl(String url) {
        this.url = url;
    }

    // Getter for the URL
    public String getUrl() {
        return url;
    }

    // Getter for the content
    public String getContent() {
        return content;
    }

    /**
     * Crawl the web page and extract its content.
     *
     * @throws IOException If an I/O error occurs during the crawl.
     */
    public void crawl() throws IOException {
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("URL must not be null or empty.");
        }

        try {
            // Connect to the URL
            URL urlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            // Read the response
            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new IOException("Failed to connect to the webpage. HTTP error code: " + responseCode);
            }

            // Use Jsoup to parse the HTML content
            Document doc = Jsoup.connect(url).get();
            content = doc.body().html();

        } catch (IOException e) {
            // Log and rethrow the exception
            throw new IOException("Error while crawling the webpage: " + e.getMessage(), e);
        }
    }

    /**
     * Download the content to a local file.
     *
     * @param filePath The path to the local file where the content will be saved.
     * @throws IOException If an I/O error occurs during the download.
     */
    public void downloadContent(String filePath) throws IOException {
        if (content == null || content.isEmpty()) {
            throw new IllegalStateException("Content must be crawled before downloading.");
        }

        Path path = Paths.get(filePath);
        Files.write(path, content.getBytes(StandardCharsets.UTF_8));
    }
}
