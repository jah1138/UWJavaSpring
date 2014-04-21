import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 * Author: Alex
 * Version: 4/20/2014.
 */
public class BigPic {

    public static void main(String[] args) {

        List<Image> list = new ArrayList<Image>();

        int i = 0;
        while (1 > 0) {
            try {
                list.add(ImageIO.read(new File("C:\\Challenger.jpg")));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            i++;
            System.out.println(i);
        }
    }

}
