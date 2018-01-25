package beans;
import Baza.Stol;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean
@SessionScoped
public class Stolovi {
    private List <Stol> stolovi;
    //private List <Stol> slobodni;
    private Stol stol = new Stol();

    public Stolovi()
    {
        stolovi=Stol.getSveStolove();
        //slobodni=Stol.getSlobodneStolove();
    }
    public List<Stol> getStolovi() {
        return stolovi;
    }

    public void setStolovi(List<Stol> stolovi) {
        this.stolovi = stolovi;
    }

  /*  public List<Stol> getSlobodni() {
        return slobodni;
    }

    public void setSlobodni(List<Stol> slobodni) {
        this.slobodni = slobodni;
    }
*/
    public Stol getStol() {
        return stol;
    }

    public void setStol(Stol stol) {
        this.stol = stol;
    }
    
    public void dodajNoviStol()
    {
        this.stol.dodajStol();
        stolovi=Stol.getSveStolove();
      //  slobodni=Stol.getSlobodneStolove();
       
    }
   
   /* public void obrisiStol(Stol s)
    {
        this.slobodni.remove(s);
    }*/
}
