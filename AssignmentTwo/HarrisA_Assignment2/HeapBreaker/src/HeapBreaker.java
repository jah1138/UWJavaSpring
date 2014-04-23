import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 * Note: This program relies on importing a 6GB image file, which I haven't included in
 * as a resource out of respect for your bandwidth. So, this won't work.
 *
 * Author: Alex
 * Version: 4/20/2014.
 */
public class BigPic {

    public static void main(String[] args) {

        List<Image> list = new ArrayList<Image>();

        int i = 0;
        while (1 > 0) {
            try {
                list.add(ImageIO.read(new File("C:\\bigImage.jpg")));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            i++;
            System.out.println(i);
        }
    }

}
