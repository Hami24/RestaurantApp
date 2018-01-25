package Funkcije;
import java.io.InputStream;
import java.io.OutputStream;
import javax.faces.context.FacesContext;
import javax.el.ELContext;

public class Funkcija {
    public static Object getBeanObject(String id) {
        ELContext fcCon = FacesContext.getCurrentInstance().getELContext();
        return fcCon.getELResolver().getValue(fcCon, null, id);
    }
     public static void inputStream2outputStream(InputStream is, OutputStream os) {
        if(is != null) {
            int read = 0;
            
            byte[] bytes = new byte[1024];
            
            try {
                while((read = is.read(bytes)) != -1) {
                    os.write(bytes, 0, read);
                }
            } catch(Exception ex) {
                
            }
        }
    }
}
