import com.claudiu.proiect.domain.Medicament;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class AdminWindow extends JFrame {
    private JTable listaMedicamente;
    private JTextField numeField;
    private JTextField pretField;
    private JTextField detaliiField;
    private JButton butonAdauga;
    private JButton butonSterge;
    private JButton butonModifica;
    private ClientCtrl ctrl;
    private JPanel panel;
    private JPanel panelMedicamente;

    public AdminWindow(String title, ClientCtrl ctrl) throws Exception {
        super(title);
        this.ctrl=ctrl;
        panel=new JPanel(new BorderLayout());
        panel.add(createOperatiiMedicament(), BorderLayout.SOUTH);
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

    private JPanel createOperatiiMedicament(){
        JPanel res=new JPanel(new GridLayout(6,1));
        JPanel line1=new JPanel();
        line1.add(new JLabel("Nume: "));
        line1.add(numeField = new JTextField(30));
        res.add(line1);
        JPanel line2=new JPanel();
        line2.add(new JLabel("Pret: "));
        line2.add(pretField = new JTextField(30));
        res.add(line2);
        JPanel line3=new JPanel();
        line3.add(new JLabel("Detalii: "));
        line3.add(detaliiField = new JTextField(30));
        res.add(line3);
        JPanel line4=new JPanel();
        line4.add(butonAdauga=new JButton("Adauga Medicament"));
        butonAdauga.addActionListener(new AdaugaListener());
        res.add(line4);
        JPanel line5=new JPanel();
        line5.add(butonSterge=new JButton("Sterge Medicament"));
        butonSterge.addActionListener(new StergeListener());
        res.add(line5);
        JPanel line6=new JPanel();
        line6.add(butonModifica=new JButton("Modifica Medicament"));
        butonModifica.addActionListener(new ModificaListener());
        res.add(line6);
        return res;
    }


    private class AdaugaListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String nume = numeField.getText();
            float pret = Float.parseFloat(pretField.getText());
            String detalii = detaliiField.getText();
            if ("".equals(nume.trim()) || "".equals(detalii.trim())) {
                JOptionPane.showMessageDialog(AdminWindow.this, "Trebuie sa introduceti datele", "Eroare la introducere ", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                ctrl.adaugaMedicament(nume,pret,detalii);
                panel.remove(panelMedicamente);
                panelMedicamente = createMedicamente();
                panel.add(panelMedicamente, BorderLayout.CENTER);
                panel.revalidate();
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(AdminWindow.this, "Eroare "+e1, "Eroare la adaugare", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }

    private class StergeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Send button pressed");
            int index = listaMedicamente.getSelectedRow();
            if (index>=0) {
                int id = Integer.parseInt(listaMedicamente.getModel().getValueAt(index, 0).toString());
                try {
                    ctrl.stergeMedicament(id);
                    panel.remove(panelMedicamente);
                    panelMedicamente = createMedicamente();
                    panel.add(panelMedicamente, BorderLayout.CENTER);
                    panel.revalidate();
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(AdminWindow.this, "Eroare  "+e1, "Eroare la stergere", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }else{
                JOptionPane.showMessageDialog(AdminWindow.this, "Trebuie sa selectati un medicament ", "Eroare la selectare", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }
    private class ModificaListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Send button pressed");
            int index = listaMedicamente.getSelectedRow();
            if (index>=0) {
                int id = Integer.parseInt(listaMedicamente.getModel().getValueAt(index, 0).toString());
                String nume = numeField.getText();
                float pret = Float.parseFloat(pretField.getText());
                String detalii = detaliiField.getText();
                if ("".equals(nume.trim()) || "".equals(detalii.trim())) {
                    JOptionPane.showMessageDialog(AdminWindow.this, "Trebuie sa introduceti datele", "Eroare la introducere ", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    ctrl.modificaMedicament(id,nume,pret,detalii);
                    panel.remove(panelMedicamente);
                    panelMedicamente = createMedicamente();
                    panel.add(panelMedicamente, BorderLayout.CENTER);
                    panel.revalidate();
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(AdminWindow.this, "Eroare  "+e1, "Eroare la modificare ", JOptionPane.ERROR_MESSAGE);
                    return;
                }

            }else{
                JOptionPane.showMessageDialog(AdminWindow.this, "Trebuie sa selectati un medicament ", "Eroare la selectare", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }
}
