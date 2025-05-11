package SchoolManagment;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import SchoolManagment.controller.PersonController;
import SchoolManagment.model.Person;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;

public class Results extends JFrame {
    private static final long serialVersionUID = 1L;

    // ─── MVC / DATA ─────────────────────────────────────────
    private final PersonController ctrl = new PersonController();
    private List<Person> list;
    private int index;

    // ─── UI COMPONENTS ─────────────────────────────────────
    private JPanel contentPane;
    private JTextField textField;    // ID
    private JTextField textField_1;  // First name
    private JTextField textField_2;  // Last name
    private JButton Backarrow, Leftarrow, Rightarrow, FullRightarrow;
    private JButton Update, Delete;
    private JLabel lblId, lblFname, lblLname, lblTitle;
    private JButton btnback;

    // ─── ENTRY POINT ────────────────────────────────────────
//    public static void main(String[] args) {
//        EventQueue.invokeLater(() -> {
//            try {
//                Results frame = new Results();
//                frame.setVisible(true);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//    }

    // ─── NO-ARG CONSTRUCTOR (for WindowBuilder) ────────────
    public Results() {
        initComponents();
    }

    // ─── FULL CONSTRUCTOR (called at runtime) ───────────────
    public Results(List<Person> list, int startIndex) {
        this.list = list;
        this.index = startIndex;
        initComponents();
        showPerson();

        // ── Navigation arrows ───────────────────────────────
        Backarrow.addActionListener(e -> {
            new Search_Insert().setVisible(true);
            dispose();
        });
        Leftarrow.addActionListener(e -> nav(index - 1));
        Rightarrow.addActionListener(e -> nav(index + 1));
        FullRightarrow.addActionListener(e -> nav(list.size() - 1));

        // ── Update / Delete ────────────────────────────────
        Update.addActionListener(e -> onUpdate());
        Delete.addActionListener(e -> onDelete());
    }

