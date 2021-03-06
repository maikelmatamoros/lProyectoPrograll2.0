package domain;

import java.io.Serializable;

public class Loan implements Serializable{
    private int code;
    private String loanDate;
    private String returnDate;
    private String ID;
    private String type;

    public Loan(){
        this.code =0;
        this.loanDate = "";
        this.returnDate = "";
        this.ID ="";
        this.type="";
    }
    
    public Loan(int code, String loanDate,String returnDate, String ID,String type) {
        this.code = code;
        this.loanDate = loanDate;
        this.returnDate=returnDate;
        this.ID = ID;
        this.type=type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Loan{" + "code=" + code + ", loanDate=" + loanDate + ", returnDate=" + returnDate + ", ID=" + ID + ", type=" + type + '}';
    }
    



    
}
