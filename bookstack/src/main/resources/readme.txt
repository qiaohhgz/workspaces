SSH 整合方案步骤

1.复制jar文件
org.springframework.aop-3.0.6.RELEASE.jar
org.springframework.asm-3.0.6.RELEASE.jar
org.springframework.aspects-3.0.6.RELEASE.jar
org.springframework.beans-3.0.6.RELEASE.jar
org.springframework.context-3.0.6.RELEASE.jar
org.springframework.context.support-3.0.6.RELEASE.jar
org.springframework.core-3.0.6.RELEASE.jar
org.springframework.expression-3.0.6.RELEASE.jar
org.springframework.instrument-3.0.6.RELEASE.jar
org.springframework.instrument.tomcat-3.0.6.RELEASE.jar
org.springframework.jdbc-3.0.6.RELEASE.jar
org.springframework.jms-3.0.6.RELEASE.jar
org.springframework.orm-3.0.6.RELEASE.jar
org.springframework.oxm-3.0.6.RELEASE.jar
org.springframework.test-3.0.6.RELEASE.jar
org.springframework.transaction-3.0.6.RELEASE.jar
org.springframework.web-3.0.6.RELEASE.jar
org.springframework.web.portlet-3.0.6.RELEASE.jar
org.springframework.web.servlet-3.0.6.RELEASE.jar
org.springframework.web.struts-3.0.6.RELEASE.jar
antlr-2.7.6.jar
commons-collections-3.1.jar
dom4j-1.6.1.jar
hibernate-jpa-2.0-api-1.0.1.Final.jar
hibernate3.jar
javassist-3.12.0.GA.jar
jta-1.1.jar
slf4j-api-1.6.1.jar
commons-logging-1.1.1.jar
classes12.jar
mysql.jar
asm-3.3.jar
asm-commons-3.3.jar
asm-tree-3.3.jar
commons-fileupload-1.2.2.jar
commons-io-2.0.1.jar
commons-lang-2.5.jar
freemarker-2.3.18.jar
ognl-3.0.3.jar
struts2-core-2.3.1.jar
xwork-core-2.3.1.jar
struts2-spring-plugin-2.3.1.jar

2.src编写配置文件
applicationContext.xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

	<bean name="sessionFactory" class="org.springframework.orm.hibernate3.LocalSessionFactoryBean">
		<property name="configLocation">
			<value>classpath:hibernate.cfg.xml</value>
		</property>
	</bean>
	<bean name="userDao" class="com.dao.impl.UserDaoImpl" scope="prototype">
		<property name="sessionFactory" ref="sessionFactory"></property>
	</bean>
	<bean name="loginAction" class="com.action.LoginAction" scope="prototype">
		<property name="userDao" ref="userDao"></property>
	</bean>
</beans>


struts.xml
<struts>
	<constant name="struts.enable.DynamicMethodInvocation" value="false" />
	<constant name="struts.devMode" value="false" />

	<package name="default" namespace="/" extends="struts-default">

		<default-action-ref name="index" />
		<global-results>
			<result name="error">/error.jsp</result>
		</global-results>
		<global-exception-mappings>
			<exception-mapping exception="java.lang.Exception" result="error" />
		</global-exception-mappings>

		<action name="index">
			<result>/index.jsp</result>
		</action>
	</package>
	<include file="struts_user.xml" />
</struts>

struts_user.xml
<struts>
	<package name="user" namespace="/user" extends="default">
		<action name="login" class="loginAction" method="login">
			<result name="loginSuccess">/index.jsp</result>
		</action>
		<action name="add" class="loginAction" method="add">
			<result name="saveSuccess">/index.jsp</result>
		</action>
	</package>
</struts>

3.修改WEB-INF/web.xml文件加入
<?xml version="1.0" encoding="UTF-8"?>
<web-app version="2.5" xmlns="http://java.sun.com/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee   http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd">
	<welcome-file-list>
		<welcome-file>index.jsp</welcome-file>
	</welcome-file-list>
	<!--
可以象下面所示例的一样使用ContextLoaderListener注册一个ApplicationContext
	-->
	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>/WEB-INF/classes/application*.xml classpath*:beans*.xml</param-value>
	</context-param>

	<listener>
		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
	</listener>

	<filter>
		<filter-name>struts2</filter-name>
		<filter-class>org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter</filter-class>
	</filter>

	<filter-mapping>
		<filter-name>struts2</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>

</web-app>

hibernate.cfg.xml
<hibernate-configuration>
<session-factory>
	<property name="dialect">org.hibernate.dialect.MySQLDialect</property>
	<property name="show_sql">true</property>
	<property name="connection.url">jdbc:mysql://127.0.0.1:3306/t1</property>
	<property name="connection.driver_class">com.mysql.jdbc.Driver</property>
	<property name="connection.username">root</property>
	<property name="connection.password">root</property>
	<property name="hbm2ddl.auto">update</property>
	<property name="hibernate.connection.autocommit">true</property>
	<property name="format_sql">true</property>
	<property name="show_sql">true</property>
	<property name="myeclipse.connection.profile">t1</property>
	<mapping class="com.entity.User" />
</session-factory>
</hibernate-configuration>

UserDaoImpl 继承HibernateDaoSupport
package com.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dao.UserDao;
import com.entity.User;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	public User saveOrUpdate(User user) {
		getHibernateTemplate().saveOrUpdate(user);
		User u = get(user.getName());
		if (null != u && u.equals(user)) {
			return u;
		}
		return null;
	}

	public User login(User user) {
		User u = get(user.getName());
		if (null != u && u.equals(user)) {
			return u;
		}
		return null;
	}

	public User get(String name) {
		return getHibernateTemplate().get(User.class, name);
	}
}


4.编写Action类,HelloAction.java
package com;
import java.io.IOException;
import org.apache.struts2.ServletActionContext;
public class HelloAction {
	public void execute() throws IOException {
		ServletActionContext.getResponse().getWriter().print("Hello World...SSH");
		System.out.println("Hello World.....");
	}

	public void abc() throws IOException{
		ServletActionContext.getResponse().getWriter().print("HelloAction().abc()...Hello World...SSH");
		System.out.println("HellAction().abc()....Hello World.....");
	}
}

5.测试http://localhost:8080/SSH_1006_DoWork_Deletes_PageBean_Transaction/

6.加入事务处理和分页
xml配置
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:p="http://www.springframework.org/schema/p" 
	xmlns:aop="http://www.springframework.org/schema/aop" 
	xmlns:tx="http://www.springframework.org/schema/tx"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans 
	http://www.springframework.org/schema/beans/spring-beans.xsd 
	http://www.springframework.org/schema/aop 
	http://www.springframework.org/schema/aop/spring-aop-3.0.xsd 
	http://www.springframework.org/schema/tx 
	http://www.springframework.org/schema/tx/spring-tx-3.0.xsd  
	">

