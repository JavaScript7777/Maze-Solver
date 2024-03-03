import java.awt.Robot; // for mouse and keyboard control also read the screen
import java.awt.event.InputEvent; // get mouse button codes
import java.io.File; // allow file access
import java.awt.Desktop; // launch the maze
import java.awt.event.KeyEvent; // get key codes
import java.io.FileWriter; // allow edit access to file access
import java.util.Scanner; // read use input and files
import java.awt.MouseInfo; // get mouse position
import java.awt.Color;

   /*
      can solve all mazes:
      
         1. mini
         2. easy
         3. medium
         4. wide
         5. hard
         6. hedges
         7. barrels easy
         8. barrels medium
         9. double dots
         10. boxes
         11. roads easy
         12. roads
         13. invisible mini
         14. invisible
         15. invisible hard
         16. jagged
         17. jagged hard
         18. colored
         19. colored hard
       
       allowed minimal code change to accept hedges and future mazes
   */
   
public class Solver {
   public static Robot r; // for control of the computer
   public static int row; // store current row
   public static int column; // store current column
   public static int direction; // store current direction
   public static int browser; // store wich profile is being used
   public static int difficulty; // what difficulty is requested
   public static boolean moveDone; // for multi color to fix already moved bug
   
   public static int HOME_ACCOUNT = 0; // which array index refers to my home account
   public static int SCHOOL_ACCOUNT = 1; // which array index refers to my school account
   
   public static final int HARD = 0; // array index for hard
   public static final int EASY = 1; // array index for easy
   public static final int MINI = 2; // array index for mini
   public static final int MEDIUM = 3; // array index for medium
   public static final int WIDE = 4; // array index for wide
   public static final int HEDGES = 5; // array index for hedges
   public static final int BARRELS_EASY = 6; // array index for easy barrels
   public static final int BARRELS_MEDIUM = 7; // array index for medium barrels
   public static final int DOUBLE_DOTS = 8; // array index for double dots
   public static final int BOXES = 9; // array index for boxes
   public static final int ROADS_EASY = 10; // array index for easy roads
   public static final int ROADS = 11; // array index for roads
   public static final int INVISIBLE_MINI = 12; // array index for mini invisible
   public static final int INVISIBLE = 13;  // array index for invisible
   public static final int INVISIBLE_HARD = 14; // array index for hard invisible
   public static final int JAGGED = 15; // array index for jagged
   public static final int JAGGED_HARD = 16; // array index for hard jagged
   public static final int COLORED = 17; // array index for color
   public static final int COLORED_HARD = 18; // array index for hard color
   
