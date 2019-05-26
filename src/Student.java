public class Student {
    public String firstName;
    public String lastName;
    public String id;
    public Float average;

    public Student(String id, String firstName, String lastName, Float average) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.average = average;
    }

    @Override
    public String toString() {
        return id + ":" + firstName+":"+lastName+":"+average.toString();
    }
}
