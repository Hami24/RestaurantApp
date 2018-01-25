package beans;

import Baza.Korisnik;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Korisnici {
    
    private List<Korisnik> kors;
    @ManagedProperty(value="#{prijava}")
    private Prijava prijava = new Prijava();
    private Korisnik korisnik = new Korisnik();
    
    public Korisnici() {
       
        kors = Korisnik.getSveKorisnike();
    }
    public Prijava getPrijava() {
        return prijava;
    }

    public void setPrijava(Prijava prijava) {
        this.prijava = prijava;
    }
   
    public void urediKorisnika(Korisnik k) {
        this.korisnik = k;
    }

    public List<Korisnik> getKors() {
        return kors;
    }

    public void setKors(List<Korisnik> kors) {
        this.kors = kors;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }
    
    public void obrisiKorisnika(Korisnik k)
    {
            this.korisnik.obrisi(k);
            this.kors.remove(k);
    }
    public void dodajKorisnika()
    {
        this.korisnik.dodajKorisnik();
        this.kors=Korisnik.getSveKorisnike();
    }
 
    
    public void snimi() {
 
        if(this.korisnik.getKorisnici_id() == 0) {
           this.korisnik.dodajKorisnik();
        } else {
            this.korisnik.azurirajKorisnika();
        }
        this.kors = Korisnik.getSveKorisnike();
      
    }
  
}