   public static final int[] PROFILE = {1300, 65}; // screen location for the profile picture
   public static final int[] MOUSE_ABORT = {0, 0}; // screen location for abort location
   public static final int[] ABORT_ACKNOWLEDGE = {100, 100}; // screen location to acnoledge the abortion
   public static final int[][] HARD_START = {{333, 122}, {368, 118}}; // starting location for hard
   public static final int[][] EASY_START = {{368, 175}, {400, 165}}; // starting location for easy
   public static final int[][] MINI_START = {{466, 208}}; // starting location for mini
   public static final int[][] MEDIUM_START = {{347, 158}}; // starting location for medium
   public static final int[][] WIDE_START = {{333, 255}}; // starting location for wide
   public static final int[][] HEDGES_START = {{438, 130}}; // starting location for hedges
   public static final int[][] BARRELS_EASY_START = {{370, 174}}; // starting location for easy barrels
   public static final int[][] BARRELS_MEDIUM_START = {{467, 131}}; // starting location for medium barrels
   public static final int[][] DOUBLE_DOTS_START = {{369, 175}}; // starting location double dots
   public static final int[][] BOXES_START = {{347, 158}}; // starting location for boxes
   public static final int[][] ROADS_EASY_START = {{359, 155}}; // starting location for easy roads
   public static final int[][] ROADS_START = {{336, 138}}; // starting location for roads
   public static final int[][] INVISIBLE_MINI_START = {{466, 209}}; // starting location for mini invisible
   public static final int[][] INVISIBLE_START = {{369, 174}}; // starting location for invisible
   public static final int[][] INVISIBLE_HARD_START = {{347, 158}}; // starting location for hard invisible
   public static final int[][] JAGGED_START = {{347, 158}}; // starting location for jagged
   public static final int[][] JAGGED_HARD_START ={{333, 122}}; // starting location for hard jagged
   public static final int[][] COLORED_START = {{347, 158}}; // starting location for color
   public static final int[][] COLORED_HARD_START = {{333, 122}}; // starting location for hard color
   public static final int[][] HARD_END = {{1032, 512}, {998, 471}}; // end location for hard
   public static final int[][] EASY_END = {{996, 459}, {966, 424}}; // end location for easy
   public static final int[][] MINI_END = {{898, 426}}; // end location for mini
   public static final int[][] MEDIUM_END = {{1019, 478}}; // end location for medium
   public static final int[][] WIDE_END = {{1033, 380}}; // end location for wide
   public static final int[][] HEDGES_END = {{926, 506}}; // end location for hedges
   public static final int[][] BARRELS_EASY_END = {{996, 461}}; // end location for easy barrels
   public static final int[][] BARRELS_MEDIUM_END = {{899, 504}}; // end location for medium barrels
   public static final int[][] DOUBLE_DOTS_END = {{996, 461}}; // end location for double dots
   public static final int[][] BOXES_END = {{1018, 477}}; // end location for boxes
   public static final int[][] ROADS_EASY_END = {{1007, 479}}; // end location for easy roads
   public static final int[][] ROADS_END = {{1029, 495}}; // end location for roads
   public static final int[][] INVISIBLE_MINI_END = {{898, 425}}; // end location for mini invisible
   public static final int[][] INVISIBLE_END = {{996, 460}}; // end location for invisible
   public static final int[][] INVISIBLE_HARD_END = {{1018, 477}}; // end location for hard invisible
   public static final int[][] JAGGED_END = {{1018, 476}}; // end location for jagged
   public static final int[][] JAGGED_HARD_END = {{1032, 512}}; // end location for hard jagged
   public static final int[][] COLORED_END = {{1018, 476}}; // end location for color
   public static final int[][] COLORED_HARD_END = {{1032, 512}}; // end location for hard color
   public static final int[][] HARD_BUTTON_LOCATION = {{825, 120}, {810, 115}}; // location for the button to start hard
   public static final int[][] EASY_BUTTON_LOCATION = {{600, 120}, {610, 120}}; // location for the button to start easy
   public static final int[][] MINI_BUTTON_LOCATION = {{547, 118}}; // location for the button to start mini
   public static final int[][] MEDIUM_BUTTON_LOCATION = {{684, 119}}; // location for the button to start medium
   public static final int[][] WIDE_BUTTON_LOCATION = {{762, 117}}; // location for the button to start wide 
   public static final int[][] HEDGES_BUTTON_LOCATION = {{555, 151}};// location for the button to start hedges
   public static final int[][] BARRELS_EASY_BUTTON_LOCATION = {{658, 150}}; // location for the button to start easy barrels
   public static final int[][] BARRELS_MEDIUM_BUTTON_LOCATION = {{795, 150}}; // location for the button to start medium barrels
   public static final int[][] DOUBLE_DOTS_BUTTON_LOCATION = {{594, 182}}; // location for the button to start double dots
   public static final int[][] BOXES_BUTTON_LOCATION = {{687, 182}}; // location for the button to start boxes
   public static final int[][] ROADS_EASY_BUTTON_LOCATION = {{786, 183}}; // location for the button to start easy roads
   public static final int[][] ROADS_BUTTON_LOCATION = {{583, 216}}; // location for the button to start roads
   public static final int[][] INVISIBLE_MINI_BUTTON_LOCATION = {{686, 215}}; // location for the button to start mini invisible
   public static final int[][] INVISIBLE_BUTTON_LOCATION = {{787, 212}}; // location for the button to start invisible
   public static final int[][] INVISIBLE_HARD_BUTTON_LOCATION = {{583, 245}}; // location for the button to start hard invisible
   public static final int[][] JAGGED_BUTTON_LOCATION = {{690, 245}}; // location for the button to start jagged
   public static final int[][] JAGGED_HARD_BUTTON_LOCATION = {{794, 246}}; // location for the button to start hard jagged
   public static final int[][] COLORED_BUTTON_LOCATION = {{601, 277}}; // location for the button to start colored
   public static final int[][] COLORED_HARD_BUTTON_LOCATION = {{704, 279}}; // location for the button to start hard colored
   public static final int[][][] DIFFICULTY_BUTTON = {HARD_BUTTON_LOCATION, EASY_BUTTON_LOCATION, MINI_BUTTON_LOCATION, MEDIUM_BUTTON_LOCATION, WIDE_BUTTON_LOCATION, HEDGES_BUTTON_LOCATION, BARRELS_EASY_BUTTON_LOCATION, BARRELS_MEDIUM_BUTTON_LOCATION, DOUBLE_DOTS_BUTTON_LOCATION, BOXES_BUTTON_LOCATION, ROADS_EASY_BUTTON_LOCATION, ROADS_BUTTON_LOCATION, INVISIBLE_MINI_BUTTON_LOCATION, INVISIBLE_BUTTON_LOCATION, INVISIBLE_HARD_BUTTON_LOCATION, JAGGED_BUTTON_LOCATION, JAGGED_HARD_BUTTON_LOCATION, COLORED_BUTTON_LOCATION, COLORED_HARD_BUTTON_LOCATION}; // compile starting button locations
   public static final int[][][] START = {HARD_START, EASY_START, MINI_START, MEDIUM_START, WIDE_START, HEDGES_START, BARRELS_EASY_START, BARRELS_MEDIUM_START, DOUBLE_DOTS_START, BOXES_START, ROADS_EASY_START, ROADS_START, INVISIBLE_MINI_START, INVISIBLE_START, INVISIBLE_HARD_START, JAGGED_START, JAGGED_HARD_START, COLORED_START, COLORED_HARD_START}; // compile starting locations
   public static final int[][][] END = {HARD_END, EASY_END, MINI_END, MEDIUM_END, WIDE_END, HEDGES_END, BARRELS_EASY_END, BARRELS_MEDIUM_END, DOUBLE_DOTS_END, BOXES_END, ROADS_EASY_END, ROADS_END, INVISIBLE_MINI_END, INVISIBLE_END, INVISIBLE_HARD_END, JAGGED_END, JAGGED_HARD_END, COLORED_END, COLORED_HARD_END}; // compile end locations

