package DataAccessLayer;

import Core.Provider;
import DataTransferObject.Ticket;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

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
}
