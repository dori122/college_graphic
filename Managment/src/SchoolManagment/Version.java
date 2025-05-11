
package SchoolManagment;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Version extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
//    private JButton btnBack;          
    private JButton btnMainMenu;
    private JLabel lblTitle, lblVersion;

//    public static void main(String[] args) {
//        EventQueue.invokeLater(() -> {
//            try {
//                Version frame = new Version();
//                frame.setVisible(true);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//    }

    public Version() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        // ─── Content Pane ────────────────────────────────
        contentPane = new JPanel();
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

//        // ─── Back Button ─────────────────────────────────
//        btnBack = new JButton("");
//        btnBack.setBackground(Color.RED);
//        // load, scale, and set the icon
//        ImageIcon raw = new ImageIcon(Version.class.getResource("/images/left.png"));
//        Image img = raw.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
//        btnBack.setIcon(new ImageIcon(img));
//        btnBack.addActionListener(e -> {
//            new MainMenu().setVisible(true);
//            dispose();
//        });

        // ─── Title Label ───────────────────────────────
        lblTitle = new JLabel("Version");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblTitle.setForeground(Color.RED);

        // ─── Version Text ───────────────────────────────
        lblVersion = new JLabel("Your version is: 1.0.3.");
        lblVersion.setHorizontalAlignment(SwingConstants.CENTER);
        lblVersion.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblVersion.setForeground(Color.RED);

        // ─── Main Menu Button ───────────────────────────
        btnMainMenu = new JButton("Main menu");
        btnMainMenu.setBackground(Color.RED);
        btnMainMenu.addActionListener(e -> {
            new MainMenu().setVisible(true);
            dispose();
        });

        // ─── Layout ─────────────────────────────────────
        GroupLayout gl = new GroupLayout(contentPane);
        gl.setHorizontalGroup(
            gl.createParallelGroup(Alignment.LEADING)
//              // Back arrow at top-left
//              .addGroup(gl.createSequentialGroup()
//                  .addComponent(btnBack, 40, 40, 40)
//                  .addContainerGap())
              // Title centered
              .addGroup(gl.createSequentialGroup()
                  .addGap(100)
                  .addComponent(lblTitle, 200, 200, 200)
                  .addContainerGap())
              // Version text centered
              .addGroup(gl.createSequentialGroup()
                  .addGap(100)
                  .addComponent(lblVersion, 200, 200, 200)
                  .addContainerGap())
              // Main menu button centered
              .addGroup(gl.createSequentialGroup()
                  .addGap(150)
                  .addComponent(btnMainMenu, 100, 100, 100)
                  .addContainerGap())
        );
        gl.setVerticalGroup(
            gl.createParallelGroup(Alignment.LEADING)
              .addGroup(gl.createSequentialGroup()
//                  // Back arrow
//                  .addComponent(btnBack, 32, 32, 32)
//                  .addPreferredGap(ComponentPlacement.UNRELATED)
                  // Title
                  .addComponent(lblTitle)
                  .addGap(20)
                  // Version text
                  .addComponent(lblVersion)
                  .addGap(20)
                  // Main menu button
                  .addComponent(btnMainMenu, 40, 40, 40)
                  .addContainerGap(60, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl);
    }
}