   public static final int[] NUM_OF_ROWS = {   20,  6, 3, 10,  7, 14,  6, 14,  6, 10,  8, 16, 3,  6, 10, 10, 20, 10, 20}; // compile row numbers for difficulties
   public static final int[] NUM_OF_COLUMNS = {35, 12, 5, 20, 35, 18, 12, 16, 12, 20, 15, 30, 5, 12, 20, 20, 35, 20, 35}; // compile columns numbers for difficulties
   public static final int NUM_OF_REPITIONS = 19; // how many times to solve mazes
   public static boolean goAgain = true; // if user has not put mouse in stopping position
   
   public static final int X = 0; // array index for the x value
   public static final int Y = 1; // array index for the y value
   
   public static final int NORTH = 0; // numeral direction for north
   public static final int EAST = 1; // numeral direction for east
   public static final int SOUTH = 2; // numeral direction for south
   public static final int WEST = 3; // numeral direction for west
   public static final int NUM_OF_DIRECTIONS = 4; // number of cardinal directions
   
   public static final int RED = 0; // array index for red
   public static final int GREEN = 1; // array index for green
   public static final int BLUE = 2; // array index for blue
   
   public static final double WALL_OFFSET_MARKER = .5; // how far from the characters position to read the wall measured in rows/columns
   public static final double SECONDARY_WALL_OFFSET = .15;
   public static final double NULL_WALL_OFFSET = 0;
   public static final int START_ONE_OFFSET = -1; // allow for start to be (1, 1) instead of (0, 0)
   public static final int RIGHT_OFFSET = 1; // direction increase for right
   public static final int LEFT_OFFSET = 3; // diection increase for left
   public static final int BACK_OFFSET = 2; // direction increase for back
   public static final int MOVE_OFFSET = 1;
   
   public static final int NORMAL_WALL = 0;
   public static final int HEDGES_WALL = 1;
   public static final int BARRELS_WALL = 2;
   public static final int ROADS_WALL = 3;
   public static final int JAGGED_WALL = 4;
   public static final int[][] WALL_COLOR = {{187, 187, 204}, {170, 204, 170}, {136, 204, 204}, {136, 170, 136}, {136, 136, 136}}; // color of walls in rgb
   public static final int[][] DOUBLE_DOTS_PALATE = {{204, 204, 170}, {170, 170, 204}};
   public static final int[][] COLOR_PALATE = {{255, 255, 255}, {238, 238, 178}, {226, 226, 124}, {218, 218, 87}, {212, 212, 63}};
   public static final int[] SPACE_COLOR = {255, 255, 255}; // color of blank space in rgb
   public static final int[] START_COLOR = {255, 255, 0}; // color of the starting circle in rgb
   public static final int[] END_COLOR = {0, 0, 255}; // color of the end circle  in rgb
   public static final int[] GUY_COLOR = {255, 215, 0}; // color of character in rgb
   public static final int[] GUY_COLOR_TWO = {255, 213, 0}; // second color charcter in rgb
   public static final int[] HOME_ACCOUNT_COLOR = {254, 254, 254}; // color of part of my home profile picture in rgb
   public static final int[] SCHOOL_ACCOUNT_COLOR = {51, 105, 30}; // color of part of my school profile picture in rgb
   public static final int SCHOOL_BLUE = 30; // blue of school profile picture for switch
   public static final int HOME_BLUE = 254; // blue of home profile picture for switch
   
   public static final int PAGE_LOAD_TIME_MILLISECONDS = 1100; // time for file to launch
   public static final int GAME_GENERATION_TIME_MILLISECONDS = 550; // time for maze to generate
   public static final int CLOSE_TAB_BUFFER_TIME = 150; // time to hold ctrl + w
   public static final int CHROME_TAB_CLOSE_PAGE_DELAY = 1000; // allow time to close tab
   public static final int MOVE_DELAY = 40; // allow character to get to final position
   public static final int READ_DELAY = 2000;
   
   public static final int SAFE_EXIT = 0; // stop code safely
   
   public static final int MILLISECONDS_TO_SECONDS = 1000; // number to dived by to go from milliseconds to seconds
   
   public static final double MOVES_PER_SECOND_PRECISION = 1000; // number of digits to find log for moves per second
   
   public static final int FOR_LOOP_START = 0; // number to startat in a for loop
   
   public static final int UNREACHABLE_DEFAULT = -1;
   
   public static final File[] LOGGER_FILE = {new File("logger files/hard.csv"), new File("logger files/easy.csv"), new File("logger files/mini.csv"), new File("logger files/medium.csv"), new File("logger files/wide.csv"), new File("logger files/hedges.csv"), new File("logger files/barrels_easy.csv"), new File("logger files/barrels_medium.csv"), new File("logger files/double_dots.csv"), new File("logger files/boxes.csv"), new File("logger files/roads_easy.csv"), new File("logger files/roads.csv"), new File("logger files/invisible_mini.csv"), new File("logger files/invisible.csv"), new File("logger files/invisible_hard.csv"), new File("logger files/jagged.csv"), new File("logger files/jagged_hard.csv"), new File("logger files/colored.csv"), new File("logger files/colored_hard.csv")}; // logger files for different difficulties
   public static final File MASTER_LOGGER_FILE = new File("logger files/MazeSolver.csv");
   public static final File GAME_FILE = new File("game/Play Mazes Online or Print Them.html"); // file to access game
   
