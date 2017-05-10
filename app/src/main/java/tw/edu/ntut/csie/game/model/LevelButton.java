package tw.edu.ntut.csie.game.model;

import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.R;

/**
 * Created by User on 2017/5/8.
 */

public class LevelButton extends Button
{
    private MovingBitmap _levelOneLabel;
    private MovingBitmap _levelTwoLabel;
    private MovingBitmap _levelThreeLabel;
    private MovingBitmap _levelFourLabel;
    private MovingBitmap _levelFiveLabel;
    private int _level;

    public LevelButton(int enableFilename, int disableFilename, int x, int y)
    {
        super(enableFilename, disableFilename, x, y);

        _levelOneLabel = new MovingBitmap(R.drawable.level1_label, x, y);
        _levelTwoLabel = new MovingBitmap(R.drawable.level2_label, x, y);
        _levelThreeLabel = new MovingBitmap(R.drawable.level3_label, x, y);
        _levelFourLabel = new MovingBitmap(R.drawable.level4_label, x, y);
        _levelFiveLabel = new MovingBitmap(R.drawable.level5_label, x, y);

        _levelTwoLabel.setVisible(false);
        _levelThreeLabel.setVisible(false);
        _levelFourLabel.setVisible(false);
        _levelFiveLabel.setVisible(false);
        _level = 1;
    }

    public void Run()
    {
    }

    //按下按鈕
    public void Push()
    {
        if(_level < 5)
        {
            _level++;
            switch (_level)
            {
                case 2:
                    _levelOneLabel.setVisible(false);
                    _levelTwoLabel.setVisible(true);
                    break;
                case 3:
                    _levelTwoLabel.setVisible(false);
                    _levelThreeLabel.setVisible(true);
                    break;
                case 4:
                    _levelThreeLabel.setVisible(false);
                    _levelFourLabel.setVisible(true);
                    break;
                case 5:
                    _levelFourLabel.setVisible(false);
                    _levelFiveLabel.setVisible(true);
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
    }
}