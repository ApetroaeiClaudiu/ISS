import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


public class ListaComenzi extends AbstractListModel {
    private List<Comanda> comenzi;
    public ListaComenzi() {
        comenzi=new ArrayList<Comanda>();
    }

    public int getSize() {
        return comenzi.size();
    }

    public Object getElementAt(int index) {
        return comenzi.get(index);
    }

    public void comandaNoua(String idClient,int idMed,int cantitate){
        Comanda comanda = new Comanda(idClient,idMed,cantitate);
        comenzi.add(comanda);
        fireContentsChanged(this, comenzi.size()-1, comenzi.size());
    }
}
