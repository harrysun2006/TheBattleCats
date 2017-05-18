package tw.edu.ntut.csie.game.model;

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;

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

    private MovingBitmap _costOneLabel;
    private MovingBitmap _costTwoLabel;
    private MovingBitmap _costThreeLabel;
    private MovingBitmap _costFourLabel;
    private MovingBitmap _costFiveLabel;

    private int _level;

    public LevelButton(int enableFilename, int disableFilename, int x, int y)
    {
        super(enableFilename, disableFilename, x, y);

        _levelOneLabel = new MovingBitmap(R.drawable.real1, x + 155, y + 60);
        _levelTwoLabel = new MovingBitmap(R.drawable.real2, x + 155, y + 60);
        _levelThreeLabel = new MovingBitmap(R.drawable.real3, x + 155, y + 60);
        _levelFourLabel = new MovingBitmap(R.drawable.real4, x + 155, y + 60);
        _levelFiveLabel = new MovingBitmap(R.drawable.real5, x + 155, y + 60);

        _costOneLabel = new MovingBitmap(R.drawable.exp1000, x + 105, y + 95);
        _costTwoLabel = new MovingBitmap(R.drawable.exp2000, x + 105, y + 95);
        _costThreeLabel = new MovingBitmap(R.drawable.exp3000, x + 105, y + 95);
        _costFourLabel = new MovingBitmap(R.drawable.exp4000, x + 105, y + 95);
        _costFiveLabel = new MovingBitmap(R.drawable.exp5000, x + 105, y + 95);

        _levelTwoLabel.setVisible(false);
        _levelThreeLabel.setVisible(false);
        _levelFourLabel.setVisible(false);
        _levelFiveLabel.setVisible(false);

        _costTwoLabel.setVisible(false);
        _costThreeLabel.setVisible(false);
        _costFourLabel.setVisible(false);
        _costFiveLabel.setVisible(false);

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