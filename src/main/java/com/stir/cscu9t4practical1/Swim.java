package com.stir.cscu9t4practical1;

public class Swim extends Entry{

    private String stroke;
    public Swim(String n, int d, int m, int y, int h, int min, int s, float dist,String stroke) {
        super(n, d, m, y, h, min, s, dist);
        this.stroke=stroke;
    }

    @Override
    public String getEntry(){
        String result = getName()+" Swam " + getDistance() + " km using "+getStroke()+" in "
                +getHour()+":"+getMin()+":"+ getSec() + " on "
                +getDay()+"/"+getMonth()+"/"+getYear()+"\n";
        return result;
    }
    public String getStroke(){
        return stroke;
    }
}
