import java.util.ArrayList;
import java.util.Scanner;

// Noah Did this class
class IOHander {
  ArrayList<Order> orders;
  public IOHandler() {
    orders = new ArrayList<Order>();
  }

  public void DisplayStartupMessage() {
    System.out.println("Welcome to the Serenaders' Music Club Valentine's Song System!\r\n" + //
                       "Select the action to do:\r\n" + //
                       "1 - Customers - Order a song for Valentine's Day\r\n" + //
                       "2 - Club members - Get a report of requests for your song\r\n" + //
                       "3 - Club members - Report back that your songs are done\r\n" + //
                       "4 - Admin - Show data for all club members\r\n" + //
                       "Enter 1, 2, 3 or 4: \r\n" + //
                       "");
  }

  public void RecieveUserInput(String startChoice) {
    try {
      switch(startChoice) {
        case "1":
          
          System.out.println("Order a song for Valentine’s Day...\r\n" + //
                             "Select the song from this list:\r\n" + //
                             "1 - \"Can't Help Falling in Love\" by Elvis Presley\r\n" + //
                             "2 - \"At Last\" by Etta James\r\n" + //
                             "3 - \"Unchained Melody\" by The Righteous Brothers\r\n" + //
                             "4 - \"Perfect\" by Ed Sheeran\r\n" + //
                             "5 - \"All of Me\" by John Legend\r\n" + //
                             "6 - \"A Thousand Years\" by Christina Perri\r\n" + //
                             "7 – \"Make You Feel My Love\" by Adele\r\n" + //
                             "Your choice (1 – 7):\r\n" + //
                             "");
          
          String songChoice = switch (scanner.nextLine()) {
            case "1" -> "\\\"Can't Help Falling in Love\\\" by Elvis Presley";
            case "2" -> "\\\"At Last\\\" by Etta James";
            case "3" -> "\\\"Unchained Melody\\\" by The Righteous Brothers";
            case "4" -> "\\\"Perfect\\\" by Ed Sheeran";
            case "5" -> "\"All of Me\" by John Legend";
            case "6" -> "\\\"A Thousand Years\\\" by Christina Perri";
            case "7" -> "\\\"Make You Feel My Love\\\" by Adele";
            default -> throw new IllegalArgumentException();
          };

          System.out.print("Enter your email address: ");
          String userEmail = scanner.nextLine();

          System.out.print("Enter your credit card number: ");
          String userCardNum = scanner.nextLine();

          System.out.print("Enter your sweetheart's name: ");
          String userSweatheart = scanner.nextLine(); 

          Order order = new Order(userEmail, userCardNum, songChoice, userSweatheart);
          orders.add(order);

          System.out.println("Done!");         

          break;
        
        case "2":
          
          break;
        
        case "3":
          break;
        
        case "4":
          break;

        default:
          throw new IllegalArgumentException();
      }
    }
    catch (Exception e) {
      System.err.print("Error, Invalid input. Please input an Integer from 1-4");
    }
  }

  public boolean CreateOrder(String email, String sweatheartName, String song, String creditNum) {
    return false;
  }

  public boolean EmailReport(MusicClubMember member) {
    return false;
  }

}

// Noah Did this class
class MusicClubMember extends IOHandler {
  private Integer ID;
  private String name;

  public MusicClubMember(Integer ID, String song, Boolean isSinging, Boolean isDone, String name) {
    this.ID = ID;
    this.name = name;
  }

  public void StartSinging() {

  }

  public void FinishSinging() {

  }
}

// Noah Did this class
class Order extends IOHandler {
  public Order(String email, String sweetheartName, String song, String creditNum) {

  }

  public void ChargeCustomer() {

  }

  public void EmailCustomer() {

  }
}

// Noah Did this class
public class Main {
  public static void main(String[] args) {
    IOHander handler = new IOHander();
    Scanner scanner = new Scanner(System.in);
    handler.DisplayStartupMessage();
    String startChoice = scanner.nextLine();
    handler.RecieveUserInput(startChoice);
    scanner.close();
  }
}