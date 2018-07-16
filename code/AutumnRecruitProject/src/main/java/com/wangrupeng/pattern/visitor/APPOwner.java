package com.wangrupeng.pattern.visitor;

public class APPOwner implements Visitor {
    @Override
    public void visit(UserVIP userVIP) {
        String estimation = userVIP.getEstimation();
        if (estimation.length() > 5) {
            System.out.println("记录一条VIP用户反馈：" + estimation);
        }
    }

    @Override
    public void visit(UserOrdinary userOrdinary) {
        String estimation = userOrdinary.getEstimation();
        if (estimation.length() > 10) {
            System.out.println("记录一条普通用户反馈：" + estimation);
        }
    }
}
