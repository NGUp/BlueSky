package DataAccessLayer;

import Core.Provider;
import DataTransferObject.Plane;
import DataTransferObject.Trip;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class TripHandler {
    private final Provider provider;
    
    public TripHandler() {
        this.provider = new Provider();
    }
    
    public boolean insert(Trip trip) throws SQLException, ClassNotFoundException {
        String sql = String.format(
                "Insert Into `TuyenBay`(MaTuyen, TenTuyen, MaSB_Di, MaSB_Den) Values ('%s', '%s', '%s', '%s')",
                trip.getID(), trip.getName(), trip.getFrom(), trip.getTo());
        
        return (this.provider.executeNonQuery(sql) > 0);
    }
    
    public ArrayList<Trip> limit(int page) throws SQLException, ClassNotFoundException, UnsupportedEncodingException {
        String sql = String.format(
                "Select * From TuyenBay Limit %d, 20", (page - 1));
        
        ResultSet result = this.provider.executeQuery(sql);
        
        ArrayList<Trip> trips = new ArrayList<>();
        
        while (result.next()) {
            Trip trip = new Trip();
            trip.setID(result.getString("MaTuyen"));
            trip.setName((new String(result.getString("TenTuyen").getBytes("8859_1"),"UTF-8")));
            trip.setFrom(result.getString("MaSB_Den"));
            trip.setTo(result.getString("MaSB_Di"));
            trips.add(trip);
        }
        
        this.provider.closeConnection();
        
        return trips;
    }
    
    public String getName(String ID) throws SQLException, ClassNotFoundException, UnsupportedEncodingException {
        String sql = String.format("Select TenSB From SanBay Where MaSB = '%s'", ID);
        
        ResultSet data = this.provider.executeQuery(sql);
        String result = "";
        
        if (data.next()) {
            result = data.getString("TenSB");
        }
        
        this.provider.closeConnection();
        
        return result;
    }
    
    public Trip one(String ID) throws SQLException, ClassNotFoundException, UnsupportedEncodingException {
        String sql = String.format(
                "Select * From TuyenBay Where MaTuyen = '%s'", ID);
        
        ResultSet result = this.provider.executeQuery(sql);
        
        Trip trip = new Trip();
        
        if (result.next()) {
            trip.setID(result.getString("MaTuyen"));
            trip.setName((new String(result.getString("TenTuyen").getBytes("8859_1"),"UTF-8")));
            trip.setFrom(result.getString("MaSB_Den"));
            trip.setTo(result.getString("MaSB_Di"));
        }
        
        this.provider.closeConnection();
        
        return trip;
    }
    
    public boolean update(Trip trip) throws SQLException, ClassNotFoundException {
        String sql = String.format(
                "Update `TuyenBay` Set TenTuyen = '%s', MaSB_Den = '%s', MaSB_Di = '%s' Where MaTuyen = '%s'",
                trip.getName(), trip.getFrom(), trip.getTo(), trip.getID());
        
        return (this.provider.executeNonQuery(sql) > 0);
    }
}
