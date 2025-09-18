// 代码生成时间: 2025-09-19 04:18:16
package com.example.networkchecker;

import java.net.HttpURLConnection;
import java.net.URL;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * Managed Bean for network connection status check.
 */
@ManagedBean
@RequestScoped
public class NetworkConnectionChecker {

    private String urlToCheck;
    private String connectionStatus;

    // Default constructor
    public NetworkConnectionChecker() {
    }

    /**
     * Setter method for the URL to check.
     * @param urlToCheck The URL to be checked for network connectivity.
     */
    public void setUrlToCheck(String urlToCheck) {
        this.urlToCheck = urlToCheck;
    }

    /**
     * Getter method for the connection status.
     * @return The status of the network connection.
     */
    public String getConnectionStatus() {
        return connectionStatus;
    }

    /**
     * Action method to check network connection status.
     * @return The navigation outcome based on the connection status.
     */
    public String checkConnection() {
        try {
            URL url = new URL(urlToCheck);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                connectionStatus = "Connected";
            } else {
                connectionStatus = "Not connected";
            }
        } catch (Exception e) {
            connectionStatus = "Error: Unable to check connection";
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
        return "success";
    }
}
