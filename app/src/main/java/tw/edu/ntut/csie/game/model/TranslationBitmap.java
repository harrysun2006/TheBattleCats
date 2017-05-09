package tw.edu.ntut.csie.game.model;

//Created by leon on 2017/5/6.

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.IOException;

import static tw.edu.ntut.csie.game.GameView.runtime;
import static tw.edu.ntut.csie.game.engine.GameEngine.canvas;

public class TranslationBitmap
{
    private int _x; //X座標
    private int _y; //Y座標
    private int _displayX; //實際顯示的X座標
    private int _displayY; //實際顯示的Y座標
    private boolean _visible;
    private Bitmap _bitmap;

    //建立一個空的TransitionalBitmap物件。
    public TranslationBitmap()
    {
        _visible = true;
    }

    //建立TransitionalBitmap物件並立即載入指定的圖片。
    public TranslationBitmap(int resId)
    {
        _visible = true;
        LoadBitmap(resId);
    }

    //建立TransitionalBitmap物件並立即載入指定的圖片。
    public TranslationBitmap(String filename)
    {
        _visible = true;
        LoadBitmap(filename);
    }

    //建立TransitionalBitmap物件並立即載入指定的圖片。
    public TranslationBitmap(int resId, int x, int y)
    {
        _visible = true;
        LoadBitmap(resId);
        SetLocation(x, y);
    }

    //建立TransitionalBitmap物件並立即載入指定的圖片。
    public TranslationBitmap(String filename, int x, int y)
    {
        _visible = true;
        LoadBitmap(filename);
        SetLocation(x, y);
    }

    //從指定的Android資源ID載入圖片。
    public void LoadBitmap(int resId)
    {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        options.inTargetDensity = 1;
        _bitmap = BitmapFactory.decodeResource(runtime.getResources(), resId, options);
    }

    //從指定的檔名載入圖片。
    public void LoadBitmap(String fileName)
    {
        try
        {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inScaled = false;
            options.inTargetDensity = 1;
            _bitmap = BitmapFactory.decodeStream(runtime.getAssets().open(fileName), null, options);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void Release()
    {
        _bitmap.recycle();
        _bitmap = null;
    }

    //將圖片縮放到指定的大小。
    public void Resize(int width, int height)
    {
        _bitmap = Bitmap.createScaledBitmap(_bitmap, width, height, true);
    }

    public void Translation(int shiftedX, int shiftedY)
    {
        _displayX = _x - shiftedX;
        _displayY = _y - shiftedY;
    }

    public void Show()
    {
        if (_visible)
        {
            canvas.drawBitmap(_bitmap, _displayX, _displayY, null);
        }
    }

    //取得圖片在遊戲畫面上實際位置的X座標。
    public int GetX()
    {
        return _x;
    }

    //取得圖片在遊戲畫面上實際位置的Y座標。
    public int GetY()
    {
        return _y;
    }

    //取得圖片在遊戲畫面上的寬度。
    public int GetWidth()
    {
        return _bitmap.getWidth();
    }

    //取得圖片在遊戲畫面上的高度。
    public int GetHeight()
    {
        return _bitmap.getHeight();
    }

    //設定圖片在遊戲畫面上顯示的位置。
    public void SetLocation(int x, int y)
    {
        _x = x;
        _y = y;
    }

    //設定圖片顯示與否。
    public void SetVisible(boolean visible)
    {
        _visible = visible;
    }
}