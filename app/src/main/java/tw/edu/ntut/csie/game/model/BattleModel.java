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

    private static final int GENERATE_ENEMIES_DELAY_COUNTER = 2 * Game.FRAME_RATE; //讓敵兵每N秒產生一個，計數器每15可以讓兵延遲1秒產生 (畫面更新頻率 = 15次/1秒)
    private int _delayCounter = GENERATE_ENEMIES_DELAY_COUNTER;//真正用來計算產兵延遲的
    private int _alliesAttackDelayCounter = 0;//控制兵攻擊頻率(因為畫面更新頻率太快，所以攻擊要DEALY一下，不然會攻擊太快)
    private int _enemiesAttackDelayCounter = 0;
    private int _knockedBackDelayCounter = 0;//同上理由，這是被擊退的頻率
    private int _recordAlliesIndex = 0;//走在最前面的友軍的index
    private int _recordEnemiesIndex = 0;//走在最前面的敵軍index
    private int _alliesMax =1000;//走在最前面的友軍的X值
    private int _EnemiesMax = 0;//走在最前面的敵軍的X值
    private int i = 0;//

    public BattleModel()
    {
        _allies = new ArrayList<>();
        _enemies = new ArrayList<>();
        _enemies.add(new Nexus(18, 200));
    }

    public void Run()
    {
        _alliesMax = 1000;
        /*for (Units element:_allies)
        {
            if (element.GetX() == _enemies.get(0).GetRightSideX())
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

        }*/
        i = 0;
        _recordAlliesIndex = 0;
        for (Units element:_allies)
        {
            if(_alliesMax > element.GetX())
            {
                _alliesMax = element.GetX();
                _recordAlliesIndex = i;
            }
            i++;
            if(element.GetX() < _EnemiesMax+20 && element.GetX() > _EnemiesMax)
            {
                _alliesAttackDelayCounter++;

                if (_alliesAttackDelayCounter == element.GetAttackSpeed() * Game.FRAME_RATE)
                {
                    element.Attack();
                    _enemies.get(_recordEnemiesIndex).Attacked(element.GetAttackDamage());
                    _alliesAttackDelayCounter = 0;
                    if(_enemies.get(_recordEnemiesIndex)._isDied = true)
                    {
                        _enemies.remove(_recordEnemiesIndex);
                    }
                }
            }
            else
            {
                element.Moving();
            }
        }
        for (Units element:_enemies) //擊退用的FOR
        {
            if (element.GetIsAttacked())
            {
                _knockedBackDelayCounter++;

                if (_knockedBackDelayCounter == 1) //迅速搖晃一下
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
        i = 0;
        _EnemiesMax = 0;
        _recordEnemiesIndex = 0;
        for(Units element:_enemies)
        {
            if (_EnemiesMax < element.GetX())
            {
                _EnemiesMax = element.GetX();
                _recordEnemiesIndex = i;
            }
            i++;
            if(element.GetX() > _alliesMax - 30 && element.GetX() < _alliesMax)
            {
                _enemiesAttackDelayCounter++;

                if (_enemiesAttackDelayCounter == element.GetAttackSpeed() * Game.FRAME_RATE)
                {
                    element.Attack();
                    _allies.get(_recordAlliesIndex).Attacked(element.GetAttackDamage());
                    _enemiesAttackDelayCounter = 0;
                    if(_allies.get(_recordAlliesIndex)._isDied = true)
                    {
                        _allies.remove(_recordAlliesIndex);
                    }
                }
            }
            else
            {
                element.Moving();
            }

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