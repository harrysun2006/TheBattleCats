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
    private int _level;

    public LevelButton(int enableFilename, int disableFilename, int x, int y)
    {
        super(enableFilename, disableFilename, x, y);
        _level = 1;
        _levelOneLabel = new MovingBitmap(R.drawable.level1_label, x, y);
        _levelTwoLabel = new MovingBitmap(R.drawable.level2_label, x, y);
        _levelThreeLabel = new MovingBitmap(R.drawable.level3_label, x, y);
        _levelFourLabel = new MovingBitmap(R.drawable.level4_label, x, y);
        _levelFiveLabel = new MovingBitmap(R.drawable.level5_label, x, y);
    }

    public void Run()
    {

    }

    //按下按鈕
    public void Push()
    {

    }

    public void SetEnable(int current, int cost)
    {

    }
}