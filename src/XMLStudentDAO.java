import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class XMLStudentDAO implements StudentDAO {
    private Document document;

    public XMLStudentDAO() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(new File("/home/daniel/IdeaProjects/horoscop/src/db.xml"));
        } catch (SAXParseException spe) {
            System.out.println("Parsing error, line " + spe.getLineNumber() + ", uri "+ spe.getSystemId());
            System.out.println("    " + spe.getMessage());
            Exception x = spe;
            if(spe.getException() != null){
                x = spe.getException();
            }
            x.printStackTrace();
        } catch (SAXException saxe) {
            Exception x = saxe;
            if (saxe.getException() != null){
                x = saxe.getException();
            }
            x.printStackTrace();
        } catch (ParserConfigurationException | IOException pce) {
            // Parser with specified options can't be built
            pce.printStackTrace();
        }
    }

    @Override
    public Student findStudent(String id) {
        Element root = document.getDocumentElement();
        NodeList students = root.getElementsByTagName("student");

        for(int i=0; i<students.getLength(); i++){
            Element student = (Element) students.item(i);
            String idx = student.getAttribute("id");
            if (idx.equals(id)){
                String firstName = student.getAttribute("firstName");
                String lastName = student.getAttribute("lastName");
                Float average = Float.parseFloat(student.getAttribute("average"));
                return new Student(idx, firstName, lastName, average);
            }
        }
        return null;
    }

    @Override
    public Student findStudentByName(String lastName) {
        Element root = document.getDocumentElement();
        NodeList students = root.getElementsByTagName("student");

        for(int i=0; i<students.getLength(); i++){
            Element student = (Element) students.item(i);
            String name = student.getAttribute("lastName");
            if (name.equals(lastName)){
                String id = student.getAttribute("id");
                String firstName = student.getAttribute("firstName");
                Float average = Float.parseFloat(student.getAttribute("average"));
                return new Student(id, firstName, lastName, average);
            }
        }
        return null;
    }

    @Override
    public ArrayList<Student> getStudents() {
        Element root = document.getDocumentElement();
        NodeList students = root.getElementsByTagName("student");
        ArrayList<Student> studentsList = new ArrayList<Student>(students.getLength());

        for (int i=0;i<students.getLength();i++){
            Element student = (Element) students.item(i);
            String id = student.getAttribute("id");
            String firstName = student.getAttribute("firstName");
            String lastName = student.getAttribute("lastName");
            Float average = Float.parseFloat(student.getAttribute("average"));
            studentsList.add(new Student(id, firstName, lastName, average));
        }
        return studentsList;
    }
}
