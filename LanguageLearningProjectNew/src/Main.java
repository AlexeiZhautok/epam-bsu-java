import com.company.model.Course;
import com.company.model.UserRole;
import com.company.service.UserService;
import com.company.view.ConsoleInterface;

public class Main {
    public static void main(String[] args) {

//        UserService test = new UserService();
//        test.createUser("TYAM", "1234", "a", "ADMIN");
//        Course IT = new Course(1, "IT", "Yandex");
//        IT.users.add(test.getByID("1"));
//        IT.users.add(test.getByID("2"));
//        System.out.println(IT);

        ConsoleInterface view = new ConsoleInterface();
        view.showInterface();
    }
}
