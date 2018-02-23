package com.andyren.rjlocker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * @author Andy.Ren
 */
public class ScreenSaverView extends RelativeLayout {

	private View rootView;
	private Button btnUnlock;
	
	public ScreenSaverView(final Context context) {
		super(context);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		rootView = inflater.inflate(R.layout.screen_saver, this);
		btnUnlock = (Button) rootView.findViewById(R.id.btn_unlock_screen);

		btnUnlock.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(context, MyService.class);
				i.setAction(MyService.UNLOCK_ACTION);
				context.startService(i);
			}
		});
	}
}
