package tw.edu.ntut.csie.game.model;

import tw.edu.ntut.csie.game.Game;

/**
 * Created by User on 2017/4/10.
 */

public class CDButton extends Button
{
    private int _cd; //CD時間
    private int _currentCd; //目前剩餘CD時間
    private boolean _isCd; //是否正在CD
    private int _percent; //currentCd/cd

    public CDButton(int enableFilename, int disableFilename, int x, int y, int cd)
    {
        super(enableFilename, disableFilename, x, y);
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
    public void Push()
    {
        _currentCd = _cd;
        _isCd = true;
    }

    public void SetEnable(int current, int cost)
    {
        if (_isCd == true || current < cost)
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

    public int GetPercent()
    {
        return _percent;
    }
}