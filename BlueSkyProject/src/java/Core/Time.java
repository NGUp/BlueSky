package Core;

public class Time {

    // Convert from "dd/MM/yyyy" to "MM/dd/yyyy"
    public String standardize(String data) {
        String[] part = data.split("/");
        
        if (part.length == 3) {
            return part[1] + "/" + part[0] + "/" + part[2];
        }
        
        return null;
    }
}