   public static final int STARTING_STEPS = 0; // number of steps to start at
   public static final int STARTING_ROW = 1; // row to start at
   public static final int STARTING_COLUMN = 1; // column to start at
   public static final int STARTING_DIRECTION = EAST; // direction to start at
   
   
      // to oversee and control operations
   public static void main(String[] args) {
   
            // set difficulty
      while (true) {
         try {
            System.out.println(" 0) hard     1) easy      2) mini     3) medium"); // print options
            System.out.println(" 4) wide           5) hedges          6) barrels easy"); // print options
            System.out.println(" 7) barrels medium 8) double dots     9) boxes"); // print options
            System.out.println("10) roads easy    11) roads          12) invisible mini"); // print options
            System.out.println("13) invisible     14) invisible hard 15) jagged"); // print options
            System.out.println("16) jagged hard   17) colored        18) colored hard"); // print options
            System.out.print("Enter maze type: "); // ask for maze type
            difficulty = new Scanner(System.in).nextInt(); // take maze type
         } catch (Exception e) { // catch string / double
            System.out.println("Enter an int."); // print errror
            continue; // get input again
         }
         if (difficulty>18 || difficulty<0) { // see if maze is out of bounds
            System.out.println("Not a maze."); // print error
            continue; // get next input
         }
         break; // get out of loop
      }
      
      //difficulty = HARD;
      
      try {
         r = new Robot(); // create robot for use throughout program
         Desktop.getDesktop().browse(GAME_FILE.toURI()); // launch maze
      } catch (Exception e) {}
      r.delay(PAGE_LOAD_TIME_MILLISECONDS); // wait for page to load
      browser = switch (r.getPixelColor(PROFILE[X], PROFILE[Y]).getBlue()) { // read browser for dimensions
            case SCHOOL_BLUE -> HOME_ACCOUNT; // 30 is blue value of school account icon minimal fix for sudden same locations
            case HOME_BLUE -> HOME_ACCOUNT; // 254 is the blue value of home account icon
            default -> throw new RuntimeException("browser broke"); // relay that it is broken
         };
         
      for (int o = FOR_LOOP_START; o < NUM_OF_REPITIONS && goAgain; o++) { // loop for set amount of time
         row = STARTING_ROW; // set original row number
         column = STARTING_COLUMN; // set original column number
         direction = STARTING_DIRECTION; // set original direction
         try {
            if (o != FOR_LOOP_START) {
               Desktop.getDesktop().browse(GAME_FILE.toURI()); // launch maze
            }
         } catch(Exception e) {}
         r.delay(PAGE_LOAD_TIME_MILLISECONDS); // wait to load page
         clickAndReturn(DIFFICULTY_BUTTON[difficulty][browser][X], DIFFICULTY_BUTTON[difficulty][browser][Y]); // move mouse to set difficulty
         r.delay(GAME_GENERATION_TIME_MILLISECONDS); // wait for the maze to generate
         
         if (MouseInfo.getPointerInfo().getLocation().getX() == MOUSE_ABORT[X] && MouseInfo.getPointerInfo().getLocation().getY() == MOUSE_ABORT[Y]) { // tell if users mouse is in the abort position
            goAgain = false; // stop repeating if mouse is in the abort position
         }
               
               // maze solver
               
         final long start = System.currentTimeMillis(); // read start time
         int steps = switch (difficulty) {
            case HARD, EASY, MINI, WIDE, MEDIUM -> Normal.follow(NORMAL_WALL); // call normal follow with typical walls
            case HEDGES -> Normal.follow(HEDGES_WALL); // call normal follow with hedges walls
            case BARRELS_EASY, BARRELS_MEDIUM -> Normal.follow(BARRELS_WALL); // call normal with barrel wall
            case DOUBLE_DOTS -> MultiColor.follow(DOUBLE_DOTS_PALATE, false); // call multi color follow with double dots palate
            case BOXES, COLORED, COLORED_HARD -> MultiColor.follow(COLOR_PALATE, true); // call multi color follow with normal color palate
            case ROADS_EASY, ROADS-> Normal.follow(ROADS_WALL); // call normal follow with roads wall
            case INVISIBLE_MINI, INVISIBLE, INVISIBLE_HARD -> Invisible.follow(); // call invisible follow
            case JAGGED, JAGGED_HARD -> Normal.follow(JAGGED_WALL); // call normal folow with jagged wall
            default -> throw new RuntimeException("steps broke");
            };
         final long end = System.currentTimeMillis(); // read end time
         System.out.println("It took " + ((double)(end-start)/MILLISECONDS_TO_SECONDS) + " seconds and " + steps + " steps. " + isFinished()); // give user information about maze time
            
         if (MouseInfo.getPointerInfo().getLocation().getX() == MOUSE_ABORT[X] && MouseInfo.getPointerInfo().getLocation().getY() == MOUSE_ABORT[Y]) { // tell if users mouse is in the abort position
            goAgain = false; // stop repeating if mouse is in the abort position
         }
                              
               // log information
               
         try {
            FileWriter f = new FileWriter(LOGGER_FILE[difficulty], true); // open end of the apropriate logger file
            f.write("\n" + ((double)(end-start)/MILLISECONDS_TO_SECONDS) + "," + steps + "," + Math.round((steps/((double)(end-start)/MILLISECONDS_TO_SECONDS))*MOVES_PER_SECOND_PRECISION)/MOVES_PER_SECOND_PRECISION + "," + isFinished()); // give logger file nessesary info in csv format
            f.close(); // close logger file
         } catch (Exception e) {}
            
         try {
            FileWriter f = new FileWriter(MASTER_LOGGER_FILE, true); // open end of the apropriate logger file
            String diff = switch (difficulty) { // set diff to the difficulty
               case MINI -> "mini"; // set to mini
               case EASY -> "easy"; // set to easzy
               case MEDIUM -> "medium"; // set to medium
               case WIDE -> "wide"; // set to wide
               case HARD -> "hard"; // set to hard
               case HEDGES -> "hedges"; // set to hedges
               case BARRELS_EASY -> "barrels easy"; // set to barrels easy
               case BARRELS_MEDIUM -> "barrels medium"; // set to barrels medium
               case DOUBLE_DOTS -> "double dots";  // set to double dots
               case BOXES -> "boxes"; // set to boxes
               case ROADS_EASY -> "roads easy"; // set to roads easy
               case ROADS -> "roads"; // set to roads
               case INVISIBLE_MINI -> "invisible mini"; // set to invisible mini
               case INVISIBLE -> "invisible"; // set to invisible
               case INVISIBLE_HARD -> "invisible hard"; // set to invisible hard
               case JAGGED -> "jagged"; // set to jagged
               case JAGGED_HARD -> "jagged hard"; // set to jaged hard
               case COLORED -> "colored"; // set to color
               case COLORED_HARD -> "colored hard"; // set to color hard
               default -> throw new RuntimeException("diff broke");
               };
            f.write("\n" + diff + "," + ((double)(end-start)/MILLISECONDS_TO_SECONDS) + "," + steps + "," + Math.round((steps/((double)(end-start)/MILLISECONDS_TO_SECONDS))*MOVES_PER_SECOND_PRECISION)/MOVES_PER_SECOND_PRECISION + "," + isFinished()); // give logger file nessesary info in csv format
            f.close(); // close logger file
         } catch (Exception e) {}
         
         
         r.delay(READ_DELAY); // wait to close tab
         closeTab(); // close tab to preserve ram
                  
      }
         
      System.out.println(); // give spave between program running and detailed information
      try {
         Scanner a = new Scanner(MASTER_LOGGER_FILE); // read logger file
         while (a.hasNext()) { // print contents of logger file
            System.out.println(a.nextLine());
         }
         a.close(); // close logger file
      } catch (Exception e) {}
            
            // End program
      
      if (!goAgain) { // move mouse for user to tell it ended
         r.mouseMove(ABORT_ACKNOWLEDGE[X], ABORT_ACKNOWLEDGE[Y]); // move mouse to confirm acknoledgment
      }
            
      System.exit(SAFE_EXIT); // to avoid not ending (actual bug not prevention)
   
   }
   
