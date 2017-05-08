package tw.edu.ntut.csie.game.state;

//Created by leon on 2017/3/8.

import java.util.Map;
import java.util.List;

import tw.edu.ntut.csie.game.engine.GameEngine;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.core.Audio;
import tw.edu.ntut.csie.game.Pointer;
import tw.edu.ntut.csie.game.R;

import tw.edu.ntut.csie.game.model.TransitionalBitmap;
import tw.edu.ntut.csie.game.model.BattleModel;
import tw.edu.ntut.csie.game.model.Capoo;
import tw.edu.ntut.csie.game.model.Pusheen;
import tw.edu.ntut.csie.game.model.Rabbit;
import tw.edu.ntut.csie.game.model.GameButton;
import tw.edu.ntut.csie.game.model.CooldownBar;
import tw.edu.ntut.csie.game.model.HealthBar;
import tw.edu.ntut.csie.game.model.ShiftingModule;

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
        InitializeMusic();
        _background = new TransitionalBitmap(R.drawable.test_background);
        _battleModel = new BattleModel();
        _capooButton = new GameButton(R.drawable.capoo_button, R.drawable.capoo_button_disabled, 10, 10, Capoo.COOLDOWN); //x from 10 ~ 10 + 78 = 10 ~ 88
        _capooCooldown = new CooldownBar(14, 60, 70); //x from 14 ~ 14 + 70 = 14 ~ 84, so that 14 - 10 = 88 - 84 = 4
        _pusheenButton = new GameButton(R.drawable.pusheen_button, R.drawable.pusheen_button_disabled, 100, 10, Pusheen.COOLDOWN);
        _pusheenCooldown = new CooldownBar(104, 60, 70);
        _rabbitButton = new GameButton(R.drawable.rabbit_button, R.drawable.rabbit_button_disabled, 190, 10, Rabbit.COOLDOWN);
        _rabbitCooldown = new CooldownBar(194, 60, 70);
        _allyNexusHealth = new HealthBar(810, 120, 120);
        _enemyNexusHealth = new HealthBar(85, 150, 120);
        _moneyAddButton = new MovingBitmap(R.drawable.money_button_80, 10, 286);

        _isGameOver = false;
        _shiftingModule = new ShiftingModule();
        _shiftingModule.SetShifting(360);
        Transition(_shiftingModule.GetShifting(), 0);
    }

    private void InitializeMusic()
    {
        _backgroundMusic = new Audio(R.raw.ntut);
        _backgroundMusic.setRepeating(true);
        _winningMusic = new Audio(R.raw.winning);
        _winningMusic.setRepeating(false);
        _losingMusic = new Audio(R.raw.losing);
        _losingMusic.setRepeating(false);
        _buyingSound = new Audio(R.raw.buy_item);
        _buyingSound.setRepeating(false);
        _backgroundMusic.play();
    }

    @Override
    public void move()
    {
        _battleModel.Run();
        _capooButton.Run();
        _pusheenButton.Run();
        _rabbitButton.Run();
        _capooButton.SetEnable(_battleModel.GetCurrentMoney(), Capoo.COST);
        _pusheenButton.SetEnable(_battleModel.GetCurrentMoney(), Pusheen.COST);
        _rabbitButton.SetEnable(_battleModel.GetCurrentMoney(), Rabbit.COST);
        _capooCooldown.SetCurrentPercentage(_capooButton.GetPercent());
        _pusheenCooldown.SetCurrentPercentage(_pusheenButton.GetPercent());
        _rabbitCooldown.SetCurrentPercentage(_rabbitButton.GetPercent());
        _allyNexusHealth.SetCurrentPercentage(_battleModel.GetAllyNexusHealthPercentage());
        _enemyNexusHealth.SetCurrentPercentage(_battleModel.GetEnemyNexusHealthPercentage());
        RunShiftingModule();

        if (_battleModel.GetBattleStatus() == 1)
        {
            if (!_isGameOver)
            {
                _shiftingModule.AssignSpecifiedSliding(0, 30);
                _backgroundMusic.stop();
                _winningMusic.play();
                _isGameOver = true;
            }
        }
        if (_battleModel.GetBattleStatus() == 2)
        {
            if (!_isGameOver)
            {
                _shiftingModule.AssignSpecifiedSliding(360, -30);
                _backgroundMusic.stop();
                _losingMusic.play();
                _isGameOver = true;
            }
        }
    }

    public void Transition(int shiftedX, int shiftedY)
    {
        _background.Transition(shiftedX, shiftedY);
        _battleModel.Transition(shiftedX, shiftedY);
        _allyNexusHealth.Transition(shiftedX, shiftedY);
        _enemyNexusHealth.Transition(shiftedX, shiftedY);
    }

    @Override
    public void show()
    {
        _background.Show();
        _battleModel.Show();
        _capooButton.Show();
        _pusheenButton.Show();
        _rabbitButton.Show();
        _capooCooldown.Show();
        _pusheenCooldown.Show();
        _rabbitCooldown.Show();
        _allyNexusHealth.Show();
        _enemyNexusHealth.Show();
        _moneyAddButton.show();
    }

    @Override
    public void release()
    {
        _backgroundMusic.release();
        _winningMusic.release();
        _buyingSound.release();
        _background.Release();
        _battleModel.release();
        _capooButton.release();
        _pusheenButton.release();
        _rabbitButton.release();
        _capooCooldown.release();
        _pusheenCooldown.release();
        _rabbitCooldown.release();
        _allyNexusHealth.release();
        _enemyNexusHealth.release();
        _moneyAddButton.release();
        _backgroundMusic = null;
        _winningMusic = null;
        _buyingSound = null;
        _background = null;
        _battleModel = null;
        _capooButton = null;
        _pusheenButton = null;
        _rabbitButton = null;
        _capooCooldown = null;
        _pusheenCooldown = null;
        _rabbitCooldown = null;
        _allyNexusHealth = null;
        _enemyNexusHealth = null;
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
                    _buyingSound.play();
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
                    _buyingSound.play();
                    _pusheenButton.Push();
                    _battleModel.GeneratePusheen();
                }
            }
        }
        if (pressedX > _rabbitButton.GetX() && pressedX < _rabbitButton.GetX() + _rabbitButton.GetWidth())
        {
            if (pressedY > _rabbitButton.GetY() && pressedY < _rabbitButton.GetY() + _rabbitButton.GetHeight())
            {
                if (_rabbitButton.GetIsEnabled() == true)
                {
                    _buyingSound.play();
                    _rabbitButton.Push();
                    _battleModel.GenerateRabbit();
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
            _currentPressedX = pointers.get(0).getX();
            _shiftingModule.HandlePointerPressed(_currentPressedX);
        }
        return true;
    }

    @Override
    public boolean pointerMoved(List<Pointer> pointers)
    {
        if (_isPressed)
        {
            _currentPressedX = pointers.get(0).getX();
            _shiftingModule.HandlePointerMoved(_currentPressedX);
            Transition(_shiftingModule.GetTempShifting(), 0);
        }
        return false;
    }

    @Override
    public boolean pointerReleased(List<Pointer> pointers)
    {
        if (_isPressed)
        {
            _isPressed = false;
            _shiftingModule.HandlePointerReleased();
        }
        return false;
    }

    @Override
    public void pause()
    {
        _backgroundMusic.pause();
    }

    @Override
    public void resume()
    {
        _backgroundMusic.play();
    }

    private void RunShiftingModule()
    {
        _shiftingModule.Run(_currentPressedX);

        if (_shiftingModule.IsAutoSlidingEnabled() || _shiftingModule.IsSpecifiedSlidingEnabled())
        {
            Transition(_shiftingModule.GetShifting(), 0);
        }
    }

    private Audio _backgroundMusic;
    private Audio _winningMusic;
    private Audio _losingMusic;
    private Audio _buyingSound;
    private TransitionalBitmap _background;
    private BattleModel _battleModel;
    private GameButton _capooButton;
    private GameButton _pusheenButton;
    private GameButton _rabbitButton;
    private CooldownBar _capooCooldown;
    private CooldownBar _pusheenCooldown;
    private CooldownBar _rabbitCooldown;
    private HealthBar _allyNexusHealth;
    private HealthBar _enemyNexusHealth;
    private MovingBitmap _moneyAddButton;

    private ShiftingModule _shiftingModule;
    private int _currentPressedX;
    private boolean _isPressed;
    private boolean _isGameOver;
}