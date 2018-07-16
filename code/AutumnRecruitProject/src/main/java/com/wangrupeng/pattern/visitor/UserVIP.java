package com.wangrupeng.pattern.visitor;

public class UserVIP implements User {
    private String estimation;
    public UserVIP(String estimation) {
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
