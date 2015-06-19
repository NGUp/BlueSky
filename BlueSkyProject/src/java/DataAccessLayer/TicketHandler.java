package DataAccessLayer;

import Core.Provider;
import DataTransferObject.Ticket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class TicketHandler {
    
    private final Provider provider;
    
    public TicketHandler() {
        this.provider = new Provider();
    }
    
    public ArrayList<Ticket> limit(int page) throws SQLException, ClassNotFoundException {
        String sql = String.format(
                "Select * From GiaVe Limit %d, 10", (page - 1) * 10);
        
        ResultSet result = this.provider.executeQuery(sql);
        
        ArrayList<Ticket> tickets = new ArrayList<>();
        
        while (result.next()) {
            Ticket ticket = new Ticket();
            ticket.setID(result.getString("MaGia"));
            ticket.setStartTime(new Date(result.getDate("NgayBD").getTime()));
            ticket.setEndTime(new Date(result.getDate("NgayKT").getTime()));
            ticket.setPrice(result.getFloat("Gia"));
            ticket.setFlight(result.getString("MaChuyen"));
            ticket.setCabin(result.getString("MaKhoang"));
            tickets.add(ticket);
        }
        
        this.provider.closeConnection();
        
        return tickets;
    }
    
    public ArrayList<Ticket> limitWithKeyword(int page, String keyword) throws SQLException, ClassNotFoundException {
        String sql = 
                "Select * From GiaVe Where MaGia Like '%" + keyword + "%' Limit " + Integer.toString((page - 1) * 10) + ", 10";
        
        ResultSet result = this.provider.executeQuery(sql);
        
        ArrayList<Ticket> tickets = new ArrayList<>();
        
        while (result.next()) {
            Ticket ticket = new Ticket();
            ticket.setID(result.getString("MaGia"));
            ticket.setStartTime(new Date(result.getDate("NgayBD").getTime()));
            ticket.setEndTime(new Date(result.getDate("NgayKT").getTime()));
            ticket.setPrice(result.getFloat("Gia"));
            ticket.setFlight(result.getString("MaChuyen"));
            ticket.setCabin(result.getString("MaKhoang"));
            tickets.add(ticket);
        }
        
        this.provider.closeConnection();
        
        return tickets;
    }
    
    public int totalPage() throws SQLException, ClassNotFoundException {
        String sql = "Select Ceiling(Count(MaGia) / 10) as Total From GiaVe";
        
        ResultSet data = this.provider.executeQuery(sql);
        int result = 0;
        
        if (data.next()) {
            result = data.getInt("Total");
        }
        
        this.provider.closeConnection();
        
        return result;
    }
    
    public int totalPageWithKeyword(String keyword) throws SQLException, ClassNotFoundException {
        String sql = "Select Ceiling(Count(MaGia) / 10) as Total From GiaVe Where MaGia Like '%" + keyword + "%'";
        
        ResultSet data = this.provider.executeQuery(sql);
        int result = 0;
        
        if (data.next()) {
            result = data.getInt("Total");
        }
        
        this.provider.closeConnection();
        
        return result;
    }
}
