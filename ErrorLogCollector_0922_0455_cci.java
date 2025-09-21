// 代码生成时间: 2025-09-22 04:55:06
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class ErrorLogCollector {
    
    // Logger for logging any errors
    private static final Logger LOGGER = Logger.getLogger(ErrorLogCollector.class.getName());

    /**
     * Collects errors and logs them to a file.
     * 
     * @param error The error to be logged.
     */
    public void collectError(Exception error) {
        try {
            // Getting the current date and time
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timestamp = dateFormat.format(new Date());
            
            // Creating the log message
            String logMessage = "Error occurred at " + timestamp + ": " + error.getMessage();
            
            // Logging the error to the console
            LOGGER.severe(logMessage);
            
            // Getting the current JSF context
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", error.getMessage()));
            
            // Writing the log message to a file
            FileWriter fileWriter = new FileWriter("error_log.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(logMessage);
            printWriter.close();
            
        } catch (IOException e) {
            // Handling any IO exceptions that occur while writing to the file
            LOGGER.severe("Error writing to log file: " + e.getMessage());
        }
    }

    // Additional methods can be added here for more functionality
    
    // For example, a method to clear the error log file
    public void clearErrorLog() {
        try {
            FileWriter fileWriter = new FileWriter("error_log.txt");
            fileWriter.close();
        } catch (IOException e) {
            LOGGER.severe("Error clearing log file: " + e.getMessage());
        }
    }
}