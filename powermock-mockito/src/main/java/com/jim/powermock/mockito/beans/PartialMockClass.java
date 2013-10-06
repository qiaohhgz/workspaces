package com.jim.powermock.mockito.beans;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 9/13/13
 * Time: 3:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class PartialMockClass {
    private boolean isDebug() {
        return false;
    }

    public void execute() throws Exception {
        if (!isDebug()) {
            throw new Exception("mock error");
        }
    }

    public void tenItemsCallIsDebugMethod() throws Exception {
        for (int i = 0; i < 9; i++) {
            isDebug();
        }
        if (!isDebug()) {
            throw new Exception("mock error");
        }
    }

    public void useClientClass() throws Exception {
        if (!getClient().isTest()) {
            throw new Exception("mock error");
        }
    }

    private Client getClient() {
        return new Client();
    }
}
