package tw.edu.ntut.csie.game.model;

//Created by leon on 2017/4/21.

public class ShiftingModule
{
    private int _previousPressedX;
    private int _currentPressedX;
    private int _shifting; //被顯示出來的遊戲畫面的原點在整個遊戲畫面中的 X 座標
    private int _tempShifting;

    private int _timingMinusTwoX; //向前 2 個時間點手指按壓的位置的 X 座標
    private int _timingMinusOneX; //向前 1 個時間點手指按壓的位置的 X 座標
    private int _currentTimingX; //當前時間點手指按壓的位置的X座標
    private int _velocity;
    private int _acceleration;
    private int _timingScale;

    private boolean _isPressed;
    private boolean _isAutoSlidingEnabled;

    public ShiftingModule()
    {

    }

    public void Run(int nextTimingX)
    {
        if (_isPressed)
        {
            UpdateStatistics(nextTimingX);
        }
        if (_isAutoSlidingEnabled)
        {
            AutoSliding();
        }
    }

    public void HandlePointerPressed(int pointerX)
    {
        _isPressed = true;
        _isAutoSlidingEnabled = false;
        _previousPressedX = pointerX;
        SynchronizeTempShifting();

        _timingMinusTwoX = pointerX;
        _timingMinusOneX = pointerX;
        _currentTimingX = pointerX;
    }

    public void HandlePointerMoved(int pointerX)
    {
        _tempShifting = -1 * (pointerX - _previousPressedX) + _shifting;

        if (_tempShifting > 360)
        {
            _tempShifting = 360;
        }
        if (_tempShifting < 0)
        {
            _tempShifting = 0;
        }
    }

    public void HandlePointerReleased()
    {
        _isPressed = false;
        _shifting = _tempShifting;

        if (_velocity != 0)
        {
            _isAutoSlidingEnabled = true;
        }
    }

    //在手指拖拉螢幕時依據手指在螢幕上最新的位置更新數據
    private void UpdateStatistics(int nextTimingX)
    {
        _timingMinusTwoX = _timingMinusOneX;
        _timingMinusOneX = _currentTimingX;
        _currentTimingX = nextTimingX;

        _velocity = _currentTimingX - _timingMinusOneX;
        int _previousVelocity = _timingMinusOneX - _timingMinusTwoX;
        _acceleration = _velocity - _previousVelocity;
    }

    //在手指離開螢幕時如果有速度則畫面依據速度自動滑動到底
    private void AutoSliding()
    {
        _shifting -= _velocity;

        if (_shifting > 360)
        {
            _shifting = 360;
            _isAutoSlidingEnabled = false;
        }
        if (_shifting < 0)
        {
            _shifting = 0;
            _isAutoSlidingEnabled = false;
        }
    }

    public int GetShifting()
    {
        return _shifting;
    }

    public void SetShifting(int shifting)
    {
        _shifting = shifting;
    }

    public int GetTempShifting()
    {
        return _tempShifting;
    }

    public void SetPreviousPressedX(int previousPressedX)
    {
        _previousPressedX = previousPressedX;
    }

    public void SetCurrentPressedX(int currentPressedX)
    {
        _currentPressedX = currentPressedX;
    }

    public boolean GetIsAutoSlidingEnabled()
    {
        return _isAutoSlidingEnabled;
    }

    //同步_tempShifting以供下一次手動滑動使用
    private void SynchronizeTempShifting()
    {
        _tempShifting = _shifting;
    }
}