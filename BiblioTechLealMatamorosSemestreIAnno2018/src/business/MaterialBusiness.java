package business;

import domain.Material;
import file.MaterialFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MaterialBusiness {

    private MaterialFile materialFile;

    public MaterialBusiness() {
        this.materialFile = new MaterialFile();
        
    } // constructor

    public void addMaterial(Material material, int type) throws IOException, ClassNotFoundException {
        //type 1= Other
        if (type == 1) {
            material.setCode(this.materialFile.getCodeMaterial());
        } else if (type == 2) {
            material.setCode(this.materialFile.getISBNBook());
        }
        this.materialFile.addMaterial(material);
    } // addMaterial

    public List<ArrayList> getBooksAndAudiovisual() throws IOException, ClassNotFoundException {
        return this.materialFile.getBooksAndAudiovisual();
    } // getBooksAndAudiovisual

    public void addBookExixting(int code, int quantity) throws IOException, ClassNotFoundException {
        this.materialFile.addBookExixting(code, quantity);
    } // addBookExixting
    
    public void update(int code, int type,int action) throws IOException, ClassNotFoundException {
        if(action==0){
            this.materialFile.updateR(code, type);
        }else{
            System.out.println("Entra1");
            this.materialFile.updateP(code, type);
        }
    }

} // fin de la clase