      // click a spot and return to original location
   public static void clickAndReturn(int x, int y) {
      int x1 = (int)MouseInfo.getPointerInfo().getLocation().getX(), y1 = (int)MouseInfo.getPointerInfo().getLocation().getY(); // store original mouse location
      r.mouseMove(x, y); // move mouse to set difficulty
      r.mousePress(InputEvent.BUTTON1_MASK); // click
      r.mouseRelease(InputEvent.BUTTON1_MASK); // click
      r.mouseMove(x1, y1); // move to original location
   }
   
      // tell if the solver finished
   public static boolean isFinished() {
      r.delay(MOVE_DELAY); // wait to ensure last move finishes
      return r.getPixelColor(xToPixel(NUM_OF_ROWS[difficulty]), yToPixel(NUM_OF_ROWS[difficulty])).getGreen() != END_COLOR[GREEN]; // return if character is at end
   }
   
      // turn right
   public static void turnRight() {
      direction += RIGHT_OFFSET; // change direction to the right
      direction %= NUM_OF_DIRECTIONS; // fix overflow
   }
   
      // turn left
   public static void turnLeft() {
      direction += LEFT_OFFSET; // change direction to the left
      direction %= NUM_OF_DIRECTIONS; // fix overflow
   }
   
      // turn around
   public static void turnAround() {
      direction += BACK_OFFSET; // change direction to the back
      direction %= NUM_OF_DIRECTIONS; // fix over flow
   }
   
      // move in the faced direction
   public static void move() {
      switch(direction) {
         case NORTH:
            r.keyPress(KeyEvent.VK_UP); // go up if facing north
            row--; // change row accordingly
            return;
         case EAST:
            r.keyPress(KeyEvent.VK_RIGHT); // go right if facing east
            column++; // change column accordingly
            return;
         case SOUTH:
            r.keyPress(KeyEvent.VK_DOWN); // go down if facing south
            row++; // change row accordingly
            return;
         case WEST:
            r.keyPress(KeyEvent.VK_LEFT); // go left if facing west
            column--; // change column accordingly
            return;
         default:
            System.out.println("move broken"); // tell if move is broken
      }
   }
   
