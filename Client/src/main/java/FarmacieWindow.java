import com.claudiu.proiect.domain.Medicament;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import com.claudiu.proiect.domain.Medicament;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class FarmacieWindow extends JFrame {
    private JTable listaMedicamente;
    private JTextField cantitateField;
    private JButton butonAdaugaComanda;
    private ClientCtrl ctrl;
    private JPanel panel;
    private JPanel panelMedicamente;

    public FarmacieWindow(String title, ClientCtrl ctrl) throws Exception {
        super(title);
        this.ctrl=ctrl;
        panel=new JPanel(new BorderLayout());
        panel.add(createAdaugaComanda(), BorderLayout.SOUTH);
        panelMedicamente = createMedicamente();
        panel.add(panelMedicamente, BorderLayout.CENTER);
        getContentPane().add(panel);
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                close();
            }
        });
    }

    private void close(){
        ctrl.logout();
    }

    private JPanel createMedicamente() throws Exception {
        JPanel res=new JPanel(new GridLayout(1,1));
        String[] columns = {"Id","Nume","Pret","Detalii"};
        List<Medicament> medicamente = ctrl.getMedicamente();
        System.out.println(medicamente);
        int size = medicamente.size();
        String[][] datas = new String[size][4];
        for(int i = 0 ;i < size;i++){
            datas[i][0] = Integer.toString(medicamente.get(i).getId());
            datas[i][1] = medicamente.get(i).getNume();
            datas[i][2] = Float.toString(medicamente.get(i).getPret());
            datas[i][3] = medicamente.get(i).getDetalii();
        }
        listaMedicamente = new JTable(datas,columns);
        JScrollPane scroll=new JScrollPane(listaMedicamente);
        res.add(scroll);
        res.setBorder(BorderFactory.createTitledBorder("Medicamente"));
        return res;
    }

    private JPanel createAdaugaComanda(){
        JPanel res=new JPanel(new GridLayout(2,1));
        JPanel line1=new JPanel();
        line1.add(new JLabel("Cantitate: "));
        line1.add(cantitateField = new JTextField(30));
        res.add(line1);
        JPanel line2=new JPanel();
        line2.add(butonAdaugaComanda=new JButton("Trimite Comanda"));
        butonAdaugaComanda.addActionListener(new AdaugaListener());
        res.add(line2);
        return res;
    }


    private class AdaugaListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            int index = listaMedicamente.getSelectedRow();
            if (index>=0) {
                int id = Integer.parseInt(listaMedicamente.getModel().getValueAt(index, 0).toString());
                int cantitate = Integer.parseInt(cantitateField.getText());
                try {
                    ctrl.trimiteComanda(id,cantitate);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(FarmacieWindow.this, "Eroare  "+e1, "Eroare la trimitere", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }else{
                JOptionPane.showMessageDialog(FarmacieWindow.this, "Trebuie sa selectati un medicament ", "Eroare la selectare", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }
}
