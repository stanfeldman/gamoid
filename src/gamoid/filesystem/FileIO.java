package gamoid.filesystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.res.AssetManager;
import android.os.Environment;

public class FileIO 
{
	public FileIO(AssetManager assets)
	{
		this.assets = assets;
		this.extStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
	}
	
	public InputStream readAsset(String fileName) throws IOException
	{
		return assets.open(fileName);
	}
	
	public InputStream readExtFile(String fileName) throws IOException
	{
		return new FileInputStream(fileName);
	}
	
	public OutputStream writeExtFile(String fileName) throws IOException
	{
		return new FileOutputStream(extStoragePath + fileName);
	}
	
	private AssetManager assets;
	private String extStoragePath;
}
