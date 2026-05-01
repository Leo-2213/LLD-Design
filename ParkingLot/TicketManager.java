package LLDDesigns.ParkingLot;

import LLDDesigns.ParkingLot.Models.Ticket;
import LLDDesigns.ParkingLot.Models.Vehicle;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TicketManager {
    private TicketManager() {}

    private final Map<String, Ticket> ticketMap = new ConcurrentHashMap<>();

    public void addTicket(Ticket ticket){
        ticketMap.put(ticket.getTicketId(), ticket);
    }
    public Ticket getTicket(String ticketId){
        return ticketMap.get(ticketId);
    }
    public Vehicle getVehicle(String ticketId){
        return ticketMap.get(ticketId).getVehicle();
    }

    public void removeTicket(String ticketId) {
        ticketMap.remove(ticketId);
    }

    static class Loader{
        public static final TicketManager INSTANCE = new TicketManager();
    }
    public static TicketManager getInstance(){
        return Loader.INSTANCE;
    }
}
