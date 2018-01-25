package beans;
import Baza.Narudzba;
import Baza.Jelo;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
@ManagedBean
@SessionScoped

public class Narudzbe {
    private Narudzba narudzba= new Narudzba();
    private Jelo j = new Jelo();
    private List<Narudzba> narudzbe;
    private List<Narudzba> mojeNarudzbe;
    @ManagedProperty(value="#{prijava}")
    Prijava prijava = new Prijava();

    public Narudzbe()
    {
        narudzbe=Narudzba.getSveNarudzbe();
      
    }
    
    public String mojeNar()
    {
          mojeNarudzbe=Narudzba.getMojeNarudzbe(prijava.getKorisnik().getKorisnici_id());
          return "mojeNarudzbe.xhtml";
    }
    
    public Narudzba getNarudzba() {
        return narudzba;
    }

    public void setNarudzba(Narudzba narudzba) {
        this.narudzba = narudzba;
    }

    public List<Narudzba> getNarudzbe() {
        return narudzbe;
    }

    public void setNarudzbe(List<Narudzba> narudzbe) {
        this.narudzbe = narudzbe;
    }

    public Prijava getPrijava() {
        return prijava;
    }

    public void setPrijava(Prijava prijava) {
        this.prijava = prijava;
    }

    public Jelo getJ() {
        return j;
    }

    public void setJ(Jelo j) {
        this.j = j;
    }

    public List<Narudzba> getMojeNarudzbe() {
        return mojeNarudzbe;
    }

    public void setMojeNarudzbe(List<Narudzba> mojeNarudzbe) {
        this.mojeNarudzbe = mojeNarudzbe;
    }
    
     public void naruci(Jelo j)
    {
        this.j=j;
        this.narudzba.dodajNarudzbu(prijava.getKorisnik(),this.j);
        narudzbe=Narudzba.getSveNarudzbe();
        mojeNarudzbe=Narudzba.getMojeNarudzbe(prijava.getKorisnik().getKorisnici_id());
    }
}
