import com.company.dbdao.DatabaseUserDao;
import com.company.model.User;
import com.company.model.UserRole;
import com.company.view.ConsoleView;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Main {
    public static void main(String[] args) {
        BasicConfigurator.configure();

        ConsoleView view = new ConsoleView();
        view.showInterface();
    }
}
