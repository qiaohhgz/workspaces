package com.util;

import java.lang.reflect.Method;

public class Convert {
	public static interface KeyConverter<K> {
		public String keyToString(K key);

		public K stringToKey(String string) throws Exception;
	}

	public static class EnumKeyConverter<E extends Enum> implements
			KeyConverter<E> {
		// private E[] eValues;
		private static Class<String> strClass = (Class<String>) "".getClass();
		private Class<E> keyClass;
		private Method valueOf;

		public void setKeyClassName(String keyClassName) throws Exception {
			keyClass = (Class<E>) Class.forName(keyClassName);
			valueOf = keyClass.getMethod("valueOf", strClass);
		}

		public String getKeyClassName() {
			return keyClass.getName();
		}

		public void setKeyClass(Class<E> keyClass) {
			this.keyClass = keyClass;
		}

		public Class<E> getKeyClass() {
			return keyClass;
		}

		public String keyToString(E key) {
			return key.toString();
		}

		public E stringToKey(String string) throws Exception {
			return (E) valueOf.invoke(null, string);
			// throw new InvalidKeyStringException("String " + string +
			// " not found in basis vector of type" + eValues.getClass());
		}
	}
}
