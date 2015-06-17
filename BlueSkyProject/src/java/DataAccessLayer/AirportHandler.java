package DataAccessLayer;

import Core.Provider;
import DataTransferObject.Airport;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AirportHandler {
    private final Provider provider;
    
    public AirportHandler() {
        this.provider = new Provider();
    }
    
    public ArrayList<Airport> getAll() throws SQLException, ClassNotFoundException {
        String sql = "Select MaSB, TenSB From SanBay";
        
        ResultSet data = this.provider.executeQuery(sql);
        ArrayList<Airport> airports = new ArrayList<>();
        
        while(data.next()) {
            Airport airport = new Airport();
            airport.setID(data.getString("MaSB"));
            airport.setName(data.getString("TenSB"));
            airports.add(airport);
        }
        
        this.provider.closeConnection();
        
        return airports;
    }
}
