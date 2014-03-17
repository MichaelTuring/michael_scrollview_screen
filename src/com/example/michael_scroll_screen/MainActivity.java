package com.example.michael_scroll_screen;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import com.example.michael_scroll_screen.R;

public class MainActivity extends Activity {
    boolean flag = true;
    private ImageView imageView;
    private ImageView imageView1;
    private ImageView imageView2;
    private ScrollView scrollView;
    private Animation set2;
    private Button button;
    private Button button2;
    private Animation animation;
    private Animation animation2;
    private  AnimationSet set;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imageView = (ImageView) findViewById(R.id.imageView1);
		imageView.setVisibility(View.INVISIBLE);
		imageView1 = (ImageView) findViewById(R.id.imageView2);
		scrollView = (ScrollView)findViewById(R.id.scr);
	    button = (Button) findViewById(R.id.button1);
	    button2 = (Button) findViewById(R.id.button2);
	    animation = new TranslateAnimation(0, 0, 0, 400);
	    animation2 = new AlphaAnimation(1.0f, 0);
	    set2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shensuo);
	    set = new AnimationSet(true); 
	    set.addAnimation(animation);
	    set.addAnimation(animation2);
	    
	    set.setDuration(2000);
	    
	    
	    initListener();
	    
	}
    private void initListener() {
		button.setOnClickListener(onClickListener);
		button2.setOnClickListener(onClickListener);
	}
    OnClickListener onClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			switch (arg0.getId()) {
			case R.id.button1:
				//imageView1.setAnimation(set).;
				imageView1.setBackground(getResources().getDrawable(R.drawable.login_up));
				imageView1.startAnimation(set2);
				break;
            case R.id.button2:
            	//BitmapDrawable bd=getBitmapByView(scrollView);  
                //imageView1.setBackgroundDrawable(bd);  
            	Bitmap bitmap = getBitmapByView(scrollView);
            	BitmapDrawable bd= new BitmapDrawable(bitmap);
            	imageView1.setBackground(bd);
				break;
			default:
				break;
			}
		}
	};
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	  /** 
     * 截屏方法 
     * @return 
     */  
	public static Bitmap getBitmapByView(ScrollView scrollView) {
        int h = 0;
        Bitmap bitmap = null;
        // 获取listView实际高度
        for (int i = 0; i < scrollView.getChildCount(); i++) {
            h += scrollView.getChildAt(i).getHeight();
            //scrollView.getChildAt(i).setBackgroundResource(R.drawable.bg3);
        }
        // 创建对应大小的bitmap
        bitmap = Bitmap.createBitmap(scrollView.getWidth(), h,
                Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        scrollView.draw(canvas);
        // 测试输出
        FileOutputStream out = null;
        try {
            out = new FileOutputStream("/sdcard/screen_test.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if (null != out) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.flush();
                out.close();
            }
        } catch (IOException e) {
            // TODO: handle exception
        }
        return bitmap;
    }
}
