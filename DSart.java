//OLD Windows only NO GUI version
import java.io.*;
import java.net.URL;
import java.util.*;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class DSart{
    public static void main(String[] args) throws IOException{
        //take drive letter of SD Card from user
        Scanner scan = new Scanner(System.in);
        System.out.print("\nEnter drive letter of SD Card: ");
        String driveLetter = scan.nextLine();
        scan.close();
        String romsPath = driveLetter + ":/roms/nds";
        System.out.println();

//UNCOMMENT AND CHANGE THIS LINE BELOW IF YOUR ROMS PATH IS DIFFERENT FROM "C:/roms/nds"
        //romsPath = "put roms path here";

        //reads files in default rom location
        FileFilter ndsFiles = new FileFilter() {
            public boolean accept(File rom){
                return rom.getName().endsWith(".nds");
            }
        };
        File[] romsFolder = new File(romsPath).listFiles(ndsFiles);

        //arrays for storing serial and file names
        ArrayList<String> titles = new ArrayList<String>();
        ArrayList<String> serials = new ArrayList<String>();

        for (File rom : romsFolder) {
            if (rom.isFile()) {
                titles.add(rom.getName());

                //reads serial from nds
                FileInputStream fileIn = new FileInputStream(rom.toPath().toString());
                fileIn.skip(12);
                String gameSerial = "";
                for (int i = 0; i < 4; i++){
                    gameSerial += (char)fileIn.read();
                }

                serials.add(gameSerial);
            }
        }


        //print to terminal (for testing)
        

        String destination = driveLetter+":"+File.separator+"_nds"+File.separator+"TWiLightMenu"+File.separator+"boxart"+File.separator;
        
//UNCOMMENT AND CHANGE THIS LINE BELOW IF YOUR BOXART PATH IS DIFFERENT FROM "C:\_nds\TWiLightMenu\boxart"
        //destination = "put box art path here";

        for (int i = 0; i < serials.size(); i++){
            //print current working rom
            System.out.println("Getting:");
            File dest = new File(destination+serials.get(i)+".png");
            System.out.println("Serial = " + serials.get(i) + "\nFile Name = " + titles.get(i));
            BufferedImage img;
            //US Rom
            if (serials.get(i).charAt(3) == 'E'){
                try {
                    URL url = new URL("https://art.gametdb.com/ds/coverSB/US/"+serials.get(i)+".png");
                    img = ImageIO.read(url);
                } catch(IIOException e){
                    URL url = new URL("https://art.gametdb.com/ds/coverS/US/"+serials.get(i)+".png");
                    img = ImageIO.read(url);
                }
                
            }
            //Japan Rom
            else if (serials.get(i).charAt(3) == 'J'){
                URL url = new URL("https://art.gametdb.com/ds/coverS/JA/"+serials.get(i)+".png");
                img = ImageIO.read(url);
            }
            //EU Rom
            else {
                URL url = new URL("https://art.gametdb.com/ds/coverS/EN/"+serials.get(i)+".png");
                img = ImageIO.read(url);
                
            }


            //Copies to boxart folder
            if (!dest.exists()){
                ImageIO.write(img, "png", dest);
            }
            else {
                System.out.println("Already have, moving on...");
            }
            System.out.println();

        }

        System.out.println("Complete!\n");
        

    }
}
