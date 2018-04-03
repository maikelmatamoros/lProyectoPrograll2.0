package business;

import domain.Loan;
import file.LoanFile;
import java.io.IOException;
import java.util.List;

public class LoanBusiness {
    private LoanFile loanFile;
    
    public LoanBusiness(){
        this.loanFile=new LoanFile();
    }
    
    public boolean addLoan(Loan loan) throws IOException, ClassNotFoundException {
        this.loanFile.addLoan(loan);
        return true;
    }
    public boolean deleteLoan(int code) throws IOException, ClassNotFoundException {
        return this.loanFile.deleteLoan(code);
    }
    public List<Loan> getPersonLoans(String ID) throws IOException, ClassNotFoundException {
        return this.loanFile.getPersonLoans(ID);
    }
        public void rewrite(int code) throws IOException, ClassNotFoundException {
            this.loanFile.rewrite(code);
        }
    
    
} // fin de la clase
