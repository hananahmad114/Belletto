/**
 * Class Pixel
 */
public class Pixel {

  //
  // Fields
  //
  private int x;
  private int y;
  
  //
  // Constructors
  //
  public Pixel () {
      x = 0;
      y = 0;
  };
  
  public Pixel (int x, int y) {
      this.x = x;
      this.y = y;
  };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //
  public int getX() {
      return x;
  }
  
  public int getY() {
      return y;
  }
  
  public void setX(int x) {
      this.x = x;
  }
  
  public void setY(int y) {
      this.y = y;
  }
  
  public static double distance(Pixel b1, Pixel b2) {
      return Math.sqrt(Math.pow(b1.getX()-b2.getX(), 2) +
                       Math.pow(b1.getY()-b2.getY(), 2));
  }

  //
  // Other methods
  //

}
