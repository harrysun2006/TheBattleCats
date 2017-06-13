package tw.edu.ntut.csie.game.model;

//Created by leon on 2017/6/13.

import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.ReleasableResource;

public class GodMode implements ReleasableResource
{
    private FadingDark _darkCover;
    private MovingBitmap _god;
    private MovingBitmap _dialog;
    private MovingBitmap _introduction;
    private MovingBitmap _acceptGodButton;
    private MovingBitmap _exitGodModeButton;
    private boolean _isActivated;

    public GodMode()
    {
        _darkCover = new FadingDark();
        _god = new MovingBitmap(R.drawable.wk, 40, 90);
        _god.setVisible(false);
        _dialog = new MovingBitmap(R.drawable.dialog, 145, 80);
        _dialog.setVisible(false);
        _introduction = new MovingBitmap(R.drawable.introduction, 135, 240);
        _introduction.setVisible(false);
        _acceptGodButton = new MovingBitmap(R.drawable.yes, 400, 325);
        _acceptGodButton.setVisible(false);
        _exitGodModeButton = new MovingBitmap(R.drawable.no, 250, 325);
        _exitGodModeButton.setVisible(false);
        _isActivated = false;
    }

    public void Run()
    {
        if (_isActivated)
        {
            _darkCover.Run();
            if (_darkCover.IsFadingFinished())
            {
                _god.setVisible(true);
                _dialog.setVisible(true);
                _introduction.setVisible(true);
                _acceptGodButton.setVisible(true);
                _exitGodModeButton.setVisible(true);
                _isActivated = false;
            }
        }
    }

    public void Activate()
    {
        _darkCover.ActivateFadeIn();
        _isActivated = true;
    }

    public void Inactivate()
    {
        _darkCover.ActivateFadeOut();
        _god.setVisible(false);
        _dialog.setVisible(false);
        _introduction.setVisible(false);
        _acceptGodButton.setVisible(false);
        _exitGodModeButton.setVisible(false);
    }

    @Override
    public void release()
    {
        _darkCover.release();
        _god.release();
        _dialog.release();
        _introduction.release();
        _acceptGodButton.release();
        _exitGodModeButton.release();
    }

    public void Show()
    {
        _darkCover.Show();
        _god.show();
        _dialog.show();
        _introduction.show();
        _acceptGodButton.show();
        _exitGodModeButton.show();
    }

    public MovingBitmap GetAcceptGodButton()
    {
        return _acceptGodButton;
    }

    public MovingBitmap GetExitGodModeButton()
    {
        return _exitGodModeButton;
    }

    public boolean IsTransitionFinished()
    {
        return _god.IsVisible();
    }
}