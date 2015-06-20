package DataAccessLayer;

import Core.Provider;
import DataTransferObject.Bill;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

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
}
