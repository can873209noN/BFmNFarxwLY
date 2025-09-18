// 代码生成时间: 2025-09-18 14:56:50
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.net.URL;
import java.net.MalformedURLException;

/**
 * Managed bean for validating URL links.
 */
@ManagedBean
@RequestScoped
@FacesValidator("urlValidator")
public class UrlValidatorBean implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        // Check if the value is null or empty to avoid unnecessary validation
        if (value == null || ((String) value).trim().isEmpty()) {
            return;
        }

        // Attempt to construct a URL object from the given value
        try {
            new URL((String) value);
        } catch (MalformedURLException e) {
            // If the URL is malformed, add a FacesMessage as a validation error
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid URL", "The provided URL is not valid.");
            throw new ValidatorException(message);
        }
    }
}
