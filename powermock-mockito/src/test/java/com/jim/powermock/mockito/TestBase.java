package com.jim.powermock.mockito;

import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.*;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 9/13/13
 * Time: 11:37 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestBase {
    private Connection connection;
    private DataSource dataSource;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;
//    private JdbcTemplate template;
    private CallableStatement callableStatement;

    @Before
    public void setup() throws Exception {
        this.connection = mock(Connection.class);
        this.dataSource = mock(DataSource.class);
        this.preparedStatement = mock(PreparedStatement.class);
        this.statement = mock(Statement.class);
        this.resultSet = mock(ResultSet.class);
//        this.template = new JdbcTemplate(this.dataSource);
        this.callableStatement = mock(CallableStatement.class);
        given(this.dataSource.getConnection()).willReturn(this.connection);
        given(this.connection.prepareStatement(anyString())).willReturn(this.preparedStatement);
        given(this.preparedStatement.executeQuery()).willReturn(this.resultSet);
        given(this.preparedStatement.executeQuery(anyString())).willReturn(this.resultSet);
        given(this.preparedStatement.getConnection()).willReturn(this.connection);
        given(this.statement.getConnection()).willReturn(this.connection);
        given(this.statement.executeQuery(anyString())).willReturn(this.resultSet);
        given(this.connection.prepareCall(anyString())).willReturn(this.callableStatement);
        given(this.callableStatement.getResultSet()).willReturn(this.resultSet);
    }

    @Test
    public void testBeanProperties() throws Exception {
//        assertTrue("datasource ok", this.template.getDataSource() == this.dataSource);
//        assertTrue("ignores warnings by default", this.template.isIgnoreWarnings());
//        this.template.setIgnoreWarnings(false);
//        assertTrue("can set NOT to ignore warnings", !this.template.isIgnoreWarnings());
    }

    @Test
    public void testUpdateCount() throws Exception {
        final String sql = "UPDATE INVOICE SET DATE_DISPATCHED = SYSDATE WHERE ID = ?";
        int idParam = 11111;
        given(this.preparedStatement.executeUpdate()).willReturn(1);
        int rowsAffected = this.preparedStatement.executeUpdate(sql);
//        Dispatcher d = new Dispatcher(idParam, sql);
//        int rowsAffected = this.template.update(d);
        assertTrue("1 update affected 1 row", rowsAffected == 1);
        verify(this.preparedStatement).setInt(1, idParam);
        verify(this.preparedStatement).close();
        verify(this.connection).close();
    }
}
