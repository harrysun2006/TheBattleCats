package tw.edu.ntut.csie.game.model;
import tw.edu.ntut.csie.game.R;

import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.extend.BitmapButton;
import tw.edu.ntut.csie.game.Game;

/**
 * Created by User on 2017/4/10.
 */

public class GameButton
{
    public MovingBitmap _button;//按鈕圖案
    private static int  _cd;//CD時間
    public int _currentCd;
    private boolean _isCd;
    private int _percent;
    public int _x;
    public int _y;

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

    public boolean Push()
    {
        if(_isCd == false)
        {
            _currentCd = _cd;
            _isCd = true;
            return (true);
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

    public double GetPerctnt()
    {
        return _percent;
    }

    public void release()
    {
        _button.release();
    }

}
