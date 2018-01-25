
package validatori;

import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("validacijaNazivJela")
public class validacijaNazivJela implements Validator{

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     if (o == null) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Polje za naziv jela je null.", "Polje za naziv jela je null."));
          
       }
        String s = o.toString();
        Pattern p = Pattern.compile("[a-zA-Z ]*?");
        if(!p.matcher(s).matches())
        {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Naziv jela nije validan.", "Naziv jela nije validan"));
        }
      
    }
}
