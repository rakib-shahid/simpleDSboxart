import java.io.*;
import java.net.URL;
import java.util.*;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import javax.swing.JFileChooser;

public class DSboxart{
    
    public static void main(String[] args) throws IOException{
        int input = 0;
        if (args.length > 0){
            input = Integer.parseInt(args[0]);
        }
        Scanner scan = new Scanner(System.in);
        String romsPath = "";
        String destination = "";
        if (input == 1){
            System.out.println("Using Default TwilightMenu Locations:");
            System.out.print("\nEnter drive letter of SD Card: ");
            String driveLetter = scan.nextLine();
            scan.close();
            romsPath = driveLetter + ":/roms/nds";
            System.out.println();
            destination = driveLetter+":"+File.separator+"_nds"+File.separator+"TWiLightMenu"+File.separator+"boxart"+File.separator;            
        }
        else {
            System.out.println("Using GUI:");
            scan.close();
            JFileChooser directoryPicker = new JFileChooser();
            directoryPicker.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            directoryPicker.setDialogTitle("Pick ROMS Location");
            directoryPicker.showOpenDialog(directoryPicker);
            romsPath = directoryPicker.getSelectedFile().toString();


        
            directoryPicker.setDialogTitle("Pick Box Art Location");
            directoryPicker.showOpenDialog(null);
            destination = directoryPicker.getSelectedFile().toString();
        }
        FileFilter ndsFiles = new FileFilter() {
            public boolean accept(File rom){
                return rom.getName().endsWith(".nds");
            }
        };
        File[] romsFolder = new File(romsPath).listFiles(ndsFiles);
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

        System.out.println(destination);
        int newArts = 0;
        for (int i = 0; i < serials.size(); i++){
            //print current working rom
            System.out.println("Getting:");
            File dest = new File(destination+File.separator+serials.get(i)+".png");
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
            if (!dest.exists()){
                ImageIO.write(img, "png", dest);
                newArts++;
            }
            else {
                System.out.println("Already have, moving on...");
            }
            System.out.println();
        }

        System.out.println("Complete!\n"+newArts+" new boxarts aquired.");
    }
}