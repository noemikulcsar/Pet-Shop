package start;
import java.sql.SQLException;
import presentation.PetShopGUI;
import javax.swing.*;
public class Start
{
    public static void main(String[] args) throws SQLException {
        SwingUtilities.invokeLater(PetShopGUI::new);
    }
}
