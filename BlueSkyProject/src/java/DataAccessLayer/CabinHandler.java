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
    
    public Cabin one(String cabinID) throws SQLException, ClassNotFoundException {
        String sql = String.format(
                "Select * From Khoang Where MaKhoang = '%s'", cabinID);
        ResultSet data = this.provider.executeQuery(sql);
        Cabin cabin = new Cabin();
        
        while(data.next()) {
            cabin.setID(data.getString("MaKhoang"));
            cabin.setName(data.getString("TenKhoang"));
            break;
        }
        
        return cabin;
    }
    
    public Cabin getDetails(String planeID, String cabinID) throws SQLException, ClassNotFoundException {
        String sql = String.format(
                "Select k.MaKhoang, k.TenKhoang, SoDay, SoHang From Khoang k Join ChiTietMayBay ct on k.MaKhoang = ct.MaKhoang Where ct.MaMayBay = '%s' And k.MaKhoang = '%s'", planeID, cabinID);
        ResultSet data = this.provider.executeQuery(sql);
        Cabin cabin = new Cabin();
        
        while(data.next()) {
            cabin.setID(data.getString("MaKhoang"));
            cabin.setName(data.getString("TenKhoang"));
            cabin.setColumns(data.getInt("SoDay"));
            cabin.setRows(data.getInt("SoHang"));
            break;
        }
        
        return cabin;
    }
    
    public boolean insert(String plane, String cabin) throws SQLException, ClassNotFoundException {
        String sql = String.format(
                "Insert Into `ChiTietMayBay`(MaKhoang, MaMayBay) Values ('%s', '%s')",
                cabin, plane);
        
        return (this.provider.executeNonQuery(sql) > 0);
    }
    
    public boolean delete(String plane, String cabin) throws SQLException, ClassNotFoundException {
        String sql = String.format(
                "Delete From `ChiTietMayBay` Where MaKhoang = '%s' And MaMayBay = '%s'",
                cabin, plane);
        
        return (this.provider.executeNonQuery(sql) > 0);
    }
    
    public boolean update(Cabin cabin) throws SQLException, ClassNotFoundException {
        String sql = String.format(
                "Update ChiTietMayBay Set SoDay = %d, SoHang = %d Where MaKhoang = '%s' And MaMayBay = '%s'",
                cabin.getColumns(), cabin.getRows(), cabin.getID(), cabin.getPlane());
        
        return (this.provider.executeNonQuery(sql) > 0);
    }
    
    public String getName(String ID) throws SQLException, ClassNotFoundException {
        String sql = String.format("Select TenKhoang From Khoang Where MaKhoang = '%s'", ID);
        
        ResultSet data = this.provider.executeQuery(sql);
        String result = "";
        
        if (data.next()) {
            result = data.getString("TenKhoang");
        }
        
        this.provider.closeConnection();
        
        return result;
    }
}