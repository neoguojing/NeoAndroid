package com.neo.neoandroidlib;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

public class FileUtils {
	
	private static  String Tag = "FileUtils";
	
	public static String getSysPathPath(){
		return Environment.getRootDirectory().getAbsolutePath()+"/";//system
	}
	
	public static String getDataPath(){
		return Environment.getDataDirectory().getAbsolutePath()+"/";///data
	}
	
	public static String getDldCachePath()
	{
		return Environment.getDownloadCacheDirectory().getAbsolutePath()+"/";//cache
	}
	
	public static String getSDPath()
	{
		return Environment.getExternalStorageDirectory().getAbsolutePath()+"/";//sdcard
	}
	
	public static String getSDPicPath(Context context)
	{
		return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath()+"/";
	}
	
	public static String getAppDataPath(Context context)
	{
		String path = null;
		try{
			path = context.getExternalFilesDir(null).getAbsolutePath()+"/";
		}catch (Exception e) {
			Log.e(Tag, e.toString());
			path = null;
		}
		return path;
	}
	
	public static String getAppCachePath(Context context)
	{
		return context.getExternalCacheDir().getAbsolutePath()+"/";
	}
	
	public static boolean isSdcardExist() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			return true;
		}
		return false;
	}

	public static void createDirFile(String path) {
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

	public static File createNewFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				return null;
			}
		}
		return file;
	}
	
	public static void overrideContent(String fileName, String content) {  
        try {  
        	if (fileName==null||content==null)
        		return;
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件  
            FileWriter writer = new FileWriter(fileName, false);  
            writer.write(content);  
            writer.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
	
	public static void appendToEnd(String fileName, String content) {  
        try {  
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件  
            FileWriter writer = new FileWriter(fileName, true);  
            writer.write(content);  
            writer.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
	
	public static void delFolder(String folderPath) {
		delAllFile(folderPath);
		String filePath = folderPath;
		filePath = filePath.toString();
		java.io.File myFilePath = new java.io.File(filePath);
		myFilePath.delete();
	}

	public static void delAllFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		if (!file.isDirectory()) {
			return;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);
				delFolder(path + "/" + tempList[i]);
			}
		}
	}

	public static Uri getUriFromFile(String path) {
		File file = new File(path);
		return Uri.fromFile(file);
	}

	public static String formatFileSize(long size) {
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "未知大小";
		if (size < 1024) {
			fileSizeString = df.format((double) size) + "B";
		} else if (size < 1048576) {
			fileSizeString = df.format((double) size / 1024) + "K";
		} else if (size < 1073741824) {
			fileSizeString = df.format((double) size / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) size / 1073741824) + "G";
		}
		return fileSizeString;
	}
	
	public static boolean isFileExist(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return false;
		}
		return true;
	}
}
