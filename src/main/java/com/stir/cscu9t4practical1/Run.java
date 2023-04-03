package com.stir.cscu9t4practical1;

public class Run extends Entry{
    private double incline;
    public Run(String n, int d, int m, int y, int h, int min, int s, float dist,double incline) {
        super(n, d, m, y, h, min, s, dist);
        this.incline=incline;
    }

    public String getEntry(){
        String result = getName()+" ran " + getDistance() + " km with"+getIncline()+"incline in "
                +getHour()+":"+getMin()+":"+ getSec() + " on "
                +getDay()+"/"+getMonth()+"/"+getYear()+"\n";
        return result;
    }
    public double getIncline(){
        return incline;
    }
}
