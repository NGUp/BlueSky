package DataAccessLayer;

import Core.Provider;
import DataTransferObject.Plane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PlaneHandler {
     private final Provider provider;
    
    public PlaneHandler() {
        this.provider = new Provider();
    }
    
    public ArrayList<Plane> limit(int page) throws SQLException, ClassNotFoundException {
        String sql = String.format(
                "Select * From MayBay Limit %d, 10", (page - 1) * 10);
        
        ResultSet result = this.provider.executeQuery(sql);
        
        ArrayList<Plane> planes = new ArrayList<>();
        
        while (result.next()) {
            Plane plane = new Plane();
            plane.setID(result.getString("MaMB"));
            plane.setName(result.getString("TenMB"));
            plane.setStart(new Date(result.getDate("NgayVanHanh").getTime()));
            plane.setManufacturer(result.getString("HangSanXuat"));
            
            planes.add(plane);
        }
        
        this.provider.closeConnection();
        
        return planes;
    }
    
    public ArrayList<Plane> getAll() throws SQLException, ClassNotFoundException {
        String sql = "Select * From MayBay";
        
        ResultSet result = this.provider.executeQuery(sql);
        
        ArrayList<Plane> planes = new ArrayList<>();
        
        while (result.next()) {
            Plane plane = new Plane();
            plane.setID(result.getString("MaMB"));
            plane.setName(result.getString("TenMB"));
            plane.setStart(new Date(result.getDate("NgayVanHanh").getTime()));
            plane.setManufacturer(result.getString("HangSanXuat"));
            
            planes.add(plane);
        }
        
        this.provider.closeConnection();
        
        return planes;
    }
    
    public ArrayList<Plane> limitWithKeyword(int page, String keyword) throws SQLException, ClassNotFoundException {
        String sql =
                "Select * From MayBay Where TenMB Like '%" + keyword + "%' Limit " + Integer.toString((page - 1) * 10) + ", 10";
        
        ResultSet result = this.provider.executeQuery(sql);
        
        ArrayList<Plane> planes = new ArrayList<>();
        
        while (result.next()) {
            Plane plane = new Plane();
            plane.setID(result.getString("MaMB"));
            plane.setName(result.getString("TenMB"));
            plane.setStart(new Date(result.getDate("NgayVanHanh").getTime()));
            plane.setManufacturer(result.getString("HangSanXuat"));
            
            planes.add(plane);
        }
        
        this.provider.closeConnection();
        
        return planes;
    }
    
    public Plane one(String ID) throws SQLException, ClassNotFoundException {
        String sql = String.format("Select * From MayBay Where MaMB = '%s'", ID);
        ResultSet result = this.provider.executeQuery(sql);
        Plane plane = new Plane();
        
        while (result.next()) {
            plane.setName(result.getString("TenMB"));
            plane.setID(result.getString("MaMB"));
            plane.setManufacturer(result.getString("HangSanXuat"));
            plane.setStart(new Date(result.getDate("NgayVanHanh").getTime()));
            break;
        }
        
        return plane;
    }
    
    public boolean insert(Plane plane) throws SQLException, ClassNotFoundException {
        String sql = String.format(
                "Insert Into `MayBay`(MaMB, TenMB, NgayVanHanh, HangSanXuat) Values('%s', '%s', '%s', '%s')",
                plane.getID(), plane.getName(), new SimpleDateFormat("yyyy-MM-dd").format(plane.getStart()), plane.getManufacturer());
        
        return (this.provider.executeNonQuery(sql) > 0);
    }
    
    public int totalPage() throws SQLException, ClassNotFoundException {
        String sql = "Select Ceiling(Count(MaMB) / 10) as Total From MayBay";
        
        ResultSet data = this.provider.executeQuery(sql);
        int result = 0;
        
        if (data.next()) {
            result = data.getInt("Total");
        }
        
        this.provider.closeConnection();
        
        return result;
    }
    
    public int totalPageWithKeyword(String keyword) throws SQLException, ClassNotFoundException {
        String sql = "Select Ceiling(Count(MaMB) / 10) as Total From MayBay Where TenMB Like '%" + keyword + "%'";
        
        ResultSet data = this.provider.executeQuery(sql);
        int result = 0;
        
        if (data.next()) {
            result = data.getInt("Total");
        }
        
        this.provider.closeConnection();
        
        return result;
    }
}
