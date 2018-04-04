package business;

import domain.Student;
import file.StudentFile;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class StudentBusiness {

    private StudentFile studentFile;

    public StudentBusiness() throws IOException {
        this.studentFile = new StudentFile(new File("Student.dat"));
    }

    public boolean addEndRecord(Student student) throws IOException {
        return this.studentFile.addEndRecord(student);
    } // addEndRecord

    public ArrayList<Student> getAllStudents() throws IOException {
        return this.studentFile.getAllStudents();
    }//getAllVehicles

    public boolean deleteStudent(String id) throws IOException {
        if (!this.studentFile.isValid(id)) {
            this.studentFile.deleteStudent(id);
            return true;
        } else {
            return false;
        }
    }

    public boolean updateRecord(Student studentUpdate, int position) throws IOException {
        return this.studentFile.updateRecord(studentUpdate, position);
    }

    public int getQuantCareer(String career) throws IOException {
        return this.studentFile.getQuantCareer(career);
    }

    public ArrayList<Student> getStudentsByName(String name) throws IOException {
        return this.studentFile.getStudentsByName(name);
        
    }
    public boolean validLogin(String ID) throws IOException {
        return this.studentFile.validLogin(ID);

    }
    public int getPosition(String id) throws IOException {
        return this.studentFile.getPosition(id);
    }
        public Student getStudent(int position) throws IOException {
            return this.studentFile.getStudent(position);
        }
} // fin de la clase
