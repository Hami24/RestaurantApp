package Listeners;

import Funkcije.Funkcija;
import beans.Prijava;
import java.util.LinkedList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class LoginListener implements PhaseListener {
    @Override
    public void afterPhase(PhaseEvent pe) {
        String viewId = pe.getFacesContext().getViewRoot().getViewId();
        System.out.println("View Id " + viewId);
        
        List <String> zasticeneStranice = new LinkedList<>();
       
        zasticeneStranice.add("/adminStranica.xhtml");
        zasticeneStranice.add("/dodajJelo.xhtml");
        zasticeneStranice.add("/dodajKorisnika.xhtml");
        zasticeneStranice.add("/dodajStol.xhtml");
        zasticeneStranice.add("/ispisJela.xhtml");
        zasticeneStranice.add("/ispisKorisnika.xhtml");
        zasticeneStranice.add("/ispisRezervacija.xhtml");
        zasticeneStranice.add("/korisnikStranica.xhtml");
        zasticeneStranice.add("/mojeNarudzbe.xhtml");
        zasticeneStranice.add("/mojeRezervacije.xhtml");
        zasticeneStranice.add("/naruci.xhtml");
        zasticeneStranice.add("/prikazStolova.xhtml");
        zasticeneStranice.add("/rezervisi.xhtml");
        zasticeneStranice.add("/ispisNarudzbi.xhtml");
        
        
        if(zasticeneStranice.contains(viewId)) {
            Prijava p = (Prijava) Funkcija.getBeanObject("prijava");
            if(p.isPrijavljen() == false){
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.getApplication().getNavigationHandler()
               .handleNavigation(fc, "login?faces-redirect=true","login?faces-redirect=true");
                fc.responseComplete();
            }
           
            
        }
    }

    @Override
    public void beforePhase(PhaseEvent pe) {
        
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

}
