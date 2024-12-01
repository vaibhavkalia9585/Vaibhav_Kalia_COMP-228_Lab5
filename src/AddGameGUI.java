import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddGameGUI extends JFrame {
    public AddGameGUI() {
        setTitle("Add Game");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setLayout(new BorderLayout());

        // Header
        JLabel headerLabel = new JLabel("Add New Game", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(new Color(34, 45, 65));
        add(headerLabel, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(240, 248, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel gameTitleLabel = new JLabel("Game Title:");
        gameTitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JTextField gameTitleField = new JTextField(20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(gameTitleLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(gameTitleField, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Save");
        saveButton.setBackground(new Color(60, 179, 113));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        saveButton.setFont(new Font("Arial", Font.BOLD, 14));
        buttonPanel.add(saveButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Save Button Action
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String gameTitle = gameTitleField.getText();
                try (Connection conn = DBConnection.getConnection()) {
                    String sql = "INSERT INTO VAIBHAV_KALIA_GAME_1 (game_title) VALUES (?)";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, gameTitle);
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Game added successfully!");
                    gameTitleField.setText("");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        setVisible(true);
    }
}
