/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import business.MaterialBusiness;
import domain.Audiovisual;
import domain.Book;
import domain.Material;
import file.MaterialFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yer
 */
public class MaterialFileTest {

    MaterialFile materialFile;

    public MaterialFileTest() {
        materialFile = new MaterialFile();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    //@Test
    public void addMaterial() {
        try {
            materialFile.addMaterial(new Book("name", "author", 2, "theme", 
                    "language", "country", "format", 1, 10, "type"));
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(MaterialFileTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    } // addMaterial
    
    //@Test
    public void getBooksAndAudiovisual(){
        try {
            List<ArrayList> list = materialFile.getBooksAndAudiovisual();
            for (ArrayList arrayList : list) {
                for (Object object : arrayList) {
                    System.out.println(object.toString());
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(MaterialFileTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    } // getBooksAndAudiovisual

    //@Test
    public void getArrayListType(){
        try {
            ArrayList<Material> list = materialFile.getArrayListType("book");
            for (Material material : list) {
                System.out.println(material.toString());
            }
        } catch (IOException ex) {
            Logger.getLogger(MaterialFileTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MaterialFileTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    } // getArrayListType
    
    //@Test
    public void getMaterialCode(){
        try {
            System.out.println(materialFile.getMaterial(10));
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(MaterialFileTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    } // getMaterialCode
    
    //@Test
    public void getCodeMaterial(){
        try {
            System.out.println(materialFile.getCodeMaterial());
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(MaterialFileTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    } // getCodeMaterial
    
    //@Test
    public void getISBNBook(){
        try {
            System.out.println(materialFile.getISBNBook());
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(MaterialFileTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    } // getISBNBook
    
    //@Test
    public void addBookExixting(){
        try {
            materialFile.addBookExixting(0, 5);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(MaterialFileTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    } // addBookExixting
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
