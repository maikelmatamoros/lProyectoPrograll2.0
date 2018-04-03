package domain;


import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
 
public class CustomPanel extends JPanel{    
    private URL url;
    private Image image;
    private int x, y, w, h;
    
    public CustomPanel(int imageNum, int x, int y, int w, int h){
        if(imageNum==1){
            this.url = getClass().getResource("/assets/fondo.png");
        }else{
            this.url = getClass().getResource("/assets/mainFondo.png");
        }
        
        this.image = new ImageIcon(url).getImage();
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
 
    @Override
    public void paint(Graphics g){
        g.drawImage(image, this.x, this.y, this.w, this.h, this);
        setOpaque(false);
        super.paint(g);
    }
    
} // fin de la clase