package com.jim.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 3/13/13
 * Time: 2:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class FreemarkerUtil {
    public Template getTemplate(String name) {
        try {
            Configuration cfg = new Configuration();
            cfg.setClassForTemplateLoading(this.getClass(), "/ftl");
            Template template = cfg.getTemplate(name);
            return template;
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    public void print(String name, Map<String, Object> rootMap) {
        try {
            Template template = this.getTemplate(name);
            template.process(rootMap, new PrintWriter(System.out));
        } catch (TemplateException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void printFile(String name, Map<String, Object> rootMap, String outFile) {
        try {
            FileWriter out = new FileWriter(new File(outFile));
            Template template = this.getTemplate(name);
            template.process(rootMap, out);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (TemplateException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
