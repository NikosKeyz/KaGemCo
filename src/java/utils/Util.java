package utils;

public class Util {
    
    public static java.util.Date sqlToJDate( java.sql.Date sqlDate ) {

        return new java.util.Date(sqlDate.getTime());
    }
    
    public static java.sql.Date checkDate( java.sql.Date date ) {
        
        if ( date.toString().equals("0000-00-00") )
            date = null;
            
        return date;
    }
    
}