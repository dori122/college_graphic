package SchoolManagment;

import java.awt.Font;
import java.awt.Color;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.GroupLayout.Alignment;
import SchoolManagment.controller.PersonController;
import SchoolManagment.model.Person;

public class New_Member extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;     // ID
    private JTextField textField_1;   // First name
    private JTextField textField_2;   // Last name
    private final PersonController ctrl = new PersonController();

//    public static void main(String[] args) {
//        EventQueue.invokeLater(() -> {
//            try {
//                New_Member frame = new New_Member();
//                frame.setVisible(true);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//    }

    public New_Member() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblHeader = new JLabel("Register");
        lblHeader.setForeground(Color.RED);
        lblHeader.setFont(new Font("Tahoma", Font.BOLD, 15));

        // ID field (digits only, non-editable)
        textField = new JTextField();
        textField.setColumns(10);
        textField.setEditable(true);
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override public void insertString(FilterBypass fb, int offs, String str, AttributeSet a)
                    throws BadLocationException {
                if (str.matches("\\d+")) super.insertString(fb, offs, str, a);
            }
            @Override public void replace(FilterBypass fb, int offs, int len, String text, AttributeSet attrs)
                    throws BadLocationException {
                if (text.matches("\\d+")) super.replace(fb, offs, len, text, attrs);
            }
        });

        // Name fields (letters + spaces only)
        textField_1 = new JTextField(); textField_1.setColumns(10);
        textField_2 = new JTextField(); textField_2.setColumns(10);
        DocumentFilter nameFilter = new DocumentFilter() {
            private final String regex = "[\\p{L} ]+";
            @Override public void insertString(FilterBypass fb, int offs, String str, AttributeSet a)
                    throws BadLocationException {
                if (str.matches(regex)) super.insertString(fb, offs, str, a);
            }
            @Override public void replace(FilterBypass fb, int offs, int len, String text, AttributeSet attrs)
                    throws BadLocationException {
                if (text.matches(regex)) super.replace(fb, offs, len, text, attrs);
            }
        };
        ((AbstractDocument) textField_1.getDocument()).setDocumentFilter(nameFilter);
        ((AbstractDocument) textField_2.getDocument()).setDocumentFilter(nameFilter);

        JLabel lblId = new JLabel("ID:");
        JLabel lblName = new JLabel("Name:");
        JLabel lblLast = new JLabel("Lastname:");
        for (JLabel lbl : new JLabel[]{ lblId, lblName, lblLast }) {
            lbl.setForeground(Color.RED);
            lbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
        }

        // Register button
        JButton btnRegister = new JButton("Register");
        btnRegister.setBackground(Color.RED);
        btnRegister.addActionListener(e -> {
            String firstName = textField_1.getText().trim();
            if (firstName.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this, "Name cannot be empty",
                    "Validation Error", JOptionPane.WARNING_MESSAGE
                );
                textField_1.requestFocusInWindow();
                return;
            }
            String lastName = textField_2.getText().trim();
            if (lastName.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this, "Lastname cannot be empty",
                    "Validation Error", JOptionPane.WARNING_MESSAGE
                );
                textField_2.requestFocusInWindow();
                return;
            }
            try {
                Person p = new Person(0, firstName, lastName);
                ctrl.add(p);  // insert & set ID
                JOptionPane.showMessageDialog(
                    this,
                    "Successful registry with ID = " + p.getId(),
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
                );
                Search_Insert search = new Search_Insert();
                search.clearField();
                search.setVisible(true);
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                    this,
                    "DB Error: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // Back button (just returns without registering)
        JButton btnBack = new JButton("");
        btnBack.setIcon(new ImageIcon(New_Member.class.getResource("/images/left.png")));
        btnBack.setBackground(Color.RED);
        btnBack.addActionListener(e -> {
            Search_Insert search = new Search_Insert();
            search.clearField();
            search.setVisible(true);
            dispose();
        });

        // Layout
        GroupLayout gl = new GroupLayout(contentPane);
        gl.setHorizontalGroup(
        	gl.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl.createSequentialGroup()
        			.addGroup(gl.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl.createSequentialGroup()
        					.addGap(42)
        					.addGroup(gl.createParallelGroup(Alignment.TRAILING)
        						.addComponent(lblLast)
        						.addComponent(lblName)
        						.addComponent(lblId)))
        				.addGroup(gl.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)))
        			.addGroup(gl.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl.createSequentialGroup()
        					.addGap(18)
        					.addGroup(gl.createParallelGroup(Alignment.LEADING, false)
        						.addComponent(textField)
        						.addComponent(textField_1)
        						.addComponent(textField_2))
        					.addGap(39)
        					.addComponent(btnRegister))
        				.addGroup(gl.createSequentialGroup()
        					.addGap(72)
        					.addComponent(lblHeader)))
        			.addContainerGap(112, Short.MAX_VALUE))
        );
        gl.setVerticalGroup(
        	gl.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl.createSequentialGroup()
        			.addGroup(gl.createParallelGroup(Alignment.TRAILING, false)
        				.addComponent(btnBack, 0, 0, Short.MAX_VALUE)
        				.addGroup(Alignment.LEADING, gl.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(lblHeader)))
        			.addGroup(gl.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl.createSequentialGroup()
        					.addGap(42)
        					.addGroup(gl.createParallelGroup(Alignment.BASELINE)
        						.addComponent(lblId)
        						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        					.addGap(20)
        					.addGroup(gl.createParallelGroup(Alignment.BASELINE)
        						.addComponent(lblName)
        						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        					.addGap(20)
        					.addGroup(gl.createParallelGroup(Alignment.BASELINE)
        						.addComponent(lblLast)
        						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        					.addContainerGap(132, Short.MAX_VALUE))
        				.addGroup(Alignment.TRAILING, gl.createSequentialGroup()
        					.addGap(135)
        					.addComponent(btnRegister)
        					.addGap(115))))
        );
        contentPane.setLayout(gl);
    }
}
