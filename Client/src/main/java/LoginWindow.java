import com.claudiu.proiect.domain.Rol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends JFrame {
    private JTextField userId;
    private JPasswordField passwd;
    private JComboBox listaRoluri;
    private JButton logBut, cancelBut;
    private ClientCtrl ctrl;

    public LoginWindow(String title, ClientCtrl ctrl) throws HeadlessException {
        super(title);
        this.ctrl = ctrl;
        getContentPane().add(createLogin());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    private JPanel createLogin(){
        JPanel res=new JPanel(new GridLayout(4,1));
        JPanel line1=new JPanel();
        line1.add(new JLabel("Username:"));
        line1.add(userId=new JTextField(15));
        res.add(line1);

        JPanel line2=new JPanel();
        line2.add(new JLabel("Password:"));
        line2.add(passwd=new JPasswordField(15));
        res.add(line2);

        JPanel line3=new JPanel();
        String[] roluri = {"admin","personalFarmacie","personalSectie"};
        line3.add(new JLabel("Roluri:"));
        line3.add(listaRoluri = new JComboBox(roluri));
        res.add(line3);

        JPanel line4=new JPanel();
        line4.add(logBut=new JButton("Login"));
        line4.add(cancelBut=new JButton("Clear"));
        ActionListener al=new ButListener();
        logBut.addActionListener(al);
        cancelBut.addActionListener(al);
        res.add(line4);
        return res;
    }

    private class ButListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==logBut){
                String user=userId.getText();
                String pass=new String(passwd.getPassword());
                Rol rol = Rol.valueOf(listaRoluri.getSelectedItem().toString());
                try{
                    ctrl.login(user,pass,rol);
                    if(rol == Rol.admin){
                        AdminWindow win=new AdminWindow("Chat window for "+user,ctrl);
                        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        win.setSize(700,800);
                        win.setLocation(150,150);
                        win.setVisible(true);
                        LoginWindow.this.dispose();
                    }
                    if(rol == Rol.personalFarmacie){
                        FarmacieWindow win=new FarmacieWindow("Chat window for "+user,ctrl);
                        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        win.setSize(700,800);
                        win.setLocation(150,150);
                        win.setVisible(true);
                        LoginWindow.this.dispose();
                    }
                    if(rol == Rol.personalSectie){
                        JOptionPane.showMessageDialog(LoginWindow.this, "Nu inca ","Eroare", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(LoginWindow.this, "Eroare la logare " + e1.getMessage(),"Eroare", JOptionPane.ERROR_MESSAGE);
                }
                return;
            } else{
                userId.setText("");
                passwd.setText("");
            }
        }
    }
}
