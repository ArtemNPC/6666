package il2cpp.typefaces;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import il2cpp.Utils;
import il2cpp.typefaces.Menu;
import android.widget.ImageView;

public class CheckBox extends LinearLayout {
	Context context;

	public LinearLayout checkbox;
	public ImageView check;
	public TextView title;
	public LinearLayout expand;

	public boolean isChecked = false;
	public Callback callback;
	public int colorMain = 0;

	public static interface Callback {
		public void onChanged(boolean checked);
	}

	public void setChecked(boolean isch) {
		isChecked = isch;
		if (callback != null) callback.onChanged(isChecked);

		if (isChecked) {
			check.setVisibility(View.VISIBLE);
			Utils.anim(checkbox, 0);
		} else {
			check.setVisibility(View.GONE);
		}
	}

	public void setCallback(Callback call) {
		callback = call;
	}

	public void setText(String t) {
		title.setText(t);
	}

	public void setColor(int c) {
		GradientDrawable grad = new GradientDrawable();
		grad.setColor(c);
		grad.setCornerRadius(0f);
		grad.setStroke(2, Color.BLACK);

		checkbox.setBackgroundDrawable(grad);
	}

	public CheckBox(Context ctx) {
		super(ctx);
		context = ctx;

		colorMain = Color.parseColor("#483D8B");

		setOrientation(LinearLayout.HORIZONTAL);

		checkbox = new LinearLayout(context);
		{ // CheckBox create
			setColor(colorMain);

     		check = new ImageView(context);
			Utils.SetAssets(context, check, "check.png");
			check.setColorFilter(Color.CYAN);
			check.setPadding(10,10,10,10);
			checkbox.addView(check, -1, -1);
		}

		expand = new LinearLayout(context);
		{ // Expand line
			expand.setPadding(10, 10, 10, 10);
			expand.setGravity(Gravity.CENTER);

			expand.addView(checkbox, -1, -1);
		}

		title = new TextView(context);
		{ // Checkbox text
			title.setTextSize(12.5f);
			title.setTypeface(Utils.font(context));
			title.setTextColor(Color.WHITE);
			title.setGravity(Gravity.CENTER_VERTICAL);
			title.setPadding(10,0,0,0);
		}

		OnClickListener clck;
		{ // Click lsitener
			clck = new OnClickListener() {
				public void onClick(View v) {
					setChecked(!isChecked);
				}
			};
			checkbox.setOnClickListener(clck);
			title.setOnClickListener(clck);
			setOnClickListener(clck);
		}

		addView(expand, Utils.dp(context, 28), Utils.dp(context, 28));
		addView(title, -1, -1);
		setLayoutParams(new LinearLayout.LayoutParams(-1, Utils.dp(context, 29)));

		setChecked(false);
	}
}


