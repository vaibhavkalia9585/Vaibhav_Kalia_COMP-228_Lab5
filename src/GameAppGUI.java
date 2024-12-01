import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameAppGUI extends JFrame {
    public GameAppGUI() {
        setTitle("Game Management System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(600, 400);

        // Create buttons
        JButton addPlayerButton = new JButton("Add Player");
        JButton addGameButton = new JButton("Add Game");
        JButton displayDataButton = new JButton("Display Data");

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addPlayerButton);
        buttonPanel.add(addGameButton);
        buttonPanel.add(displayDataButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners
        addPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddPlayerGUI();
            }
        });

        addGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddGameGUI();
            }
        });

        displayDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DisplayDataGUI();
            }
        });

        setVisible(true);
    }
}
