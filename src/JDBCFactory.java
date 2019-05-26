public class JDBCFactory extends DAOFactory {
    @Override
    public StudentDAO getStudentDAO() {
        return new JDBCStudentDAO();
    }
}
