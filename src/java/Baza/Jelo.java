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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Entity
@Table(name="jela")
public class Jelo {
    @Id
    @Column(name="id_jela")
    private int id_jela;
    @Column(name="naziv")
    private String naziv;
    @Column(name="cijena")
    private double cijena;
    @Column(name="slika")
    private String slika;
    @OneToMany(mappedBy = "jelo", cascade = CascadeType.ALL)
    
    private List <Narudzba> narudzba;

    public Jelo(int id_jela, String naziv, double cijena, String slika) {
        this.id_jela = id_jela;
        this.naziv = naziv;
        this.cijena = cijena;
        this.slika = slika;
    }

   
    public Jelo() {
    }
    
    
    public int getId_jela() {
        return id_jela;
    }

    public void setId_jela(int id_jela) {
        this.id_jela = id_jela;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getCijena() {
        return cijena;
    }

    public void setCijena(double cijena) {
        this.cijena = cijena;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }
    
     public boolean dodajJelo(String slika2) {
       try{
            SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                                                .buildSessionFactory();
            
    
            Session session = factory.getCurrentSession();
            Jelo k = new Jelo(0,naziv,cijena,slika2);
            
            session.beginTransaction();
           
            session.save(k);
            session.getTransaction().commit();

            factory.close();
            FacesContext context = FacesContext.getCurrentInstance();
         
            context.addMessage(null, new FacesMessage("Uspješno",  "Jelo dodano!") );
            return true;
            
            }
            
          
           
         catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Greska2");
            return false;
        }
    }
     public static List<Jelo> getSveJela() {
        try{
            SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                                                .buildSessionFactory();
    
            Session session = factory.getCurrentSession();

            session.beginTransaction();
            List <Jelo> kor =session.createQuery("from Jelo").list();
        
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
}
