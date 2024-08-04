package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDate;

public final class Validation {
    private Validation(){}
    
    /**Check if the string is a valid contact
     * 
     * @param s the {@code String} to be tested
     * @return {@code true} if string is a valid contact number
     */
    public static boolean validContact(String s){
        Pattern pattern = Pattern.compile("^(\\+?6?01)[02-46-9]-*[0-9]{7}$|^(\\+?6?01)[1]-*[0-9]{8}$");
        Matcher matcher = pattern.matcher(s);
        
        return matcher.find();
    }
    
    /**Check if the string is a valid expiration date for credit cards.
     * 
     * <p>The string will be considered invalid if it is not in a "mm/yy" format.  
     * The string will be considered invalid if it equal or less than current
     * system month and year.
     * 
     * @param s the {@code String} to be tested
     * @return {@code true} if string is a valid expiration date
     */
    public static boolean validExp(String s){
        Pattern pattern = Pattern.compile("\\d\\d/\\d\\d");
        Matcher matcher = pattern.matcher(s);
        if(matcher.find()){
            System.out.println("Entered");
            LocalDate currentDate = LocalDate.now();
            int currYear = currentDate.getYear() % 100;
            
            String[] parts = s.split("/");
            int month = Integer.parseInt(parts[0]);
            int year = Integer.parseInt(parts[1]);
            
            //check if it is a valid month
            if(month > 0 && month <= 12 && year >= currYear){
                //if it is expiring this year, check if it has expired            
                if(year == currYear){
                    if(month <= currentDate.getMonthValue()){
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
    
    /**Check if the string is a valid password.
     * 
     * <p>Check if password contains {@code 8 characters}, {@code lowercase(s)},
     * {@code uppercase(s)}, {@code digit(s)}, {@code special character(s)}.
     * 
     * @param s the {@code String} to be tested
     * @return empty string if it passed the test; all unsatisfied conditions otherwise
     */
    public static String validPassword(String s) {
        
        String result = "";
        
        if(s.length() < 8)
            result += "\n - 8 Characters";
        
        Pattern pat = Pattern.compile("[a-z]");
        Matcher mat = pat.matcher(s);
        if(!mat.find())
            result += "\n - Lowercase Letter";

        pat = Pattern.compile("[A-Z]");
        mat = pat.matcher(s);
        if(!mat.find())
            result += "\n - Uppercase Letter";
        
        pat = Pattern.compile("[0-9]");
        mat = pat.matcher(s);
        if(!mat.find())
            result += "\n - Digit";
        
        pat = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        mat = pat.matcher(s);
        if(!mat.find())
            result += "\n - Special Character";
        
        if(!result.equals("")){
            result = "Password doesn't contain" + result;
        }
        
        return result;
    }
    
    public static int intInRange(String s ,int min){
        int result = -1;
        try{
            result = Integer.parseInt(s.strip());
            if(result < min){
                result = -1;
            }
        }catch(Exception ex){
            result = -1;
        }
        
        return result;
    }
    
    public static int intInRange(String test ,int min, int max){
        int result = -1;
        try{
            result = Integer.parseInt(test.strip());
            if(result < min || result > max){
                result = -1;
            }
        }catch(Exception ex){
            result = -1;
        }
        
        return result;
    }
}