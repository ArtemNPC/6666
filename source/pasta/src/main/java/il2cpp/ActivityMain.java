package il2cpp;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActivityMain extends Activity {
    private EditText keyInput;
    private TextView logText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showAuthScreen();
    }

    private void showAuthScreen() {
        FrameLayout root = new FrameLayout(this);
        root.setBackgroundColor(Color.parseColor("#202020"));

        LinearLayout panel = new LinearLayout(this);
        panel.setOrientation(LinearLayout.VERTICAL);
        panel.setPadding(dp(20), dp(20), dp(20), dp(20));
        panel.setGravity(Gravity.CENTER);

        GradientDrawable bg = new GradientDrawable();
        bg.setColor(Color.parseColor("#2D2D2D"));
        bg.setCornerRadius(dp(12));
        bg.setStroke(dp(1), Color.parseColor("#555555"));
        panel.setBackground(bg);

        TextView title = new TextView(this);
        title.setText("Authoization");
        title.setTextColor(Color.WHITE);
        title.setTextSize(18);
        title.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        titleParams.bottomMargin = dp(16);
        panel.addView(title, titleParams);

        TextView label = new TextView(this);
        label.setText("Enter key:");
        label.setTextColor(Color.WHITE);
        label.setTextSize(14);
        panel.addView(label);

        keyInput = new EditText(this);
        keyInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        keyInput.setSingleLine(true);
        keyInput.setTextColor(Color.WHITE);
        keyInput.setHintTextColor(Color.parseColor("#888888"));
        GradientDrawable inputBg = new GradientDrawable();
        inputBg.setColor(Color.parseColor("#1A1A1A"));
        inputBg.setCornerRadius(dp(8));
        inputBg.setStroke(dp(1), Color.parseColor("#555555"));
        keyInput.setBackground(inputBg);
        keyInput.setPadding(dp(16), dp(12), dp(16), dp(12));
        LinearLayout.LayoutParams inputParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        inputParams.bottomMargin = dp(8);
        panel.addView(keyInput, inputParams);

        logText = new TextView(this);
        logText.setText("");
        logText.setTextColor(Color.parseColor("#FF4C4C"));
        logText.setTextSize(12);
        LinearLayout.LayoutParams logParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        logParams.bottomMargin = dp(12);
        panel.addView(logText, logParams);

        Button login = new Button(this);
        login.setText("Login in");
        login.setTextColor(Color.WHITE);
        login.setBackgroundColor(Color.parseColor("#4CAF50"));
        login.setPadding(dp(16), dp(12), dp(16), dp(12));
        panel.addView(login, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        keyInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
                    tryLogin();
                    return true;
                }
                return false;
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryLogin();
            }
        });

        FrameLayout.LayoutParams panelParams = new FrameLayout.LayoutParams(dp(400), dp(300));
        panelParams.gravity = Gravity.CENTER;
        root.addView(panel, panelParams);

        setContentView(root);
    }

    private void tryLogin() {
        String entered = keyInput.getText().toString().trim();
        String correctKey = "PLUTONIUM_FFSBZDHX38";
        if (entered.equals(correctKey)) {
            setContentView(new LinearLayout(this));
            Main.start(this);
        } else {
            logText.setText("Invalid key!");
            keyInput.setText("");
            keyInput.setError("Попробуйте снова");
        }
    }

    private int dp(int value) {
        float density = getResources().getDisplayMetrics().density;
        return (int) (value * density + 0.5f);
    }
}
