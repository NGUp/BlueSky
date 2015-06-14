package DataAccessLayer;

import Core.Provider;
import DataTransferObject.Cabin;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CabinHandler {
    private final Provider provider;
    
    public CabinHandler() {
        this.provider = new Provider();
    }
    
    public ArrayList<Cabin> getByPlane(String plane) throws SQLException, ClassNotFoundException {
        String sql = String.format(
                "Select k.MaKhoang, TenKhoang From ChiTietMayBay ct join Khoang k on ct.MaKhoang = k.MaKhoang Where ct.MaMayBay = '%s'", plane);
        
        ResultSet data = this.provider.executeQuery(sql);
        ArrayList<Cabin> cabins = new ArrayList<>();
        
        while(data.next()) {
            Cabin cabin = new Cabin();
            cabin.setID(data.getString("MaKhoang"));
            cabin.setName(data.getString("TenKhoang"));
            cabins.add(cabin);
        }
        
        return cabins;
    }
    
    public ArrayList<Cabin> getAvailableCabins(String plane) throws SQLException, ClassNotFoundException {
        String sql = String.format(
                "SELECT a.MaKhoang, a.TenKhoang FROM Khoang a LEFT JOIN	(SELECT * FROM ChiTietMayBay WHERE MaMayBay = '%s') b ON a.MaKhoang = b.MaKhoang WHERE b.MaKhoang IS NULL", plane);
        
        ResultSet data = this.provider.executeQuery(sql);
        ArrayList<Cabin> cabins = new ArrayList<>();
        
        while(data.next()) {
            Cabin cabin = new Cabin();
            cabin.setID(data.getString("MaKhoang"));
            cabin.setName(data.getString("TenKhoang"));
            cabins.add(cabin);
        }
        
        return cabins;
    }
    
    public boolean insert(String plane, String cabin) throws SQLException, ClassNotFoundException {
        String sql = String.format("Insert Into `ChiTietMayBay`(MaKhoang, MaMayBay) Values ('%s', '%s')",
                cabin, plane);
        
        return (this.provider.executeNonQuery(sql) > 0);
    }
}
