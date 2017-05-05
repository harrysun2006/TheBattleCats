package tw.edu.ntut.csie.game.model;

/*import android.content.Context;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;*/
import java.io.*;


/**
 * Created by User on 2017/5/3.
 */

public class RecordAccessor
{
    /*String filename = "myfile";
    String string = "Hello world!";
    FileOutputStream outputStream;
    File file = new File(context.getFilesDir(), filename);

    try
    {
        outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
        outputStream.write(string.getBytes());
        outputStream.close();
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }*/


    public void create() throws IOException {
            File newTxt = new File("test.txt");

            if( !newTxt.exists() )
            {
                //在此java檔目錄下建立test.txt檔
                newTxt.createNewFile();
            }
            else{
                System.out.println("檔案已存在!");
            }

    }

    public void WriteTxt() throws IOException {
            FileWriter dataFile = new FileWriter("test.txt");
            BufferedWriter input = new BufferedWriter(dataFile);

            //輸入Hello! World! 到test.txt檔裡
            input.write("Hello! World!");

            //寫入完關閉檔案，並儲存
            input.close();

    }

    public void ReadTxt() throws IOException
    {
            FileReader dataFile = new FileReader("test.txt");
            BufferedReader output = new BufferedReader(dataFile);

            String str;
            while( (str = output.readLine()) != null ){
                System.out.println(str);
            }

            output.close();
    }

}