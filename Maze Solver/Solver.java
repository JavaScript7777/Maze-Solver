import java.awt.Robot; // for mouse and keyboard control also read the screen
import java.awt.event.InputEvent; // get mouse button codes
import java.io.File; // allow file access
import java.awt.Desktop; // launch the maze
import java.awt.event.KeyEvent; // get key codes
import java.io.FileWriter; // allow edit access to file access
import java.util.Scanner; // read use input and files
import java.awt.MouseInfo; // get mouse position
   
public class Solver {
   public static Robot r; // for control of the computer
   public static int row; // store current row
   public static int column; // store current column
   public static int direction; // store current direction
   public static int browser; // store wich profile is being used
   public static int difficulty; // what difficulty is requested
   
   public static int HOME_ACCOUNT = 0; // which array index refers to my home account
   public static int SCHOOL_ACCOUNT = 1; // which array index refers to my school account
   
   public static final int HARD = 0; // array index for hard
   public static final int EASY = 1; // array index for easy
   
   public static final int[] PROFILE = {1300, 65}; // screen location for the profile picture
   public static final int[][] HARD_START = {{333, 122}, {368, 118}}; // starting location for hard
   public static final int[][] EASY_START = {{368, 175}, {400, 165}}; // starting location for easy
   public static final int[][] HARD_END = {{1032, 512}, {998, 471}}; // end location for hard
   public static final int[][] EASY_END = {{996, 459}, {966, 424}}; // end location for easy
   public static final int[][] HARD_BUTTON_LOCATION = {{825, 120}, {810, 115}}; // location for the button to start hard
   public static final int[][] EASY_BUTTON_LOCATION = {{600, 120}, {610, 120}}; // location for the button to start easy
   public static final int[][][] DIFFICULTY_BUTTON = {HARD_BUTTON_LOCATION, EASY_BUTTON_LOCATION}; // compile starting button locations
   public static final int[][][] START = {HARD_START, EASY_START}; // compile starting locations
   public static final int[][][] END = {HARD_END, EASY_END}; // compile end locations

   public static final int[] NUM_OF_ROWS = {20, 6}; // compile row numbers for difficulties
   public static final int[] NUM_OF_COLUMNS = {35, 12}; // compile columns numbers for difficulties
   public static final int NUM_OF_REPITIONS = 10; // how many times to solve mazes
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
   
   public static final double WALL_OFFSET_MARKER = .6; // how far from the characters position to read the wall measured in rows/columns
   public static final int START_ONE_OFFSET = -1; // allow for start to be (1, 1) instead of (0, 0)
   public static final int RIGHT_OFFSET = 1; // direction increase for right
   public static final int LEFT_OFFSET = 3; // diection increase for left
   public static final int BACK_OFFSET = 2; // direction increase for back
   
   public static final int[] WALL_COLOR = {187, 187, 204}; // color of walls in rgb
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
   public static final int LAST_MOVE_DELAY = 40; // allow character to get to final position
   
   public static final int SAFE_EXIT = 0; // stop code safely
   
   public static final int MILLISECONDS_TO_SECONDS = 1000; // number to dived by to go from milliseconds to seconds
   
   public static final double MOVES_PER_SECOND_PRECISION = 1000; // number of digits to find log for moves per second
   
   public static final int FOR_LOOP_START = 0; // number to startat in a for loop
   
   public static final File[] LOGGER_FILE = {new File("hard.csv"), new File("easy.csv")}; // logger files for different difficulties
   public static final File GAME_FILE = new File("Play Mazes Online or Print Them.html"); // file to access game
   
