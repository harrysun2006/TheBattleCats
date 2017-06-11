package tw.edu.ntut.csie.game.state;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

import tw.edu.ntut.csie.game.Game;
import tw.edu.ntut.csie.game.Pointer;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.Audio;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.engine.GameEngine;

/**
 * Created by User on 2017/6/1.
 */

public class StateMap extends GameState
{
    public StateMap(GameEngine engine)
    {
        //To invoke constructor in tw.edu.ntut.csie.game.state.GameState (super class).
        super(engine);
    }

    @Override
    public void initialize(Map<String, Object> data)
    {
        _background = new MovingBitmap(R.drawable.gamemap);
        _music = new Audio(R.raw.ntut);
        _music.setRepeating(true);
        _music.play();
        _gameLevel = 0;
    }

    @Override
    public void release()
    {
        _background.release();
        _music.release();
        _background = null;
        _music = null;
    }

    @Override
    public void move()
    {
    }

    @Override
    public void show()
    {
        _background.show();
    }

    @Override
    public boolean pointerPressed(List<Pointer> pointers)
    {
        //億光大樓 左上(563, 264) 右下(609, 293)
        if (pointers.get(0).getX() >= 563 && pointers.get(0).getX() <= 609 && pointers.get(0).getY() >= 264 && pointers.get(0).getY() <= 293)
        {
            _gameLevel = 1;
        }
        //科研 左上(106, 38) 右下(157, 83)
        else if (pointers.get(0).getX() >= 106 && pointers.get(0).getX() <= 157 && pointers.get(0).getY() >= 38 && pointers.get(0).getY() <= 83)
        {
            _gameLevel = 2;
        }
        //行政大樓 左上(262, 210) 右下(304, 239)
        else if (pointers.get(0).getX() >= 262 && pointers.get(0).getX() <= 304 && pointers.get(0).getY() >= 210 && pointers.get(0).getY() <= 239)
        {
            _gameLevel = 3;
        }
        //如果有選擇到關卡(_gameLevel != 0)
        if (_gameLevel != 0)
        {
            Map<String, Object> input = new HashMap<>();
            input.put("game_level", _gameLevel);
            changeState(Game.BATTLE_STATE, input);
        }
        return false;
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
    private int _gameLevel;
}