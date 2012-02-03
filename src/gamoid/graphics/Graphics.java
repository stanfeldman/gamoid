package gamoid.graphics;

import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Typeface;

public class Graphics
{
	public Graphics(AssetManager assets, Bitmap frameBuffer)
	{
		this.assets = assets;
		this.frameBuffer = frameBuffer;
		this.canvas = new Canvas(frameBuffer);
	}
	
	public Bitmap newImage(String fileName)
	{ 
		InputStream is = null;
		Bitmap bitmap = null;
		try
		{
			is = assets.open(fileName);
			bitmap = BitmapFactory.decodeStream(is);
			if(bitmap == null)
				throw new RuntimeException("Couldn't load bitmap from asset");
			return bitmap;
		}
		catch(IOException ex) { throw new RuntimeException("Couldn't load bitmap from asset"); }
		finally
		{
			if(is != null)
				try
				{
					is.close();
				}
				catch(IOException ex) {}
		}
	}
	
	public void drawPixel(int x, int y, int color)
	{
		paint.setColor(color);
		canvas.drawPoint(x, y, paint);
	}
	
	public void drawLine(int x1, int y1, int x2, int y2, int color)
	{
		paint.setColor(color);
		canvas.drawLine(x1, y1, x2, y2, paint);
	}
	
	public void drawRect(int x, int y, int width, int height, int color)
	{
		paint.setColor(color);
		paint.setStyle(Style.FILL);
		canvas.drawRect(x, y, x + width	- 1, y + height - 1, paint);
	}
	
	public void drawText(String text, int x, int y, int size, String typefacePath)
	{
		Paint paint = new Paint();
		paint.setTextSize(size);
		Typeface typeface = Typeface.createFromAsset(assets, typefacePath);
		paint.setTypeface(typeface);
		paint.setAntiAlias(true);
		canvas.drawText(text, x, y, paint);
	}
	
	public void drawImage(Bitmap bitmap, int x, int y, int srcX, int srcY, int srcWidth, int srcHeight)
	{
		srcRect.left = srcX;
		srcRect.top = srcY;
		srcRect.right = srcX + srcWidth - 1;
		srcRect.bottom = srcY + srcHeight - 1;
		dstRect.left = x;
		dstRect.top = y;
		dstRect.right = x + srcWidth -1;
		dstRect.bottom = y + srcHeight - 1;
		canvas.drawBitmap(bitmap, srcRect, dstRect, null);
	}
	
	public void drawImage(Bitmap bitmap, int x, int y)
	{
		canvas.drawBitmap(bitmap, x, y, null);
	}
	
	public void clear(int color)
	{
		canvas.drawRGB((color & 0xff0000) >> 16, (color & 0xff00) >> 8, (color & 0xff));
	}
	
	public int getWidth()
	{ 
		return frameBuffer.getWidth(); 
	}
	
	public int getHeight()
	{ 
		return frameBuffer.getHeight(); 
	}
	
	private Bitmap frameBuffer;
	private AssetManager assets;
	private Canvas canvas;
	private Paint paint = new Paint();
	private Rect srcRect = new Rect();
	private Rect dstRect = new Rect();
}
