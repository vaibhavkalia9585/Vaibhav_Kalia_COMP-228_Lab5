import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DisplayDataGUI extends JFrame {
    public DisplayDataGUI() {
        setTitle("Display Data");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(900, 500);
        setLayout(new BorderLayout());

        // Header
        JLabel headerLabel = new JLabel("Player and Game Information", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(new Color(34, 45, 65));
        add(headerLabel, BorderLayout.NORTH);

        // Table Panel
        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(Color.WHITE);
        add(scrollPane, BorderLayout.CENTER);

        // Footer Panel
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(240, 248, 255));
        JLabel footerLabel = new JLabel("Data fetched from the database");
        footerLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        footerLabel.setForeground(Color.GRAY);
        footerPanel.add(footerLabel);
        add(footerPanel, BorderLayout.SOUTH);

        // Load data from the database
        try (Connection conn = DBConnection.getConnection()) {
            String sql = """
                SELECT 
                    p.player_id, 
                    p.first_name, 
                    p.last_name, 
                    p.phone_number, 
                    g.game_title, 
                    pg.playing_date, 
                    pg.score 
                FROM 
                    VAIBHAV_KALIA_PLAYER_1 p
                LEFT JOIN 
                    VAIBHAV_KALIA_PLAYER_AND_GAME pg 
                ON 
                    p.player_id = pg.player_id
                LEFT JOIN 
                    VAIBHAV_KALIA_GAME_1 g 
                ON 
                    pg.game_id = g.game_id
            """;

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // Prepare table model
            DefaultTableModel model = new DefaultTableModel(
                    new String[]{"Player ID", "First Name", "Last Name", "Phone", "Game Title", "Playing Date", "Score"},
                    0
            );

            // Populate table with data
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("player_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("phone_number"),
                        rs.getString("game_title"),
                        rs.getDate("playing_date"),
                        rs.getInt("score")
                });
            }

            table.setModel(model);
            table.setFont(new Font("Arial", Font.PLAIN, 14));
            table.setRowHeight(25);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }

        setVisible(true);
    }
}

