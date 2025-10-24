package il2cpp.typefaces;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import il2cpp.Utils;
import android.widget.ImageView;

public class PageButton extends LinearLayout {
	Context context;
	
	public static interface Callback {
		public void onClick();
	}
	public Callback callback;
	View __isopen;
	
	


	public void show() {
		__isopen.setVisibility(View.VISIBLE);
	}
	
	public void hide() {
		__isopen.setVisibility(View.GONE);
	}
	
	public void anim() {
		Utils.anim(this, 400);
	}
	
	public PageButton(Context context, String __text, String __src) {
		super(context);
		this.context = context;
		
		
		
				{
					this.setOrientation(1);
					this.setPadding(5, 5, 5, 5);
					this.setGravity(51);
					
					GradientDrawable design = new GradientDrawable();
					design.setColor(0);
					design.setCornerRadii(new float[] { 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f });
					design.setStroke(1, -16777216);
					this.setBackgroundDrawable(design);
					
					LayoutParams lp = new LayoutParams(-2, -2, 0);
					lp.leftMargin   = 0;
					lp.topMargin    = 0;
					lp.rightMargin  = 0;
					lp.bottomMargin = 0;
					this.setLayoutParams(lp);
				}
LinearLayout _isopen = new LinearLayout(context);

TextView _pagetitle = new TextView(context);

ImageView _pagesrc = new ImageView(context);

		
		__isopen = _isopen;
		
		this.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				anim();
				if (callback != null) callback.onClick();
			}
		});
		_pagetitle.setText(__text);
		Utils.SetAssets(context, _pagesrc, __src);
	}
}
