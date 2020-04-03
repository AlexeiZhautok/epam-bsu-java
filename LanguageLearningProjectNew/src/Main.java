import com.company.model.UserRole;
import com.company.service.UserService;
import com.company.view.ConsoleInterface;

public class Main {
    public static void main(String[] args) {
        UserService test = new UserService();
//        test.createUser(1, "Nachalas", "228", "nachalasalive@list.ru", UserRole.ADMIN);
//        for(var i : test.getAll()) {
//            System.out.println(i.toString());
//        }
//        System.out.println(test.getByID(-1));

        ConsoleInterface view = new ConsoleInterface();
        view.showInterface();
    }
}
