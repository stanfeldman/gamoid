package gamoid.game.models;

import java.util.HashMap;
import java.util.Map;

import gamoid.filesystem.FileIO;
import gamoid.game.views.RenderView;
import gamoid.game.views.Screen;
import gamoid.graphics.Graphics;
import gamoid.input.MultiTouchInput;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;

public abstract class Game extends Activity 
{
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
		int frameBufferWidth = isLandscape ? 480 : 320;
		int frameBufferHeight = isLandscape ? 320 : 480;
		frameBuffer = Bitmap.createBitmap(frameBufferWidth, frameBufferHeight, Config.RGB_565);
		renderView = new RenderView(this, frameBuffer);
		assets = getAssets();
		graphics = new Graphics(assets, frameBuffer);
		fileIO = new FileIO(assets);
		float scaleX = (float)frameBufferWidth / getWindowManager().getDefaultDisplay().getWidth();
		float scaleY = (float)frameBufferHeight / getWindowManager().getDefaultDisplay().getHeight();
		multiTouchInput = new MultiTouchInput(renderView, scaleX, scaleY);
		screen = getStartScreen();
		setContentView(renderView);
		PowerManager powerManager = (PowerManager)getSystemService(Context.POWER_SERVICE);
		wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, this.getClass().getName() + " Game");
	}
	
	@Override
	protected void onResume()
	{
		super.onResume();
		wakeLock.acquire();
		screen.resume();
		renderView.resume();
	}

	@Override
	protected void onPause()
	{
		super.onPause();
		wakeLock.release();
		renderView.pause();
		screen.pause();
		if(isFinishing())
			screen.dispose();
	}

	@Override
	public void onBackPressed() 
	{
		if(!screen.back())
			super.onBackPressed();
	}

	public void setScreen(Screen screen)
	{
		if(screen == null)
			throw new IllegalArgumentException("Screen must not be null");
		this.screen.pause();
		this.screen.dispose();
		this.screen = screen;
		this.screen.resume();
		this.screen.update(0);
	}
	
	public abstract Screen getStartScreen();
	
	public Screen getCurrentScreen() { return screen; }
	
	public MultiTouchInput getMultiTouchInput() { return multiTouchInput; }
	
	public Graphics getGraphics() { return graphics; }
	
	public FileIO getFileIO() { return fileIO; }
	
	public Map<String, Object> getAssetsCache() { return assetsCache; }
	
	public boolean isOver() { return isOver; }
	
	public void isOver(boolean over) { isOver = over; }
	
	protected Bitmap frameBuffer;
	protected RenderView renderView;
	protected Graphics graphics;
	protected FileIO fileIO;
	protected MultiTouchInput multiTouchInput;
	protected Screen screen;
	protected WakeLock wakeLock;
	protected AssetManager assets;
	protected Map<String, Object> assetsCache = new HashMap<String, Object>();
	protected boolean isOver = false;
}
