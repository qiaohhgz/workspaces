package com.db.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

import com.google.gson.Gson;
import com.util.DB;
import com.util.FileHelper;

public abstract class BaseDao {
	public static final String FILE_PATH = "db.data";
	public static final String JSON_PATH = "db.json";
	public static final Random r = new Random();
	public static final Gson gson = new Gson();
	public static final boolean useJosnData = true;
	
	public Object execute(CallBack callBack) {
		DB db = getDB();
		Object back = callBack.execute(db);
		setDB(db);
		return back;
	}

	public static DB getDB() {
		if (useJosnData) {
			if (hasFileExists(JSON_PATH)) {
				return readGsonData(JSON_PATH, DB.class);
			}
		} else {
			if (hasFileExists(FILE_PATH)) {
				return (DB) readObject(FILE_PATH);
			}
		}
		return new DB();
	}

	private void setDB(DB db) {
		if (useJosnData) {
			writeGsonData(JSON_PATH, db);
		} else {
			writeObject(FILE_PATH, db);
		}
	}

	public static Object readObject(String path) {
		try {
			if (!hasFileExists(path)) {
				return null;
			}
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(path)));
			Object obj = ois.readObject();
			ois.close();
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void writeObject(String path, Object obj) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(path)));
			oos.writeObject(obj);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static <T> T readGsonData(String path, Class<T> clazz) {
		if (hasFileExists(path)) {
			return gson.fromJson(FileHelper.readString(path), clazz);
		}
		return null;
	}

	public static void writeGsonData(String path, Object obj) {
		try {
			String data = gson.toJson(obj);
			FileHelper.write(new File(path), data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean hasFileExists(String path) {
		return new File(path).exists();
	}

	public interface CallBack {
		Object execute(DB db);
	}
}
