package file;

import domain.Book;
import domain.Material;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MaterialFile {

    private String path;

    public MaterialFile() {
        this.path = "material.dat";
    } // constructor

    public void addMaterial(Material material) throws IOException, ClassNotFoundException {
        File file = new File(this.path);
        List<Material> materialList = new ArrayList<Material>();
        if (file.exists()) {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            Object aux = objectInputStream.readObject();
            materialList = (List<Material>) aux;
            objectInputStream.close();
        } // if(file.exists())
        materialList.add(material);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
        objectOutputStream.writeUnshared(materialList);
        objectOutputStream.close();
    } // addMaterial: agrega nuevo registro

    public List<ArrayList> getBooksAndAudiovisual() throws IOException, ClassNotFoundException {
        List<Material> materials = getAllMaterials();
        ArrayList<Material> books = new ArrayList<Material>();
        ArrayList<Material> audiovisuals = new ArrayList<Material>();

        for (int i = 0; i < materials.size(); i++) {
            if (materials.get(i).getClass().getName().equals("domain.Book")) {
                books.add(materials.get(i));
            } else {
                audiovisuals.add(materials.get(i));
            }
        } // for
        List<ArrayList> booksAndAudiovisual = new ArrayList<>();
        booksAndAudiovisual.add(books);
        booksAndAudiovisual.add(audiovisuals);
        return booksAndAudiovisual;
    } // getBooksAndAudiovisual: retorna lista con ArrayList de libros en la posicion 0
    // y ArrayList de Audiovisuales en la posicion 1

    public List<Material> getAllMaterials() throws IOException, ClassNotFoundException {
        File myFile = new File(this.path);
        List<Material> materialList = new ArrayList<Material>();

        if (myFile.exists()) {
            ObjectInputStream objectInputStream
                    = new ObjectInputStream(
                            new FileInputStream(myFile)
                    );
            Object aux = objectInputStream.readObject();
            materialList = (List<Material>) aux;
            objectInputStream.close();
        } // if(myFile.exists())
        return materialList;
    } // getAllMaterials: retorna lista de todos los materiales

    public ArrayList<Material> getMaterial(String type) throws IOException, ClassNotFoundException {
        if (type.equalsIgnoreCase("book")) {
            return getBooksAndAudiovisual().get(0);
        }
        ArrayList<Material> materialType = new ArrayList<>();
        ArrayList<Material> audiovisuals = getBooksAndAudiovisual().get(1);
        for (int i = 0; i < audiovisuals.size(); i++) {
            if (audiovisuals.get(i).getType().equalsIgnoreCase(type)) {
                materialType.add(audiovisuals.get(i));
            }
        } // for
        return materialType;
    } // getMaterial: retorna ArrayList según tipo 

    public Material getMaterial(int code) throws IOException, ClassNotFoundException {
        List<Material> materialList = getAllMaterials();
        Material material = null;
        for (int i = 0; i < materialList.size(); i++) {
            if (materialList.get(i).getCode() == code) {
                material = materialList.get(i);
                break;
            } // if 
        } // for i
        return material;
    } // getMaterial: retorna objeto material segun codigo

    public int getCodeMaterial() throws IOException, ClassNotFoundException {
        return getBooksAndAudiovisual().get(1).size() + 10000;
    } // getCodeMaterial: retorna codigo de material único

    public int getISBNBook() throws IOException, ClassNotFoundException {
        return getBooksAndAudiovisual().get(0).size() + 100000000;
    } // getCodeMaterial: retorna ISBN ficticio

    public void addBookExixting(int code, int quantity) throws IOException, ClassNotFoundException {
        List<Material> materials = getAllMaterials();
        for (Material material : materials) {
            if (material.getCode() == code) {
                ((Book)material).addUnit(quantity);
                rewrite(materials);
                break;
            }
        } // for
    } // addBook: agrega cantidad deseada al libro según el código

    private void rewrite(List<Material> list) throws IOException, ClassNotFoundException {
        File file = new File(this.path);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
        objectOutputStream.writeUnshared(list);
        objectOutputStream.close();
    } // rewrite: Actualiza el archivo

    private boolean update(int code, int type) throws IOException, ClassNotFoundException {
        List<ArrayList> materials = getBooksAndAudiovisual();
        
        if(type==0){
            
        }
            for (int i = 0; i < materials.get(type).size(); i++) {
                if(code>5){
                    
                }
            }

        
            return true;
    }

} // fin de la clase
