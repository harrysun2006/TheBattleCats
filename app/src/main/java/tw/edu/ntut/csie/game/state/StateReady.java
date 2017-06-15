package tw.edu.ntut.csie.game.state;

import java.util.Map;

import tw.edu.ntut.csie.game.Game;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.Audio;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.engine.GameEngine;
import tw.edu.ntut.csie.game.extend.BitmapButton;
import tw.edu.ntut.csie.game.extend.ButtonEventHandler;

public class StateReady extends AbstractGameState {

    private MovingBitmap _helpInfo;
    private MovingBitmap _aboutInfo;
    private MovingBitmap _background;
    private MovingBitmap _cheatInfo;

    private BitmapButton _startButton;
    private BitmapButton _helpButton;
    private BitmapButton _aboutButton;
    private BitmapButton _exitButton;
    private BitmapButton _menuButton;
    private BitmapButton _shopButton;
    private BitmapButton _cheatButton;

    private Audio _music;

    private boolean _showHelp;
    private boolean _showAbout;

    public StateReady(GameEngine engine) {
        super(engine);
    }

    @Override
    public void initialize(Map<String, Object> data) {
        addGameObject(_helpInfo = new MovingBitmap(R.drawable.new_help_info));
        addGameObject(_background = new MovingBitmap(R.drawable.new_state_ready));
        addGameObject(_aboutInfo = new MovingBitmap(R.drawable.new_about_info));
        addGameObject(_cheatInfo = new MovingBitmap(R.drawable.cheat_info));
        initializeStartButton();
        initializeHelpButton();
        initializeAboutButton();
        InitializeShopButton();
        initializeExitButton();
        initializeMenuButton();
        InitializeCheatButton();
        setVisibility(false, false);
        _cheatInfo.setVisible(false);
        _cheatButton.setVisible(false);
        addReleasableResource(_music = new Audio(R.raw.home));
        _music.setRepeating(true);
        _music.play();
    }

    /**
     * ��l�ơyStart�z�����s�C
     */
    private void initializeStartButton() {
        addGameObject(_startButton = new BitmapButton(R.drawable.start, R.drawable.start_pressed, 465, 120));
        _startButton.addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                changeState(Game.MAP_STATE);
            }
        });
        addPointerEventHandler(_startButton);
    }

    /**
     * ��l�ơyHelp�z�����s�C
     */
    // �C������
    private void initializeHelpButton() {
        addGameObject(_helpButton = new BitmapButton(R.drawable.help, R.drawable.help_pressed, 465, 170));
        _helpButton.addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                setVisibility(true, false);
                _cheatButton.setVisible(true);
            }
        });
        addPointerEventHandler(_helpButton);
    }

    /**
     * ��l�ơyAbout�z�����s�C
     */
    // �}�o²��
    private void initializeAboutButton() {
        addGameObject(_aboutButton = new BitmapButton(R.drawable.about, R.drawable.about_pressed, 465, 220));
        _aboutButton.addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                setVisibility(false, true);
            }
        });
        addPointerEventHandler(_aboutButton);
    }

    private void InitializeShopButton()
    {
        addGameObject(_shopButton = new BitmapButton(R.drawable.shop, R.drawable.shop_pressed, 465, 270));
        _shopButton.addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                changeState(Game.SHOP_STATE);
            }
        });
        addPointerEventHandler(_shopButton);
    }

    /**
     * ��l�ơyExit�z�����s�C
     */
    private void initializeExitButton() {
        addGameObject(_exitButton = new BitmapButton(R.drawable.exit, R.drawable.exit_pressed, 465, 320));
        _exitButton.addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                _engine.exit();
            }
        });
        addPointerEventHandler(_exitButton);
    }

    /**
     * ��l�ơyMenu�z�����s�C
     */
    private void initializeMenuButton() {
        addGameObject(_menuButton = new BitmapButton(R.drawable.menu, R.drawable.menu_pressed, 465, 320));
        _menuButton.addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                setVisibility(false, false);
                _cheatInfo.setVisible(false);
                _cheatButton.setVisible(false);
            }
        });
        addPointerEventHandler(_menuButton);
    }

    private void InitializeCheatButton()
    {
        addGameObject(_cheatButton = new BitmapButton(R.drawable.cheat_button, R.drawable.cheat_button_pressed, 465, 270));
        _cheatButton.addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                _helpInfo.setVisible(false);
                _cheatInfo.setVisible(true);
                _cheatButton.setVisible(false);
            }
        });
        addPointerEventHandler(_cheatButton);
    }

    @Override
    public void pause() {
        _music.pause();
    }

    @Override
    public void resume() {
        _music.resume();
    }

    /**
     * �]�w�e���W���ǹϤ�����ܡA���ǹϤ������áC
     *
     * @param showHelp  ���Help�e��
     * @param showAbout ���About�e��
     */
    private void setVisibility(boolean showHelp, boolean showAbout) {
        _showHelp = showHelp;
        _showAbout = showAbout;
        boolean showMenu = !_showAbout && !_showHelp;
        _helpInfo.setVisible(_showHelp);
        _aboutInfo.setVisible(_showAbout);
        _background.setVisible(showMenu);

        _startButton.setVisible(showMenu);
        _helpButton.setVisible(showMenu);
        _aboutButton.setVisible(showMenu);
        _shopButton.setVisible(showMenu);
        _exitButton.setVisible(showMenu);
        _menuButton.setVisible(!showMenu);
    }
}
