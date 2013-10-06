package com.jim.test.dbunit.v2;

import org.apache.log4j.Logger;
import org.dbunit.Assertion;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import javax.sql.DataSource;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;

import static org.junit.Assert.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 9/16/13
 * Time: 10:58 AM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:v2/applicationContext.xml"})
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
public class TestExportDataFromDB {
    private static Logger logger = Logger.getLogger(TestExportDataFromDB.class);
    @Autowired
    private DataSource dataSource;
    private String dataSetFilePath = "v2/dataset-book.xml";

    private IDatabaseConnection getConnection() throws Exception {
        logger.debug("getting connection 100");
        Connection con = dataSource.getConnection();
        logger.debug("getting connection 200");
        IDatabaseConnection connection = new DatabaseConnection(con);
        logger.debug("getting connection 300");
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

    private void extractTables(String targetDirectory, String[] tableNames)
            throws Exception {
        IDatabaseConnection connection = getConnection();
        for (int i = 0; i < tableNames.length; i++) {
            String tableName = tableNames[i];
            IDataSet partialDataSet = connection.createDataSet(new String[]{tableName});
            FlatXmlDataSet.write(partialDataSet, new FileOutputStream(targetDirectory + "/" + tableName + ".xml"));
        }
    }

    private void extractAllTableWithSingleFile(String targetDirectory)
            throws Exception {
        IDatabaseConnection connection = getConnection();
        IDataSet partialDataSet = connection.createDataSet();
        String[] tableNames = partialDataSet.getTableNames();
        for (String tableName : tableNames) {
            System.out.println("tableName = " + tableName);
        }
        FlatXmlDataSet.write(partialDataSet, new FileOutputStream(targetDirectory + "/local.xml"));
    }

    @Test(timeout = 3000)
    public void testExtractTables() throws Exception {
        extractTables("", new String[]{"book"});
    }

    @Test(timeout = 3000)
    public void testExtractAllTableWithSingleFile() throws Exception {
        extractAllTableWithSingleFile("");
    }

    @Test(timeout = 3000)
    public void testDataTableMerge() throws Exception {
        ITable bookFromDB = getConnection().createQueryTable("book", "select * from book where id = 1");
        ITable bookFromFile = getDataSet().getTable("book");
        Assertion.assertEquals(bookFromDB, bookFromFile);
    }

    @Test(timeout = 3000)
    public void testExportRequestDataTableForYellowBook() throws Exception {
        IDataSet partialDataSet = getConnection().createDataSet(new String[]{"wr2.BudgetEstimateRequest"});
        String[] tableNames = partialDataSet.getTableNames();
        for (String tableName : tableNames) {
            logger.debug("tableName = " + tableName);
        }
        FlatXmlDataSet.write(partialDataSet, new FileOutputStream("DataTable/KeywordToolInsertion/wr2/BudgetEstimateRequest.xml"));
    }
}
