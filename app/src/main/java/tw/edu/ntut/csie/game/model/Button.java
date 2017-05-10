package tw.edu.ntut.csie.game.model;

import tw.edu.ntut.csie.game.ReleasableResource;
import tw.edu.ntut.csie.game.core.MovingBitmap;

/**
 * Created by User on 2017/5/8.
 */

public abstract class Button implements ReleasableResource
{
    protected MovingBitmap _enableButton; //按鈕圖案
    protected MovingBitmap _disableButton; //不可按時的按鈕圖案
    protected int _x; //按鈕X座標
    protected int _y; //按鈕Y座標
    protected boolean _isEnabled; //按鈕是否可按

    public Button(int enableFilename, int disableFilename, int x, int y)
    {
        _x = x;
        _y = y;
        _enableButton = new MovingBitmap(enableFilename, _x, _y);
        _disableButton = new MovingBitmap(disableFilename, _x, _y);
        _enableButton.setVisible(false);
        _isEnabled = false;
    }

    public abstract void Run();

    //按下按鈕
    public abstract void Push();

    public abstract void SetEnable(int current, int cost);

    public void Show()
    {
        _enableButton.show();
        _disableButton.show();
    }

    public int GetX()
    {
        return _x;
    }

    public int GetY()
    {
        return _y;
    }

    public int GetWidth()
    {
        return _enableButton.getWidth();
    }

    public int GetHeight()
    {
        return _enableButton.getHeight();
    }

    public boolean GetIsEnabled()
    {
        return _isEnabled;
    }

    public void release()
    {
        _enableButton.release();
        _disableButton.release();
    }
}