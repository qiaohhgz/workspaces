package util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 
 * use Gson to mapping Json String and Java Object
 * 
 * @author john_zhang
 *
 */
public class JSONUtils {

	private static Gson gson = new Gson();
	
	/**
	 * 
	 * 	Java Object to Json String
	 * 
	 * @param object
	 * @return
	 */
	public static String toJson(Object object) {
		return gson.toJson(object);
	}
	
	/**
	 * 
	 * 	Java Collection to Json String
	 * 
	 * @param collection 
	 * @return
	 */
	public static <T> String toJson(Collection<T> collection) {
		Type type = new TypeToken<Collection<T>>(){}.getType();
		return gson.toJson(collection, type);
	}
	
	/**
	 * Json String to Java Object
	 * 
	 * @param <T>
	 * @param jsonString	
	 * @param clazz
	 * @return
	 */
	public static <T> T fromJson(String jsonString, Class<T> clazz){
		return gson.fromJson(jsonString, clazz);
	}
	
	/**
	 * Json InputStream to Java Object
	 * 
	 * @param <T>
	 * @param jsonInputStream
	 * @param clazz
	 * @return
	 */
	public static <T> T fromJson(InputStream jsonInputStream, Class<T> clazz){
		Reader reader = new InputStreamReader(jsonInputStream);
		return gson.fromJson(reader, clazz);
	}
	
	/**
	 * Json String to Java List
	 * 
	 * @param <T>
	 * @param jsonString
	 * @param type
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> fromJsonToCollection(String jsonString,
			Type type, Class<T> clazz) {
		return gson.fromJson(jsonString, type);
	}
	
	/**
	 * Json InputStream to Java List
	 * 
	 * @param <T>
	 * @param jsonInputStream
	 * @param type
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> fromJsonToCollection(InputStream jsonInputStream,
			Type type, Class<T> clazz) {
		Reader reader = new InputStreamReader(jsonInputStream);
		return gson.fromJson(reader, type);
	}
	
	
}
