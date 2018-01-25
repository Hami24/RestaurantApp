package beans;

import Funkcije.Funkcija;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.event.FileUploadEvent;

@ManagedBean
@SessionScoped
public class Upload {
    private String fileName;
    private boolean uploaded = false;
    
    public void fileDownload() {
        Upload up = (Upload) Funkcija.getBeanObject("upload");
        
        if(up.getFileName() != null) {
           try {
               System.out.println("Download datoteke");
               
               FileInputStream fis = new FileInputStream("C:/" + up.getFileName());
               
               HttpServletResponse hsr = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
               hsr.setContentType("application/force-download");
               hsr.setHeader("Content-Disposition", "Attachment;filename=" + up.getFileName());
               hsr.setContentLength(fis.available());
               
               OutputStream os = hsr.getOutputStream();
               Funkcija.inputStream2outputStream(fis, hsr.getOutputStream());
               os.flush();
               os.close();
               fis.close();
           } catch(Exception ex) {
               ex.printStackTrace(System.out);
           }
           
           System.out.println("Download complete " + up.getFileName());
           FacesContext.getCurrentInstance().responseComplete();
        }
    }
    
    public void onFileUpload(FileUploadEvent event) {
        System.out.println("Upload datoteke");
        
        if(event.getFile().getSize() > 0) {
            String fn = event.getFile().getFileName();
            String xfn = fn.contains(".")?fn.substring(0, fn.lastIndexOf(".")):fn;
            String yfn = fn.contains(".")?fn.substring(fn.lastIndexOf(".")):"";
            
            File f = new File("C:/Users/Muhamed/Documents/NetBeansProjects/OOBP_projekat/web/slike/" + fn);
            int i = 0;
            
            while(f.exists()) {
                fn = (xfn) + (i++) + yfn;
                f = new File("C:/Users/Muhamed/Documents/NetBeansProjects/OOBP_projekat/web/slike/" + fn);
            }
            
            fileName = fn;
            
            System.out.println(fn + ":" + event.getFile().getSize());
            
            try {
                FileOutputStream fos = new FileOutputStream(f);
                
                Funkcija.inputStream2outputStream(event.getFile().getInputstream(), fos);
                
                fos.flush();
                fos.close();
                event.getFile().getInputstream().close();
            } catch(Exception ex) {
                ex.printStackTrace(System.out);
            }
        }
        this.uploaded = true;
        System.out.println(getFileName());
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isUploaded() {
        return uploaded;
    }

    public void setUploaded(boolean uploaded) {
        this.uploaded = uploaded;
    }
}
