package file;

import domain.Student;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class StudentFile {

    private RandomAccessFile randomAccessFile;
    private int regsQuantity;
    private int regSize;
    private String myFilePath;

    public StudentFile(File file) throws IOException {
        this.myFilePath = file.getPath();
        this.regSize = 100;
        if (file.exists() && !file.isFile()) {
            throw new IOException(file.getName() + " is an invalid file");
        } else {
            this.randomAccessFile = new RandomAccessFile(file, "rw");
            this.regsQuantity = (int) Math.ceil((double) this.randomAccessFile.length() / (double) regSize);
        }
    } // constructor

    private void close() throws IOException {
        randomAccessFile.close();
    } // close: MUY IMPORTANTE, cerrar nuestros archivos

    public int fileSize() {
        return this.regsQuantity;
    } // fileSize: indica la cantidad de registros de nuestro archivo

    private boolean putValue(int position, Student student) throws IOException {
        if (!(position >= 0 && position <= this.regsQuantity)) {
            System.err.println("1001 - Record position is out of bounds");
            return false;
        } else {
            if (student.sizeInBytes() > this.regSize) {
                System.err.println("1002 - Record size id out of bounds");
                return false;
            } else {
                this.randomAccessFile.seek(position * this.regSize);
                this.randomAccessFile.writeUTF(student.getName());
                this.randomAccessFile.writeUTF(student.getLastName());
                this.randomAccessFile.writeUTF(student.getCarrera());
                this.randomAccessFile.writeInt(student.getYear());
                this.randomAccessFile.writeUTF(student.getId());
                return true;
            } // if (vehicle.sizeInBytes() > this.regSize)
        } // if (!(position >= 0 && position <= this.regsQuantity))
    } // putValue: insertar un nuevo registro en una posición específica

    public boolean addEndRecord(Student student) throws IOException {
        boolean success = putValue(this.regsQuantity, student);
        if (success) {
            ++this.regsQuantity;
        } // if
        return success;
    } // addEndRecord: insertar al final del archivo

    public Student getStudent(int position) throws IOException {
        if (position >= 0 && position <= this.regsQuantity) {
            this.randomAccessFile.seek(position * this.regSize);
            Student tempStudent = new Student();
            tempStudent.setName(this.randomAccessFile.readUTF());
            tempStudent.setLastName(this.randomAccessFile.readUTF());
            tempStudent.setCarrera(this.randomAccessFile.readUTF());
            tempStudent.setYear(this.randomAccessFile.readInt());
            tempStudent.setId(this.randomAccessFile.readUTF());

            return tempStudent;

        } else {
            System.err.println("1003 - position is out of bouns");
            return null;
        }
    } // getVehicle: obtiene vehiculo segun posicion

    public ArrayList<Student> getAllStudents() throws IOException {
        ArrayList<Student> vehiclesArray = new ArrayList<>();
        for (int i = 0; i < this.regsQuantity; i++) {
            Student studentTemp = this.getStudent(i);
            if (studentTemp != null) {
                vehiclesArray.add(studentTemp);
            }
        } // for
        return vehiclesArray;
    } // getAllVehicles: retorna todos los vehiculos registrados

    public void deleteStudent(String id) throws IOException {
        Student studentTemp;
        ArrayList<Student> list = new ArrayList<>();
        for (int i = 0; i < this.regsQuantity; i++) {
            studentTemp = this.getStudent(i);
            if (!studentTemp.getId().equals(id)) {
                list.add(studentTemp);
            }//if
        }//for

        File file = new File(myFilePath);
        file.delete();
        this.randomAccessFile = new RandomAccessFile(myFilePath, "rw");
        this.regsQuantity = 0;
        for (int i = 0; i < list.size(); i++) {
            this.addEndRecord(list.get(i));
        }//for

    } // eliminar vehicle: elimina los Vehiculos del archivo

    public boolean isValid(String id) throws IOException {
        ArrayList<Student> list = getAllStudents();
        for (Student student : list) {
            if (student.getId().equalsIgnoreCase(id)) {
                return false;
            }
        }
        return true;
    } // is Valid: verifica que la serie no esté repetida.

    public boolean updateRecord(Student studentUpdate, int position) throws IOException {
        return putValue(position, studentUpdate);
    } // updateRecord: actualiza registro

    public int getQuantCareer(String career) throws IOException {
        ArrayList<Student> list = getAllStudents();
        int quantity = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCarrera().equals(career)) {
                quantity++;
            }
        }
        return quantity;
    }

    public ArrayList<Student> getStudentsByName(String name) throws IOException {
        ArrayList<Student> vehiclesArray = new ArrayList<>();
        for (int i = 0; i < this.regsQuantity; i++) {

            Student studentTemp = this.getStudent(i);
            if (name.length() <= studentTemp.getName().length()) {
                String subString = studentTemp.getName().substring(0, name.length());
                if (subString.equalsIgnoreCase(name)) {
                    vehiclesArray.add(studentTemp);
                }
            }

        } // for
        return vehiclesArray;
    } // getAllVehicles: retorna todos los vehiculos registrados
        public boolean validLogin(String ID) throws IOException {
        for (int i = 0; i < this.regsQuantity; i++) {
            Student studentTemp = this.getStudent(i);
            if (studentTemp.getId().equalsIgnoreCase(ID)) {
                return true;
            }
        } // for
        return false;
    } // getAllVehicles: retorna todos los vehiculos registrados

} // fin de la clase
