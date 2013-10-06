package com.common;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by IntelliJ IDEA.
 * User: petrop01
 * Date: Oct 24, 2011
 * Time: 1:20:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class PropertyManager {

    private Map<String, Object> targets = new TreeMap<String, Object>();
    private Map<String, Object> t = new HashMap<String, Object>();
    private String linkName = "";
    private String linkValue = "";

    public List<Map<String, String>> getAllProperties(){
        List<Map<String, String>> lst = new ArrayList<Map<String, String>>();

        for (String objName : targets.keySet()){
            TreeMap<String, String> hm = new TreeMap<String, String>();
            Object obj = targets.get(objName);
            Class cl = obj.getClass();
            Method[] methods = cl.getDeclaredMethods();
            for (Method m : methods){
                try{
                    Object result = null;
                    if (checkMethod(m)) {
                        String propName = getPropertyName(m.getName());
                        try {
                            result = m.invoke(obj, new Object[]{});
                            if (result == null){
                                result = "null";
                            }
                        }catch (Exception e){
                            //Logging.logError("Error while calling method " + m.getName(), e);
                            result = null;
                        }
                        if (result!=null){
                            hm.put(objName.substring(objName.indexOf(".")+1) + "." + propName, result.toString());
                        }
                    }
                }catch (Exception ex){
                    //Logging.logError("Error while processing method " + m.getName(), ex);
                }
            }

            lst.add(hm);
        }

        return lst;
    }

    public boolean saveProperty(String property, String value){
        String objectName = property.substring(0, property.indexOf("."));
        String propName = property.substring(property.indexOf(".") + 1);
        Object obj = this.t.get(objectName);
        Class clazz = obj.getClass();
        boolean success = false;
        for(Method m : clazz.getMethods()){
            if (
                   ("set" + propName).equals(m.getName()) &&
                   m.getParameterTypes().length == 1 &&
                   ((m.getModifiers() & Modifier.STATIC) == 0)
               )
            {
                try {
                    Object v = convertStringToObject(value, m.getParameterTypes()[0]);
                    m.invoke(obj, new Object[]{v});
                    success = true;
                    break;
                } catch (Exception ex) {
                    //Logging.logError("Failed to invoke setter method.", ex);
                }
            }
        }

        return success;
    }

    private static Object convertStringToObject(String value, Class c){
        if (value == null){
            return null;
        }else if ("null".equals(value)){
            return null;
        }else if (c == String.class){
            return value;
        }else if (c == Integer.class || c == int.class){
            return new Integer(value);
        }else if (c == Long.class || c == long.class){
            return new Long(value);
        }else if (c == Boolean.class || c == boolean.class){
            return new Boolean(value.toLowerCase());
        }else{
            throw new IllegalArgumentException
            ("Argument conversion failed for ["+value+", " + c.getName() +"].");
        }
    }

    private static boolean checkMethod(Method m){
        boolean ok =
        (m.getName().startsWith("get") || m.getName().startsWith("is")) &&
        m.getParameterTypes().length==0 &&
        (m.getModifiers() & Modifier.STATIC) == 0;

        if (!ok) {
            return false;
        } else {
            Annotation[] anns = m.getAnnotations();
            for (Annotation ann : anns){
                Class clazz = ann.annotationType();
                if (clazz == PropertyEditable.class){
                    return true;
                }
            }
            return false;
        }
    }

    private static String getPropertyName(String nameGetterSetter){
        if (nameGetterSetter.startsWith("is")){
            return nameGetterSetter.substring(2);
        }else{
            return nameGetterSetter.substring(3);
        }
    }

    public Map<String, Object> getTargets() {
        return targets;
    }

    public void setTargets(Map<String, Object> targets) {
        this.targets.clear();
        this.targets.putAll(targets);
        this.t.clear();
        for(String key : targets.keySet()){
            int ind = key.indexOf(".");
            this.t.put(key.substring(ind+1), targets.get(key));
        }
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getLinkValue() {
        return linkValue;
    }

    public void setLinkValue(String linkValue) {
        this.linkValue = linkValue;
    }
}


