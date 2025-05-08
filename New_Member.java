package SchoolManagment;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

public class New_Member extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					New_Member frame = new New_Member();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public New_Member() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel Registerbtn = new JLabel("Register");
		Registerbtn.setForeground(Color.RED);
		Registerbtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		textField = new JTextField();
		textField.setColumns(10);
		((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
		    @Override
		    public void insertString(FilterBypass fb, int offs, String str, AttributeSet a)
		            throws BadLocationException {
		        if (str.matches("\\d+")) {
		            super.insertString(fb, offs, str, a);
		        }
		        // else ignore non-digits
		    }
		    @Override
		    public void replace(FilterBypass fb, int offs, int len, String text, AttributeSet attrs)
		            throws BadLocationException {
		        if (text.matches("\\d+")) {
		            super.replace(fb, offs, len, text, attrs);
		        }
		    }
		});

		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		DocumentFilter nameFilter = new DocumentFilter() {
		    private final String regex = "[\\p{L} ]+";  // any unicode letter or space
		    @Override
		    public void insertString(FilterBypass fb, int offs, String str, AttributeSet a)
		        throws BadLocationException {
		        if (str.matches(regex)) {
		            super.insertString(fb, offs, str, a);
		        }
		    }
		    @Override
		    public void replace(FilterBypass fb, int offs, int len, String text, AttributeSet attrs)
		        throws BadLocationException {
		        if (text.matches(regex)) {
		            super.replace(fb, offs, len, text, attrs);
		        }
		    }
		};

		
		((AbstractDocument)textField_1.getDocument()).setDocumentFilter(nameFilter);
		((AbstractDocument)textField_2.getDocument()).setDocumentFilter(nameFilter);
		
		JLabel id = new JLabel("ID:");
		id.setForeground(Color.RED);
		id.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel name = new JLabel("Name:");
		name.setForeground(Color.RED);
		name.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lastname = new JLabel("Lastname:");
		lastname.setForeground(Color.RED);
		lastname.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JButton insert = new JButton("Register");
		insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textField_1.getText().trim();
	            if (name.isEmpty()) {
	                JOptionPane.showMessageDialog(
	                    New_Member.this,
	                    "name cannot be empty",
	                    "Validation Error",
	                    JOptionPane.WARNING_MESSAGE
	                );
	                textField_1.requestFocusInWindow();
	                return;
	            }
	            //  Validate Lastname 
	            String lastname = textField_2.getText().trim();
	            if (lastname.isEmpty()) {
	                JOptionPane.showMessageDialog(
	                    New_Member.this,
	                    "lastname cannot be empty",
	                    "Validation Error",
	                    JOptionPane.WARNING_MESSAGE
	                );
	                textField_2.requestFocusInWindow();
	                return;
	            }
	            JOptionPane.showMessageDialog(
	                    New_Member.this,
	                    "Επιτυχής εγγραφή!",
	                    "Success",
	                    JOptionPane.INFORMATION_MESSAGE
	                );
	                textField.setText("");     
	                textField_1.setText("");    // clear Name
	                textField_2.setText("");
			}
		});
		insert.setBackground(Color.RED);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lastname, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(name, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
									.addGap(47))
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(id, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
									.addGap(27)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(69)
									.addComponent(insert, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(185)
							.addComponent(Registerbtn)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(Registerbtn)
							.addGap(62)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(id))
							.addGap(27)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(name))
							.addGap(37)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lastname)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(180)
							.addComponent(insert, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(29, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
