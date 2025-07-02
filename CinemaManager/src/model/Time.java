package model;


public class Time {
    int hour;
    int minute;
    int second;
    int day;
    int month;
    int year;

    public Time(int d, int mth, int y, int h, int m, int s) {
        setTime(d, mth, y, h, m, s);
    }

    public Time setTime(int d, int mth, int y, int h, int m, int s) {
        setDay(d);
        setMonth(mth);
        setYear(y);
        setHour(h);
        setMinute(m);
        setSecond(s);
        return this;
    }

    public Time setDay(int d){
        day = ((d >= 1 && d <= 31) ? d : 0);
        return this;
    }

    public Time setMonth(int mth){
        month = ((mth >= 1 && mth <= 12) ? mth : 0);
        return this;
    }

    public Time setYear(int y){
        year = ((y >= 0) ? y : 2000);
        return this;
    }

    public Time setHour(int h) {
        hour = ((h >= 0 && h < 24) ? h : 0);
        return this;
    }

    public Time setMinute(int m) {
        minute = ((m >= 0 && m < 60) ? m : 0);
        return this;
    }

    public Time setSecond(int s) {
        second = ((s >= 0 && s < 60) ? s : 0);
        return this;
    }

    public int getDay(){
        return day;
    }

    public int getMonth(){
        return month;
    }

    public int getYear(){
        return year;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public String toString() {
        return (day < 10 ? "0" :"") + day + "/" +
        (month < 10 ? "0" : "") + year +
        ((hour == 0 || hour == 12) ? 12 : hour % 12) +
                ":" + (minute < 10 ? "0" : "") + minute +
                ":" + (second < 10 ? "0" : "") + second +
                (hour < 12 ? " AM" : " PM");
    }

}
