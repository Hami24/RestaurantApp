package Listeners;

import beans.Upload;
import Funkcije.Funkcija;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileDownloadListener implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent pe) {

    }

    @Override
    public void beforePhase(PhaseEvent pe) {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if (req.getRequestURI().endsWith("download.xhtml")) {
            Upload up = (Upload) Funkcija.getBeanObject("upload");
            if (up.getFileName() != null) {
                try {
                    File f = new File("C:/" + up.getFileName());
                    FileInputStream fis = new FileInputStream(f);
                    HttpServletResponse hsr = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                    String contentType = req.getServletContext().getMimeType(up.getFileName());
                    if (contentType == null) {
                        hsr.setContentType("application/force-download;");
                    } else {
                        hsr.setContentType(contentType);
                    }
                    hsr.setHeader("Content-Disposition", "Attachment;filename=" + up.getFileName());
                    hsr.setContentLengthLong(f.length());
                    OutputStream os = hsr.getOutputStream();
                    Funkcija.inputStream2outputStream(fis, hsr.getOutputStream());
                    os.flush();
                    os.close();
                    fis.close();
                } catch (Exception ex) {
                    ex.printStackTrace(System.out);
                }
                FacesContext.getCurrentInstance().responseComplete();
            }
        }
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

}
