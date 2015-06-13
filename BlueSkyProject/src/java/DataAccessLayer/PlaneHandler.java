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
                "Select * From MayBay Limit %d, 20", (page - 1));
        
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
    
    public boolean insert(Plane plane) throws SQLException, ClassNotFoundException {
        String sql = String.format(
                "Insert Into `MayBay`(MaMB, TenMB, NgayVanHanh, HangSanXuat) Values('%s', '%s', '%s', '%s')",
                plane.getID(), plane.getName(), new SimpleDateFormat("yyyy-MM-dd").format(plane.getStart()), plane.getManufacturer());
        
        return (this.provider.executeNonQuery(sql) > 0);
    }
}
