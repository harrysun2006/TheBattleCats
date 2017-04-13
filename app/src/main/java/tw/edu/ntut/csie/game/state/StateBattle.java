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
import tw.edu.ntut.csie.game.model.GameButton;
import tw.edu.ntut.csie.game.model.Money;

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
        _capooButton = new GameButton(R.drawable.test_capoo_button, 10, 10, 2);
        _music = new Audio(R.raw.ntut);
        _music.setRepeating(true);
        _music.play();
        _battleModel = new BattleModel();

        _shifting = 360;
        _background.SaveRealPosition();
        Transition(_shifting, 0);
    }

    @Override
    public void move()
    {
        _battleModel.Run();
        _capooButton.Run();
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
        _capooButton.Show();
        _battleModel.ShowAll();
        _capooButton.Show();
    }

    @Override
    public void release()
    {
        _background.release();
        _capooButton.release();
        _music.release();
        _battleModel.release();
        _background = null;
        _capooButton = null;
        _music = null;
        _battleModel = null;
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
        if (pressedX > _capooButton.GetX() && pressedX < _capooButton.GetY() + _capooButton.GetWidth())
        {
            if (pressedY > _capooButton.GetY() && pressedY < _capooButton.GetY() + _capooButton.GetHeight())
            {
                if (_capooButton.Push() == true)
                {
                    _battleModel.GenerateCapoo();
                }
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
    private GameButton _capooButton;
    private Audio _music;
    private BattleModel _battleModel;

    private boolean _isPressed;
    private int _previousPressedX;
    private int _shifting; //被顯示出來的遊戲畫面的原點在整個遊戲畫面中的X座標
    private int _tempShifting;
}