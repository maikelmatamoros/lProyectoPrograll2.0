package domain;

public class Book extends Material {

    private String name;
    private String author;
    private int year;
    private String theme;
    private String language;
    private String country;
    private String format;
    private int amount;
    private int amountAvaiable;

    public Book() {
        this.name = "";
        this.author = "";
        this.year = -1;
        this.theme = "";
        this.language = "";
        this.country = "";
        this.format = "";
        this.amount = -1;
        this.amountAvaiable = -1;
    } // constructor

    public Book(String name, String author, int year, String theme, String language, String country, String format, int amount, int amountAvaiable, int code, String type) {
        super(code, type);
        this.name = name;
        this.author = author;
        this.year = year;
        this.theme = theme;
        this.language = language;
        this.country = country;
        this.format = format;
        this.amount = amount;
        this.amountAvaiable = amountAvaiable;
    } // constructor sobrecargado

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmountAvaiable() {
        return amountAvaiable;
    }

    public void setAmountAvaiable(int amountAvaiable) {
        this.amountAvaiable = amountAvaiable;
    }

    public void addUnit(int quantity) {
        setAmount(getAmount() + quantity);
        setAmountAvaiable(getAmountAvaiable() + quantity);
    } // addUnit

    @Override
    public String toString() {
        return super.toString() + "Book{" + "name=" + name + ", author=" + author + ", year=" + year + ", theme=" + theme + ", language=" + language + ", country=" + country + ", format=" + format + ", amount=" + amount + ", amountAvaiable=" + amountAvaiable + '}';
    }
    
    

} // fin de la clase
