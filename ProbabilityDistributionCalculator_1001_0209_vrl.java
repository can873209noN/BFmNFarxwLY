// 代码生成时间: 2025-10-01 02:09:24
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * ManagedBean for calculating probability distributions.
 */
@ManagedBean
@RequestScoped
public class ProbabilityDistributionCalculator {

    private Map<String, Double> probabilityDistribution;
    private int numberOfTrials;
    private int numberOfSuccesses;
    private String errorMessage;

    /**
     * Default constructor.
     */
    public ProbabilityDistributionCalculator() {
        probabilityDistribution = new HashMap<>();
    }

    /**
     * Calculate the probability distribution based on the number of trials and successes.
     * 
     * @return A map of outcomes and their respective probabilities.
     */
    public Map<String, Double> calculateProbability() {
        try {
            Random random = new Random();
            int total = 0;
            for (int i = 0; i < numberOfTrials; i++) {
                if (random.nextDouble() < (numberOfSuccesses / (double) numberOfTrials)) {
                    total++;
                }
            }
            double probability = (double) total / numberOfTrials;
            probabilityDistribution.put("Success Probability", probability);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error calculating probability: " + e.getMessage(), ""));
            errorMessage = e.getMessage();
        }
        return probabilityDistribution;
    }

    /**
     * Getters and Setters.
     */
    public Map<String, Double> getProbabilityDistribution() {
        return probabilityDistribution;
    }

    public void setProbabilityDistribution(Map<String, Double> probabilityDistribution) {
        this.probabilityDistribution = probabilityDistribution;
    }

    public int getNumberOfTrials() {
        return numberOfTrials;
    }

    public void setNumberOfTrials(int numberOfTrials) {
        this.numberOfTrials = numberOfTrials;
    }

    public int getNumberOfSuccesses() {
        return numberOfSuccesses;
    }

    public void setNumberOfSuccesses(int numberOfSuccesses) {
        this.numberOfSuccesses = numberOfSuccesses;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}