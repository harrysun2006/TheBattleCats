package tw.edu.ntut.csie.game.state;

//Created by leon on 2017/3/8.

import java.util.Map;
import java.util.List;

import tw.edu.ntut.csie.game.engine.GameEngine;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.core.Audio;
import tw.edu.ntut.csie.game.Pointer;
import tw.edu.ntut.csie.game.R;

import tw.edu.ntut.csie.game.model.BattleModel;
import tw.edu.ntut.csie.game.model.Capoo;
import tw.edu.ntut.csie.game.model.Pusheen;
import tw.edu.ntut.csie.game.model.GameButton;
import tw.edu.ntut.csie.game.model.CooldownBar;
import tw.edu.ntut.csie.game.model.HealthBar;

public class StateBattle extends GameState
{
    public StateBattle(GameEngine engine)
    {
        //To invoke constructor in tw.edu.ntut.csie.game.state.GameState (super class).
        super(engine);
    }

    @Override
    public void initialize(Map<String, Object> data)
    {
        _background = new MovingBitmap(R.drawable.test_background);
        _music = new Audio(R.raw.ntut);
        _music.setRepeating(true);
        _music.play();
        _battleModel = new BattleModel();
        _capooButton = new GameButton(R.drawable.capoo_button, 10, 10, Capoo.COOLDOWN); //x from 10 ~ 10 + 78 = 10 ~ 88
        _capooCooldown = new CooldownBar(14, 60, 70); //x from 14 ~ 14 + 70 = 14 ~ 84, so that 14 - 10 = 88 - 84 = 4
        _pusheenButton = new GameButton(R.drawable.pusheen_button, 100, 10, Pusheen.COOLDOWN);
        _pusheenCooldown = new CooldownBar(104, 60, 70);
        _allyNexusHealth = new HealthBar(320, 20, 100);
        _enemyNexusHealth = new HealthBar(200, 20, 100);
        _moneyAddButton = new MovingBitmap(R.drawable.money_button_80, 10, 286);

        _shifting = 360;
        _background.SaveRealPosition();
        Transition(_shifting, 0);
    }

    @Override
    public void move()
    {
        _battleModel.Run();
        _capooButton.Run();
        _pusheenButton.Run();
        _capooCooldown.SetCurrentPercentage(_capooButton.GetPercent());
        _pusheenCooldown.SetCurrentPercentage(_pusheenButton.GetPercent());
        _allyNexusHealth.SetCurrentPercentage(_battleModel.GetAllyNexusHealthPercentage());
        _enemyNexusHealth.SetCurrentPercentage(_battleModel.GetEnemyNexusHealthPercentage());
        _capooButton.SetEnable(_battleModel.GetCurrentMoney(), Capoo.COST);
        _pusheenButton.SetEnable(_battleModel.GetCurrentMoney(), Pusheen.COST);
    }

    public void Transition(int shiftedX, int shiftedY)
    {
        _background.setLocation(_background.GetRealX() - shiftedX, _background.GetRealY() - shiftedY);
        _battleModel.Transition(shiftedX, shiftedY);
    }

    @Override
    public void show()
    {
        _background.show();
        _battleModel.Show();
        _capooButton.Show();
        _pusheenButton.Show();
        _capooCooldown.Show();
        _pusheenCooldown.Show();
        _allyNexusHealth.Show();
        _enemyNexusHealth.Show();
        _moneyAddButton.show();
    }

    @Override
    public void release()
    {
        _background.release();
        _music.release();
        _battleModel.release();
        _capooButton.release();
        _pusheenButton.release();
        _capooCooldown.release();
        _pusheenCooldown.release();
        _moneyAddButton.release();
        _background = null;
        _music = null;
        _battleModel = null;
        _capooButton = null;
        _pusheenButton = null;
        _capooCooldown = null;
        _pusheenCooldown = null;
        _moneyAddButton = null;
    }

    @Override
    public void keyPressed(int keyCode)
    {
    }

    @Override
    public void keyReleased(int keyCode)
    {
    }

    @Override
    public void orientationChanged(float pitch, float azimuth, float roll)
    {
    }

    @Override
    public void accelerationChanged(float dX, float dY, float dZ)
    {
    }

    @Override
    public boolean pointerPressed(List<Pointer> pointers)
    {
        int pressedX = pointers.get(0).getX();
        int pressedY = pointers.get(0).getY();
        if (pressedX > _capooButton.GetX() && pressedX < _capooButton.GetX() + _capooButton.GetWidth())
        {
            if (pressedY > _capooButton.GetY() && pressedY < _capooButton.GetY() + _capooButton.GetHeight())
            {
                if (_capooButton.GetIsEnabled() == true)
                {
                    _capooButton.Push();
                    _battleModel.GenerateCapoo();
                }
            }
        }
        if (pressedX > _pusheenButton.GetX() && pressedX < _pusheenButton.GetX() + _pusheenButton.GetWidth())
        {
            if (pressedY > _pusheenButton.GetY() && pressedY < _pusheenButton.GetY() + _pusheenButton.GetHeight())
            {
                if (_pusheenButton.GetIsEnabled() == true)
                {
                    _pusheenButton.Push();
                    _battleModel.GeneratePusheen();
                }
            }
        }
        if (pressedX > _moneyAddButton.getX() && pressedX < _moneyAddButton.getX() + _moneyAddButton.getWidth())
        {
            if (pressedY > _moneyAddButton.getY() && pressedY < _moneyAddButton.getY() + _moneyAddButton.getHeight())
            {
                _battleModel.AddMoneyMax();
            }
        }
        else
        {
            _isPressed = true;
            _previousPressedX = pointers.get(0).getX();
        }
        return true;
    }

    @Override
    public boolean pointerMoved(List<Pointer> pointers)
    {
        if (_isPressed)
        {
            _tempShifting = -1 * (pointers.get(0).getX() - _previousPressedX) + _shifting;
            if (_tempShifting > 360)
            {
                _tempShifting = 360;
            }
            if (_tempShifting < 0)
            {
                _tempShifting = 0;
            }
            Transition(_tempShifting, 0);
        }
        return false;
    }

    @Override
    public boolean pointerReleased(List<Pointer> pointers)
    {
        if (_isPressed)
        {
            _isPressed = false;
            _shifting = _tempShifting;
        }
        return false;
    }

    @Override
    public void pause()
    {
        _music.pause();
    }

    @Override
    public void resume()
    {
        _music.play();
    }

    private MovingBitmap _background;
    private Audio _music;
    private BattleModel _battleModel;
    private GameButton _capooButton;
    private GameButton _pusheenButton;
    private CooldownBar _capooCooldown;
    private CooldownBar _pusheenCooldown;
    private HealthBar _allyNexusHealth;
    private HealthBar _enemyNexusHealth;
    private MovingBitmap _moneyAddButton;

    private boolean _isPressed;
    private int _previousPressedX;
    private int _shifting; //被顯示出來的遊戲畫面的原點在整個遊戲畫面中的X座標
    private int _tempShifting;
}