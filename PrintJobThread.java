package src;

public class PrintJobThread extends Thread
{
    int user_number;
    int printer_number;
    StringBuffer file_print;
	/*int print_dn;
	int print_ss;
	int print_fl;*/

    public PrintJobThread(StringBuffer input, int unum)
    {
        file_print = input;
        printer_number = 0;
        user_number = unum;
        // Get the info of the file to print
		/*FileInfo file_info = Main.FileDirectory.lookup(file_print);
		print_dn = file_info.diskNumber;
		print_ss = file_info.startingSector;
		print_fl = file_info.fileLength;*/

    }

    public void run()
    {
        // Get the info of the file to print
        //System.out.println(file_print.toString());
        FileInfo fi = Main.FileDirectory.lookup(file_print.toString());
        int dn = fi.diskNumber;
        int ss = fi.startingSector;
        int fl = fi.fileLength;

		/*System.out.print(file_print.toString());
		System.out.print(" ");
		System.out.print(dn);
		System.out.print(" ");
                System.out.print(ss);
		System.out.print(" ");
                System.out.print(fl);
		System.out.println();	*/

        printer_number = Main.PrinterResource.request();
        Printer free_printer = Main.Printers[printer_number];

        Disk disk_to_use = Main.Disks[dn];

        StringBuffer temp = new StringBuffer(0);
        System.out.println("USER" + user_number + " is printing on PRINTER" + printer_number);
        for (int i = 0; i < fl; ++i)
        {
            try
            {
                JavaFXMain.update(user_number-1, "Waiting for " + file_print.toString() + " to print");
                disk_to_use.read(ss + i, temp);
                Thread.sleep(200);
                //temp.append(System.getProperty("line.separator"));
                //System.out.println(temp.toString());
                JavaFXMain.update(printer_number+6, temp.toString() + " is being printed");
                free_printer.print(temp);
                Thread.sleep(2750);
                temp.delete(0,temp.length());

            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        System.out.println("USER" + user_number + " finished printing on PRINTER" + printer_number);
        JavaFXMain.update(dn+4, "finished");
        JavaFXMain.update(printer_number+6, "finished");
        JavaFXMain.update(user_number-1, "finished");
        Main.PrinterResource.release(printer_number);

    }
}
