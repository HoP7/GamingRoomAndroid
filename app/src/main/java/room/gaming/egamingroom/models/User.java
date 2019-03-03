package room.gaming.egamingroom.models;

import java.util.List;

public class User {
    public int Id;
    public  String FirstName;
    public  String LastName;
    public  String Email;
    public String Username;
    public String Password;
    public String FullName;
    public int AddedFromLastBonus;
    public int Coins;

    public List<Transfer> Incoming;

    public List<Transfer> Outgoing;
}
