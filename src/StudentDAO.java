import java.util.ArrayList;

public interface StudentDAO {
    Student findStudent(String id);
    Student findStudentByName(String lastName);
    ArrayList<Student> getStudents();

}
