package beans;

import Baza.Korisnik;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


@ManagedBean
@SessionScoped
public class Prijava {
    private Korisnik korisnik=new Korisnik();

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }
    private boolean prijavljen;
    
    public Prijava() {
        this.prijavljen = false;
    }

    public boolean isPrijavljen() {
        return prijavljen;
    }

    public void setPrijavljen(boolean prijavljen) {
        this.prijavljen = prijavljen;
    }
    
    public String prijavaStr() {
        Korisnik k = Korisnik.getKorByImeAndLozinka(this.korisnik.getKorIme(), 
                this.korisnik.getLozinka());
        System.out.println(this.korisnik.getKorIme());
       
      
        if(k != null) {
            this.korisnik=k;
            
            if("Admin".equals(this.korisnik.getTip_korisnika()))
            {
                this.prijavljen = true;
                return "adminStranica?faces-redirect=true";
            }
            else 
            {
                this.prijavljen = true;
                System.out.println(this.korisnik.getKorIme());
                return "korisnikStranica?faces-redirect=true";
            }
          }
        else {
            
           FacesContext context = FacesContext.getCurrentInstance();
         
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Netacna kombinacija korisnickog imena i lozinke", "Unesite vase podatke ponovo"));
            this.prijavljen = false;          
            return null;
            }
    }
   
    public String odjava() {
        this.prijavljen = false;
        this.korisnik.setTip_korisnika("");
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        String a="login?faces-redirect=true";
        System.out.println(a);
        return a;
    }
}
