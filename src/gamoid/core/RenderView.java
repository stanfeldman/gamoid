package gamoid.core;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class RenderView extends SurfaceView implements Runnable 
{
	public RenderView(Game game, Bitmap frameBuffer)
	{
		super(game);
		this.game = game;
		this.frameBuffer = frameBuffer;
		this.holder = getHolder();
	}
	
	public void run() 
	{
		Rect dstRect = new Rect();
		long startTime = System.nanoTime();
		while(isRunning)
		{
			if(!holder.getSurface().isValid())
				continue;
			float deltaTime = (System.nanoTime()-startTime) / 1000000000.0f;
            startTime = System.nanoTime();
			// обновляем состояние
			game.getCurrentScreen().update(deltaTime);
			// перерисовываем экран
			game.getCurrentScreen().draw(deltaTime);
			Canvas canvas = holder.lockCanvas();
			canvas.getClipBounds(dstRect);
			canvas.drawBitmap(frameBuffer, null, dstRect, null);
			holder.unlockCanvasAndPost(canvas);
		}
	}

	public void resume()
	{
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}

	public void pause()
	{
		isRunning = false;
		while(true)
		{
			try
			{
				thread.join();
				break;
			}
			catch(InterruptedException ex) {}
		}
	}
	
	private Game game;
	private Bitmap frameBuffer;
	private SurfaceHolder holder;
	private Thread thread;
	private volatile boolean isRunning = false;
}
