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

    private static final int GENERATE_ENEMIES_DELAY_COUNTER = 4 * Game.FRAME_RATE; //讓敵兵每N秒產生一個，計數器每15可以讓兵延遲1秒產生 (畫面更新頻率 = 15次/1秒)
    private int _generateEnemiesDelayCounter = GENERATE_ENEMIES_DELAY_COUNTER; //真正用來計算產兵延遲的
    private int _knockedBackDelayCounter = 0; //同上理由，這是被擊退的頻率
    private int _recordAlliesIndex = 0; //走在最前面的友軍的index
    private int _recordEnemiesIndex = 0; //走在最前面的敵軍的index
    private int _alliesMax = 1000; //走在最前面的友軍的X值
    private int _enemiesMax = 0; //走在最前面的敵軍的X值
    private int _shifting = 0;
    private Money money;

    public BattleModel()
    {
        _allies = new ArrayList<>();
        _enemies = new ArrayList<>();
        _allies.add(new AllyNexus(800, 150, _shifting));
        _enemies.add(new EnemyNexus(120, 200, _shifting));
        money = new Money();
    }

    public void Run()
    {
        AlliesRun();
        EnemiesRun();
        ProduceNexusKnockedBack();
        GenerateEnemies();
    }

    //讓所有友軍進行攻擊或移動
    private void AlliesRun()
    {
        int index = 0;
        _alliesMax = 1000;
        _recordAlliesIndex = 0;

        for (Units element:_allies)
        {
            if (element.GetIsDying())
            {
                element.Dying();
                if (element.GetY() < 0)
                {
                    _allies.remove(index);
                    return;
                }
            }
            else
            {
                if (element.GetX() <= _enemiesMax + 5 && element.GetX() >= _enemiesMax)
                {
                    element.SetAttackDelayCounter(element.GetAttackDelayCounter() + 1);
                    if (element.GetAttackDelayCounter() == 11 * element.GetAttackSpeed())
                    {
                        element.Attack();
                    }
                    else if (element.GetAttackDelayCounter() == 13 * element.GetAttackSpeed())
                    {
                        element.Attack();
                    }
                    if (element.GetAttackDelayCounter() == element.GetAttackSpeed() * Game.FRAME_RATE)
                    {
                        element.Attack();
                        _enemies.get(_recordEnemiesIndex).Attacked(element.GetAttackDamage());
                        element.SetAttackDelayCounter(0);
                        element.SetIsAttacking(false);
                    }
                }
                else
                {
                    element.Moving();
                }
            }
            if ((_alliesMax > element.GetX() && !(element.GetIsDying())))
            {
                _alliesMax = element.GetX();
                _recordAlliesIndex = index;
            }
            index++;
        }
    }

    //讓所有敵軍進行攻擊或移動
    private void EnemiesRun()
    {
        int index = 0;
        _enemiesMax = 0;
        _recordEnemiesIndex = 0;

        for (Units element:_enemies)
        {
            if (element.GetIsDying())
            {
                element.Dying();
                if (element.GetY() < 0)
                {
                    _enemies.remove(index);
                    return;
                }
            }
            else
            {
                if (element.GetRightSideX() >= _alliesMax - 5 && element.GetRightSideX() <= _alliesMax)
                {
                    element.SetAttackDelayCounter(element.GetAttackDelayCounter() + 1);
                    if (element.GetAttackDelayCounter() == element.GetAttackSpeed() * Game.FRAME_RATE)
                    {
                        element.Attack();
                        _allies.get(_recordAlliesIndex).Attacked(element.GetAttackDamage());
                        element.SetAttackDelayCounter(0);
                    }
                }
                else
                {
                    element.Moving();
                }
            }
            if (_enemiesMax < element.GetRightSideX() && !(element.GetIsDying()))
            {
                _enemiesMax = element.GetRightSideX();
                _recordEnemiesIndex = index;
            }
            index++;
        }
    }

    //讓主堡被攻擊時產生擊退的效果
    private void ProduceNexusKnockedBack()
    {
        List<Units> nexus = new ArrayList<>();
        nexus.add(_allies.get(0));
        nexus.add(_enemies.get(0));

        for (Units element:nexus)
        {
            if (element.GetIsAttacked())
            {
                _knockedBackDelayCounter++;

                if (_knockedBackDelayCounter == 1)
                {
                    element.KnockedBack();
                }
                else if (_knockedBackDelayCounter == 2)
                {
                    element.KnockedBack();
                }
                else if (_knockedBackDelayCounter == 15)
                {
                    element.SetIsAttacked(false);
                    _knockedBackDelayCounter = 0;
                }
            }
        }
    }

    //根據_generateEnemiesDelayCounter產生敵軍
    private void GenerateEnemies()
    {
        _generateEnemiesDelayCounter--;

        if (_generateEnemiesDelayCounter == 0)
        {
            GenerateOtter();
            _generateEnemiesDelayCounter = GENERATE_ENEMIES_DELAY_COUNTER;
        }
    }

    public void GenerateCapoo()
    {
        _allies.add(new Capoo(800, 200, _shifting));
    }

    public void GenerateOtter()
    {
        _enemies.add(new Otter(20, 200, _shifting));
    }

    public void UpdateShifting(int shifting)
    {
        _shifting = shifting;
    }

    public void Transition(int shiftedX, int shiftedY)
    {
        UpdateShifting(shiftedX);
        for (Units element:_allies)
        {
            element.Transition(_shifting, 0);
        }
        for (Units element:_enemies)
        {
            element.Transition(_shifting, 0);
        }
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
        money.show();
    }

    public void release()
    {
        _allies.clear();
        _enemies.clear();
        _allies = null;
        _enemies = null;
    }
}