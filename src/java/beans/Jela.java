package beans;
import Baza.Jelo;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Jela {
    
    private Jelo jelo=new Jelo();
    private List<Jelo> jela;
    @ManagedProperty(value="#{upload}")
    private Upload upload= new Upload();
    public Jela()
    {
        jela=Jelo.getSveJela();
    }

    public Jelo getJelo() {
        return jelo;
    }

    public void setJelo(Jelo jelo) {
        this.jelo = jelo;
    }

    public List<Jelo> getJela() {
        return jela;
    }

    public void setJela(List<Jelo> jela) {
        this.jela = jela;
    }

    public Upload getUpload() {
        return upload;
    }

    public void setUpload(Upload upload) {
        this.upload = upload;
    }
    
    public void dodaj()
    {
        if(this.jelo.dodajJelo(this.upload.getFileName()))
        {
            System.out.println("dodano jelo");
            this.jela=Jelo.getSveJela();
        }
        else
        {
            System.out.println("greska");
        }
    }
}
