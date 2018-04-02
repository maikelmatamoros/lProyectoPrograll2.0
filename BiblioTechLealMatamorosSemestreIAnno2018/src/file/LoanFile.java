package file;

import domain.Loan;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class LoanFile {

    private String path;

    public LoanFile() {
        this.path = "loans.dat";
    } // constructor

    public void addLoan(Loan loan) throws IOException, ClassNotFoundException {
        File file = new File(this.path);
        List<Loan> loanList = new ArrayList<>();
        if (file.exists()) {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            Object aux = objectInputStream.readObject();
            loanList = (List<Loan>) aux;
            objectInputStream.close();
        } // if(file.exists())
        loanList.add(loan);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
        objectOutputStream.writeUnshared(loanList);
        objectOutputStream.close();
        System.out.println("Guarda");
    } // addLoan: agrega nuevo registro

    public boolean deleteLoan(int code) throws IOException, ClassNotFoundException {
        File myFile = new File(this.path);
        List<Loan> loanList = new ArrayList<>();

        if (myFile.exists()) {
            ObjectInputStream objetInputStream = new ObjectInputStream(new FileInputStream(myFile));
            Object aux = objetInputStream.readObject();
            loanList = (List<Loan>) aux;
        }

        for (int i = 0; i < loanList.size(); i++) {
            if (loanList.get(i).getCode()==code) {
                loanList.remove(i);
                ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(myFile));
                output.writeUnshared(loanList);
                output.close();
                return true;
            }
        }
        return false;
    }

    public List<Loan> getPersonLoans(String ID) throws IOException, ClassNotFoundException {
        File myFile = new File(this.path);
        List<Loan> loanList = new ArrayList<>();

        if (myFile.exists()) {
            ObjectInputStream objetInputStream = new ObjectInputStream(new FileInputStream(myFile));
            Object aux = objetInputStream.readObject();
            loanList = (List<Loan>) aux;
            objetInputStream.close();
        }

        for (int i = 0; i < loanList.size(); i++) {
            if (!loanList.get(i).getID().equalsIgnoreCase(ID)) {
                loanList.remove(i);
            }
        }
        return loanList;
    } // getAllMaterials: retorna lista de todos los materiales

} // fin de la clase
