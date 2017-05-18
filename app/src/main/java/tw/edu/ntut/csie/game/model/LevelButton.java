package tw.edu.ntut.csie.game.model;

import tw.edu.ntut.csie.game.core.MovingBitmap;

/**
 * Created by User on 2017/5/8.
 */

public class LevelButton extends Button
{
    protected MovingBitmap _levelOneLabel;
    protected MovingBitmap _levelTwoLabel;
    protected MovingBitmap _levelThreeLabel;
    protected MovingBitmap _levelFourLabel;
    protected MovingBitmap _levelFiveLabel;
    protected MovingBitmap _costOneLabel;
    protected MovingBitmap _costTwoLabel;
    protected MovingBitmap _costThreeLabel;
    protected MovingBitmap _costFourLabel;
    protected MovingBitmap _costFiveLabel;
    protected int _level;

    public LevelButton(int enableFilename, int disableFilename, int x, int y)
    {
        super(enableFilename, disableFilename, x, y);
        _level = 1;
    }

    public void Run()
    {
    }

    //按下按鈕
    public void Push()
    {
        if (_level < 5)
        {
            _level++;
            switch (_level)
            {
                case 2:
                    _levelOneLabel.setVisible(false);
                    _costOneLabel.setVisible(false);
                    _levelTwoLabel.setVisible(true);
                    _costTwoLabel.setVisible(true);
                    break;
                case 3:
                    _levelTwoLabel.setVisible(false);
                    _costTwoLabel.setVisible(false);
                    _levelThreeLabel.setVisible(true);
                    _costThreeLabel.setVisible(true);
                    break;
                case 4:
                    _levelThreeLabel.setVisible(false);
                    _costThreeLabel.setVisible(false);
                    _levelFourLabel.setVisible(true);
                    _costFourLabel.setVisible(true);
                    break;
                case 5:
                    _levelFourLabel.setVisible(false);
                    _costFourLabel.setVisible(false);
                    _levelFiveLabel.setVisible(true);
                    _costFiveLabel.setVisible(true);
                    break;
            }
        }
    }

    public void SetEnable(int current, int cost)
    {
        if (current < cost || _level >= 5)
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
        _levelOneLabel.show();
        _levelTwoLabel.show();
        _levelThreeLabel.show();
        _levelFourLabel.show();
        _levelFiveLabel.show();
        _costOneLabel.show();
        _costTwoLabel.show();
        _costThreeLabel.show();
        _costFourLabel.show();
        _costFiveLabel.show();
    }
}