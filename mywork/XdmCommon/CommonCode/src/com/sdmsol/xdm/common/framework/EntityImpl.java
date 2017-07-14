package com.sdmsol.xdm.common.framework;

import oracle.jbo.server.TransactionEvent;

public class EntityImpl extends oracle.jbo.server.EntityImpl {
    @Override
    public void afterRemove(TransactionEvent transactionEvent) {
        // TODO Implement this method
        super.afterRemove(transactionEvent);
    }

    @Override
    protected void doDML(int i, TransactionEvent transactionEvent) {
        // TODO Implement this method
        super.doDML(i, transactionEvent);
    }
}
