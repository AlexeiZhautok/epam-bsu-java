import com.company.dao.CourseDao;
import com.company.model.Course;
import com.company.model.UserRole;
import com.company.service.UserService;
import com.company.view.ConsoleInterface;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserService test = new UserService();

        CourseDao CDTest = new CourseDao();
        //Course IT = new Course(1, "IT", "Yandex");
        //IT.users.add(1L);
        //IT.users.add(2L);
        //CDTest.createNewCourse("IT", "Yandex");
        //CDTest.recreateCourse(IT);
        List<Course> testCourses = CDTest.getAll();
        for(Course iter : testCourses) {
            System.out.println(iter.toStringFileFormat());
        }


        ConsoleInterface view = new ConsoleInterface();
        view.showInterface();
    }
}
