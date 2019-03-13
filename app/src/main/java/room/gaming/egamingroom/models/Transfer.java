package room.gaming.egamingroom.models;

import java.util.Date;

public class Transfer {
    public int id;
    public int coins;
    public User receiver;
    public  User sender;
    public int receiverId;
    public int senderId;
    public Date transferDate;
}
