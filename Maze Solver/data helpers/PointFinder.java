import java.awt.MouseInfo;
import java.awt.Robot;

public class PointFinder {
   public static void main(String[] args) {
      while (true) {
         System.out.println(MouseInfo.getPointerInfo().getLocation().toString());
         try {
            new Robot().delay(200);
         } catch (Exception e) {}
      }
   }
}