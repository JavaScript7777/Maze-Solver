import java.awt.Robot;
import java.awt.Point;
import java.awt.MouseInfo;

public class ColorFinder {
   public static void main(String[] args) {
      while (true) {
         try {
            Point p = MouseInfo.getPointerInfo().getLocation();
            System.out.println(new Robot().getPixelColor((int)p.getX(), (int)p.getY()));
            new Robot().delay(200);
         } catch (Exception e) {}
      }
   }
}