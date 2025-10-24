package il2cpp.typefaces;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import il2cpp.Utils;

// 1 line
public class Slider extends LinearLayout {
	Context context;

	public SeekBar slider;
	public RelativeLayout line;
	public TextView title, title2;

	public int value;
	public Callback callback;

	public void setValue(int v) {
		value = v;
		slider.setProgress(v);
		title.setText(Integer.toString(v));
	}

	public Slider(Context ctx, String txt, int max, int current) {
		super(ctx);
		this.context = ctx;

		line = new RelativeLayout(context);
		line.setLayoutParams(new RelativeLayout.LayoutParams(-1, Utils.dp(context, 35)));
		line.setPadding(5,-5,5,5);
		line.setGravity(Gravity.CENTER);


		slider = new SeekBar(context);
		slider.setPadding(10,-10,10,0);
		GradientDrawable thumb = new GradientDrawable();
		thumb.setColor(Color.parseColor("#4682B4"));
		thumb.setSize(Utils.dp(context, 5), Utils.dp(context, 16));
		thumb.setCornerRadius(0);
		thumb.setPadding(20, -5, 20, 0);

		thumb.setTintMode(PorterDuff.Mode.MULTIPLY);

		slider.setThumb(thumb);

		GradientDrawable btn = new GradientDrawable();
		btn.setSize(Utils.dp(context, 15), Utils.dp(context, 15));

		btn.setCornerRadius(0f);
		btn.setStroke(2, Color.parseColor("#483D8B"));
		slider.setPadding(5,-5,5,0);

		slider.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

				public void onProgressChanged(final SeekBar s, int val, boolean c) {
					setValue(val);

					if (callback != null) callback.onChange(value);
				}

				public void onStopTrackingTouch(SeekBar b) {}

				public void onStartTrackingTouch(SeekBar b) {}
			});

		btn.setTintMode(PorterDuff.Mode.CLEAR);

		btn.setCornerRadius(0);

		slider.setProgressDrawable(btn);

		title = new TextView(context);
		title.setText(Integer.toString(current));
		title.setTextColor(Color.WHITE);
		title.setTextSize(10f);
		title.setTypeface(Utils.font(context));
		title.setGravity(Gravity.CENTER);

		title2 = new TextView(context);
		title2.setText(txt);
		title2.setTextColor(Color.WHITE);
		title2.setTextSize(14.5f);
		title2.setTypeface(Utils.font(context));
		title2.setGravity(Gravity.CENTER_VERTICAL);

		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
			-1, -1);
		params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

		line.addView(slider, params);

		RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(
			-1, -1);
		params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

		line.addView(title, params2);

		setPadding(0,-5,0,0);

		setOrientation(LinearLayout.HORIZONTAL);

		addView(line, Utils.dp(context, 160), 45);
		addView(title2, -1, -1);
		setValue(current);
		slider.setMax(max);

		setLayoutParams(new LinearLayout.LayoutParams(-1, Utils.dp(context, 35)));
	}

	public static interface Callback {
		public void onChange(int v);
	}
}

