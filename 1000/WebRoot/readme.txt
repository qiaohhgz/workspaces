<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns:tx="http://www.springframework.org/schema/tx"
        xmlns:aop="http://www.springframework.org/schema/aop"
        xmlns:dwr="http://www.directwebremoting.org/schema/spring-dwr"       
        xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-2.0.xsd
        http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-2.0.xsd
        http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-2.0.xsd
        http://www.directwebremoting.org/schema/spring-dwr http://www.directwebremoting.org/schema/spring-dwr-2.0.xsd">
       
        <bean id="countryUtil" class="com.newegg.demo.dwr.CountryUtil">
                <dwr:remote javascript="Country" />
        </bean>
       
        <bean id="personFetcher" class="com.newegg.demo.dwr.PersonFetcher">
                <dwr:remote javascript="PersonFetcher" />
        </bean>
       
        <bean id="spinUtil" class="com.newegg.demo.dwr.SpinUtil">
                <dwr:remote javascript="Spin" />
        </bean>

        <bean id="progressMonitor" class="com.newegg.demo.dwr.ProgressMonitor">
                <dwr:remote javascript="ProgressMonitor" />
        </bean>

        <dwr:configuration>
                <dwr:convert class="com.newegg.demo.dwr.Person" type="bean" />
        </dwr:configuration>
       

</beans>

dwr的单独配置文件dwr.xml可以省略，但是在web.xml中需要把原来的Dwr Servelet：org.directwebremoting.servlet.DwrServlet更换为org.directwebremoting.spring.DwrSpringServlet，其他配置均保持不变。

以上配置的spring版本是2.0+，DWR是2.0+。