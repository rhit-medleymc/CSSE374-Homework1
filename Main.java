import java.util.ArrayList;
import java.util.Scanner;

// Noah did this class
class IOHandler {
  private ArrayList<Order> orders;
  private ArrayList<MusicClubMember> members;
// Noah did this class
  public IOHandler(ArrayList<MusicClubMember> members) {
    orders = new ArrayList<Order>();
    this.members = members;
  }
// Noah did this class
  public void startUI(Scanner scanner) {
    System.out.println("Welcome to the Serenaders' Music Club Valentine's Song System!\r\n" + //
                       "Select the action to do:\r\n" + //
                       "1 - Customers - Order a song for Valentine's Day\r\n" + //
                       "2 - Club members - Get a report of requests for your song\r\n" + //
                       "3 - Club members - Report back that your songs are done\r\n" + //
                       "4 - Admin - Show data for all club members\r\n" + //
                       "Enter 1, 2, 3 or 4: \r\n" + //
                       "");
    recieveUserInput(scanner);
  }
// Noah did this class
  public void recieveUserInput(Scanner scanner) {
    String startChoice = scanner.nextLine();
    try {
      switch(startChoice) {
        case "1": //order a song
          createOrder(scanner);
          break;
        
        case "2": //Get a report of requests
          emailReport(scanner);
          break;
        
        case "3": //Report back that songs are done
          finishSinging(scanner);
          break;
        
        case "4": //Show data for all club members
          displayAdminData();
          break;

        default:
          throw new IllegalArgumentException();
      }
    }
    catch (Exception e) {
      System.err.print("Error, Invalid input. Please input an Integer from 1-4");
    }
    startUI(scanner); //Restart
  }
// Noah did this class
  private void createOrder(Scanner scanner){
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

    if(getMusicianBySong(songChoice).getIsSinging()){
      System.out.println("Musician is already singing and song cannot be added.");
    }

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
  //Florence did this in class
  private void emailReport(Scanner scanner){
    String introMessage = "Get a report of requests for your song...\r\n" + //
                          "Enter your member ID number: \r\n";
    System.out.print(introMessage);

    int ID = -1;
    try{
      ID = Integer.parseInt(scanner.nextLine());
    }catch(Exception E){
      System.err.println(E);
      System.err.println("Please enter a correct ID");
      emailReport(scanner);
    }

    String output = "";
    for(Order o: orders){
      String song = o.getSong();
      MusicClubMember musician = getMusicianBySong(song);
      if(musician.getID() == ID){
        musician.StartSinging();
        output = output + "\n" + o.getSweetheart();
      }
    }

    System.out.println(output);
  }
  //Florence did this in class
  private void displayAdminData() {
    for(MusicClubMember m: members){
      System.out.println(m);
    }
    for(Order o: orders){
      System.out.println(o);
    }
  }
  //Florence did this in class
  private MusicClubMember getMusicianBySong(String song) {
    for(MusicClubMember m: members){
      if(m.getSong().equals(song)){
        return m;
      }
    }
    return null;
  }
  //Florence did this in class
  private void finishSinging(Scanner scanner) {
    System.out.println("Report back that your songs are done...\r\n" + //
            "Enter your member ID number: \r\n");
    
    int ID = Integer.parseInt(scanner.nextLine());
    MusicClubMember member = getMusicianByID(ID);
    member.FinishSinging();
    String song = member.getSong();

    for(Order o: orders){
      if(o.getSong().equals(song)){
        o.ChargeCustomer();
      }
    }

  }
  //Florence did this in class
  private MusicClubMember getMusicianByID(int ID) {
    for(MusicClubMember m: members){
      if(m.getID() == ID){
        return m;
      }
    }
    return null;
  }

}

// Noah Did this class
class MusicClubMember {
  private Integer ID;
  private String name;
  private String song;
  private Boolean isSinging;
  private Boolean isDone;
  //Florence did this in class
  public MusicClubMember(Integer ID, String song, String name) {
    this.ID = ID;
    this.name = name;
    this.song = song;
    this.isSinging = false;
    this.isDone = false;
  }
// Noah did this class
  public boolean getIsSinging() {
    return this.isSinging;
  }
// Noah did this class
  public int getID() {
    return this.ID;
  }
// Noah did this class
  public String getSong() {
    return this.song;
  }
  //Florence did this in class
  public void StartSinging() {
    this.isSinging = true;
  }
  //Florence did this in class
  public void FinishSinging() {
    this.isSinging = false;
    this.isDone = true;
  }
  //Florence did this in class
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
  //Florence did this in class
  public Order(String email, String sweetheartName, String song, String creditNum) {
    this.email = email;
    this.sweetheartName = sweetheartName;
    this.song = song;
    this.creditNum = creditNum;
  }
  //Florence did this in class
  public String getSweetheart() {
    return this.sweetheartName;
  }
  //Florence did this in class
  public String getSong() {
    return song;
  }
  //Florence did this in class
  public void ChargeCustomer() {
    System.out.println("Charging " + this.email + "at card number " + this.creditNum);
  }
  //Florence did this in class
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
    handler.startUI(scanner);
    scanner.close();
  }
}