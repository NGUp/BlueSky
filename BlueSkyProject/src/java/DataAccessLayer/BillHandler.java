package DataAccessLayer;

import Core.Provider;
import DataTransferObject.Bill;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BillHandler {
    
    private final Provider provider;
    
    public BillHandler() {
        this.provider = new Provider();
    }
    
    public boolean insert(Bill bill) throws SQLException, ClassNotFoundException {
        String sql = String.format(
                "Insert Into `HoaDon`(MaHD, Ngay, TongTien, MaKH) Values('%s', '%s', %f, '%s')",
                bill.getID(), new SimpleDateFormat("yyyy-MM-dd hh:MM:ss").format(bill.getTime()), bill.getTotal(), bill.getCustomer());
        
        return (this.provider.executeNonQuery(sql) > 0);
    }
    
    public ArrayList<Bill> getByCustomer(String ID) throws SQLException, ClassNotFoundException {
        String sql = String.format("Select * From HoaDon Where MaKH = '%s'", ID);
        
        ResultSet result = this.provider.executeQuery(sql);
        
        ArrayList<Bill> bills = new ArrayList<>();
        
        while (result.next()) {
            Bill bill = new Bill();
            bill.setID(result.getString("MaHD"));
            bill.setTime(new Date(result.getTimestamp("Ngay").getTime()));
            bill.setTotal(result.getFloat("TongTien"));
            bill.setCustomer(result.getString("MaKH"));
            bills.add(bill);
        }
        
        this.provider.closeConnection();
        
        return bills;
    }
}
