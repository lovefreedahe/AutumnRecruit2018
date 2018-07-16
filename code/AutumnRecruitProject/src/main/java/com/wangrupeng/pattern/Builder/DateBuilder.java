package com.wangrupeng.pattern.Builder;

class MyDate {
    String date;
}

public class DateBuilder implements IDateBuilder{
    private MyDate myDate;
    public DateBuilder(MyDate date) {
        this.myDate = date;
    }
    @Override
    public IDateBuilder buildDate(int year, int month, int day) {
        myDate.date = year + "-" + month + "-" + day;
        return this;
    }

    @Override
    public String getDate() {
        return myDate.date;
    }
}

interface IDateBuilder {
    IDateBuilder buildDate(int year, int month, int day);
    String getDate();
}
