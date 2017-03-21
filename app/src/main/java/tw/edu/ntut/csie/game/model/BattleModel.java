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
    private List<Units> _allies;
    private List<Units> _enemies;

    private static final int GENERATE_ENEMIES_DELAY_COUNTER = 2 * Game.FRAME_RATE; //讓兵每N秒產生一個，計數器每15可以讓兵延遲1秒產生 (畫面更新頻率 = 15次/1秒)
    private int _delayCounter = GENERATE_ENEMIES_DELAY_COUNTER;
    private int _attackDelayCounter = 0;

    public BattleModel()
    {
        _allies = new ArrayList<>();
        _enemies = new ArrayList<>();
        _enemies.add(new Nexus(20, 200));
    }

    public void Run()
    {
        for (Units element:_allies)
        {
            if (element.GetX() == _enemies.get(0).GetX())
            {
                _attackDelayCounter++;

                if (_attackDelayCounter == element.GetAttackSpeed() * Game.FRAME_RATE)
                {
                    element.Attack();
                    _enemies.get(0).Attacked(element.GetAttackDamage());
                    _attackDelayCounter = 0;
                }
            }
            else
            {
                element.Moving();
            }
        }
        //GenerateEnemies();
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
        _allies.add(new Capoo(500, 200));
    }

    public void GenerateOtter()
    {
        _enemies.add(new Otter(20, 200));
    }

    public void ShowAll()
    {
        for (Units element:_allies)
        {
            element.Show();
        }
        for (Units element:_enemies)
        {
            element.Show();
        }
    }

    public void release()
    {
        _allies.clear();
        _enemies.clear();
        _allies = null;
        _enemies = null;
    }
}