public class Main {
    public static void main(String[] args) {

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.JDBC);

        StudentDAO studentDAO = factory.getStudentDAO();
        if (studentDAO != null){
            Horoscop horoscop = new Horoscop();
            System.out.println(studentDAO.getStudents());
            System.out.println(studentDAO.findStudent("lm57109"));
            System.out.println(studentDAO.findStudentByName("Popescu"));
            System.out.println(horoscop.isAverageIncreasing(studentDAO.findStudent("lm65410")));
            System.out.println(horoscop.isAverageIncreasing(studentDAO.findStudent("lm31017")));
            System.out.println(horoscop.willHaveAGoodDay(studentDAO.findStudent("lm31017")));
            System.out.println(horoscop.willHaveAGoodDay(studentDAO.findStudent("lm31017")));
            System.out.println(horoscop.countFutureLowerAverages(studentDAO.getStudents()));
        }
        else System.out.println("Could not get a Student DAO");

    }
}
