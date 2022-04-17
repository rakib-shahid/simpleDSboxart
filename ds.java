import java.io.*;
import java.util.*;

public class ds{
    public static void main(String[] args) throws IOException{
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter drive letter: ");
        String driveLetter = scan.nextLine();
        scan.close();

        File folder = new File(driveLetter+":/roms/nds");
        File[] listOfFiles = folder.listFiles();

        ArrayList<String> serials = new ArrayList<String>();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                FileInputStream fileIn = new FileInputStream(file.toPath().toString());
                fileIn.skip(12);
                String gameSerial = "";
                for (int i = 0; i < 4; i++){
                    gameSerial += (char)fileIn.read();
                }
                serials.add(gameSerial);
            }
        }
        
        for (String gameSerial : serials){
            System.out.println(gameSerial);
        }
        

    }
}
