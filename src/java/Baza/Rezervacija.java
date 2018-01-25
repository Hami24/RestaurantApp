package Baza;
import Baza.konekcija.MySQLpodaci;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.hibernate.annotations.Type;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


/**
 *
 * @author Hami
 */
@Entity
@Table(name="rezervacije")
public class Rezervacija {
   
    @Id
    private int id_rezervacije;
    
    @ManyToOne
    @JoinColumn(name="korisnici_id",nullable=false)
    private Korisnik korisnik;
    @ManyToOne
    @JoinColumn(name="id_stolovi",nullable=false)
    private Stol stol;
    
    @Column
    @Type(type="date")
    private Date datum;
    
    @Type(type="time")
    private Date vrijeme_pocetka ;
    @Type(type="time")
    private Date vrijeme_kraj ;
 
     @Transient
     private String ime;
     @Transient
     private String prezime;
     @Transient
     private String korIme;
     @Transient
     private String naziv;  

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Stol getStol() {
        return stol;
    }

    public void setStol(Stol stol) {
        this.stol = stol;
    }

    /*public static void main(String [] args)
    {
        try{
            SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                                                .buildSessionFactory();
    
            Session session = factory.getCurrentSession();
            
            session.beginTransaction();
            String hql="select k from Rezervacija k join fetch k.korisnik join fetch k.stol ";
            Query query =session.createQuery(hql);
            List<Rezervacija> upit = query.list();
            List<Rezervacija> rs = new LinkedList<>();
            Rezervacija r = null;
            for(int i=0;i<upit.size();i++)
            {
                r = new Rezervacija();
                r.setId_rezervacije(upit.get(i).getId_rezervacije());
                r.setIme(upit.get(i).getKorisnik().getIme());
                r.setPrezime(upit.get(i).getKorisnik().getPrezime());
                r.setNaziv(upit.get(i).getStol().getNaziv());
                rs.add(r);
            }

          
            session.getTransaction().commit();
            
            return rs;
            factory.close();
            
            
            }
            
          
           
         catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Greska2");
            return null;
        }
    }*/
    
    public int getId_rezervacije() {
        return id_rezervacije;
    }

    public void setId_rezervacije(int id_rezervacije) {
        this.id_rezervacije = id_rezervacije;
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

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Date getVrijeme_pocetka() {
        return vrijeme_pocetka;
    }

    public void setVrijeme_pocetka(Date vrijeme_pocetka) {
        this.vrijeme_pocetka = vrijeme_pocetka;
    }

    public Date getVrijeme_kraj() {
        return vrijeme_kraj;
    }

    public void setVrijeme_kraj(Date vrijeme_kraj) {
        this.vrijeme_kraj = vrijeme_kraj;
    }

   
  
  
    
    
    public static List<Rezervacija> getSveRezervacije() {
        try{
            SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                                                .buildSessionFactory();
    
            Session session = factory.getCurrentSession();
            
            session.beginTransaction();
            String hql="select k from Rezervacija k join fetch k.korisnik join fetch k.stol ";
            Query query =session.createQuery(hql);
            List<Rezervacija> upit = query.list();
            List<Rezervacija> rs = new LinkedList<>();
            Rezervacija r = null;
            for(int i=0;i<upit.size();i++)
            {
                r = new Rezervacija();
                r.setId_rezervacije(upit.get(i).getId_rezervacije());
                r.setIme(upit.get(i).getKorisnik().getIme());
                r.setPrezime(upit.get(i).getKorisnik().getPrezime());
                r.setNaziv(upit.get(i).getStol().getNaziv());
                r.setDatum(upit.get(i).getDatum());
                r.setVrijeme_pocetka(upit.get(i).getVrijeme_pocetka());
                r.setVrijeme_kraj(upit.get(i).getVrijeme_kraj());
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
    
    
    public boolean dodajRezervaciju(Korisnik k,Stol s) {
            SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                                                        .buildSessionFactory();
            System.out.println(s.getNaziv());
            String hql = "from Rezervacija r where r.datum = :datum and r.stol.id_stolovi = :id" ;
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            Query query = session.createQuery(hql);
       
            query.setParameter("datum", datum);
            query.setParameter("id",s.getId_stolovi());
            System.out.println(datum);
            int brojac = 0;
            List <Rezervacija> lista = query.list();
           
            long novi_vp = vrijeme_pocetka.getTime();
            long novi_vk = vrijeme_kraj.getTime();
            for(int i=0;i<lista.size();i++)
            {
               System.out.println(lista.get(i).getVrijeme_pocetka().getTime());
               long vp = lista.get(i).getVrijeme_pocetka().getTime();
               long vk = lista.get(i).getVrijeme_kraj().getTime();
               
               System.out.println(vp);
               System.out.println(vk);
               System.out.println(novi_vp);
               System.out.println(novi_vk);
               
               if(novi_vp < vp && novi_vk<=vp || novi_vp>=vk && novi_vk > vk)
               {
                  System.out.println("Sve je ok");
                  
               }
               else
               {
                   brojac++;
               }
                
            }
            if(vrijeme_pocetka.getTime()>vrijeme_kraj.getTime())
              {
                  FacesContext context = FacesContext.getCurrentInstance();
         
                context.addMessage(null, new FacesMessage("Neuspješno",  "Vrijeme početka rezervacije mora biti prije vremena završetka rezervacije!") );
                return false;
              }
            if(brojac==0)
            {
              
                Rezervacija r = new Rezervacija();
                r.setKorisnik(k);
                r.setStol(s);
                System.out.println(s.getNaziv());
          
           /* DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
           
            Date today = Calendar.getInstance().getTime();        
            String reportDate = df.format(today); 
            */
            
                r.setDatum(datum);
                r.setVrijeme_pocetka(vrijeme_pocetka);
                r.setVrijeme_kraj(vrijeme_kraj);
                session.save(r);
                session.getTransaction().commit();
                FacesContext context = FacesContext.getCurrentInstance();
         
                context.addMessage(null, new FacesMessage("Uspješno",  "Rezervisano!") );
                factory.close();
                return true;
            }
           
            FacesContext context = FacesContext.getCurrentInstance();
         
            context.addMessage(null, new FacesMessage("Neuspješno",  "Zauzeto!") );
            factory.close();
            return false;
          
            
           
    }
    public static List<Rezervacija> mojeRezervacije(int k_id) {
            
            try{
            SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                                                .buildSessionFactory();
    
            Session session = factory.getCurrentSession();
            
            session.beginTransaction();
            String hql="select k from Rezervacija k join fetch k.korisnik join fetch k.stol where k.korisnik.id=:id ";
            Query query =session.createQuery(hql);
            query.setParameter("id",k_id );
            List<Rezervacija> upit = query.list();
            List<Rezervacija> rs = new LinkedList<>();
            Rezervacija r = null;
            for(int i=0;i<upit.size();i++)
            {
                r = new Rezervacija();
                r.setId_rezervacije(upit.get(i).getId_rezervacije());
                r.setIme(upit.get(i).getKorisnik().getIme());
                r.setPrezime(upit.get(i).getKorisnik().getPrezime());
                r.setNaziv(upit.get(i).getStol().getNaziv());
                r.setDatum(upit.get(i).getDatum());
                r.setVrijeme_pocetka(upit.get(i).getVrijeme_pocetka());
                r.setVrijeme_kraj(upit.get(i).getVrijeme_kraj());
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
