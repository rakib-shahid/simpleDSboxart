# simpleDSboxart
Gets box arts for DS games and places them in the proper folder for Twilight Menu

Currently only supports **US**, **EU**, and **Japan** roms


### TwilightBoxart wasn't working one day so I decided to make this
https://github.com/KirovAir/TwilightBoxart

# To Run

Download and extract.

Compile 
> javac DSboxart.java

To bring up the directory picker GUI (All OS),

Run with 

> java DSboxart

OR

To automatically use default TwilightMenu locations (SD Card letter still required (Windows only))

Use

> java DSboxart 1


You will be prompted for the drive letter of the SD Card, enter it and press enter, everything else is automatic




# You might have to change file paths in DSart.java.

By default the roms are read from:   

**"driveLetter:/roms/nds"**

and the arts are placed in:          

**"driveLetter:\_nds\TWiLightMenu\boxart"**

(the path strings are commented, uncomment and change them as necessary)


### Box arts are from https://www.gametdb.com/DS/
