package room.gaming.egamingroom.models;

import java.util.Date;

public class Transfer {
    public int Id;
    public int Coins;
    public User Receiver;
    public  User Sender;
    public int ReceiverId;
    public int SenderId;
    public Date TransferDate;
}
