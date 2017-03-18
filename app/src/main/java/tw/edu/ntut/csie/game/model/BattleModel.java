package tw.edu.ntut.csie.game.model;

import java.util.List;
import java.util.ArrayList;
import tw.edu.ntut.csie.game.ReleasableResource;
import tw.edu.ntut.csie.game.Game;

/**
 * Created by User on 2017/3/10.
 */

public class BattleModel implements ReleasableResource
{
    private List<Units> _units;
    private static final int GENERATE_ENEMIES_DELAY_COUNTER = 2 * Game.FRAME_RATE; //讓兵每N秒產生一個，計數器每15可以讓兵延遲1秒產生 (畫面更新頻率 = 15次/1秒)
    private int _delayCounter = GENERATE_ENEMIES_DELAY_COUNTER;

    public BattleModel()
    {
        _units = new ArrayList<>();
    }

    public void Run()
    {
        for (Units element:_units)
        {
            element.Moving();
        }

        GenerateEnemies();
    }

    public void GenerateEnemies()
    {
        _delayCounter--;

        if (_delayCounter == 0)
        {
            GenerateOtter();
            _delayCounter = GENERATE_ENEMIES_DELAY_COUNTER;
        }
    }

    public void GenerateCapoo()
    {
        _units.add(new Capoo(500, 200));
    }

    public void GenerateOtter()
    {
        _units.add(new Otter(30, 200));
    }

    public void ShowAll()
    {
        for (Units element:_units)
        {
            element.Show();
        }
    }

    public void release()
    {
        _units.clear();
        _units = null;
    }
}