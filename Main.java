package src;

import javafx.application.Application;

public class Main
{
    public static int NUMBER_OF_USERS = 4;
    public static int NUMBER_OF_DISKS = 2;
    public static int NUMBER_OF_PRINTERS = 3;

    public static UserThread Users[] = new UserThread[NUMBER_OF_USERS];
    public static Printer Printers[] = new Printer[NUMBER_OF_PRINTERS];
    public static Disk Disks[] = new Disk[NUMBER_OF_DISKS];

    public static DirectoryManager FileDirectory = new DirectoryManager();
    public static ResourceManager DiskResource = new ResourceManager(NUMBER_OF_DISKS);
    public static ResourceManager PrinterResource = new ResourceManager(NUMBER_OF_PRINTERS);



    public static void main(String[] args)
    {
        System.out.println("Starting 141OS Application");

        for (int i = 0; i < NUMBER_OF_DISKS; ++i)
        {
            Disks[i] = new Disk(i+1);
        }

        for (int i = 0; i < NUMBER_OF_PRINTERS; ++i)
        {
            Printers[i] = new Printer(i+1);
        }

        for (int i = 0; i < NUMBER_OF_USERS; ++i)
        {
            Users[i] = new UserThread(i+1);
        }

        for (int i = 0; i < NUMBER_OF_USERS; ++i)
        {
            Users[i].start();
        }

        Application.launch(JavaFXMain.class,args);


    }
}

