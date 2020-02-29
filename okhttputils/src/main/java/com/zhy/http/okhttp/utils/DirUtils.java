package com.zhy.http.okhttp.utils;
import android.os.Environment;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DirUtils {
	private static String HOME_PATH = "/vrpalyer";
	private static String BasePath = Environment.getExternalStorageDirectory()
			.getAbsolutePath();// SD卡目录
	private static final String SD_ROOTPATH = Environment
			.getExternalStorageDirectory().getAbsolutePath() + File.separator;
	private static final String HOME_PATH_FOR_HIDE = HOME_PATH + File.separator+ (new File(SD_ROOTPATH + File.separator+ "kuwo.zhp").exists() ? "" : ".");
	private static String SdPath = null;
	public static final int 
		DIR_ROOT = 0,
		VR_HOME = 1,
		VR_DOWNLOAD = 2,
		VR_CACHE = 3,
	      VR_MEDIA = 4,
			VR_CRASH = 8,
		EXTERNAL_SD_PATH = 9,
		SETTING = 10,
		LOG = 11,
		OFFLINE_LOG = 12,
		SKIN = 13,
		TEMP = 14,
		LIBS = 15,
		WEB_CONTENT = 16,
		PICTURE = 17,
		WELCOME = 18,
		EXT_ROOT = 19;
		

	private static File VOLD_FSTAB = new File(Environment.getRootDirectory()
			.getAbsoluteFile()
			+ File.separator
			+ "etc"
			+ File.separator
			+ "vold.fstab");

	private static ArrayList<String> cache = new ArrayList<String>();

	private static void initVoldFstabToCache() throws IOException {

		cache.clear();
		BufferedReader br = new BufferedReader(new FileReader(VOLD_FSTAB));
		String tmp = null;
		while ((tmp = br.readLine()) != null) {
			// the words startsWith "dev_mount" are the SD info
			if (tmp.startsWith("dev_mount")) {
				cache.add(tmp);
			}
		}
		br.close();
		cache.trimToSize();
	}

	//获取的目录后面自动带了"/",不需要再额外添加
	public static String getDirectory(int dirID) {
		String dirPath = "";
		switch (dirID) {
		case DIR_ROOT:
			dirPath = BasePath;
			break;
		case VR_HOME:
			dirPath = BasePath + HOME_PATH;
			break;
		case VR_CACHE:
			dirPath = BasePath + HOME_PATH + "/Cache";
			break;
		case VR_MEDIA:
			dirPath = BasePath + HOME_PATH + "/media";
			break;
		case WEB_CONTENT:
			dirPath = BasePath + HOME_PATH + "/web";
			break;
		case PICTURE:
			dirPath = BasePath + HOME_PATH + "/picture";
			break;
		case WELCOME:
			dirPath = BasePath + HOME_PATH + "/welcome";
			break;
		case VR_CRASH:
			dirPath = BasePath + HOME_PATH + "/crash";
			break;
		case EXTERNAL_SD_PATH: {
			if (SdPath == null) {
				SdPath = "";
				try {
					initVoldFstabToCache();
				} catch (IOException e) {
					e.printStackTrace();
					return "";
				}
				if (cache.size() > 1) {
					String[] sinfo = cache.get(1).split("\t");
					if (sinfo.length > 2)
						SdPath = sinfo[2];
				}
			}
			dirPath = SdPath;
			return dirPath;
		}
		case SETTING:
			dirPath = BasePath + HOME_PATH + "/setting";
			break;
		case LOG:
			dirPath = BasePath + HOME_PATH + "/log";
			break;
		case OFFLINE_LOG:
			dirPath = BasePath + HOME_PATH + "/offlinelog";
			break;
		case SKIN:
			dirPath =  BasePath + HOME_PATH + "/skin";
			break;
		case TEMP:
			dirPath = BasePath + HOME_PATH + "/temp";
			break;
		case LIBS:
			dirPath = BasePath + HOME_PATH + "/libs";
			break;
		case EXT_ROOT:
			ArrayList<String> cache = null;
			try {
				cache = initVoldFstabToCache2();
			} catch (IOException e) {
				e.printStackTrace();
				return "";
			}
			if (cache != null && cache.size() > 1) {
				cache.trimToSize();
				for(String paths : cache){
					String[] sinfo = paths.split(" |\t");
					if (sinfo.length > 2) {
						if("sdcard".equals(sinfo[1].toLowerCase())){
							String temp = sinfo[2] + File.separator;
							if(!(temp.toLowerCase()).equals(SD_ROOTPATH.toLowerCase()))
								dirPath = temp;
							break;
						}

					}
				}

			}
			break;
		}

		//目录后面增加"/"
		if (!TextUtils.isEmpty(dirPath) && !dirPath.endsWith(File.separator)) {
			dirPath += File.separator;
		}

		File dir = new File(dirPath);
		if (!dir.exists()) {
			boolean b = dir.mkdirs();
		}
		return dirPath;
	}
	private static ArrayList<String> initVoldFstabToCache2() throws IOException {
		File voldFstab = new File(Environment.getRootDirectory()
				.getAbsoluteFile()
				+ File.separator
				+ "etc"
				+ File.separator
				+ "vold.fstab");
		BufferedReader br = new BufferedReader(new FileReader(voldFstab));

		ArrayList<String> cache = new ArrayList<String>();
		String tmp = null;
		try {
			while ((tmp = br.readLine()) != null) {
				if (tmp.trim().startsWith("dev_mount")) {
					cache.add(tmp.trim());
				}
			}
		} finally {
			br.close();
		}

		return cache;
	}

	public static boolean deleteFiles(int dirType){
		File dir = new File(DirUtils.getDirectory(dirType));
		return  deleteFiles(dir);
	}

	public static boolean deleteFiles(File dir){
		if(dir == null || dir.isFile() || !dir.exists())
			return false;
		File[] picFiles = dir.listFiles();
		if (picFiles == null || picFiles.length == 0) {
			return false;
		}
		int len = picFiles.length;
		for (int i = 0; i < len; i++) {
			picFiles[i].delete();
		}
		return true;
	}
}
