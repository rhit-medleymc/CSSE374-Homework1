import java.util.ArrayList;
import java.util.Scanner;

// Noah Did this class
class IOHandler {
  private ArrayList<Order> orders;
  private ArrayList<MusicClubMember> members;

  public IOHandler(ArrayList<MusicClubMember> members) {
    orders = new ArrayList<Order>();
    this.members = members;
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
  
  private void orderSong(Scanner scanner){
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

    Order order = new Order(userEmail, userSweatheart, songChoice, userCardNum);
    orders.add(order);

    System.out.println("Done!");  
  }
  
  private void EmailReport(Scanner scanner){
    String introMessage = "Get a report of requests for your song...\r\n" + //
                          "Enter your member ID number: \r\n";
    System.out.print(introMessage);

    int ID = -1;
    try{
      ID = Integer.parseInt(scanner.nextLine());
    }catch(Exception E){
      System.err.println(E);
      System.err.println("Please enter a correct ID");
      EmailReport(scanner);
    }

    String output = "";
    for(Order o: orders){
      String song = o.getSong();
      MusicClubMember musician = getMusicianBySong(song);
      if(musician.getID() == ID){
        output = output + "\n" + o.getSweetheart();
      }
    }

    System.out.println(output);
  }
  
  private MusicClubMember getMusicianBySong(String song) {
    for(MusicClubMember m: members){
      if(m.getSong().equals(song)){
        return m;
      }
    }
    return null;
  }
  
  public void RecieveUserInput(Scanner scanner) {
    String startChoice = scanner.nextLine();
    try {
      switch(startChoice) {
        case "1": //order a song
          orderSong(scanner);
          break;
        
        case "2": //Get a report of requests
          EmailReport(scanner);
          break;
        
        case "3": //Report back that songs are done
          chargeCustomers(scanner);
          break;
        
        case "4": //Show data for all club members
          DisplayAdminData();
          break;

        default:
          throw new IllegalArgumentException();
      }
    }
    catch (Exception e) {
      System.err.print("Error, Invalid input. Please input an Integer from 1-4");
    }
    DisplayStartupMessage(); //Restart
    RecieveUserInput(scanner);
  }

  private void DisplayAdminData() {
    for(MusicClubMember m: members){
      System.out.println(m);
    }
    for(Order o: orders){
      System.out.println(o);
    }
  }

  private void chargeCustomers(Scanner scanner) {
    System.out.println("Report back that your songs are done...\r\n" + //
            "Enter your member ID number: \r\n");
    
    int ID = Integer.parseInt(scanner.nextLine());
    for(Order o: orders){
      String song = getSongByMusicianID(ID);
      if(o.getSong().equals(song)){
        o.ChargeCustomer();
      }
    }
  }

  private String getSongByMusicianID(int ID) {
    for(MusicClubMember m: members){
      if(m.getID() == ID){
        return m.getSong();
      }
    }
    return "huhh??";
  }

}

// Noah Did this class
class MusicClubMember {
  private Integer ID;
  private String name;
  private String song;
  private Boolean isSinging;
  private Boolean isDone;

  public MusicClubMember(Integer ID, String song, String name) {
    this.ID = ID;
    this.name = name;
    this.song = song;
    this.isSinging = false;
    this.isDone = false;
  }

  public int getID() {
    return this.ID;
  }

  public String getSong() {
    return this.song;
  }

  public void StartSinging() {
    this.isSinging = true;
  }

  public void FinishSinging() {
    this.isSinging = false;
    this.isDone = true;
  }
  
  @Override
  public String toString(){
    return "Member:\t\tID: " + ID + "\tname: " + name + "\tSong: " + song + "\tisSinging: " + isSinging + "\tisDone: " + isDone;
  }
}

// Noah Did this class
class Order {
  private String email;
  private String sweetheartName;
  private String song;
  private String creditNum;

  public Order(String email, String sweetheartName, String song, String creditNum) {
    this.email = email;
    this.sweetheartName = sweetheartName;
    this.song = song;
    this.creditNum = creditNum;
  }

  public String getSweetheart() {
    return this.sweetheartName;
  }

  public String getSong() {
    return song;
  }

  public void ChargeCustomer() {
    System.out.println("Charging " + this.email + "at card number" + this.creditNum);
  }

  @Override
  public String toString(){
    return "Order:\t\tEmail: " + email + "\tSong: " + song + "\tSweetheart Name: " + sweetheartName + "\tCredit Card Number: " + creditNum;
  }
}

// Noah Did this class
public class Main {
  public static void main(String[] args) {
    ArrayList<MusicClubMember> members = new ArrayList<MusicClubMember>();
    String[] songList = {"\\\"Can't Help Falling in Love\\\" by Elvis Presley", "\\\"At Last\\\" by Etta James", "\\\"Unchained Melody\\\" by The Righteous Brothers", "\\\"Perfect\\\" by Ed Sheeran", "\"All of Me\" by John Legend", "\\\"A Thousand Years\\\" by Christina Perri", "\\\"Make You Feel My Love\\\" by Adele"};
    String[] nameList = {"Jeff", "Jimmy", "John", "Jimmy", "Jacob", "Jack", "Jonah"};
    for(int i = 0; i < 7; i++){
      MusicClubMember newMember = new MusicClubMember(i, songList[i], nameList[i]);
      members.add(newMember);
    }
    IOHandler handler = new IOHandler(members);
    Scanner scanner = new Scanner(System.in);
    handler.DisplayStartupMessage();
    handler.RecieveUserInput(scanner);
    scanner.close();
  }
}