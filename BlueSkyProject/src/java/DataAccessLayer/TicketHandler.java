package DataAccessLayer;

import Core.Provider;
import DataTransferObject.Ticket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TicketHandler {
    
    private final Provider provider;
    
    public TicketHandler() {
        this.provider = new Provider();
    }
    
    public boolean insert(Ticket ticket) throws ClassNotFoundException, SQLException {
        String sql = String.format(
                "Insert Into `Ve`(MaVe, LoaiVe, NgayMua, MaChuyen, MaGhe, MaKH, MaHD, MaGia) Values ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                ticket.getID(), ticket.getCabin(), new SimpleDateFormat("yyyy-MM-dd hh:MM:ss").format(ticket.getTime()), ticket.getFlight(),
                ticket.getSeat(), ticket.getCustomer(), ticket.getBill(), ticket.getPrice());
        
        return (this.provider.executeNonQuery(sql) > 0);
    }
    
    public ArrayList<String> getBookedSeats(String flight, String cabin) throws SQLException, ClassNotFoundException {
        String sql = String.format(
                "Select MaGhe From Ve Where MaChuyen = '%s' And LoaiVe = '%s'", flight, cabin);
        
        ResultSet result = this.provider.executeQuery(sql);
        
        ArrayList<String> ticketIDs = new ArrayList<>();
        
        while (result.next()) {
            String ticketID = result.getString("MaGhe");
            
            ticketIDs.add(ticketID);
        }
        
        this.provider.closeConnection();
        
        return ticketIDs;
    }
}
