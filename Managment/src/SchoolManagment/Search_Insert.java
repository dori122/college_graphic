package SchoolManagment;

import java.awt.Font;
import java.awt.Color;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import SchoolManagment.controller.PersonController;
import SchoolManagment.model.Person;

public class Search_Insert extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;              // for entering Lastname
    private final PersonController ctrl = new PersonController();

//    public static void main(String[] args) {
//        EventQueue.invokeLater(() -> {
//            try {
//                Search_Insert frame = new Search_Insert();
//                frame.setVisible(true);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//    }

    public Search_Insert() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        // ─── Content Pane ─────────────────────────────────────
        contentPane = new JPanel();
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        // ─── Back Arrow ───────────────────────────────────────
        JButton btnBack = new JButton("");
        btnBack.setIcon(new ImageIcon(Search_Insert.class.getResource("/images/left.png")));
        btnBack.setBackground(Color.RED);
        btnBack.addActionListener(e -> {
            new MainMenu().setVisible(true);
            dispose();
        });

        // ─── Title ────────────────────────────────────────────
        JLabel lblTitle = new JLabel("Search/Register");
        lblTitle.setForeground(Color.RED);
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));

        // ─── Lastname Field + Label ──────────────────────────
        JLabel lblLastname = new JLabel("Lastname:");
        lblLastname.setForeground(Color.RED);
        lblLastname.setFont(new Font("Tahoma", Font.PLAIN, 12));

        textField = new JTextField();
        textField.setColumns(10);

        // ─── SEARCH Button ───────────────────────────────────
        JButton btnSearch = new JButton("Search");
        btnSearch.setBackground(Color.RED);
        btnSearch.addActionListener(e -> {
            String lname = textField.getText().trim();
            if (lname.isEmpty()) {
                JOptionPane.showMessageDialog(
                    Search_Insert.this,
                    "Please fill the lastname",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
                );
                textField.requestFocusInWindow();
                return;
            }
            try {
                List<Person> results = ctrl.search(lname);
                if (results.isEmpty()) {
                    // no matching surname → go to registration
                    clearField();
                    new New_Member().setVisible(true);
                } else {
                    // show the results at index 0
                    clearField();
                    new Results(results, 0).setVisible(true);
                }
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                    Search_Insert.this,
                    "DB Error: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // ─── REGISTER Button ─────────────────────────────────
        JButton btnRegister = new JButton("Register");
        btnRegister.setBackground(Color.RED);
        btnRegister.addActionListener(e -> {
            String lname = textField.getText().trim();
            if (!lname.isEmpty()) {
                // user has typed something—likely meant to search
                JOptionPane.showMessageDialog(
                    Search_Insert.this,
                    "Please clear the Lastname field before registering a new member.",
                    "Action Error",
                    JOptionPane.ERROR_MESSAGE
                );
                textField.requestFocusInWindow();
                return;
            }
            // field is empty → launch registration
            new New_Member().setVisible(true);
            dispose();
        });

        // ─── Layout via GroupLayout ─────────────────────────
        GroupLayout gl = new GroupLayout(contentPane);
        gl.setHorizontalGroup(
            gl.createParallelGroup(Alignment.LEADING)
              // back arrow top-left
              .addGroup(gl.createSequentialGroup()
                  .addContainerGap()
                  .addComponent(btnBack, 36, 36, 36)
                  .addContainerGap(411, Short.MAX_VALUE))
              // title centered
              .addGroup(gl.createSequentialGroup()
                  .addGap(144)
                  .addComponent(lblTitle, 118, 118, 118)
                  .addGap(188))
              // lastname + search
              .addGroup(gl.createSequentialGroup()
                  .addGap(65)
                  .addComponent(textField, 163, 163, 163)
                  .addPreferredGap(ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                  .addComponent(btnSearch, 110, 110, 110)
                  .addGap(41))
              // label for lastname
              .addGroup(gl.createSequentialGroup()
                  .addGap(92)
                  .addComponent(lblLastname, 76, 76, 76)
                  .addContainerGap(282, Short.MAX_VALUE))
              // register button
              .addGroup(gl.createSequentialGroup()
                  .addGap(151)
                  .addComponent(btnRegister, 110, 110, 110)
                  .addContainerGap(162, Short.MAX_VALUE))
        );
        gl.setVerticalGroup(
            gl.createParallelGroup(Alignment.LEADING)
              .addGroup(gl.createSequentialGroup()
                  .addGap(5)
                  .addComponent(btnBack, 30, 30, 30)
                  .addPreferredGap(ComponentPlacement.UNRELATED)
                  .addComponent(lblTitle)
                  .addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                  .addComponent(lblLastname)
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addGroup(gl.createParallelGroup(Alignment.BASELINE)
                      .addComponent(btnSearch, 53, 53, 53)
                      .addComponent(textField, 36, 36, 36))
                  .addGap(18)
                  .addComponent(btnRegister, 53, 53, 53)
                  .addGap(47))
        );
        contentPane.setLayout(gl);
    }

    /** Clears the lastname field so Register/Search start fresh */
    public void clearField() {
        textField.setText("");
    }
}
