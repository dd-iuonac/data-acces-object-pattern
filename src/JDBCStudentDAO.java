import java.sql.*;
import java.util.ArrayList;

public class JDBCStudentDAO implements StudentDAO {
    private Connection connection;

    public JDBCStudentDAO() {
        try {

            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your PostgreSQL JDBC Driver? "
                    + "Include in your library path!");
            e.printStackTrace();
            return;

        }

        System.out.println("PostgreSQL JDBC Driver Registered!");

        connection = null;
        try {

            connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/horoscopdb", "horoscop",
                    "123456");

        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;

        }

        if (connection != null) {
            System.out.println("Database connection successfull!");
//            Statement statement = null;
//            try {
//                statement = connection.createStatement();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            try {
//                statement.executeUpdate("insert into Students (id, firstName, lastName, average) values ('LM21130', 'George', 'Popescu', '7.86'); " +
//                        "insert into Students (id, firstName, lastName, average) values ('LM65410', 'Octavian Aurelian', 'Popescu', '5.15'); " +
//                        "insert into Students (id, firstName, lastName, average) values ('LM57109', 'George', 'Lungu', '9.9'); " +
//                        "insert into Students (id, firstName, lastName, average) values ('LM31216', 'Constantin', 'Ionescu', '6.38'); " +
//                        "insert into Students (id, firstName, lastName, average) values ('LM31017', 'Ciprian', 'Ion', '8.9'); ");
//                System.out.println("inserted");
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
        } else {
            System.out.println("Failed to make connection!");
        }


    }

    @Override
    public Student findStudent(String id) {
        Statement stmt = null;
        String sql = "SELECT * from Students where id='" + id+"';";

        try {
            stmt = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet rs = null;
        try {
            System.out.println(sql);
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (rs == null)
                System.out.println("Result se is null");
            if (rs.next()){
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                Float average = rs.getFloat("average");
                rs.close();
                return new Student(id, firstName, lastName, average);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Student findStudentByName(String lastName) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String sql = "SELECT * FROM students WHERE lastName='"+lastName+"';";
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            String id = rs.getString("id");
            String firstName = rs.getString("firstName");
            Float average = rs.getFloat("average");
            return new Student(id, firstName, lastName, average);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Student> getStudents() {
        ArrayList<Student> studentArrayList = new ArrayList<Student>();
        Statement statement = null;
        ResultSet rs = null;

        try {
            statement = connection.createStatement();

            String sql = "SELECT * FROM students;";

            rs = statement.executeQuery(sql);

            while (rs.next()) {
                String id = null;
                id = rs.getString("id");
                String firstName = null;
                firstName = rs.getString("firstName");
                String lastName = null;
                lastName = rs.getString("lastName");
                Float average = null;
                average = rs.getFloat("average");

                Student student = new Student(id, firstName, lastName, average);
                studentArrayList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentArrayList;
    }
}
