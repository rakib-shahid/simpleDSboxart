import java.io.*;
import java.util.*;

public class ds{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter drive letter: ");
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
            //System.out.println(x);
        }

    }
}
