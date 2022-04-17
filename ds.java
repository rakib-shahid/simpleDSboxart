import java.io.*;
import java.util.*;

public class ds{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter drive letter: ");
        String driveLetter = scan.nextLine();
        scan.close();

        File folder = new File(driveLetter+":/roms/nds");
        File[] listOfFiles = folder.listFiles();
        ArrayList<String> titles = new ArrayList<String>();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                titles.add(file.getName());
            }
        }
        
        for (String x : titles){
            if (x.charAt(5) == '-' &&
                x.charAt(4) == x.charAt(6) &&
                x.charAt(4) == ' '
            ){
                x = x.substring(7,x.indexOf(')') + 1);
            }
            else {
                x = x.substring(0,x.indexOf(')') + 1);
                
            //x = x.substring(0,x.length()-4 );
            }
            
            System.out.println(x);
        }
        System.out.println();

    }
}
