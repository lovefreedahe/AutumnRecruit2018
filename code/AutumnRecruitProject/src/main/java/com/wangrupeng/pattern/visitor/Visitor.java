package com.wangrupeng.pattern.visitor;

public interface Visitor {
    void visit(UserVIP userVIP);
    void visit(UserOrdinary userOrdinary);
}
