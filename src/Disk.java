package src;

public class Disk
{
    static final int NUM_SECTORS = 1024;
    StringBuffer sectors[] = new StringBuffer[NUM_SECTORS];
    int disk_number;
    int next_sector;

    Disk(int dnum)
    {
        for (int i = 0; i < sectors.length; ++i)
        {
            sectors[i] = new StringBuffer();
        }
        disk_number = dnum;
        next_sector = 0;
    }


    void write(int sector, StringBuffer data)
    {
        JavaFXMain.update(disk_number+3, data.toString() + " is being written");
        sectors[sector].append(data);
        next_sector++;
        //JavaFXMain.update(disk_number+3, "finished");
    }

    void read(int sector, StringBuffer data)
    {
        JavaFXMain.update(disk_number+3, sectors[sector].toString() + " is being read");
        data.append(sectors[sector]);
        //JavaFXMain.update(disk_number+3, "finished");
    }

    int id()
    {
        return disk_number;
    }

    int next()
    {
        return next_sector;
    }

    void print_disk()
    {
        for (int i = 0; i < next_sector; ++i)
        {
            System.out.println(sectors[i].toString());
        }
    }
}

