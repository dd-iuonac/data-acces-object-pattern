/*
 *
 *    @author Daniel-Domiţian Iuonac, dd.iuonac@gmail.com
 *    @since 23/01/2018
 *
 */

import java.util.ArrayList;
import java.util.Calendar;

public class Horoscop {
    public boolean isAverageIncreasing(Student student){
        return  ((student.id.length() + student.firstName.length() + student.lastName.length()) % 2 == 1);
    }

    public int countFutureLowerAverages(ArrayList<Student> students){
        Student student;
        int count = 0;
        for (int i=0; i<students.size(); i++){
            student = students.get(i);
            if (student.average > 8.00 && isAverageIncreasing(student))
                count ++;
        }
        return count;
    }

    public boolean willHaveAGoodDay(Student student){
        Calendar cal = Calendar.getInstance();
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

        return ( ( dayOfMonth + student.lastName.charAt(0)) % 2 == 1);
    }
}
