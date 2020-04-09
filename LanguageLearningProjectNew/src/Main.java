import com.company.model.Course;
import com.company.model.UserRole;
import com.company.service.UserService;
import com.company.view.ConsoleInterface;

public class Main {
    public static void main(String[] args) {

        UserService test = new UserService();
        Course IT = new Course(1, "IT", "Yandex");
        //IT.users.add(1L);
        //IT.users.add(2L);
        System.out.println(IT.toStringFileFormat());

        ConsoleInterface view = new ConsoleInterface();
        view.showInterface();
    }
}
