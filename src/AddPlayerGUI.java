import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddPlayerGUI extends JFrame {
    public AddPlayerGUI() {
        setTitle("Add Player");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Header
        JLabel headerLabel = new JLabel("Add New Player", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(new Color(34, 45, 65));
        add(headerLabel, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(240, 248, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JTextField firstNameField = new JTextField(20);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JTextField lastNameField = new JTextField(20);

        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JTextField phoneField = new JTextField(15);

        JLabel provinceLabel = new JLabel("Province:");
        provinceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JTextField provinceField = new JTextField(15);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JTextField addressField = new JTextField(30);

        JLabel postalCodeLabel = new JLabel("Postal Code:");
        postalCodeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JTextField postalCodeField = new JTextField(10);

        // Adding components to the form
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(firstNameLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(firstNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(lastNameLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(lastNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(phoneLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(provinceLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(provinceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(addressLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(addressField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(postalCodeLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(postalCodeField, gbc);

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
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String phone = phoneField.getText();
                String province = provinceField.getText();
                String address = addressField.getText();
                String postalCode = postalCodeField.getText();

                try (Connection conn = DBConnection.getConnection()) {
                    String sql = """
                        INSERT INTO VAIBHAV_KALIA_PLAYER_1 
                        (first_name, last_name, phone_number, province, address, postal_code) 
                        VALUES (?, ?, ?, ?, ?, ?)
                    """;
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, firstName);
                    stmt.setString(2, lastName);
                    stmt.setString(3, phone);
                    stmt.setString(4, province);
                    stmt.setString(5, address);
                    stmt.setString(6, postalCode);
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Player added successfully!");
                    firstNameField.setText("");
                    lastNameField.setText("");
                    phoneField.setText("");
                    provinceField.setText("");
                    addressField.setText("");
                    postalCodeField.setText("");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new AddPlayerGUI();
    }
}
