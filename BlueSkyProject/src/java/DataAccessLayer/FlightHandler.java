package DataAccessLayer;

import Core.Provider;
import DataTransferObject.Flight;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
            flight.setPlane(result.getString("MaMB"));
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
            flight.setPlane(result.getString("MaMB"));
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
    
    public boolean insert(Flight flight) throws SQLException, ClassNotFoundException {
        String sql = String.format(
                "Insert Into `ChuyenBay`(MaChuyen, TG_XuatPhat, TG_HaCanh, MaMB, MaTuyen, MaLaiChinh, MaLaiPhu, MaTiepVienTruong) Values" +
                "('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                flight.getID(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(flight.getDeparture()), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(flight.getArrival()),
                flight.getPlane(), flight.getTrip(), flight.getMainPilot(), flight.getVicePilot(), flight.getMainStewardess());
        
        return (this.provider.executeNonQuery(sql) > 0);
    }
    
    public boolean update(Flight flight) throws SQLException, ClassNotFoundException {
        String sql = String.format(
                "Update `ChuyenBay` Set TG_XuatPhat = '%s', TG_HaCanh = '%s', MaMB = '%s', MaTuyen = '%s', MaLaiChinh = '%s', MaLaiPhu = '%s', MaTiepVienTruong = '%s' Where MaChuyen = '%s'",
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(flight.getDeparture()), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(flight.getArrival()),
                flight.getPlane(), flight.getTrip(), flight.getMainPilot(), flight.getVicePilot(), flight.getMainStewardess(), flight.getID());
        
        return (this.provider.executeNonQuery(sql) > 0);
    }
    
    public Flight one(String ID) throws SQLException, ClassNotFoundException {
        String sql = String.format(
                "Select * From ChuyenBay Where MaChuyen = '%s'", ID);
        
        ResultSet result = this.provider.executeQuery(sql);
        
        Flight flight = null;
        
        if (result.next()) {
            flight = new Flight();
            flight.setID(result.getString("MaChuyen"));
            flight.setDeparture(new Date(result.getTimestamp("TG_XuatPhat").getTime()));
            flight.setArrival(new Date(result.getTimestamp("TG_HaCanh").getTime()));
            flight.setMainPilot(result.getString("MaLaiChinh"));
            flight.setTrip(result.getString("MaTuyen"));
            flight.setPlane(result.getString("MaMB"));
            flight.setVicePilot(result.getString("MaLaiPhu"));
            flight.setMainStewardess(result.getString("MaTiepVienTruong"));
        }
        
        this.provider.closeConnection();
        
        return flight;
    }
}