    // ─── LAYOUT & COMPONENT INITIALIZATION ─────────────────
    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        lblTitle = new JLabel("Results");
        lblTitle.setForeground(Color.RED);
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);

        lblId = new JLabel("ID:");
        lblId.setForeground(Color.RED);
        lblId.setHorizontalAlignment(SwingConstants.CENTER);

        lblFname = new JLabel("Name:");
        lblFname.setForeground(Color.RED);

        lblLname = new JLabel("Lastname:");
        lblLname.setForeground(Color.RED);

        textField = new JTextField();
        textField.setColumns(10);
        textField.setEditable(false);

        textField_1 = new JTextField();
        textField_1.setColumns(10);

        textField_2 = new JTextField();
        textField_2.setColumns(10);

        // Back arrow
        Backarrow = new JButton("");
        Backarrow.setBackground(Color.WHITE);
        ImageIcon rawIcon = new ImageIcon(Results.class.getResource("/images/back.png"));
        Image scaledImage = rawIcon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        Backarrow.setIcon(new ImageIcon(scaledImage));

        // Left arrow
        Leftarrow = new JButton("");
        Leftarrow.setIcon(new ImageIcon(
            Results.class.getResource("/images/left.png")));

        // Right arrow
        Rightarrow = new JButton("");
        Rightarrow.setIcon(new ImageIcon(
            Results.class.getResource("/images/chevron.png")));

        // Full right arrow
        FullRightarrow = new JButton("");
        FullRightarrow.setIcon(new ImageIcon(
            Results.class.getResource("/images/right.png")));

        Update = new JButton("Update");
        Update.setBackground(Color.RED);
        Update.setFont(new Font("Tahoma", Font.PLAIN, 13));

        Delete = new JButton("Delete");
        Delete.setBackground(Color.RED);
        Delete.setFont(new Font("Tahoma", Font.PLAIN, 13));
        
        btnback = new JButton("");
        btnback.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Search_Insert search = new Search_Insert();
        	    search.clearField();   // reset the lastname field
        	    search.setVisible(true);
        	    dispose();
        
        	}
        });
        btnback.setBackground(new Color(255, 0, 0));
        btnback.setIcon(new ImageIcon(Results.class.getResource("/images/left.png")));

        // ── Layout ─────────────────────────────────────────
        GroupLayout gl = new GroupLayout(contentPane);
        gl.setHorizontalGroup(
        	gl.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl.createSequentialGroup()
        			.addComponent(btnback, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
        			.addGroup(gl.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl.createSequentialGroup()
        					.addGap(37)
        					.addGroup(gl.createParallelGroup(Alignment.LEADING)
        						.addGroup(gl.createSequentialGroup()
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(lblLname, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
        							.addPreferredGap(ComponentPlacement.UNRELATED)
        							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
        						.addGroup(gl.createSequentialGroup()
        							.addGap(37)
        							.addComponent(Backarrow, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
        							.addGap(22)
        							.addComponent(Leftarrow, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
        							.addGap(20)
        							.addComponent(Rightarrow, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
        							.addGap(20)
        							.addComponent(FullRightarrow, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
        						.addGroup(gl.createSequentialGroup()
        							.addGap(37)
        							.addComponent(Update)
        							.addGap(37)
        							.addComponent(Delete))
        						.addGroup(gl.createSequentialGroup()
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addGroup(gl.createParallelGroup(Alignment.LEADING)
        								.addComponent(lblId)
        								.addComponent(lblFname, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
        							.addGap(29)
        							.addGroup(gl.createParallelGroup(Alignment.TRAILING)
        								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
        				.addGroup(gl.createSequentialGroup()
        					.addGap(85)
        					.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap(185, Short.MAX_VALUE))
        );
        gl.setVerticalGroup(
        	gl.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl.createSequentialGroup()
        			.addGroup(gl.createParallelGroup(Alignment.LEADING)
        				.addComponent(btnback, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblTitle))
        			.addGap(16)
        			.addGroup(gl.createParallelGroup(Alignment.LEADING)
        				.addComponent(lblId)
        				.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(7)
        			.addGroup(gl.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblFname)
        				.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(7)
        			.addGroup(gl.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblLname)
        				.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(26)
        			.addGroup(gl.createParallelGroup(Alignment.LEADING)
        				.addComponent(Backarrow, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
        				.addComponent(Leftarrow, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        				.addComponent(Rightarrow, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        				.addComponent(FullRightarrow, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
        			.addGap(32)
        			.addGroup(gl.createParallelGroup(Alignment.BASELINE)
        				.addComponent(Update)
        				.addComponent(Delete))
        			.addContainerGap(76, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl);
    }

    // ─── DISPLAY CURRENT PERSON ──────────────────────────
    private void showPerson() {
        Person p = list.get(index);
        textField   .setText(String.valueOf(p.getId()));
        textField_1 .setText(p.getFname());
        textField_2 .setText(p.getLname());

        // enable/disable arrows
        Backarrow   .setEnabled(true);
        Leftarrow   .setEnabled(index > 0);
        Rightarrow  .setEnabled(index < list.size() - 1);
        FullRightarrow.setEnabled(index < list.size() - 1);
    }

    // ─── NAVIGATION HELP ──────────────────────────────────
    private void nav(int newIndex) {
        if (newIndex >= 0 && newIndex < list.size()) {
            index = newIndex;
            showPerson();
        }
    }

    // ─── UPDATE HELP ─────────────────────────────────────
    private void onUpdate() {
        // 1) Read & validate directly from the fields
        String newF = textField_1.getText().trim();
        if (newF.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "First name cannot be empty",
                "Validation Error",
                JOptionPane.WARNING_MESSAGE
            );
            textField_1.requestFocusInWindow();
            return;
        }

        String newL = textField_2.getText().trim();
        if (newL.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Last name cannot be empty",
                "Validation Error",
                JOptionPane.WARNING_MESSAGE
            );
            textField_2.requestFocusInWindow();
            return;
        }

        // 2) Update the model
        Person p = list.get(index);
        p.setFname(newF);
        p.setLname(newL);

        // 3) Persist via controller + show feedback
        try {
            ctrl.update(p);
            JOptionPane.showMessageDialog(
                this,
                "Record updated successfully",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
            );
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                this,
                "DB Error: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ─── DELETE HELP ─────────────────────────────────────
    private void onDelete() {
        if (JOptionPane.showConfirmDialog(this, "Confirm deletion?",
                "Delete", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
                int id = list.get(index).getId();
                ctrl.delete(id);
                JOptionPane.showMessageDialog(this, "Record deleted",
                                              "Deleted", JOptionPane.INFORMATION_MESSAGE);
                list.remove(index);
                if (list.isEmpty()) {
                    new Search_Insert().setVisible(true);
                    dispose();
                } else {
                    nav(Math.min(index, list.size() - 1));
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "DB Error: " + ex.getMessage(),
                                              "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
