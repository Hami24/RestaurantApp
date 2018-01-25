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

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


@Entity
@Table(name="stolovi")
public class Stol {
    @Id
    @Column(name="id_stolovi")
    private int id_stolovi;
    @Column(name="naziv")
    private String naziv;
    @OneToMany(mappedBy = "stol", cascade = CascadeType.ALL)
    private List <Rezervacija> rezervacija;

    public List<Rezervacija> getRezervacija() {
        return rezervacija;
    }

    public void setRezervacija(List<Rezervacija> rezervacija) {
        this.rezervacija = rezervacija;
    }

    public Stol() {
    }
    
    

    public Stol(int id_stolovi, String naziv) {
        this.id_stolovi = id_stolovi;
        this.naziv = naziv;
    }

    

    
    public int getId_stolovi() {
        return id_stolovi;
    }

    public void setId_stolovi(int id_stolovi) {
        this.id_stolovi = id_stolovi;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    
    public boolean dodajStol() {
       try{
            SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                                                .buildSessionFactory();
            
    
            Session session = factory.getCurrentSession();
            Stol k = new Stol(0,naziv);
            
            session.beginTransaction();
           
            session.save(k);
            session.getTransaction().commit();

            factory.close();
            FacesContext context = FacesContext.getCurrentInstance();
         
            context.addMessage(null, new FacesMessage("Uspješno",  "Stol dodan!") );
            return true;
            
            }
            
          
           
         catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Greska2");
            return false;
        }
    }
    
    public static List<Stol> getSveStolove() {
         try{
            SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                                                        .buildSessionFactory();
    
            Session session = factory.getCurrentSession();

            session.beginTransaction();
            List <Stol> st =session.createQuery("from Stol").list();
        
            session.getTransaction().commit();

            factory.close();
            return st;
            
            }
            
          
           
         catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Greska2");
            return null;
        }
    }
    
   /* public static List<Stol> getSlobodneStolove() {
        try{
            SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                                                        .buildSessionFactory();
    
            Session session = factory.getCurrentSession();

            session.beginTransaction();
            Query query =session.createQuery("from Stol k where k.status=:status");
            query.setParameter("status", "slobodan");
            List <Stol> kor = query.list();
            session.getTransaction().commit();

            factory.close();
            return kor;
            
            }
            
          
           
         catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Greska2");
            return null;
        }
    }*/
}
