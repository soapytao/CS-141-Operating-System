package src;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.*;


class Printer
{
    File f;
    File dir;
    int printer_number;
    //BufferedWriter bw;

    Printer(int pnum)
    {
        dir = new File("141OS\\outputs");
        f = new File("141OS\\outputs\\PRINTER" + pnum);
        printer_number = pnum;
        //bw = null;
    }

    void print(StringBuffer line)
    {
        try
        {
            if (!dir.exists())
            {
                dir.mkdirs();
            }
            if (!f.exists())
            {
                f.createNewFile();
            }
            //FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
            System.out.println("Printing " + line.toString() + " to PRINTER" + printer_number);
            //System.out.println(line.toString());
            bw.write(line.toString());
            bw.write("\n");
            //JavaFXMain.update(printer_number+5, "finished");
            //bw.flush();

            bw.close();
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
