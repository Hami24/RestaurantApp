package beans;
import Baza.Rezervacija;
import Baza.Stol;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
@ManagedBean
@SessionScoped

public class Rezervacije {
    private Rezervacija rezervacija=new Rezervacija();
    private List<Rezervacija> rezervacije;
    private List<Rezervacija> mojeRezervacije;
    private Stol s = new Stol();
    @ManagedProperty(value="#{prijava}")
    private Prijava prijava = new Prijava();
    
    public Rezervacije()
    {
        rezervacije=Rezervacija.getSveRezervacije();
    }
    
    public String mojeRez()
    {
        mojeRezervacije=Rezervacija.mojeRezervacije(prijava.getKorisnik().getKorisnici_id());
        return "mojeRezervacije.xhtml";
    }
    public Rezervacija getRezervacija() {
        return rezervacija;
    }

    public void setRezervacija(Rezervacija rezervacija) {
        this.rezervacija = rezervacija;
    }

    public List<Rezervacija> getRezervacije() {
        return rezervacije;
    }

    public void setRezervacije(List<Rezervacija> rezervacije) {
        this.rezervacije = rezervacije;
    }

    public List<Rezervacija> getMojeRezervacije() {
        return mojeRezervacije;
    }

    public void setMojeRezervacije(List<Rezervacija> mojeRezervacije) {
        this.mojeRezervacije = mojeRezervacije;
    }

    public Stol getS() {
        return s;
    }

    public void setS(Stol s) {
        this.s = s;
    }

    public Prijava getPrijava() {
        return prijava;
    }

    public void setPrijava(Prijava prijava) {
        this.prijava = prijava;
    }
    
    
    public void rezervisi()
    {
        
        this.rezervacija.dodajRezervaciju(prijava.getKorisnik(),this.s);
        rezervacije=Rezervacija.getSveRezervacije();
        mojeRezervacije=Rezervacija.mojeRezervacije(prijava.getKorisnik().getKorisnici_id());
    }
}
