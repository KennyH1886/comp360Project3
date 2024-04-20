package comp360project3;
import java.time.LocalDate;

public class ticket {
    protected static int time; 
    private static int numOfSeats; 
    private static LocalDate date; // impelement date implementation. 
    private static int seatNum; 


public ticket(int time, int numOfSeats, LocalDate date2, int seatNum){
    this.time = time;
    this.numOfSeats = numOfSeats;
    this.date = date2;
    this.seatNum = seatNum;
}
 


public static LocalDate getDate() {
    return date;
}

public static int getNumOfSeats() {
    return numOfSeats;
}

public static int getTime() {
    return time;
}

public static int getSeatNum() {
    return seatNum;
}

public void setSeatNum(int seatNum) {
    this.seatNum = seatNum;
}

public void setNumOfSeats(int numOfSeats) {
    this.numOfSeats = numOfSeats;
}

public void setTime(int time) {
    this.time = time;
}





}