   public static final int STARTING_STEPS = 0; // number of steps to start at
   public static final int STARTING_ROW = 1; // row to start at
   public static final int STARTING_COLUMN = 1; // column to start at
   public static final int STARTING_DIRECTION = EAST; // direction to start at
   
   
      // to oversee and control operations
   public static void main(String[] args) {
   
//          // variable difficulty
//       System.out.println("0) Hard 1) Easy");
//       difficulty = new Scanner(System.in).nextInt();

            // set difficulty
         difficulty = HARD;
         
//          // Find points in maze comment out everything else when in use
//       try {
//          r = new Robot();
//          Desktop.getDesktop().browse(GAME_FILE.toURI());
//       } catch (Exception e) {}
//       r.delay(PAGE_LOAD_TIME_MILLISECONDS); // wait to load page
//       r.mouseMove(DIFFICULTY_BUTTON[EASY][HOME_ACCOUNT][X], DIFFICULTY_BUTTON[EASY][HOME_ACCOUNT][Y]);
//       r.mousePress(InputEvent.BUTTON1_MASK); // click
//       r.mouseRelease(InputEvent.BUTTON1_MASK); // click
//       r.mouseMove(966, 424);
   
         try {
            r = new Robot(); // create robot for use throughout program
            Desktop.getDesktop().browse(GAME_FILE.toURI()); // launch maze
         } catch (Exception e) {}
         r.delay(PAGE_LOAD_TIME_MILLISECONDS); // wait for page to load
         browser = switch (r.getPixelColor(PROFILE[X], PROFILE[Y]).getBlue()) { // read browser for dimensions
            case SCHOOL_BLUE -> SCHOOL_ACCOUNT; // 30 is blue value of school account icon
            case HOME_BLUE -> HOME_ACCOUNT; // 254 is the blue value of home account icon
            default -> u(r.getPixelColor(PROFILE[X], PROFILE[Y]).toString()); // relay that it is broken
         };
         
         for (int o = FOR_LOOP_START; o < NUM_OF_REPITIONS && goAgain; o++) { // loop for set amount of time
            row = STARTING_ROW; // set original row number
            column = STARTING_COLUMN; // set original column number
            direction = STARTING_DIRECTION; // set original direction
            try {
               if (o != 0) {
                  Desktop.getDesktop().browse(GAME_FILE.toURI()); // launch maze
               }
            } catch(Exception e) {}
            r.delay(PAGE_LOAD_TIME_MILLISECONDS); // wait to load page
            r.mouseMove(DIFFICULTY_BUTTON[difficulty][browser][X], DIFFICULTY_BUTTON[difficulty][browser][Y]); // move mouse to set difficulty
            r.mousePress(InputEvent.BUTTON1_MASK); // click
            r.mouseRelease(InputEvent.BUTTON1_MASK); // click
            r.delay(GAME_GENERATION_TIME_MILLISECONDS); // wait for the maze to generate
               
               // maze solver
               
            final long start = System.currentTimeMillis(); // read start time
            int steps = STARTING_STEPS; // set starting steps
            while(row != NUM_OF_ROWS[difficulty] || column != NUM_OF_COLUMNS[difficulty]) { // repeat until at end
               if (!wallRight()) { // find next move
                  turnRight();
               } else if (!wallFront()) {
               } else if (!wallLeft()) {
                  turnLeft();
               } else {
                  turnAround(); // turn if dead end
               }
               move(); // move in the direction it is facing
               steps++; // indicate a step
            }
            final long end = System.currentTimeMillis(); // read end time
            System.out.println("It took " + ((double)(end-start)/MILLISECONDS_TO_SECONDS) + " seconds and " + steps + " steps."); // give user information about maze time
            
            if (MouseInfo.getPointerInfo().getLocation().getX() == 0 && MouseInfo.getPointerInfo().getLocation().getY() == 0) { // tell if users mouse is in the abort position
               goAgain = false; // stop repeating if mouse is in the abort position
            }
                              
               // log information
               
            try {
               FileWriter f = new FileWriter(LOGGER_FILE[difficulty], true); // open end of the apropriate logger file
               f.write("\n" + ((double)(end-start)/MILLISECONDS_TO_SECONDS) + "," + steps + "," + Math.round((steps/((double)(end-start)/MILLISECONDS_TO_SECONDS))*MOVES_PER_SECOND_PRECISION)/MOVES_PER_SECOND_PRECISION + "," + isFinished()); // give logger file nessesary info in csv format
               f.close(); // close logger file
            } catch (Exception e) {}
                  
            closeTab(); // close tab to preserve ram
                  
         }
         
         System.out.println(); // give spave between program running and detailed information
         try {
            Scanner a = new Scanner(LOGGER_FILE[difficulty]); // read logger file
            while (a.hasNext()) { // print contents of logger file
               System.out.println(a.nextLine());
            }
            a.close(); // close logger file
         } catch (Exception e) {}
            
            // End program
            
         System.exit(SAFE_EXIT); // to avoid not ending (actual bug not prevention)
   
   }
   
