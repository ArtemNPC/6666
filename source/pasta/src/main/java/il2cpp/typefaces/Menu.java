package il2cpp.typefaces;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.os.*;
import android.text.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import android.widget.LinearLayout.LayoutParams;
import java.util.ArrayList;

public class Menu
{
    protected Context context;
    protected FrameLayout _parentBox;
    protected LinearLayout __page;
    protected ScrollView __scroll;
    
    public ArrayList<LinearLayout> __pages = new ArrayList<>();
    
    public ImageView _icon;
    
    boolean _isShow = false;
    
    LinearLayout menulayout, linear7, _scroll;
    TextView textview12;
    ImageView _close;

    protected WindowManager _wmManager;
    protected WindowManager.LayoutParams _wmParams;
    
    // Флаг показа
    private boolean _isAuthenticated = true; // авторизация перенесена в ActivityMain
    private boolean _isAttachedToWindow = false;
    
    protected void init(Context context) {
        this.context = context;
        
        _parentBox = new FrameLayout(context);
        _parentBox.setOnTouchListener(handleMotionTouch);
        _wmManager = ((Activity)context).getWindowManager();
        
        int aditionalFlags = 0;
        if (Build.VERSION.SDK_INT >= 11)
            aditionalFlags = WindowManager.LayoutParams.FLAG_SPLIT_TOUCH;
        if (Build.VERSION.SDK_INT >= 3)
            aditionalFlags = aditionalFlags | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM;
            
        _wmParams = new WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            0, 0,
            WindowManager.LayoutParams.TYPE_APPLICATION,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_OVERSCAN |
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN |
            aditionalFlags,
            PixelFormat.TRANSPARENT
        );
        _wmParams.gravity = Gravity.CENTER;
    }
    
    public int dpi(float dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
    
    public void showMenu() {
        if (!_isAuthenticated || menulayout == null) return;
        
        _close.setRotation(0);
        _isShow = false;
        _parentBox.removeAllViews();
        _parentBox.addView(menulayout, 600, 600);
    }

    public void hideMenu() {
        if (!_isAuthenticated || menulayout == null) return;
        
        _close.setRotation(-90);
        _isShow = true;
        _parentBox.removeAllViews();
        _parentBox.addView(menulayout, 600, dpi(25));
    }
    
    public Menu(Context context) {
        init(context);
        createMainMenu();
    }
    
    // Метод создания основного меню
    private void createMainMenu() {
        // Создаем главный контейнер меню
        menulayout = new LinearLayout(context);
        menulayout.setOrientation(LinearLayout.VERTICAL);
        menulayout.setBackgroundColor(0xAA000000);

        // После авторизации возвращаем прежние флаги окна (без фокуса)
        int additional = 0;
        if (Build.VERSION.SDK_INT >= 11) additional = WindowManager.LayoutParams.FLAG_SPLIT_TOUCH;
        if (Build.VERSION.SDK_INT >= 3) additional |= WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM;
        _wmParams.flags =
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_OVERSCAN |
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN |
            additional;
        if (_isAttachedToWindow) {
            _wmManager.updateViewLayout(_parentBox, _wmParams);
        }
        
        // Header
        linear7 = new LinearLayout(context);
        linear7.setOrientation(LinearLayout.HORIZONTAL);
        linear7.setBackgroundColor(0xFF2D2D2D);
        linear7.setPadding(10, 10, 10, 10);
        
        // Кнопка закрытия
        _close = new ImageView(context);
        _close.setImageResource(android.R.drawable.ic_menu_close_clear_cancel);
        _close.setColorFilter(0xFFFFFFFF);
        _close.setLayoutParams(new LayoutParams(dpi(30), dpi(30)));
        
        // Заголовок
        textview12 = new TextView(context);
        textview12.setText("Mod Menu");
        textview12.setTextColor(0xFFFFFFFF);
        textview12.setTextSize(16);
        textview12.setPadding(10, 0, 0, 0);
        textview12.setGravity(Gravity.CENTER_VERTICAL);
        
        linear7.addView(_close);
        linear7.addView(textview12);
        
        // Основная область контента
        _scroll = new LinearLayout(context);
        _scroll.setOrientation(LinearLayout.VERTICAL);
        _scroll.setPadding(10, 10, 10, 10);
        _scroll.setBackgroundColor(0xAA1A1A1A);
        
        // Создаем скролл и страницу
        __scroll = new ScrollView(context);
        __page = new LinearLayout(context);
        __page.setOrientation(LinearLayout.VERTICAL);
        
        // Добавляем тестовые функции
        addFeatureToMenu("Radar Hack", true);
        addFeatureToMenu("FOV Changer", false);
        addFeatureToMenu("Aimbot", true);
        addFeatureToMenu("No Recoil", true);
        addFeatureToMenu("Speed Hack", false);
        
        __scroll.addView(__page);
        _scroll.addView(__scroll);
        
        // Собираем меню
        menulayout.addView(linear7, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        menulayout.addView(_scroll, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        
        // Обработчик клика
        _close.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (_isShow) {
                    showMenu();
                } else {
                    hideMenu();
                }
            }
        });
        
        // Показываем меню (контейнер уже прикреплён на экране авторизации)
        showMenu();
    }
    
    // Метод для добавления функций в меню
    private void addFeatureToMenu(String featureName, boolean isSwitch) {
        LinearLayout featureLayout = new LinearLayout(context);
        featureLayout.setOrientation(LinearLayout.HORIZONTAL);
        featureLayout.setPadding(0, 10, 0, 10);
        featureLayout.setGravity(Gravity.CENTER_VERTICAL);
        
        // Текст функции
        TextView featureText = new TextView(context);
        featureText.setText(featureName);
        featureText.setTextColor(0xFFFFFFFF);
        featureText.setTextSize(14);
        featureText.setLayoutParams(new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1));
        
        featureLayout.addView(featureText);
        
        if (isSwitch) {
            Switch featureSwitch = new Switch(context);
            featureLayout.addView(featureSwitch);
        } else {
            SeekBar featureSlider = new SeekBar(context);
            featureSlider.setMax(100);
            featureSlider.setProgress(50);
            featureSlider.setLayoutParams(new LayoutParams(dpi(100), LayoutParams.WRAP_CONTENT));
            featureLayout.addView(featureSlider);
        }
        
        __page.addView(featureLayout);
    }
    
    View.OnTouchListener handleMotionTouch = new View.OnTouchListener() {
        private float initX, initY, touchX, touchY;
        private long clock = 0;
        
        @Override
        public boolean onTouch(View vw, MotionEvent ev) {
            if (!_isAuthenticated) return false;

            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    initX = _wmParams.x;
                    initY = _wmParams.y;
                    touchX = ev.getRawX();
                    touchY = ev.getRawY();
                    clock = System.currentTimeMillis();
                    break;

                case MotionEvent.ACTION_MOVE:
                    _wmParams.x = (int)(initX + (ev.getRawX() - touchX));
                    _wmParams.y = (int)(initY + (ev.getRawY() - touchY));
                    _wmManager.updateViewLayout(vw, _wmParams);
                    break;

                case MotionEvent.ACTION_UP:
                    if (!_isShow && (System.currentTimeMillis() < (clock + 200))) {
                        showMenu();
                    }
                    break;
            }
            return true;
        }
    };
}