      // mazes that have one color and have walls in directions that it cant go
   public static class Normal {
   
         // follow the mazes that I consider "normal"
      public static int follow(int color) {
         int steps = STARTING_STEPS; // set starting steps
         do { // repeat until at end
            if (!wallRight(color)) { // find next move
               turnRight();
            } else if (!wallFront(color)) {
            } else if (!wallLeft(color)) {
               turnLeft();
            } else {
               turnAround(); // turn if dead end
            }
            move(); // move in the direction it is facing
            steps++; // indicate a step
         //r.delay(2000);
         } while(row != NUM_OF_ROWS[difficulty] || column != NUM_OF_COLUMNS[difficulty]);
         return steps;
      }
      
         // tell if wall is in front
      public static boolean wallFront(int color) {
         return wallDir(direction, color); // use wallDir to check for appropriate direction in front
      }
   
         // tell if wall is in a given direction
      public static boolean wallDir(int dir, int color) {
      //       switch(dir) {
      //          case NORTH -> r.mouseMove(xToPixel(column), yToPixel(row-WALL_OFFSET_MARKER));
      //          case EAST -> r.mouseMove(xToPixel(column+WALL_OFFSET_MARKER), yToPixel(row));
      //          case SOUTH -> r.mouseMove(xToPixel(column), yToPixel(row+WALL_OFFSET_MARKER));
      //          case WEST -> r.mouseMove(xToPixel(column-WALL_OFFSET_MARKER), yToPixel(row));
      //       }
      //       r.delay(2000);
         return switch(dir) {
            case NORTH -> r.getPixelColor(xToPixel(column), yToPixel(row-WALL_OFFSET_MARKER)).getBlue() == WALL_COLOR[color][BLUE]; // check north for wall
            case EAST -> r.getPixelColor(xToPixel(column+WALL_OFFSET_MARKER), yToPixel(row)).getBlue() == WALL_COLOR[color][BLUE]; // check east for wall
            case SOUTH -> r.getPixelColor(xToPixel(column), yToPixel(row+WALL_OFFSET_MARKER)).getBlue() == WALL_COLOR[color][BLUE]; // check south for wall
            case WEST -> r.getPixelColor(xToPixel(column-WALL_OFFSET_MARKER), yToPixel(row)).getBlue() == WALL_COLOR[color][BLUE]; // check west for wall
            default -> throw new RuntimeException("normal wall dir broken");
            };     
      }
   
         // tell if wall is on the left
      public static boolean wallLeft(int color) {
         return wallDir((direction+LEFT_OFFSET)%NUM_OF_DIRECTIONS, color); // use wallDir to check for appropriate direction for left
      }
   
         // tell if wall is on the right
      public static boolean wallRight(int color) {
         return wallDir((direction+RIGHT_OFFSET)%NUM_OF_DIRECTIONS, color); // use wallDir to check for appropriate direction for right
      }
   
         // tell if there is a wall behind you
      public static boolean wallBack(int color) {
         return wallDir((direction+BACK_OFFSET)%NUM_OF_DIRECTIONS, color); // use wallDir to check for appropriate direction for the back
      }
   }
   
      // mazes with multi color walls
   public class MultiColor {
      
         // follow the multi color maze
      public static int follow(int[][] palate, boolean isColor) {
         int steps = STARTING_STEPS; // set starting steps
         do { // repeat until at end
            moveDone = false; // reset the variable
            if (!wallRight(palate, isColor)) { // find next move
               if (!moveDone) { // if was not done already
                  turnRight();
               }
            } else if (!wallFront(palate, isColor)) {
            } else if (!wallLeft(palate, isColor)) {
               if (!moveDone) { // if was not done already
                  turnLeft();
               }
            } else {
               if (!moveDone) { // if was not done already
                  turnAround(); // turn if dead end
               }
            }
            if (!moveDone) { // if was not done already
               move(); // move in the direction it is facing
            }
            steps++; // indicate a step
            //r.delay(2000);
         } while(row != NUM_OF_ROWS[difficulty] || column != NUM_OF_COLUMNS[difficulty]); // while unfinished
         return steps; // return number of total steps
      }
      
         // tell if wall is in front
      public static boolean wallFront(int[][] palate, boolean isColor) {
         return wallDir(direction, palate, isColor); // use wallDir to check for appropriate direction in front
      }
   
