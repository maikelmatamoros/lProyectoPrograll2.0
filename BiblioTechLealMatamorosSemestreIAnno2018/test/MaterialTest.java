
import domain.Audiovisual;
import domain.Book;
import domain.Material;
import file.MaterialFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;

public class MaterialTest {

    MaterialFile materialFile;

    public MaterialTest() {
        materialFile = new MaterialFile();
    }

    //@BeforeClass
    public static void setUpClass() {
    }

    //@AfterClass
    public static void tearDownClass() {
    }

    //@Before
    public void setUp() {
    }

    //@After
    public void tearDown() {
    }

    //@Test
    public void add10Materials() {
        for (int i = 1; i <= 5; i++) {
            try {
                if (i % 2 == 0) {
//                    materialFile.addMaterial(new Book(name, author, i, theme, language, country, format, i, i, i, type)
                } else {
                    materialFile.addMaterial(new Audiovisual("brand"+i, "description"+i, i, "type"+i, true));
                }
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(MaterialTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } // add10Materials

    //@Test
    public void getAllMaterials() throws IOException {
        try {
            List<Material> materials = materialFile.getAllMaterials();
            for (Material material : materials) {
                System.out.println(material.toString());     
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MaterialTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } // getAllMaterials

    //@Test
    public ArrayList<Material> getListType(){
        try {
            ArrayList<Material> materials = materialFile.getMaterial("Book");
            materials.forEach((material) -> {
                System.out.println("- "+material.toString());
            });
            return materials;
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(MaterialTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } // getListType
    
    //@Test
    public void printBooks(){
        try {
            ArrayList<Material> books = materialFile.getBooksAndAudiovisual().get(0);
            books.forEach((book) -> {
                System.out.println("- "+book.toString());
            });
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(MaterialTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //@Test
    public void quantityBooks(){
        try {
            System.out.println(materialFile.getCodeMaterial());
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(MaterialTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    } // quantityBooks
    
    //@Test
    public void addBookExisting(){
        ArrayList<Material> list = getListType();
        for (Material material : list) {
            if(material.getCode()==100000003){
                try {
                    materialFile.addBookExixting(material.getCode(), 10);
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(MaterialTest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                System.out.println("no");
            }
        }
    }
    
} // fin de la clase
