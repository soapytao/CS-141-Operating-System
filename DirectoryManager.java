package src;

import java.util.Hashtable;

public class DirectoryManager
{
    Hashtable<String,FileInfo> T = new Hashtable<String,FileInfo>();

    void enter(String key, FileInfo file)
    {
        T.put(key,file);
    }

    FileInfo lookup(String key)
    {
        return T.get(key);
    }
}