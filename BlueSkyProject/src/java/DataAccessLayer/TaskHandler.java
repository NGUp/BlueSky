package DataAccessLayer;

import Core.Provider;
import DataTransferObject.Task;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class TaskHandler {
    private final Provider provider;
    
    public TaskHandler() {
        this.provider = new Provider();
    }
    
    public int totalPageForStewardess(String stewardess) throws SQLException, ClassNotFoundException {
        String sql = String.format("Select Ceiling(Count(MaChuyen) / 10) as Total From PhuTrachKhoang Where MaNV = '%s'", stewardess);
        
        ResultSet data = this.provider.executeQuery(sql);
        int result = 0;
        
        if (data.next()) {
            result = data.getInt("Total");
        }
        
        this.provider.closeConnection();
        
        return result;
    }
    
    public ArrayList<Task> limitForStewardess(String stewardess, int page) throws SQLException, ClassNotFoundException, UnsupportedEncodingException {
        String sql = String.format(
                "Select cb.MaChuyen, tb.TenTuyen, cb.TG_XuatPhat, k.TenKhoang From ((PhuTrachKhoang ptk Join ChuyenBay cb on ptk.MaChuyen = cb.MaChuyen) Join TuyenBay tb On cb.MaTuyen = tb.MaTuyen) Join Khoang k on ptk.MaKhoang = k.MaKhoang Where ptk.MaNV = '%s' Limit %d, 10",
                stewardess, (page - 1) * 10
        );
        
        ResultSet result = this.provider.executeQuery(sql);
        
        ArrayList<Task> tasks = new ArrayList<>();
        
        while (result.next()) {
            Task task = new Task();
            task.setFlightID(result.getString("MaChuyen"));
            task.setFlightName((new String(result.getString("TenTuyen").getBytes("8859_1"),"UTF-8")));
            task.setStartTime(new Date(result.getDate("TG_XuatPhat").getTime()));
            task.setCabin(result.getString("TenKhoang"));
            
            tasks.add(task);
        }
        
        this.provider.closeConnection();
        
        return tasks;
    }
}
