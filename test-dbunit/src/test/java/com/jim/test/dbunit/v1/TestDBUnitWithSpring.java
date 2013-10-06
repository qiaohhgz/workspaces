package com.jim.test.dbunit.v1;

import org.apache.log4j.Logger;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mssql.InsertIdentityOperation;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static junit.framework.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 9/16/13
 * Time: 10:58 AM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:v1/applicationContext.xml"})
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
public class TestDBUnitWithSpring {
    static Logger logger = Logger.getLogger(TestDBUnitWithSpring.class);
    @Autowired
    private DataSource dataSource;
    private String dataSetFilePath = "v1/dataset-book.xml";
    private String expectedDataSetFilePath = "v1/dataset-expected-book.xml";

    @Before
    public void init() throws Exception {
        logger.info("init method");
        cleanAndInsertDataWithIdentityOperation();
    }

    @After
    public void after() throws Exception {
        deleteAll();
    }

    private void deleteAll() throws Exception {
        DatabaseOperation.DELETE_ALL.execute(getConnection(), getDataSet());
    }

    private void cleanAndInsertData() throws Exception {
        DatabaseOperation.CLEAN_INSERT.execute(getConnection(), getDataSet());
    }

    private void cleanAndInsertDataWithIdentityOperation() throws Exception {
        InsertIdentityOperation.CLEAN_INSERT.execute(getConnection(), getDataSet());
    }

    private IDatabaseConnection getConnection() throws Exception {
        Connection con = dataSource.getConnection();
        IDatabaseConnection connection = new DatabaseConnection(con, "dbo");//scheam e.g dbo
        return connection;
    }

    private IDataSet getDataSet() throws Exception {
        InputStream stream = getClass().getClassLoader().getResourceAsStream(dataSetFilePath);
        return new FlatXmlDataSetBuilder().build(stream);
    }

    @Test
    public void testGetConnection() throws Exception {
        IDatabaseConnection connection = getConnection();
        assertNotNull(connection);
    }

    @Test
    public void testSQLUpdate() throws Exception {
        Connection con = dataSource.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rst = stmt.executeQuery("select * from book where id = 1");
        if (rst.next()) {
            assertEquals("Think in java", rst.getString("name"));
            rst.close();

            int count = stmt.executeUpdate("update book set name='Java OOP' where id=1");

            stmt.close();
            con.close();

            assertEquals("one row should be updated", 1, count);

            QueryDataSet databaseSet = new QueryDataSet(getConnection());
            databaseSet.addTable("book", "select * from book where id = 1");
            ITable actualTable = databaseSet.getTables()[0];

            InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(expectedDataSetFilePath);
            IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(resourceAsStream);
            ITable expectedTable = expectedDataSet.getTable("book");

            actualTable = DefaultColumnFilter.includedColumnsTable(actualTable, expectedTable.getTableMetaData().getColumns());

            assertEquals(1, expectedTable.getRowCount());
            assertEquals(expectedTable.getRowCount(), actualTable.getRowCount());
            assertEquals(expectedTable.getValue(0, "name"), actualTable.getValue(0, "name"));

        } else {
            fail("no rows");
            rst.close();
            stmt.close();
            con.close();
        }
    }

    @Test
    public void testName() throws Exception {

    }
}
