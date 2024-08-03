package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Validation {
    private Validation(){}
    
    public static boolean validContact(String test){
        Pattern pattern = Pattern.compile("^(\\+?6?01)[02-46-9]-*[0-9]{7}$|^(\\+?6?01)[1]-*[0-9]{8}$");
        Matcher matcher = pattern.matcher(test);
        
        return matcher.find();
    }
    
    public static boolean intWithLength(String test, int length){
        Pattern pattern = Pattern.compile("^[0-9]{"+length+"}$");
        Matcher matcher = pattern.matcher(test);
        return matcher.find();
    }
    
    public static boolean validExp(String test){
        if(test.length() == 5 && test.charAt(2) == '/'){
            String[] parts = test.split("/");
            int month = Integer.parseInt(parts[0]);
            if(month > 0 && month <= 12){
                return true;
            }
        }
        return false;
    }
    public static String validPassword(String password) {
        
        String result = "";
        
        if(password.length() < 8)
            result += "\n - 8 Characters";
        
        Pattern pat = Pattern.compile("[a-z]");
        Matcher mat = pat.matcher(password);
        if(!mat.find())
            result += "\n - Lowercase Letter";

        pat = Pattern.compile("[A-Z]");
        mat = pat.matcher(password);
        if(!mat.find())
            result += "\n - Uppercase Letter";
        
        pat = Pattern.compile("[0-9]");
        mat = pat.matcher(password);
        if(!mat.find())
            result += "\n - Digit";
        
        pat = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        mat = pat.matcher(password);
        if(!mat.find())
            result += "\n - Special Character";
        
        if(!result.equals("")){
            result = "Password doesn't contain" + result;
        }
        
        return result;
    }
    
    public static int intInRange(String test ,int min){
        int result = -1;
        try{
            result = Integer.parseInt(test.strip());
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