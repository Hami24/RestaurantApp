package org.primefaces.showcase.view.data.gmap;
 
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
 
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
 
@ManagedBean
public class MarkersView implements Serializable {
    
    private MapModel simpleModel;
  
    @PostConstruct
    public void init() {
        simpleModel = new DefaultMapModel();
          
        //Shared coordinates
        LatLng coord1 = new LatLng(44.8557394, 15.882111499999999);

          
        //Basic marker
        simpleModel.addOverlay(new Marker(coord1, "Lokacija restorana"));
        
    }
  
    public MapModel getSimpleModel() {
        return simpleModel;
    }
}