package tw.edu.ntut.csie.game.model;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import tw.edu.ntut.csie.game.Game;

//Created by Leon on 2017/5/19.

public class IOModel
{
    private Game _game;
    private Context _context;

    public IOModel(Game game, Context context)
    {
        _game = game;
        _context = context;
    }

    public void Write(String inputData)
    {
        try
        {
            //在 getFilesDir() 目錄底下建立record.txt檔案用來進行寫入
            FileOutputStream stream = _context.openFileOutput("record.txt", Context.MODE_PRIVATE);
            stream.write(inputData.getBytes());
            stream.close();
        }
        catch (Exception e)
        {
            Toast.makeText(_game, "File not found: " + e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public String Read()
    {
        StringBuilder stringBuilder = new StringBuilder();

        try
        {
            InputStream inputStream = _context.openFileInput("record.txt");

            if (inputStream != null)
            {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receivedString;

                while ((receivedString = bufferedReader.readLine()) != null)
                {
                    stringBuilder.append(receivedString);
                }
                inputStream.close();
            }
        }
        catch (FileNotFoundException e)
        {
//            Toast.makeText(_game, "File not found: " + e.toString(), Toast.LENGTH_LONG).show();
        }
        catch (IOException e)
        {
            Toast.makeText(_game, "Can not read file: " + e.toString(), Toast.LENGTH_LONG).show();
        }
        return stringBuilder.toString();
    }

    public boolean Delete()
    {
        if (_context.deleteFile("record.txt"))
        {
            Toast.makeText(_game, "record.txt deleted", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}