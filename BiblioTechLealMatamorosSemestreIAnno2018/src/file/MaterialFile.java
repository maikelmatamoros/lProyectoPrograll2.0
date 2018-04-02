package file;

import domain.Audiovisual;
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
        List<ArrayList> materialList = new ArrayList<ArrayList>();
        ArrayList<Book> books = new ArrayList<Book>();
        ArrayList<Audiovisual> audiovisuals = new ArrayList<Audiovisual>();
        materialList.add(books);
        materialList.add(audiovisuals);
        if (file.exists()) {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            Object aux = objectInputStream.readObject();
            materialList = (List<ArrayList>) aux;
            objectInputStream.close();
        } // if(file.exists())
        if (material.getClass().getName().equals("domain.Book")) {
            materialList.get(0).add(material);
        } else {
            materialList.get(1).add(material);
        }
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
        objectOutputStream.writeUnshared(materialList);
        objectOutputStream.close();
    } // addMaterial: agrega nuevo registro

    public List<ArrayList> getBooksAndAudiovisual() throws IOException, ClassNotFoundException {
        File myFile = new File(this.path);
        List<ArrayList> materialList = new ArrayList<ArrayList>();
        if (myFile.exists()) {
            ObjectInputStream objectInputStream
                    = new ObjectInputStream(
                            new FileInputStream(myFile)
                    );
            Object aux = objectInputStream.readObject();
            materialList = (List<ArrayList>) aux;
            objectInputStream.close();
        } // if(myFile.exists())
        return materialList;
    } // getBooksAndAudiovisual: retorna lista con ArrayList en 0 Books y en 1 Audiovisuals

    public ArrayList<Material> getArrayListType(String type) throws IOException, ClassNotFoundException {
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
    } // getArrayListType: retorna ArrayList según tipo 

    public Material getMaterial(int code) throws IOException, ClassNotFoundException {
        List<ArrayList> materialList = getBooksAndAudiovisual();
        Material material = null;
        for (int i = 0; i < materialList.size(); i++) {
            ArrayList<Material> temp = materialList.get(i);
            for (int j = 0; j < temp.size(); j++) {
                if (temp.get(j).getCode() == code) {
                    material = (Material) materialList.get(i).get(j);
                    break;
                } // if 
            } // for j
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
        List<ArrayList> list = getBooksAndAudiovisual();
        for (int i = 0; i < list.get(0).size(); i++) {
            if (((Book) list.get(0).get(i)).getCode() == code) {
                ((Book) list.get(0).get(i)).addUnit(quantity);
                break;
            }
        }
        rewrite(list);
    } // addBook: agrega cantidad deseada al libro según el código

    private void rewrite(List<ArrayList> list) throws IOException, ClassNotFoundException {
        File file = new File(this.path);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
        objectOutputStream.writeUnshared(list);
        objectOutputStream.close();
    } // rewrite: Actualiza el archivo

    public boolean update(int code, int type) throws IOException, ClassNotFoundException {
        List<ArrayList> materials = getBooksAndAudiovisual();
        if (type == 0) {
            ArrayList<Book> listBook = materials.get(0);
            for (int i = 0; i < materials.get(type).size(); i++) {
                if (listBook.get(i).getCode() == code) {
                    listBook.get(i).setAmountAvaiable(listBook.get(i).getAmountAvaiable() - 1);
                    materials.add(0, listBook);
                }
            }
        } else {
            ArrayList<Audiovisual> listAudioV = materials.get(1);
            for (int i = 0; i < materials.get(type).size(); i++) {
                if (listAudioV.get(i).getCode() == code) {
                    listAudioV.get(i).setAvailability(false);
                    materials.add(1, listAudioV);
                }
            }
        }
        rewrite(materials);
        return true;
    } // update

} // fin de la clase
