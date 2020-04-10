import com.company.dao.CourseDao;
import com.company.service.UserService;
import com.company.view.ConsoleView;

public class Main {
    public static void main(String[] args) {

        UserService test = new UserService();

        CourseDao CDTest = new CourseDao();
        //Course IT = new Course(1, "IT", "Yandex");
        //IT.users.add(1L);
        //IT.users.add(2L);
        //CDTest.createNewCourse("IT", "Yandex");
        //CDTest.recreateCourse(IT);
//        CDTest.createNewCourse("TI", "Xendexox");
//        System.out.println(CDTest.getLastID());
//        CDTest.updateInfoByID(2, "AI", "Xaxdex");
//        CDTest.addUserByID(1, 1);

        ConsoleView view = new ConsoleView();
        view.showInterface();
    }
}
