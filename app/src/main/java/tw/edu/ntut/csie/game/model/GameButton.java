package tw.edu.ntut.csie.game.model;

import tw.edu.ntut.csie.game.ReleasableResource;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.Game;

/**
 * Created by User on 2017/4/10.
 */

public class GameButton implements ReleasableResource
{
    private MovingBitmap _enableButton; //按鈕圖案
    private MovingBitmap _disableButton; //不可按時的按鈕圖案
    private int _cd; //CD時間
    private int _currentCd; //目前剩餘CD時間
    private boolean _isCd; //是否正在CD
    private int _percent; //currentCd/cd
    private int _x; //按鈕X座標
    private int _y; //按鈕Y座標
    private boolean _isEnabled; //按鈕是否可按

    public GameButton(int enableFilename, int disableFilename, int x, int y, int cd)
    {
        _x = x;
        _y = y;
        _enableButton = new MovingBitmap(enableFilename, _x, _y);
        _disableButton = new MovingBitmap(disableFilename, _x, _y);
        _cd = cd * Game.FRAME_RATE;
        _currentCd = 0;
        _isCd = false;
        _percent = 0;
        _isEnabled = false;
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
    public void Push()
    {
        _currentCd = _cd;
        _isCd = true;
    }

    public void SetEnable(int currentMoney, int costMoney)
    {
        if (_isCd == true || currentMoney < costMoney)
        {
            _isEnabled = false;
            _enableButton.setVisible(false);
            _disableButton.setVisible(true);
            return;
        }
        _isEnabled = true;
        _enableButton.setVisible(true);
        _disableButton.setVisible(false);
    }

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

    public int GetPercent()
    {
        return _percent;
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