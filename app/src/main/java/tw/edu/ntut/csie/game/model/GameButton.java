package tw.edu.ntut.csie.game.model;

import tw.edu.ntut.csie.game.ReleasableResource;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.Game;

/**
 * Created by User on 2017/4/10.
 */

public class GameButton implements ReleasableResource
{
    private MovingBitmap _button; //按鈕圖案
    private int _cd; //CD時間
    private int _currentCd; //目前剩餘CD時間
    private boolean _isCd; //是否正在CD
    private int _percent; //currentcd/cd
    private int _x; //按鈕X座標
    private int _y; //按鈕Y座標

    public GameButton(int filename, int x, int y, int cd)
    {
        _x = x;
        _y = y;
        _button = new MovingBitmap(filename, _x, _y);
        _cd = cd * Game.FRAME_RATE;
        _currentCd = 0;
        _isCd = false;
        _percent = 0;
    }

    public void Run()
    {
        if (_isCd == true)
        {
            _currentCd--;
            _percent = _currentCd * 100 / _cd;
            if (_currentCd == 0)
            {
                _isCd = false;
                _percent = 0;
            }
        }
    }

    //按下按鈕
    public boolean Push()
    {
        if (_isCd == false)
        {
            _currentCd = _cd;
            _isCd = true;
            return true;
        }
        else
        {
            return false;
        }
    }

    public void Show()
    {
        _button.show();
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
        return _button.getWidth();
    }

    public int GetHeight()
    {
        return _button.getHeight();
    }

    public int GetPercent()
    {
        return _percent;
    }

    public void release()
    {
        _button.release();
    }
}