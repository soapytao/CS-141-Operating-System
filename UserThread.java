package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;


public class UserThread extends Thread
{
    File f;
    int user_number;
    StringBuffer input_buffer;
    StringBuffer opened_file;
    //BufferedReader br;

    int disk_number;


    public UserThread(int unum)
    {
        user_number = unum;
        String unum_string = Integer.toString(unum);
        f = new File("141OS\\inputs\\USER" + unum_string);

        input_buffer = new StringBuffer(0);
        opened_file = new StringBuffer(0);
        disk_number = 0;

        //br = null;
    }

    public void run()
    {
        try
        {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null)
            {
                // clear the input buffer and append the newly read line
                input_buffer.delete(0,input_buffer.length());
                input_buffer.append(line);

                //System.out.println(input_buffer.toString());

                if (input_buffer.subSequence(0,4).toString().equals(".end"))
                {
                    opened_file = new StringBuffer(0);
                    Main.DiskResource.release(disk_number);
                }
                else if (input_buffer.subSequence(0,5).toString().equals(".save"))
                {
                    disk_number = Main.DiskResource.request();

                    String file_name_string = input_buffer.substring(6);
                    opened_file = new StringBuffer(file_name_string);

                    //JavaFXMain.update(user_number, "Saved " + file_name_string);
                }
                else if (input_buffer.subSequence(0,6).toString().equals(".print"))
                {
                    String file_name_string = input_buffer.substring(7);
                    //System.out.println(file_name_string);
                    StringBuffer file_name = new StringBuffer(file_name_string);

                    PrintJobThread PJB = new PrintJobThread(file_name, user_number);
                    PJB.start();
                    //JavaFXMain.update(user_number-1, "finished");

                }
                else
                {
                    if (opened_file.length() != 0)
                    {
                        Disk to_use = Main.Disks[disk_number];
                        FileInfo test = Main.FileDirectory.lookup(opened_file.toString());
                        if (test == null)
                        {
                            //System.out.println("not yet an entry");
                            FileInfo file_info = new FileInfo();
                            file_info.diskNumber = disk_number;
                            file_info.startingSector = to_use.next();
                            file_info.fileLength = 1;

                            Main.FileDirectory.enter(opened_file.toString(),file_info);

                            JavaFXMain.update(user_number-1, "Writing to DISK" + (disk_number+1));
                            System.out.println("USER" + user_number + " is writing to DISK" + (disk_number+1));
                            to_use.write(file_info.startingSector,input_buffer);
                            Thread.sleep(200);
                            System.out.println("USER" + user_number + " finished writing to DISK" + (disk_number+1));
                            JavaFXMain.update(disk_number+4, "finished");
                            JavaFXMain.update(user_number-1, "finished");
                        }
                        else
                        {
                            //System.out.println("already an entry");
                            JavaFXMain.update(user_number-1, "Writing to DISK" + (disk_number+1));
                            System.out.println("USER" + user_number + " is writing to DISK" + (disk_number+1));
                            to_use.write(to_use.next(),input_buffer);
                            Thread.sleep(200);
                            System.out.println("USER" + user_number + " finished writing to DISK" + (disk_number+1));
                            ++test.fileLength;
                            JavaFXMain.update(disk_number+4, "finished");
                            JavaFXMain.update(user_number-1, "finished");


                        }

                    }
                }
            }

            //JavaFXMain.update(user_number-1, "finished");

            fr.close();
			/*for(int i = 0; i < 2; ++i)
			{
				System.out.println(i);
				Main.Disks[i].print_disk();
			}*/
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}

