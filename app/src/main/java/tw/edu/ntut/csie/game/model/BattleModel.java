package tw.edu.ntut.csie.game.model;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.ReleasableResource;
import tw.edu.ntut.csie.game.Game;

/**
 * Created by User on 2017/3/10.
 */

public class BattleModel implements ReleasableResource
{
    private List<Units> _allies;
    private List<Units> _enemies;
    private Money _money;
    private Score _score;

    private static final int GENERATE_ENEMIES_DELAY_COUNTER = 3 * Game.FRAME_RATE; //讓敵兵每N秒產生一個，計數器每15可以讓兵延遲1秒產生 (畫面更新頻率 = 15次/1秒)
    private int _generateEnemiesDelayCounter = GENERATE_ENEMIES_DELAY_COUNTER; //真正用來計算產兵延遲的
    private int _knockedBackDelayCounter = 0; //同上理由，這是被擊退的頻率
    private int _recordAlliesIndex = 0; //走在最前面的友軍的index
    private int _recordEnemiesIndex = 0; //走在最前面的敵軍的index
    private int _alliesMax = 1000; //走在最前面的友軍的X值
    private int _enemiesMax = 0; //走在最前面的敵軍的X值
    private int _shifting = 0;
    private int _battleStatus; //0: 戰鬥中，1: 遊戲勝利，2: 遊戲失敗

    public BattleModel(RecordModel recordModel, Map<String, Object> data)
    {
        _allies = new ArrayList<>();
        _enemies = new ArrayList<>();
        _allies.add(new AllyNexus(800, 150, recordModel.GetAllyNexusHealth(), _shifting));
        _enemies.add(GenerateEnemyNexus(data));
        _money = new Money(recordModel, 385, 10);
        _score = new Score();
        _battleStatus = 0;
    }

    //根據關卡產生相對應的敵軍主堡造型
    private EnemyNexus GenerateEnemyNexus(Map<String, Object> data)
    {
        if (data == null)
        {
            return new EnemyNexus(80, 100, _shifting, R.drawable.enemy_nexus, R.drawable.enemy_nexus_attacked);
        }
        else if ((int) data.get("game_level") == 1)
        {
            return new EnemyNexus(40, 180, _shifting, R.drawable.audi, R.drawable.audi_attacked);
        }
        else if ((int) data.get("game_level") == 2)
        {
            return new EnemyNexus(70, 105, _shifting, R.drawable.wang, R.drawable.wang_attacked);
        }
        else if ((int) data.get("game_level") == 3)
        {
            return new EnemyNexus(90, 110, _shifting, R.drawable.yao, R.drawable.yao_attacked);
        }
        else
        {
            return new EnemyNexus(80, 100, _shifting, R.drawable.enemy_nexus, R.drawable.enemy_nexus_attacked);
        }
    }

    public void Run()
    {
        AlliesRun();
        EnemiesRun();
        ProduceNexusKnockedBack();
        GenerateEnemies();
        UpdateBattleStatus();
        _money.AddMoney();
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
                if (element.GetX() <= _enemiesMax + 20 && element.GetX() >= _enemiesMax)
                {
                    element.SetAttackDelayCounter(element.GetAttackDelayCounter() + 1);
                    element.Attack();
                    if (element.GetAttackDelayCounter() == element.GetAttackSpeed() * Game.FRAME_RATE)
                    {
                        _enemies.get(_recordEnemiesIndex).Attacked(element.GetAttackDamage());
                        element.SetAttackDelayCounter(0);
                    }
                }
                else
                {
                    element.SetIsAttacking(false);
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
                    _score.IncreaseEnemyKilled();
                    return;
                }
            }
            else
            {
                if (element.GetRightSideX() >= _alliesMax - 20 && element.GetRightSideX() <= _alliesMax)
                {
                    element.SetAttackDelayCounter(element.GetAttackDelayCounter() + 1);
                    element.Attack();
                    if (element.GetAttackDelayCounter() == element.GetAttackSpeed() * Game.FRAME_RATE)
                    {
                        _allies.get(_recordAlliesIndex).Attacked(element.GetAttackDamage());
                        element.SetAttackDelayCounter(0);
                    }
                }
                else
                {
                    element.SetIsAttacking(false);
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
        if (_battleStatus == 0)
        {
            _generateEnemiesDelayCounter--;
            if (_generateEnemiesDelayCounter == 0)
            {
                Random random = new Random();
                int result = random.nextInt(3);
                switch (result)
                {
                    case 0:
                        GenerateOtter();
                        break;
                    case 1:
                        GenerateDeer();
                        break;
                    case 2:
                        GenerateHoshi();
                        break;
                }
                _generateEnemiesDelayCounter = GENERATE_ENEMIES_DELAY_COUNTER;
            }
        }
    }

    //更新戰鬥模式狀態
    private void UpdateBattleStatus()
    {
        if (_allies.get(0).GetCurrentHealthPercentage() <= 0)
        {
            _battleStatus = 2;
        }
        else if (_enemies.get(0).GetCurrentHealthPercentage() <= 0)
        {
            _battleStatus = 1;
        }
        else
        {
            _battleStatus = 0;
        }
    }

    public void GenerateCapoo()
    {
        _money.SubtractMoney(Capoo.COST);
        _allies.add(new Capoo(800, 210, _shifting));
    }

    public void GeneratePusheen()
    {
        _money.SubtractMoney(Pusheen.COST);
        _allies.add(new Pusheen(800, 200, _shifting));
    }

    public void GenerateRabbit()
    {
        _money.SubtractMoney(Rabbit.COST);
        _allies.add(new Rabbit(800, 170, _shifting));
    }

    public void GenerateBird()
    {
        _money.SubtractMoney(Bird.COST);
        _allies.add(new Bird(800, 210, _shifting));
    }

    private void GenerateOtter()
    {
        _enemies.add(new Otter(20, 200, _shifting));
    }

    private void GenerateDeer()
    {
        _enemies.add(new Deer(20, 195, _shifting));
    }

    private void GenerateHoshi()
    {
        _enemies.add(new Hoshi(80, 220, _shifting));
    }

    public void Translation(int shiftedX, int shiftedY)
    {
        UpdateShifting(shiftedX);
        for (Units element:_allies)
        {
            element.Translation(_shifting, 0);
        }
        for (Units element:_enemies)
        {
            element.Translation(_shifting, 0);
        }
    }

    private void UpdateShifting(int shifting)
    {
        _shifting = shifting;
    }

    public void Show()
    {
        for (Units element:_allies)
        {
            element.Show();
        }
        for (Units element:_enemies)
        {
            element.Show();
        }
        _money.show();
    }

    public void release()
    {
        _allies.clear();
        _enemies.clear();
        _allies = null;
        _enemies = null;
    }

    public int GetAllyNexusHealthPercentage()
    {
        return _allies.get(0).GetCurrentHealthPercentage();
    }

    public int GetEnemyNexusHealthPercentage()
    {
        return _enemies.get(0).GetCurrentHealthPercentage();
    }

    public void AddMoneyMax()
    {
        _money.AddMoneyMax();
    }

    public int GetCurrentMoney()
    {
        return _money.GetCurrentMoney();
    }

    //取得戰鬥模式狀態，0 = 戰鬥中，1 = 遊戲勝利，2 = 遊戲失敗
    public int GetBattleStatus()
    {
        return _battleStatus;
    }

    public int GetScore()
    {
        return _score.GetScore();
    }

    public void KillAllEnemies()
    {
        for (int index = 1; index < _enemies.size(); index++)
        {
            _enemies.get(index).SetIsDying(true);
        }
    }
}