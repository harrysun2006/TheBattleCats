package tw.edu.ntut.csie.game.state;

//Created by leon on 2017/3/8.

import java.util.Map;
import java.util.List;

import tw.edu.ntut.csie.game.engine.GameEngine;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.core.Audio;
import tw.edu.ntut.csie.game.Pointer;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.Game;

import tw.edu.ntut.csie.game.model.TranslationBitmap;
import tw.edu.ntut.csie.game.model.RecordModel;
import tw.edu.ntut.csie.game.model.BattleModel;
import tw.edu.ntut.csie.game.model.Capoo;
import tw.edu.ntut.csie.game.model.Pusheen;
import tw.edu.ntut.csie.game.model.Rabbit;
import tw.edu.ntut.csie.game.model.Bird;
import tw.edu.ntut.csie.game.model.Button;
import tw.edu.ntut.csie.game.model.CDButton;
import tw.edu.ntut.csie.game.model.CooldownBar;
import tw.edu.ntut.csie.game.model.HealthBar;
import tw.edu.ntut.csie.game.model.BattleLevelButton;
import tw.edu.ntut.csie.game.model.FadeInBlack;
import tw.edu.ntut.csie.game.model.HorizontalTransition;
import tw.edu.ntut.csie.game.model.VerticalTransition;
import tw.edu.ntut.csie.game.extend.Integer;
import tw.edu.ntut.csie.game.model.ShiftingModule;
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
        InitializeMusic();
        InitializeButtonComponent();
        _background = new TranslationBitmap(R.drawable.test_background);
        _battleModel = new BattleModel(new RecordModel(_engine), data); //存取RecordModel來初始化BattleModel裡面的金錢以及主堡血量
        _allyNexusHealth = new HealthBar(810, 120, 120);
        _enemyNexusHealth = new HealthBar(70, 100, 120);
        _moneyAddButton = new BattleLevelButton(R.drawable.money_button_80, R.drawable.money_button_80_disabled, 10, 286);

        _losingCover = new FadeInBlack();
        _winningBanner = new MovingBitmap(R.drawable.winning_banner, -299, 125);
        _winningBannerTransition = new HorizontalTransition(_winningBanner, 170);
        _losingBanner = new MovingBitmap(R.drawable.losing_banner, 220, -83);
        _losingBannerTransition = new VerticalTransition(_losingBanner, 120);

        _experienceBanner = new MovingBitmap(R.drawable.exp_banner, 0, 205);
        _experienceBanner.setVisible(false);
        _exitBattleButton = new MovingBitmap(R.drawable.exit_battle_button, 240, 250);
        _exitBattleButton.setVisible(false);
        _pauseBanner = new MovingBitmap(R.drawable.pause_banner, 0, 170);
        _pauseBanner.setVisible(false);
        _pauseButton = new MovingBitmap(R.drawable.pause, 592, 5);
        _experienceValue = new Integer(4, 0, 640, 376); //讓遊戲勝利會獲得的經驗值先暫時放在遊戲畫面外

        _isGameOver = false;
        _exitBattleButtonDelay = 0;
        _isPaused = false;
        _shiftingModule = new ShiftingModule(360);
        _shiftingModule.SetShifting(360);
        Translation(_shiftingModule.GetShifting(), 0);
    }

    private void InitializeMusic()
    {
        _backgroundMusic = new Audio(R.raw.background);
        _backgroundMusic.setRepeating(true);
        _winningMusic = new Audio(R.raw.winning);
        _winningMusic.setRepeating(false);
        _losingMusic = new Audio(R.raw.losing);
        _losingMusic.setRepeating(false);
        _buyingSound = new Audio(R.raw.buy_item);
        _buyingSound.setRepeating(false);
        _backgroundMusic.play();
    }

    private void InitializeButtonComponent()
    {
        _capooButton = new CDButton(R.drawable.capoo_button, R.drawable.capoo_button_disabled, 10, 10, Capoo.COOLDOWN); //x from 10 ~ 10 + 78 = 10 ~ 88
        _capooCooldown = new CooldownBar(14, 60, 70); //x from 14 ~ 14 + 70 = 14 ~ 84, so that 14 - 10 = 88 - 84 = 4
        _pusheenButton = new CDButton(R.drawable.pusheen_button, R.drawable.pusheen_button_disabled, 100, 10, Pusheen.COOLDOWN);
        _pusheenCooldown = new CooldownBar(104, 60, 70);
        _rabbitButton = new CDButton(R.drawable.rabbit_button, R.drawable.rabbit_button_disabled, 190, 10, Rabbit.COOLDOWN);
        _rabbitCooldown = new CooldownBar(194, 60, 70);
        _birdButton = new CDButton(R.drawable.bird_button, R.drawable.bird_button_disabled, 280, 10, Bird.COOLDOWN);
        _birdCooldown = new CooldownBar(284, 60, 70);
    }

    @Override
    public void move()
    {
        if (_isPaused)
        {
            return;
        }
        _battleModel.Run();
        RunButtonComponent();
        RunShiftingModule();
        DetectBattleStatus();
    }

    private void RunButtonComponent()
    {
        _capooButton.Run();
        _pusheenButton.Run();
        _rabbitButton.Run();
        _birdButton.Run();
        _capooButton.SetEnable(_battleModel.GetCurrentMoney(), Capoo.COST);
        _pusheenButton.SetEnable(_battleModel.GetCurrentMoney(), Pusheen.COST);
        _rabbitButton.SetEnable(_battleModel.GetCurrentMoney(), Rabbit.COST);
        _birdButton.SetEnable(_battleModel.GetCurrentMoney(), Bird.COST);
        _capooCooldown.SetCurrentPercentage(_capooButton.GetPercent());
        _pusheenCooldown.SetCurrentPercentage(_pusheenButton.GetPercent());
        _rabbitCooldown.SetCurrentPercentage(_rabbitButton.GetPercent());
        _birdCooldown.SetCurrentPercentage(_birdButton.GetPercent());
        _allyNexusHealth.SetCurrentPercentage(_battleModel.GetAllyNexusHealthPercentage());
        _enemyNexusHealth.SetCurrentPercentage(_battleModel.GetEnemyNexusHealthPercentage());
        _moneyAddButton.SetEnable(_battleModel.GetCurrentMoney(), Money.ADD_MONEY_COST);
    }

    //運作ShiftingModule: 當_shifting改變時會呼叫Translation()位移畫面的物件
    private void RunShiftingModule()
    {
        _shiftingModule.Run(_currentPressedX);

        if (_shiftingModule.IsAutoSlidingEnabled() || _shiftingModule.IsSpecifiedSlidingEnabled())
        {
            Translation(_shiftingModule.GetShifting(), 0);
        }
    }

    //偵測BattleModel戰鬥是否結束(主堡血量歸零)，是的話則觸發勝利/失敗動畫
    private void DetectBattleStatus()
    {
        if (_battleModel.GetBattleStatus() == 1)
        {
            HandleWinningEvent();
            _winningBannerTransition.Run();
        }
        if (_battleModel.GetBattleStatus() == 2)
        {
            HandleLosingEvent();
            _losingBannerTransition.Run();
        }
    }

    private void HandleWinningEvent()
    {
        if (!_isGameOver)
        {
            _shiftingModule.AssignSpecifiedSliding(0, 30);
            _winningBannerTransition.Activate();
            _backgroundMusic.stop();
            _winningMusic.play();
            _isGameOver = true;
        }
        if (_winningBannerTransition.IsTransitionFinished()) //勝利轉場結束後延遲4/3秒跳出離開按鈕以及獲得的經驗值
        {
            _exitBattleButtonDelay++;
            if (_exitBattleButtonDelay == Game.FRAME_RATE * 4 / 3)
            {
                RecordModel recordModel = new RecordModel(_engine);
                recordModel.AddExperience((int) (_battleModel.GetScore() * recordModel.GetExperienceLearningScale()));
                _experienceBanner.setVisible(true);
                _exitBattleButton.setVisible(true);
                _experienceValue.setValue((int) (_battleModel.GetScore() * recordModel.GetExperienceLearningScale()));
                _experienceValue.setLocation(265, 214);
            }
        }
    }

    private void HandleLosingEvent()
    {
        if (!_isGameOver)
        {
            _shiftingModule.AssignSpecifiedSliding(360, -30);
            _losingBannerTransition.Activate();
            _backgroundMusic.stop();
            _losingMusic.play();
            _isGameOver = true;
        }
        if (_losingBannerTransition.IsTransitionFinished()) //失敗轉場結束後延遲4/3秒畫面開始漸漸變暗，結束後跳出離開按鈕
        {
            _exitBattleButtonDelay++;
            if (_exitBattleButtonDelay >= Game.FRAME_RATE * 4 / 3)
            {
                _losingCover.Run();
                if (_losingCover.IsFadeInFinished())
                {
                    _exitBattleButton.setVisible(true);
                }
            }
        }
    }

    private void Translation(int shiftedX, int shiftedY)
    {
        _background.Translation(shiftedX, shiftedY);
        _battleModel.Translation(shiftedX, shiftedY);
        _allyNexusHealth.Translation(shiftedX, shiftedY);
        _enemyNexusHealth.Translation(shiftedX, shiftedY);
    }

    @Override
    public void show()
    {
        _background.Show();
        _battleModel.Show();
        _capooButton.Show();
        _pusheenButton.Show();
        _rabbitButton.Show();
        _birdButton.Show();
        _capooCooldown.Show();
        _pusheenCooldown.Show();
        _rabbitCooldown.Show();
        _birdCooldown.Show();
        _allyNexusHealth.Show();
        _enemyNexusHealth.Show();
        _moneyAddButton.Show();
        _losingCover.Show();
        _winningBanner.show();
        _losingBanner.show();
        _experienceBanner.show();
        _exitBattleButton.show();
        _pauseBanner.show();
        _pauseButton.show();
        _experienceValue.show();
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
        _birdButton.release();
        _capooCooldown.release();
        _pusheenCooldown.release();
        _rabbitCooldown.release();
        _birdCooldown.release();
        _allyNexusHealth.release();
        _enemyNexusHealth.release();
        _moneyAddButton.release();
        _losingCover.release();
        _winningBanner.release();
        _losingBanner.release();
        _experienceBanner.release();
        _exitBattleButton.release();
        _pauseBanner.release();
        _pauseButton.release();
        _experienceValue.release();
        _backgroundMusic = null;
        _winningMusic = null;
        _buyingSound = null;
        _background = null;
        _battleModel = null;
        _capooButton = null;
        _pusheenButton = null;
        _rabbitButton = null;
        _birdButton = null;
        _capooCooldown = null;
        _pusheenCooldown = null;
        _rabbitCooldown = null;
        _birdCooldown = null;
        _allyNexusHealth = null;
        _enemyNexusHealth = null;
        _moneyAddButton = null;
        _losingCover = null;
        _winningBanner = null;
        _losingBanner = null;
        _experienceBanner = null;
        _exitBattleButton = null;
        _pauseBanner = null;
        _pauseButton = null;
        _experienceValue = null;
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
        if (_isPaused)
        {
            return true;
        }
        if (IsPointerOnButton(pointers.get(0), _capooButton))
        {
            _pressedButton = 1;
        }
        else if (IsPointerOnButton(pointers.get(0), _pusheenButton))
        {
            _pressedButton = 2;
        }
        else if (IsPointerOnButton(pointers.get(0), _rabbitButton))
        {
            _pressedButton = 3;
        }
        else if (IsPointerOnButton(pointers.get(0), _birdButton))
        {
            _pressedButton = 4;
        }
        else if (IsPointerOnButton(pointers.get(0), _moneyAddButton))
        {
            _pressedButton = 5;
        }
        else if (IsPointerOnButton(pointers.get(0), _pauseButton))
        {
            _pressedButton = 6;
        }
        else
        {
            _isPressed = true;
            _currentPressedX = pointers.get(0).getX();
            _shiftingModule.HandlePointerPressed(_currentPressedX);
        }
        if (_exitBattleButton.IsVisible())
        {
            if (IsPointerOnButton(pointers.get(0), _exitBattleButton))
            {
                _pressedButton = 7;
            }
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
            Translation(_shiftingModule.GetTempShifting(), 0);
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
        else if (IsPointerOnButton(pointers.get(0), _capooButton) && _pressedButton == 1)
        {
            if (_capooButton.GetIsEnabled())
            {
                _buyingSound.play();
                _capooButton.Push();
                _battleModel.GenerateCapoo();
            }
        }
        else if (IsPointerOnButton(pointers.get(0), _pusheenButton) && _pressedButton == 2)
        {
            if (_pusheenButton.GetIsEnabled())
            {
                _buyingSound.play();
                _pusheenButton.Push();
                _battleModel.GeneratePusheen();
            }
        }
        else if (IsPointerOnButton(pointers.get(0), _rabbitButton) && _pressedButton == 3)
        {
            if (_rabbitButton.GetIsEnabled())
            {
                _buyingSound.play();
                _rabbitButton.Push();
                _battleModel.GenerateRabbit();
            }
        }
        else if (IsPointerOnButton(pointers.get(0), _birdButton) && _pressedButton == 4)
        {
            if (_birdButton.GetIsEnabled())
            {
                _buyingSound.play();
                _birdButton.Push();
                _battleModel.GenerateBird();
            }
        }
        else if (IsPointerOnButton(pointers.get(0), _moneyAddButton) && _pressedButton == 5)
        {
            if (_moneyAddButton.GetIsEnabled())
            {
                _buyingSound.play();
                _moneyAddButton.Push();
                _battleModel.AddMoneyMax();
            }
        }
        else if (IsPointerOnButton(pointers.get(0), _pauseButton) && _pressedButton == 6)
        {
            _isPaused = !_isPaused;
            _pauseBanner.setVisible(_isPaused);
        }
        if (_exitBattleButton.IsVisible())
        {
            if (IsPointerOnButton(pointers.get(0), _exitBattleButton) && _pressedButton == 7)
            {
                changeState(Game.OVER_STATE);
            }
        }
        return false;
    }

    //判斷該指標是否在按鈕上方
    private boolean IsPointerOnButton(Pointer pointer, Button button)
    {
        int pressedX = pointer.getX();
        int pressedY = pointer.getY();
        return (pressedX > button.GetX() && pressedX < button.GetX() + button.GetWidth() &&
                pressedY > button.GetY() && pressedY < button.GetY() + button.GetHeight());
    }

    //判斷該指標是否在按鈕上方
    private boolean IsPointerOnButton(Pointer pointer, MovingBitmap button)
    {
        int pressedX = pointer.getX();
        int pressedY = pointer.getY();
        return (pressedX > button.getX() && pressedX < button.getX() + button.getWidth() &&
                pressedY > button.getY() && pressedY < button.getY() + button.getHeight());
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

    private Audio _backgroundMusic;
    private Audio _winningMusic;
    private Audio _losingMusic;
    private Audio _buyingSound;
    private TranslationBitmap _background;
    private BattleModel _battleModel;
    private CDButton _capooButton;
    private CDButton _pusheenButton;
    private CDButton _rabbitButton;
    private CDButton _birdButton;
    private CooldownBar _capooCooldown;
    private CooldownBar _pusheenCooldown;
    private CooldownBar _rabbitCooldown;
    private CooldownBar _birdCooldown;
    private HealthBar _allyNexusHealth;
    private HealthBar _enemyNexusHealth;
    private BattleLevelButton _moneyAddButton;
    private FadeInBlack _losingCover;
    private MovingBitmap _winningBanner;
    private MovingBitmap _losingBanner;
    private HorizontalTransition _winningBannerTransition;
    private VerticalTransition _losingBannerTransition;
    private MovingBitmap _experienceBanner;
    private MovingBitmap _exitBattleButton;
    private MovingBitmap _pauseBanner;
    private MovingBitmap _pauseButton;
    private Integer _experienceValue;

    private ShiftingModule _shiftingModule;
    private int _currentPressedX;
    private boolean _isPressed;
    private int _pressedButton;
    private boolean _isGameOver;
    private int _exitBattleButtonDelay;
    private boolean _isPaused;
}