package com.zhongchou.run;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.jfinal.core.JFinal;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.StrKit;
import com.oreilly.servlet.multipart.FileRenamePolicy;

public class UpFileRenamePolicy implements FileRenamePolicy {

	public static String relative_directory = null;

	@Override
	public File rename(File f) {
		// 获取webRoot目录
		String webRoot = PathKit.getWebRootPath();
		// 用户设置的默认上传目录
		String saveDir = JFinal.me().getConstants()
				.getUploadedFileSaveDirectory();
		// 添加时间作为目录
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		// /xxx/JFinal2.0-beetl-training/upload/20150710/1436542837568.txt
		relative_directory = File.separator
				+ (StrKit.isBlank(saveDir) ? "upload" : saveDir)
				+ File.separator + dateFormat.format(new Date())
				+ File.separator + System.currentTimeMillis()
				+ getFileExt(f.getName());
		StringBuilder newFileName = new StringBuilder(webRoot)
				.append(relative_directory);

		File dest = new File(newFileName.toString());
		// 创建上层目录
		File dir = dest.getParentFile();
		if (!dir.exists()) {
			dir.mkdirs();
		}

		f.renameTo(dest);
		return dest;
	}

	/**
	 * 获取文件后缀
	 * 
	 * @param @param fileName
	 * @param @return 设定文件
	 * @return String 返回类型
	 */
	public static String getFileExt(String fileName) {
		return fileName.substring(fileName.lastIndexOf('.'), fileName.length());
	}
}