         // tell if wall is in a given direction
      public static boolean wallDir(int dir, int[][] palate, boolean isColor) {
      //          switch (dir) {
      //             case NORTH -> r.mouseMove(xToPixel(column+((isColor)? 0:SECONDARY_WALL_OFFSET)), yToPixel(row-WALL_OFFSET_MARKER)); // move mouse to check location
      //             case EAST -> r.mouseMove(xToPixel(column+WALL_OFFSET_MARKER), yToPixel(row+((isColor)? 0:SECONDARY_WALL_OFFSET))); // move mouse to check location
      //             case SOUTH -> r.mouseMove(xToPixel(column+((isColor)? 0:SECONDARY_WALL_OFFSET)), yToPixel(row+WALL_OFFSET_MARKER)); // move mouse to check location
      //             case WEST -> r.mouseMove(xToPixel(column-WALL_OFFSET_MARKER), yToPixel(row+((isColor)? 0:SECONDARY_WALL_OFFSET))); // move mouse to check location
      //          }
         //r.delay(2000);
         switch (difficulty) {
            case COLORED, COLORED_HARD: // if color or hard color
               switch (column) {
                  case STARTING_COLUMN: // if at column 1
                     switch (row) {
                        case STARTING_ROW: // if at (1, 1)
                           boolean done1 = Invisible.moveInvis(dir); // get answer
                           if (!done1) { // check answer
                              moveDone = true; // show that character has moved
                           }
                           return done1; // return answer
                        case STARTING_ROW+MOVE_OFFSET: // if at (1, 2)
                           switch (dir) {
                              case NORTH: // if facing north
                                 boolean done2 = Invisible.moveInvis(dir); // get answer
                                 if (!done2) { // check answer
                                    moveDone = true; // show that character has moved
                                 }
                                 return done2; // return answer
                           }
                     }
                     break;
                  case STARTING_COLUMN+MOVE_OFFSET: // if at column 2
                     switch (row) {
                        case STARTING_ROW: // if at (2, 1)
                           switch (dir) {
                              case WEST: // if checking west
                                 boolean done3 = Invisible.moveInvis(dir); // get answer
                                 if (!done3) { // check answer
                                    moveDone = true; // show that the character has moved
                                 }
                                 return done3; // return answer
                           }
                     }
               }
               if (column == NUM_OF_COLUMNS[difficulty]-MOVE_OFFSET && row == NUM_OF_ROWS[difficulty]) { // if directly left of last square
                  switch (dir) {
                     case EAST: // if checking east
                        boolean done4 = Invisible.moveInvis(dir); // get answer
                        if (!done4) { // check answer
                           moveDone = true; // show that the character has moved
                        }
                        return done4; // return answer
                  }
               }
               if (column == NUM_OF_COLUMNS[difficulty] && row == NUM_OF_ROWS[difficulty]-MOVE_OFFSET) { // if on top of the last square
                  switch (dir) {
                     case SOUTH: // if checking south
                        boolean done5 = Invisible.moveInvis(dir); // get answer
                        if (!done5) { // check answer
                           moveDone = true; // show that the character has moved
                        }
                        return done5; // return answer
                  }
               }
         }
         return switch(dir) {
            case NORTH -> inPalate(r.getPixelColor(xToPixel(column+((isColor)? NULL_WALL_OFFSET:SECONDARY_WALL_OFFSET)), yToPixel(row-WALL_OFFSET_MARKER)), palate, isColor); // check north for wall
            case EAST -> inPalate(r.getPixelColor(xToPixel(column+WALL_OFFSET_MARKER), yToPixel(row+((isColor)? NULL_WALL_OFFSET:SECONDARY_WALL_OFFSET))), palate, isColor); // check east for wall
            case SOUTH -> inPalate(r.getPixelColor(xToPixel(column+((isColor)? NULL_WALL_OFFSET:SECONDARY_WALL_OFFSET)), yToPixel(row+WALL_OFFSET_MARKER)), palate, isColor); // check south for wall
            case WEST -> inPalate(r.getPixelColor(xToPixel(column-WALL_OFFSET_MARKER), yToPixel(row+((isColor)? NULL_WALL_OFFSET:SECONDARY_WALL_OFFSET))), palate, isColor); // check west for wall
            default -> throw new RuntimeException("Multicolor.walldir error");
            };     
      }
      
         // return if color is in array
      public static boolean inPalate(Color color, int[][] palate, boolean isColor) {
         final int[] color1 = {color.getRed(), color.getGreen(), color.getBlue()}; // turn color to array
         boolean equal = false; // set default
         for (int i = FOR_LOOP_START; i < palate.length && !equal; i++) { // while next element or has next match
            //if (!isColor) {
            equal = (color1[RED] == palate[i][RED]) && (color1[GREEN] == palate[i][GREEN]) && (color1[BLUE] == palate[i][BLUE]); // colors are equal
            //}
         }
         return (isColor)? !equal:equal;
      }
   
         // tell if wall is on the left
      public static boolean wallLeft(int[][] palate, boolean isColor) {
         return wallDir((direction+LEFT_OFFSET)%NUM_OF_DIRECTIONS, palate, isColor); // use wallDir to check for appropriate direction for left
      }
   
         // tell if wall is on the right
      public static boolean wallRight(int[][] palate, boolean isColor) {
         return wallDir((direction+RIGHT_OFFSET)%NUM_OF_DIRECTIONS, palate, isColor); // use wallDir to check for appropriate direction for right
      }
   
         // tell if there is a wall behind you
      public static boolean wallBack(int[][] palate, boolean isColor) {
         return wallDir((direction+BACK_OFFSET)%NUM_OF_DIRECTIONS, palate, isColor); // use wallDir to check for appropriate direction for the back
      }
   }
   
      // for mazes with invisible walls
   public static class Invisible {
      
         // follow the mazes that I consider "normal"
      public static int follow() {
         int steps = STARTING_STEPS; // set starting steps
         do { // repeat until at end
            if (!wallRight()) { // find next move
            } else if (!wallFront()) {
            } else if (!wallLeft()) {
            } else if (!wallBack()) {
            }
            steps++; // indicate a step
         //r.delay(2000);
         } while(row != NUM_OF_ROWS[difficulty] || column != NUM_OF_COLUMNS[difficulty]); // while the maze is unfinished
         return steps; // return number of total steps
      }
   
