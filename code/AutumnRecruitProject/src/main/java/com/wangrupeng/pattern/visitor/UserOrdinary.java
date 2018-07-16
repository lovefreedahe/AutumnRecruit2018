package com.wangrupeng.pattern.visitor;

public class UserOrdinary implements User {
    private String estimation;
    public UserOrdinary(String estimation) {
        this.estimation = estimation;
    }
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String getEstimation() {
        return estimation;
    }
}
