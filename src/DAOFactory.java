/*
 *
 *    @author Daniel-Domiţian Iuonac, dd.iuonac@gmail.com
 *    @since 23/01/2018
 *
 */

public abstract class DAOFactory {
    public static final int XML = 1;
    public static final int JDBC = 2;

    public abstract StudentDAO getStudentDAO();

    public static DAOFactory getDAOFactory(int whichFactory){
        switch (whichFactory){
            case XML:
                return new XMLFactory();
            case JDBC:
                return new JDBCFactory();
            default:
                return null;
        }
    }


}
