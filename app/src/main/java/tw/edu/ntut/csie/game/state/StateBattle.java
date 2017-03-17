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
        _capooButton = new MovingBitmap(R.drawable.test_capoo_button, 10, 10);
        _music = new Audio(R.raw.ntut);
        _music.setRepeating(true);
        _music.play();
        _battleModel = new BattleModel();
    }

    @Override
    public void move()
    {
        _battleModel.Run();
        _battleModel.GenerateOtter();
    }

    @Override
    public void show()
    {
        _background.show();
        _capooButton.show();
        _battleModel.ShowAll();
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
        if (pressedX > _capooButton.getX() && pressedX < _capooButton.getX() + _capooButton.getWidth())
        {
            if (pressedY > _capooButton.getY() && pressedY < _capooButton.getY() + _capooButton.getHeight())
            {
                _battleModel.GenerateCapoo();
            }
        }
        return true;
    }

    @Override
    public boolean pointerMoved(List<Pointer> pointers)
    {
        return false;
    }

    @Override
    public boolean pointerReleased(List<Pointer> pointers)
    {
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
    private MovingBitmap _capooButton;
    private Audio _music;
    private BattleModel _battleModel;
}