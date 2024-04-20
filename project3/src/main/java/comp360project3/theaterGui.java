package comp360project3;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

public class theaterGui extends ticket {
    private static final int MAX_SEATS = 30;
    private int ticketCounterTheater1 = MAX_SEATS;
    private int ticketCounterTheater2 = MAX_SEATS;
    private int currentSeatNumberTheater1 = 1; // Initialize seat number for theater 1
    private int currentSeatNumberTheater2 = 1; // Initialize seat number for theater 2r
    LocalDate date;
    int time;
    private int theaterNumber;


    public theaterGui(int time, int numOfSeats, LocalDate date, int theaterNumber) {
        super(time, numOfSeats, date, theaterNumber);
        if (theaterNumber == 1) {
            this.currentSeatNumberTheater1 = 1; // Initialize seat number for theater 1
        } else if (theaterNumber == 2) {
            this.currentSeatNumberTheater2 = 1; // Initialize seat number for theater 2
        }
    }


    /**
     * function for if the theater is full. 
     * @param theaterNumber the specific theater chosen for the show time for the specific kung fu panda show. 
     */
    public void artistTheaterFull(int theaterNumber) {
        if (theaterNumber == 1) {
            if (ticketCounterTheater1 == 0) {
                JOptionPane.showMessageDialog(null, "Sorry, theater 1 is full for Kung Fu Panda 4 at 1:00 PM.");
            } else {
                JOptionPane.showMessageDialog(null, "Seats are available for theater 1 for Kung Fu Panda 4 at 1:00 PM.");
            }
        } else if (theaterNumber == 2) {
            if (ticketCounterTheater2 == 0) {
                JOptionPane.showMessageDialog(null, "Sorry, theater 2 is full for Kung Fu Panda 4 at 8:00 PM.");
            } else {
                JOptionPane.showMessageDialog(null, "Seats are available for theater 2 for Kung Fu Panda 4 at 8:00 PM.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid theater number.");
        }
    }



    /**
     *  Buys the ticket per the theater adn throws an excpetion if it can't . 
     * increments the seat number and updates the amount of tickets when selected 
     * @param theaterNumber the specific theater chosen for the show time for the specific kung fu panda show. 
     * @throws NoSeatAvailableException throws an excpetion if there is no seat avaliable.
     */
    public void buyTicket(int theaterNumber, int seatNumber) throws NoSeatAvailableException {
        int numOfSeats = 1; // Number of seats to buy
    
        int time1 = 1300;
        int time2 = 2000;
    
        // formats time from military time
        LocalTime localTime = LocalTime.of(time1 / 100, time1 % 100);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        String formattedTime1 = localTime.format(formatter);
    
        LocalTime localTime2 = LocalTime.of(time2 / 100, time2 % 100);
        String formattedTime2 = localTime2.format(formatter); // Update to format the correct localTime2
    
        int nextSeatNumber = getNextSeatNumber(theaterNumber, numOfSeats);
        
        if (theaterNumber == 1 && ticketCounterTheater1 > 0) {
            ticketCounterTheater1 -= numOfSeats;
            currentSeatNumberTheater1 = nextSeatNumber + numOfSeats; // Increment seat number for theater 1
            JOptionPane.showMessageDialog(null, "Ticket purchased successfully for theater 1 for Kung Fu Panda 4 at 1:00 PM! \nDate: " + getDate() +
                    "\nTime: " + formattedTime1 + "\nSeat Number: " + nextSeatNumber);
        } else if (theaterNumber == 2 && ticketCounterTheater2 > 0) {
            ticketCounterTheater2 -= numOfSeats;
            currentSeatNumberTheater2 = nextSeatNumber + numOfSeats; // Increment seat number for theater 2
            JOptionPane.showMessageDialog(null, "Ticket purchased successfully for theater 2 for Kung Fu Panda 4 at 8:00 PM! \nDate: " + getDate() +
                    "\nTime: " + formattedTime2 + "\nSeat Number: " + nextSeatNumber);
        } else {
            throw new NoSeatAvailableException("No seats available for the requested show in theater " + theaterNumber + "!");
        }

          // After a successful ticket purchase, increment the seat number for the theater
    if (theaterNumber == 1) {
        currentSeatNumberTheater1 += numOfSeats;
    } else if (theaterNumber == 2) {
        currentSeatNumberTheater2 += numOfSeats;
    }
}

    

    
    /**
     * Returns the next available seat number for the specified theater.
     * @param theaterNumber the specific theater chosen for the show time for the specific kung fu panda show.
     * @param numOfSeats the number of seats to buy.
     * @return the next available seat number.
     */
    private int getNextSeatNumber(int theaterNumber, int numOfSeats) {
        if (theaterNumber == 1) {
            int nextSeatNumber = currentSeatNumberTheater1;
            currentSeatNumberTheater1 += numOfSeats;
            return nextSeatNumber;
        } else if (theaterNumber == 2) {
            int nextSeatNumber = currentSeatNumberTheater2;
            currentSeatNumberTheater2 += numOfSeats;
            return nextSeatNumber;
        }
        return -1; // Invalid theater number
    }


 /**
 * Returns a ticket for the specified theater and seat number.
 * Increments the ticket counter and adjusts the current seat number.
 * @param theaterNumber The specific theater from which to return the ticket.
 * @param seatNumber The seat number being returned.
 * @throws IllegalArgumentException If the theater number or seat number is invalid.
 */
public void returnTicket(int theaterNumber, int seatNumber) {
    if (theaterNumber != 1 && theaterNumber != 2) {
        throw new IllegalArgumentException("Invalid theater number.");
    }

    if (theaterNumber == 1) {
        // Increment the ticket counter for theater 1
        ticketCounterTheater1++;
        // Decrement the current seat number for theater 1
        currentSeatNumberTheater1--;
        JOptionPane.showMessageDialog(null, "Ticket returned successfully for Kung Fu Panda 4 at 1:00 PM! Seat Number: " + seatNumber);
    } else if (theaterNumber == 2) {
        // Increment the ticket counter for theater 2
        ticketCounterTheater2++;
        // Decrement the current seat number for theater 2
        currentSeatNumberTheater2--;
        JOptionPane.showMessageDialog(null, "Ticket returned successfully for Kung Fu Panda 4 at 8:00 PM! Seat Number: " + seatNumber);
    }
}





    /**
     * This gui asks if you want to go to either the first or second theater time for the new kung fu panda movie. 
     * 
     * once you pick,  it loops if you want to buy more tickets. 
     * 
     * @param args
     */
    public static void main(String[] args) {
        LocalDate date;
        int time;
        int theaterNumber;
        theaterGui theater; // Declare the theaterGui object
    
        // Initialize the theaterGui object once before the loop
        theater = new theaterGui(0, 1, LocalDate.now(), 0); // Initializing with dummy values
    
        boolean continuePicking = true;
        while (continuePicking) {
            // Prompt the user for their choice
            String choice = JOptionPane.showInputDialog("Enter (1) to buy a ticket or (2) to return a ticket:");
            
            if (choice.equals("1")) {
                // User wants to buy a ticket
                theaterNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter theater number (1) for Kung Fu Panda 4 at 1:00PM" + "\n" + " or (2) for Kung Fu Panda 4 at 8:00PM:"));
    
                // Set date and time based on theater number
                if (theaterNumber == 1) {
                    date = LocalDate.of(2024, 4, 20); // April 20, 2024
                    time = 1300; // 1:00 PM
                } else if (theaterNumber == 2) {
                    date = LocalDate.of(2024, 4, 28); // April 28, 2024
                    time = 2000; // 8:00 PM
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid theater number.");
                    continue; // Go back to the loop if theater number is invalid
                }
    
                LocalTime localTime = LocalTime.of(time / 100, time % 100);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
                String formattedTime = localTime.format(formatter);
    
                // Update the theaterGui object with new date and time
                theater.setDate(date);
                theater.setTime(time);
                theater.theaterNumber = theaterNumber;
    
                try {
                    theater.artistTheaterFull(theaterNumber);
                    int numOfSeats = 1; // Number of seats to buy
                    int seatNumber = theater.getNextSeatNumber(theaterNumber, numOfSeats); // Get next available seat number    
                    theater.buyTicket(theaterNumber, seatNumber); // Buy ticket with the obtained seat number
                    int option = JOptionPane.showConfirmDialog(null, "Do you want to buy more tickets?", "Continue", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.NO_OPTION) {
                        continuePicking = false;
                    }
                } catch (NoSeatAvailableException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                    continuePicking = false; // Exit loop if no more seats available
                }
            } else if (choice.equals("2")) {
                // User wants to return a ticket
                try {
                    // Prompt the user for the theater number
                    theaterNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter theater number (1 or 2) to return the ticket:"));
    
                    // Prompt the user for the seat number
                    int seatNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter seat number to return:"));
    
                    // Call the returnTicket method with the specified theater number and seat number
                    theater.returnTicket(theaterNumber, seatNumber);
    
                    // Display a confirmation message
                    JOptionPane.showMessageDialog(null, "Ticket returned successfully.");
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Invalid theater number or seat number.");
                }
            } else {
                // Invalid choice
                JOptionPane.showMessageDialog(null, "Invalid choice. Please choose (1) to buy a ticket or (2) to return a ticket.");
            }
        }
    }
    
    
    private void setDate(LocalDate date) {
        this.date = date; // Update the date field
    }
    
    public void setTime(int time) {
        this.time = time; // Update the time field
    }

    
/**
 * Exception for no seats. 
 */
class NoSeatAvailableException extends Exception {
    public NoSeatAvailableException(String message) {
        super(message);
    }
}

}