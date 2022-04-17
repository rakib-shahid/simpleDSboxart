import java.io.*;
import java.util.*;

public class ds{
    public static void main(String[] args) throws IOException{
        //take drive letter of SD Card from user
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter drive letter: ");
        String driveLetter = scan.nextLine();
        scan.close();

        //reads files in default rom location
        File folder = new File(driveLetter+":/roms/nds");
        File[] fileList = folder.listFiles();

        //arrays for storing serial and file names
        ArrayList<String> titles = new ArrayList<String>();
        ArrayList<String> serials = new ArrayList<String>();

        for (File file : fileList) {
            if (file.isFile()) {
                titles.add(file.getName());

                //reads serial from nds
                FileInputStream fileIn = new FileInputStream(file.toPath().toString());
                fileIn.skip(12);
                String gameSerial = "";
                for (int i = 0; i < 4; i++){
                    gameSerial += (char)fileIn.read();
                }

                serials.add(gameSerial);
            }
        }


        //print to terminal (for testing)
        for (int i = 0; i < serials.size(); i++){
            System.out.println("Serial = " + serials.get(i) + "\nFile Name = " + titles.get(i) + "\n");
        }
        

    }
}
