package DataAccessLayer;

import Core.Provider;
import DataTransferObject.Flight;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class FlightHandler {
    private final Provider provider;
    
    public FlightHandler() {
        this.provider = new Provider();
    }
    
    public ArrayList<Flight> limit(int page) throws SQLException, ClassNotFoundException {
        String sql = String.format(
                "Select * From ChuyenBay Limit %d, 10", (page - 1) * 10);
        
        ResultSet result = this.provider.executeQuery(sql);
        
        ArrayList<Flight> flights = new ArrayList<>();
        
        while (result.next()) {
            Flight flight = new Flight();
            flight.setID(result.getString("MaChuyen"));
            flight.setDeparture(new Date(result.getTimestamp("TG_XuatPhat").getTime()));
            flight.setArrival(new Date(result.getTimestamp("TG_HaCanh").getTime()));
            flight.setMainPilot(result.getString("MaLaiChinh"));
            flight.setTrip(result.getString("MaTuyen"));
            flight.setVicePilot(result.getString("MaLaiPhu"));
            flight.setMainStewardess(result.getString("MaTiepVienTruong"));
            flights.add(flight);
        }
        
        this.provider.closeConnection();
        
        return flights;
    }
    
    public ArrayList<Flight> limitWithKeyword(int page, String keyword) throws SQLException, ClassNotFoundException {
        String sql =
                "Select * From ChuyenBay Where MaChuyen Like '%" + keyword + "%' Limit " + Integer.toString((page - 1) * 10) + ", 10";
        
        ResultSet result = this.provider.executeQuery(sql);
        
        ArrayList<Flight> flights = new ArrayList<>();
        
        while (result.next()) {
            Flight flight = new Flight();
            flight.setID(result.getString("MaChuyen"));
            flight.setDeparture(new Date(result.getTimestamp("TG_XuatPhat").getTime()));
            flight.setArrival(new Date(result.getTimestamp("TG_HaCanh").getTime()));
            flight.setMainPilot(result.getString("MaLaiChinh"));
            flight.setTrip(result.getString("MaTuyen"));
            flight.setVicePilot(result.getString("MaLaiPhu"));
            flight.setMainStewardess(result.getString("MaTiepVienTruong"));
            flights.add(flight);
        }
        
        this.provider.closeConnection();
        
        return flights;
    }
    
    public int totalPage() throws SQLException, ClassNotFoundException {
        String sql = "Select Ceiling(Count(MaChuyen) / 10) as Total From ChuyenBay";
        
        ResultSet data = this.provider.executeQuery(sql);
        int result = 0;
        
        if (data.next()) {
            result = data.getInt("Total");
        }
        
        this.provider.closeConnection();
        
        return result;
    }
    
    public int totalPageWithKeyword(String keyword) throws SQLException, ClassNotFoundException {
        String sql = "Select Ceiling(Count(MaChuyen) / 10) as Total From ChuyenBay Where MaChuyen Like '%" + keyword + "%'";
        
        ResultSet data = this.provider.executeQuery(sql);
        int result = 0;
        
        if (data.next()) {
            result = data.getInt("Total");
        }
        
        this.provider.closeConnection();
        
        return result;
    }
}
