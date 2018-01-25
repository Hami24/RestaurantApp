package Baza;
import Baza.konekcija.MySQLpodaci;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

@Entity
@Table(name="narudzbe")
public class Narudzba {
    
    @Id
    private int id_narudzbe;
    
    
    @ManyToOne
    @JoinColumn(name="korisnici_id_nar",nullable=false)
    private Korisnik korisnik;
    
    @ManyToOne
    @JoinColumn(name="id_jela_nar",nullable=false)
    private Jelo jelo;
    
    @Column
    @CreationTimestamp
    @Type(type="timestamp")
    private Date datum_narudzbe;
    
    @Transient
    private String ime;
    @Transient
    private String prezime;
    @Transient
    private String korIme;
    @Transient
    private String naziv;
    @Transient
    private String slika;

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Jelo getJelo() {
        return jelo;
    }

    public void setJelo(Jelo jelo) {
        this.jelo = jelo;
    }

    
    
    
    public int getId_narudzbe() {
        return id_narudzbe;
    }

    public void setId_narudzbe(int id_narudzbe) {
        this.id_narudzbe = id_narudzbe;
    }

 
    @Transient
    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }
    @Transient
    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
    @Transient
    public String getKorIme() {
        return korIme;
    }

    public void setKorIme(String korIme) {
        this.korIme = korIme;
    }
    @Transient
    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    @Transient
    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public Date getDatum_narudzbe() {
        return datum_narudzbe;
    }

    public void setDatum_narudzbe(Date datum_narudzbe) {
        this.datum_narudzbe = datum_narudzbe;
    }

    
    public static List<Narudzba> getSveNarudzbe() {
        try{
            SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                                                .buildSessionFactory();
    
            Session session = factory.getCurrentSession();
            
            session.beginTransaction();
            String hql="select k from Narudzba k join fetch k.korisnik join fetch k.jelo ";
            Query query =session.createQuery(hql);
            List<Narudzba> upit = query.list();
            List<Narudzba> rs = new LinkedList<>();
            Narudzba r = null;
            for(int i=0;i<upit.size();i++)
            {
                r = new Narudzba();
                r.setId_narudzbe(upit.get(i).getId_narudzbe());
                r.setIme(upit.get(i).getKorisnik().getIme());
                r.setPrezime(upit.get(i).getKorisnik().getPrezime());
                r.setKorIme(upit.get(i).getKorisnik().getKorIme());
                r.setNaziv(upit.get(i).getJelo().getNaziv());
                r.setSlika(upit.get(i).getSlika());
                r.setDatum_narudzbe(upit.get(i).getDatum_narudzbe());
                rs.add(r);
            }

          
            session.getTransaction().commit();
            
           
            factory.close();
            return rs;
            
            }
            
          
           
         catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Greska2");
            return null;
        }
    }
    
    public boolean dodajNarudzbu(Korisnik k,Jelo j) {
       SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                                                        .buildSessionFactory();
                                                     
         
            
            Session session = factory.getCurrentSession();
            Narudzba r = new Narudzba();
            r.setKorisnik(k);
            r.setJelo(j);
                  
            session.beginTransaction();
            session.save(r);
            
  
            session.getTransaction().commit();
            FacesContext context = FacesContext.getCurrentInstance();
         
            context.addMessage(null, new FacesMessage("Uspješno",  "Naručeno!") );
            factory.close();
            
            return true;
    }
    
    public static List<Narudzba> getMojeNarudzbe(int k_id) {
       try{
            SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                                                .buildSessionFactory();
    
            Session session = factory.getCurrentSession();
            
            session.beginTransaction();
            String hql="select k from Narudzba k join fetch k.korisnik join fetch k.jelo where k.korisnik.id=:id ";
            Query query =session.createQuery(hql);
            query.setParameter("id",k_id );
            List<Narudzba> upit = query.list();
            List<Narudzba> rs = new LinkedList<>();
            Narudzba r = null;
            for(int i=0;i<upit.size();i++)
            {
                r = new Narudzba();
                r.setId_narudzbe(upit.get(i).getId_narudzbe());
                r.setIme(upit.get(i).getKorisnik().getIme());
                r.setPrezime(upit.get(i).getKorisnik().getPrezime());
                r.setKorIme(upit.get(i).getKorisnik().getKorIme());
                r.setNaziv(upit.get(i).getJelo().getNaziv());
                r.setSlika(upit.get(i).getSlika());
                r.setDatum_narudzbe(upit.get(i).getDatum_narudzbe());
                rs.add(r);
            }

          
            session.getTransaction().commit();
            
           
            factory.close();
            return rs;
            
            }
            
          
           
         catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Greska2");
            return null;
        }
    }
}