         // return if has moved
      public static boolean moveInvis(int dir) {
         switch(dir) { // use dir for switch
            case NORTH: // if direction is north
               Color colorO1 = r.getPixelColor(xToPixel(column), yToPixel(row-MOVE_OFFSET)); // get color of intended square
               r.keyPress(KeyEvent.VK_UP); // try moving
               r.delay(MOVE_DELAY); // wait for frame to pass
               Color colorS1 = r.getPixelColor(xToPixel(column), yToPixel(row-MOVE_OFFSET)); // get color of intended square
               if (!colorO1.equals(colorS1)) { // see if moved to intended square
                  row--; // log move to row
                  direction = NORTH; // update direction
                  return false; // return no wall
               }
               return true; // return that there is a wall
            case EAST: // if direction is east
               Color colorO2 = r.getPixelColor(xToPixel(column+MOVE_OFFSET), yToPixel(row)); // get color of intended square
               r.keyPress(KeyEvent.VK_RIGHT); // try moving
               r.delay(MOVE_DELAY); // wait for frame to pass
               Color colorS2 = r.getPixelColor(xToPixel(column+MOVE_OFFSET), yToPixel(row)); // get color of intended square
               if (!colorO2.equals(colorS2)) { // see if moved to intended square
                  column++; // log move to column
                  direction = EAST; // update direction
                  return false; // return no wall
               }
               return true; // return that there is a wall
            case SOUTH: // if direction is south
               Color colorO3 = r.getPixelColor(xToPixel(column), yToPixel(row+MOVE_OFFSET)); // get color of intended square
               r.keyPress(KeyEvent.VK_DOWN); // try moving
               r.delay(MOVE_DELAY); // wait for frame to pass
               Color colorS3 = r.getPixelColor(xToPixel(column), yToPixel(row+MOVE_OFFSET)); // get color of intended square
               if (!colorO3.equals(colorS3)) { // see if moved to intended square
                  row++; // log move to row
                  direction = SOUTH; // update direction
                  return false; // return no wall
               }
               return true; // return that there is a wall
            case WEST: // if direction is west
               Color colorO4 = r.getPixelColor(xToPixel(column-MOVE_OFFSET), yToPixel(row)); // get color of intended square
               r.keyPress(KeyEvent.VK_LEFT); // try moving
               r.delay(MOVE_DELAY); // wait for frame to pass
               Color colorS4 = r.getPixelColor(xToPixel(column-MOVE_OFFSET), yToPixel(row)); // get color of intended square
               if (!colorO4.equals(colorS4)) { // see if moved to intended square
                  column--; // log move to column
                  direction = WEST; // update direction
                  return false; // return no wall
               }
               return true; // return that there is a wall
            default: throw new RuntimeException("move invis broken"); // throw exception if it this fails or given wrong value
         }
      }
      
         // return if a wall is right
      public static boolean wallRight() {
         return (moveInvis((direction+RIGHT_OFFSET)%NUM_OF_DIRECTIONS)); // call move invis to right
      }
      
         // return if a wall is left
      public static boolean wallLeft() {
         return (moveInvis((direction+LEFT_OFFSET)%NUM_OF_DIRECTIONS)); // call move invis to left
      }
      
         // return if a wall is in front
      public static boolean wallFront() {
         return (moveInvis(direction)); // call move invis forward
      }
      
         // return if there is a wall behind
      public static boolean wallBack() {
         return (moveInvis((direction+BACK_OFFSET)%NUM_OF_DIRECTIONS)); // call move invis backward
      }
      
   }
   
      // turn column into robot readable value
   public static int xToPixel(double x) {
      x += START_ONE_OFFSET; // add number to allow strat to be (1,1)
      return (int)(((END[difficulty][browser][X]-START[difficulty][browser][X])/((double)NUM_OF_COLUMNS[difficulty]+START_ONE_OFFSET))*x)+START[difficulty][browser][X]; // return screen location in pixels
   }
   
      // turn column into robot readable value
   public static int yToPixel(double y) {
      y += START_ONE_OFFSET; // add number to allow start to be (1,1)
      return (int)(((END[difficulty][browser][Y]-START[difficulty][browser][Y])/((double)NUM_OF_ROWS[difficulty]+START_ONE_OFFSET))*y)+START[difficulty][browser][Y]; // return screen location in pixels
   }

      // close tabs
   public static void closeTab() {
      Runnable h = () -> {r.keyPress(KeyEvent.VK_CONTROL); r.delay(CLOSE_TAB_BUFFER_TIME); r.keyRelease(KeyEvent.VK_CONTROL);}; // create lambda interface for ctrl
      Runnable k = () -> {r.keyPress(KeyEvent.VK_W); r.delay(CLOSE_TAB_BUFFER_TIME); r.keyRelease(KeyEvent.VK_W);}; // create lambda interface for w
      new Thread(h).start();
      new Thread(k).start(); // press ctrl + w to close tab
      r.delay(CHROME_TAB_CLOSE_PAGE_DELAY); // allow time for chrome to register the short cut
   }
}