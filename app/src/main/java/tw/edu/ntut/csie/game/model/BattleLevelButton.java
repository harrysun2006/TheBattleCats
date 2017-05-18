package tw.edu.ntut.csie.game.model;

//Created by leon on 2017/5/18.

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;

public class BattleLevelButton extends LevelButton
{
    public BattleLevelButton(int enableFilename, int disableFilename, int x, int y)
    {
        super(enableFilename, disableFilename, x, y);

        _levelOneLabel = new MovingBitmap(R.drawable.level1_label, x + 5, y);
        _levelTwoLabel = new MovingBitmap(R.drawable.level2_label, x + 5, y);
        _levelThreeLabel = new MovingBitmap(R.drawable.level3_label, x + 5, y);
        _levelFourLabel = new MovingBitmap(R.drawable.level4_label, x + 5, y);
        _levelFiveLabel = new MovingBitmap(R.drawable.level5_label, x + 5, y);

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
    }
}