package model;

/**
 * Created by Ольга on 21.10.2016.
 */
public class Segment {
    private Double start;
    private Double end;

    public Segment(Double start, Double end) {
        this.start = start;
        this.end = end;
    }

    public Double getStart() {
        return start;
    }

    public void setStart(Double start) {
        this.start = start;
    }

    public Double getEnd() {
        return end;
    }

    public void setEnd(Double end) {
        this.end = end;
    }


    public Double middle() {
        return (end + start) / 2;
    }

    public Double length() {
        return end - start;
    }
}
