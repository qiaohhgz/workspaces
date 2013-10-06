package com.util;

import java.io.File;
import java.io.FileFilter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileHelper {
	public static void main(String[] args) {
		// File file = new File("E:\\t");
		// new FileHelper().searchRepeatMusicFile(file, ".txt");
		// File[] lrcs = f.listFiles(new FileFilter() {
		// public boolean accept(File pathname) {
		// return (pathname.getName().endsWith(".lrc"));
		// }
		// });

		// for (File f : deletes) {
		// System.out.println(f.getPath());
		// }
		
		
		BigDecimal bd = new BigDecimal(100.0123234235);
		DecimalFormat format = new DecimalFormat("0.0");
		System.out.println(format.format(bd.doubleValue()));
	}

	Map<String, File> files = new HashMap<String, File>();
	Map<String, File> lrcs = new HashMap<String, File>();
	List<File> deletes = new ArrayList<File>();

	public void searchRepeatMusicFile(final File file, final String endsWithName) {
		if (file.isDirectory()) {
			File[] music = file.listFiles(new FileFilter() {
				public boolean accept(File pathname) {
					if (pathname.isDirectory()) {
						searchRepeatMusicFile(pathname, endsWithName);
						return false;
					} else if (pathname.isFile() && pathname.getName().endsWith(endsWithName)) {
						return true;
					} else {
						return false;
					}
				}
			});
			for (int i = 0; i < music.length; i++) {
				File f = music[i];
				if (files.containsKey(f.getName())) {
					if (files.get(f.getName()).length() < f.length()) {
						deletes.add(files.get(f.getName()));
						files.put(f.getName(), f);
					} else {
						deletes.add(f);
					}
				} else {
					files.put(f.getName(), f);
				}
			}
		}
	}
}
