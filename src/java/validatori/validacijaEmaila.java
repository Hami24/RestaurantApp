package validatori;

import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("validacijaEmaila")
public class validacijaEmaila implements Validator{

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     if (o == null) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Polje za email adresu je null.", "Polje za  email adresu je null."));
          
       }
        String s = o.toString();
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                                                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern p = Pattern.compile(EMAIL_PATTERN);
        if(!p.matcher(s).matches())
        {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email adresa nije validna.", "Email adresa nije validna"));
        }
        
        
      
    }
}
