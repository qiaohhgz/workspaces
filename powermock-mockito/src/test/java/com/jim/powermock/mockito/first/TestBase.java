package com.jim.powermock.mockito.first;

import com.jim.powermock.mockito.beans.Client;
import com.jim.powermock.mockito.beans.PartialMockClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 9/13/13
 * Time: 3:06 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({PartialMockClass.class})
public class TestBase {
    @Test
    public void privatePartialMockingWithPowerMock() throws Exception {
        PartialMockClass partialMockClass = PowerMockito.spy(new PartialMockClass());

        // use PowerMockito to set up your expectation
        PowerMockito.doReturn(true).when(partialMockClass, "isDebug");

        // execute your test
        partialMockClass.execute();

        // Use PowerMockito.verify() to verify result
        PowerMockito.verifyPrivate(partialMockClass, times(1)).invoke("isDebug");
    }

    @Test
    public void testTenItemsCallIsDebugMethod() throws Exception {
        PartialMockClass partialMockClass = PowerMockito.spy(new PartialMockClass());
        PowerMockito.doReturn(true).when(partialMockClass, "isDebug");
        partialMockClass.tenItemsCallIsDebugMethod();
        PowerMockito.verifyPrivate(partialMockClass, times(10)).invoke("isDebug");
    }

    @Test
    public void testUseClient() throws Exception {
        PartialMockClass partialMockClass = PowerMockito.spy(new PartialMockClass());
        Client client = mock(Client.class);
        when(client.isTest()).thenReturn(true);
        PowerMockito.doReturn(client).when(partialMockClass, "getClient");
        partialMockClass.useClientClass();
    }
}
