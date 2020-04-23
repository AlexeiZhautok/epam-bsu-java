import com.company.dbdao.DatabaseUserDao;
import com.company.model.User;
import com.company.model.UserRole;
import com.company.view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        DatabaseUserDao databaseUserDao = new DatabaseUserDao();
        databaseUserDao.insert(new User(4, "NewUser", "12345", "d@d", UserRole.ADMIN));

        ConsoleView view = new ConsoleView();
        view.showInterface();
    }
}
