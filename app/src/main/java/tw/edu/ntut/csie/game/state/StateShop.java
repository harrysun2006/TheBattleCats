package tw.edu.ntut.csie.game.state;

import java.util.List;
import java.util.Map;

import tw.edu.ntut.csie.game.Pointer;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.Audio;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.engine.GameEngine;
import tw.edu.ntut.csie.game.model.BattleModel;
import tw.edu.ntut.csie.game.model.Capoo;
import tw.edu.ntut.csie.game.model.CooldownBar;
import tw.edu.ntut.csie.game.model.GameButton;
import tw.edu.ntut.csie.game.model.HealthBar;
import tw.edu.ntut.csie.game.model.Pusheen;
import tw.edu.ntut.csie.game.model.ShiftingModule;
import tw.edu.ntut.csie.game.model.ShopModel;

/**
 * Created by User on 2017/4/26.
 */

public class StateShop extends GameState
{
    public StateShop(GameEngine engine)
    {
        //To invoke constructor in tw.edu.ntut.csie.game.state.GameState (super class).
        super(engine);
    }

    @Override
    public void initialize(Map<String, Object> data)
    {
        _background = new MovingBitmap(R.drawable.background);
        _music = new Audio(R.raw.ntut);
        _music.setRepeating(true);
        _music.play();
        _black = new GameButton(R.drawable.android_black, R.drawable.android_green,10, 150, 0);
        _green = new GameButton(R.drawable.android_green, R.drawable.android_black, 100, 150, 0);
        _shopModel = new ShopModel();
    }

    @Override
    public void move()
    {

    }

    public void Transition(int shiftedX, int shiftedY)
    {

    }

    @Override
    public void show()
    {
        _background.show();
        _black.Show();
        _green.Show();
    }

    @Override
    public void release()
    {
        _background.release();
        _black.release();
        _green.release();
        _music.release();
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
        if (pressedX > _black.GetX() && pressedX < _black.GetX() + _black.GetWidth() && pressedY > _black.GetY() && pressedY < _black.GetY() + _black.GetHeight())
        {
            _shopModel.AddMoneyPocket();
            /*if (_black.GetIsEnabled() == true)
            {//錢包大小升級測試
                _black.Push();
                _shopModel.AddMoneyPocket();
            }*/
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
    private GameButton _black;
    private GameButton _green;
    private ShopModel _shopModel;

    private ShiftingModule _shiftingModule;
    private int _currentPressedX;
    private boolean _isPressed;
}