// 代码生成时间: 2025-09-24 01:10:35
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DataAnalysisManager is a JavaServer Faces bean that provides data analysis functionality.
 * It is designed to be easily understandable, maintainable, and extensible.
 */
@ManagedBean
@ViewScoped
public class DataAnalysisManager {

    private static final Logger LOGGER = Logger.getLogger(DataAnalysisManager.class.getName());

    // List to hold data points for analysis
    private List<DataPoint> dataPoints = new ArrayList<>();

    /**
     * Represents a data point with a value.
     */
    public static class DataPoint {
        private double value;

        public DataPoint(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }
    }

    /**
     * Adds a data point to the analysis.
     * @param value the value of the data point
     */
    public void addDataPoint(double value) {
        try {
            dataPoints.add(new DataPoint(value));
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to add data point", e);
        }
    }

    /**
     * Calculates the mean of the data points.
     * @return the mean value of the data points
     */
    public double calculateMean() {
        try {
            if (dataPoints.isEmpty()) {
                return 0;
            }
            double sum = 0;
            for (DataPoint dp : dataPoints) {
                sum += dp.getValue();
            }
            return sum / dataPoints.size();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to calculate mean", e);
            return 0;
        }
    }

    /**
     * Calculates the median of the data points.
     * @return the median value of the data points
     */
    public double calculateMedian() {
        try {
            if (dataPoints.isEmpty()) {
                return 0;
            }
            List<Double> values = new ArrayList<>();
            for (DataPoint dp : dataPoints) {
                values.add(dp.getValue());
            }
            values.sort(null);
            int middle = values.size() / 2;
            if (values.size() % 2 == 0) {
                return (values.get(middle - 1) + values.get(middle)) / 2;
            } else {
                return values.get(middle);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to calculate median", e);
            return 0;
        }
    }

    /**
     * Gets the list of data points.
     * @return the list of data points
     */
    public List<DataPoint> getDataPoints() {
        return dataPoints;
    }

    /**
     * Sets the list of data points.
     * @param dataPoints the list of data points
     */
    public void setDataPoints(List<DataPoint> dataPoints) {
        this.dataPoints = dataPoints;
    }
}