      // failed code display
   public static int u(String s) {
      System.out.println("fail"); // print that the code failed
      System.out.println(s); // print read color
      return HARD_START.length; // return out of bounds number
   }
   
      // tell if the solver finished
   public static boolean isFinished() {
      r.delay(LAST_MOVE_DELAY); // wait to ensure last move finishes
      return r.getPixelColor(xToPixel(NUM_OF_ROWS[difficulty]), yToPixel(NUM_OF_ROWS[difficulty])).getGreen() != END_COLOR[GREEN]; // return if character is at end
   }
   
      // turn right (under the hood)
   public static void turnRight() {
      direction += RIGHT_OFFSET; // change direction to the right
      direction %= NUM_OF_DIRECTIONS; // fix overflow
   }
   
      // turn left (under the hood)
   public static void turnLeft() {
      direction += LEFT_OFFSET; // change direction to the left
      direction %= NUM_OF_DIRECTIONS; // fix overflow
   }
   
      // turn around (under the hood)
   public static void turnAround() {
      direction += BACK_OFFSET; // change direction to the back
      direction %= NUM_OF_DIRECTIONS; // fix over flow
   }
   
      // move in the under the hood diection visibly
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
   
      // tell if wall is in front
   public static boolean wallFront() {
      return wallDir(direction);
   }
   
    // tell if wall is in a given direction
   public static boolean wallDir(int dir) {
      return switch(dir) {
         case NORTH -> r.getPixelColor(xToPixel(column), yToPixel(row-WALL_OFFSET_MARKER)).getBlue() == WALL_COLOR[BLUE];
         case EAST -> r.getPixelColor(xToPixel(column+WALL_OFFSET_MARKER), yToPixel(row)).getBlue() == WALL_COLOR[BLUE];
         case SOUTH -> r.getPixelColor(xToPixel(column), yToPixel(row+WALL_OFFSET_MARKER)).getBlue() == WALL_COLOR[BLUE];
         case WEST -> r.getPixelColor(xToPixel(column-WALL_OFFSET_MARKER), yToPixel(row)).getBlue() == WALL_COLOR[BLUE];
         default -> true;
         };
   }
   
      // tell if wall is on the left
   public static boolean wallLeft() {
      return wallDir((direction+LEFT_OFFSET)%NUM_OF_DIRECTIONS);
   }
   
      // tell if wall is on the right
   public static boolean wallRight() {
      return wallDir((direction+RIGHT_OFFSET)%NUM_OF_DIRECTIONS);
   }
   
      // tell if there is a wall behind you
   public static boolean wallBack() {
      return wallDir((direction+BACK_OFFSET)%NUM_OF_DIRECTIONS);
   }
   
     // turn column into robot readable value
   public static int xToPixel(double x) {
      x = x + START_ONE_OFFSET;
      return (int)(((END[difficulty][browser][X]-START[difficulty][browser][X])/((double)NUM_OF_COLUMNS[difficulty]+START_ONE_OFFSET))*x)+START[difficulty][browser][X];
   }
   
      // turn column into robot readable value
   public static int yToPixel(double y) {
      y = y + START_ONE_OFFSET;
      return (int)(((END[difficulty][browser][Y]-START[difficulty][browser][Y])/((double)NUM_OF_ROWS[difficulty]+START_ONE_OFFSET))*y)+START[difficulty][browser][Y];
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