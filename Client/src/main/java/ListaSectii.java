import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ListaSectii extends AbstractListModel {
    private List<String> sectii;

    public ListaSectii() {
        sectii=new ArrayList<String>();
    }

    public int getSize() {
        return sectii.size();
    }

    public Object getElementAt(int index) {
        return sectii.get(index);
    }

    public void sectieLoggedIn(String id){
        sectii.add(id);
        fireContentsChanged(this,sectii.size()-1,sectii.size());
    }

    public void sectieLoggedOut(String id){
       sectii.remove(id);
        fireContentsChanged(this,0, sectii.size());
    }
}
