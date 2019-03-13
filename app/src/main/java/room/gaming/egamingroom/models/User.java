package room.gaming.egamingroom.models;

import java.util.List;

public class User {
    public int id;
    public  String firstName;
    public  String lastName;
    public  String email;
    public String username;
    public String password;
    public String fullName;
    public int addedFromLastBonus;
    public int coins;
    public String token;
    public List<Transfer> incoming;

    public List<Transfer> outgoing;
}
