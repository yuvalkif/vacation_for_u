package dbObjects;

public class ElectronicTicket {
    private Vacation vacation;
    private String ticketId;

    public ElectronicTicket(Vacation vacation, String ticketId) {
        this.vacation = vacation;
        this.ticketId = ticketId;
    }

    public Vacation getVacation() {
        return vacation;
    }

    public String getTicketId() {
        return ticketId;
    }
}
