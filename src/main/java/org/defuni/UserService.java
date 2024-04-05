package org.defuni;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.LoadType;
import org.defuni.account.Student;

public class UserService {

    static {
        ObjectifyService.init();
        ObjectifyService.register(Student.class);
    }

    public void saveStudent(Student student) {
        ObjectifyService.ofy().save().entity(student).now();
    }

    public void getStudent(String userName) {
        LoadType<Student> loadType = ObjectifyService.ofy().load().type(Student.class);
        Student student = loadType.filter("userName", userName).first().now();
        System.out.println(student);
    }

    public void deleteUser(String userName) {
        LoadType<Student> loadType = ObjectifyService.ofy().load().type(Student.class);
        Student student = loadType.filter("userName", userName).first().now();
        ObjectifyService.ofy().delete().entity(student).now();
    }
}
