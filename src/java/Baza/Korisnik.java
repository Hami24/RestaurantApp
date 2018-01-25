
package Baza;

import Baza.konekcija.MySQLpodaci;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
@Entity
@Table(name="korisnici")
public class Korisnik {
   @Id
   @Column(name="korisnici_id") 
    private int korisnici_id;
   @Column(name="korIme")
    private String korIme;
   @Column(name="lozinka")
    private String lozinka;
   @Column(name="ime")
    private String ime;
   @Column(name="prezime")
    private String prezime;
   @Column(name="email")
    private String email;
   @Column(name="adresa")
    private String adresa;
   @Column(name="tip_korisnika")
    private String tip_korisnika;
    @OneToMany(mappedBy = "korisnik", cascade = CascadeType.ALL)
    
    
    private List<Rezervacija> rezervacija;
    @OneToMany(mappedBy = "korisnik", cascade = CascadeType.ALL)
    private List<Narudzba> narudzba;

    public List<Narudzba> getNarudzba() {
        return narudzba;
    }

    public void setNarudzba(List<Narudzba> narudzba) {
        this.narudzba = narudzba;
    }
    
    
    public List<Rezervacija> getRezervacija() {
        return rezervacija;
    }

    public void setRezervacija(List<Rezervacija> rezervacija) {
        this.rezervacija = rezervacija;
    }
    
    
    public Korisnik()
    {
        
    }
    public Korisnik(int korisnici_id, String korIme, String lozinka, String ime, String prezime, String email, String adresa, String tip_korisnika) {
        this.korisnici_id = korisnici_id;
        this.korIme = korIme;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.adresa = adresa;
        this.tip_korisnika = tip_korisnika;
    }

   
   
    public int getKorisnici_id() {
        return korisnici_id;
    }

    public void setKorisnici_id(int korisnici_id) {
        this.korisnici_id = korisnici_id;
    }

    public String getKorIme() {
        return korIme;
    }

    public void setKorIme(String korIme) {
        this.korIme = korIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getTip_korisnika() {
        return tip_korisnika;
    }

    public void setTip_korisnika(String tip_korisnika) {
        this.tip_korisnika = tip_korisnika;
    }
    
    public boolean dodajKorisnik() {
        try{
            SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                                                .buildSessionFactory();

            Session session = factory.getCurrentSession();
            session.beginTransaction();
            List <Korisnik> kor = session.createQuery("from Korisnik").list();
            for(int i=0;i<kor.size();i++)
            {
                if(korIme.equals(kor.get(i).getKorIme()))
                {
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage("Neuspješno",  "Postoji korisnik s tim korisničkim imenom!") );
                    return false;
                }
            }
            
            
            
            Korisnik k = new Korisnik(0,korIme,lozinka,ime,prezime,email,adresa,tip_korisnika);
            
            
           
            session.save(k);
            session.getTransaction().commit();

            factory.close();
            FacesContext context = FacesContext.getCurrentInstance();
         
            context.addMessage(null, new FacesMessage("Uspješno",  "Korisnik dodan!") );
            return true;
            
            }
            
          
           
         catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Greska2");
            return false;
        }
    }
    
    public boolean azurirajKorisnika() {
       try{
            SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                                                .buildSessionFactory();

            Session session = factory.getCurrentSession();
           
            session.beginTransaction();
       
            String hql = "Update Korisnik set korIme=:korIme, lozinka=:lozinka, ime=:ime,"
                    + " prezime=:prezime, email=:email, adresa=:adresa, tip_korisnika=:tip_korisnika where korisnici_id=:korisnici_id";
            Query query = session.createQuery(hql);
            query.setParameter("korIme",korIme);
            query.setParameter("lozinka",lozinka);
            query.setParameter("ime",ime);
            query.setParameter("prezime",prezime);
            query.setParameter("email",email);
            query.setParameter("adresa",adresa);
            query.setParameter("tip_korisnika",tip_korisnika);
            query.setParameter("korisnici_id",korisnici_id);
            query.executeUpdate();
            session.getTransaction().commit();

            factory.close();
            FacesContext context = FacesContext.getCurrentInstance();
         
            context.addMessage(null, new FacesMessage("Uspješno",  "Korisnik ažuriran!") );
            return true;
            
            }
            
          
           
         catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Greska2");
            return false;
        }
    }
    
    public static List<Korisnik> getSveKorisnike() {
        
        try{
            SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                                                .buildSessionFactory();
    
            Session session = factory.getCurrentSession();

            session.beginTransaction();
            List <Korisnik> kor =session.createQuery("from Korisnik").list();
        
            session.getTransaction().commit();

            factory.close();
            return kor;
            
            }
            
          
           
         catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Greska2");
            return null;
        }
    }
    
    public static  Korisnik getKorByImeAndLozinka(String korIme, String lozinka) {
       
        try{
           SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                                                .buildSessionFactory();
    
            Session session = factory.getCurrentSession();
    
  
        
            session.beginTransaction();
            String hql="FROM Korisnik where korIme like:korime and lozinka like:lozinka";
            Query query =session.createQuery(hql);
            query.setParameter("korime", korIme);
            query.setParameter("lozinka",lozinka);
            List<Korisnik> kor = query.list();
            Korisnik novi = kor.get(0);
       
            session.getTransaction().commit();
        
  
   
            factory.close();
            return novi;
            
            }
            
         
            
        catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Greska2");
            return null;
        }
    }
   public boolean obrisi(Korisnik k) {
          try
          {
            SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                                                .buildSessionFactory();
    
            Session session = factory.getCurrentSession();

            session.beginTransaction();
            
            String hql="delete FROM Korisnik where korisnici_id=:id";
            Query query =session.createQuery(hql);
            query.setParameter("id", k.getKorisnici_id());
            query.executeUpdate();
            System.out.println(k.getKorisnici_id());
            session.getTransaction().commit();
            FacesContext context = FacesContext.getCurrentInstance();
         
            context.addMessage(null, new FacesMessage("Uspješno",  "Korisnik obrisan!") );
            factory.close();
            

            return true;
          } 
          catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Greska2");
            return false;
        }
    }
}
