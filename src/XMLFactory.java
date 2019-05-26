public class XMLFactory extends DAOFactory {
    @Override
    public StudentDAO getStudentDAO() {
        return new XMLStudentDAO();
    }
}
