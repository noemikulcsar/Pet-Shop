package presentation.view;
import presentation.ImageUtils;
import javax.swing.*;
import java.awt.*;
/**
 * Panoul principal al aplicației, afișând un mesaj de bun venit și un fundal.
 */
public class MainPagePanel extends JPanel {
    private final JLabel titleLabel;
    /**
     * Constructor care inițializează panoul principal și adaugă componentele necesare.
     */
    public MainPagePanel() {
        setLayout(new BorderLayout());
        titleLabel = new JLabel("Welcome to Animal Land!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Impact", Font.ITALIC, 36));
        titleLabel.setForeground(Color.WHITE);
        Image backgroundImage = ImageUtils.resizeImage("fundal.jpg", 1000, 600);
        ImageIcon backgroundIcon = new ImageIcon(backgroundImage);
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setLayout(new BorderLayout());
        backgroundLabel.add(titleLabel, BorderLayout.CENTER);
        add(backgroundLabel, BorderLayout.CENTER);
    }
}
