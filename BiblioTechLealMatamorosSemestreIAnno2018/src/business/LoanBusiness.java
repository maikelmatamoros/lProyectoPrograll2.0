/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import domain.Loan;
import file.LoanFile;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author maikel
 */
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
    
}
