package controller;

import controller.interpolators.LagrangeInterpolator;
import model.Segment;

import java.util.ArrayList;

/**
 * Created by Ольга on 21.10.2016.
 */
public class NonLinearEquationSolver {

    private Double segmentStart;
    private Double segmentEnd;
    private LagrangeInterpolator lagrangeInterpolator;
    private Double fValue;

    public NonLinearEquationSolver(Double segmentStart, Double segmentEnd,
                                   LagrangeInterpolator lagrangeInterpolator, Double fValue) {
        this.segmentStart = segmentStart;
        this.segmentEnd = segmentEnd;
        this.lagrangeInterpolator = lagrangeInterpolator;
        this.fValue = fValue;
    }

    public Double solveEquation() {
        ArrayList<Segment> segmentsWithRoots = separateRoots();
        if (segmentsWithRoots.size() == 0) {
            return 0.0;
        }
        return specifyRoots(segmentsWithRoots.get(0));
    }

    private boolean isSegmentAccurate(Segment curSegment) {
        Double accuracy = 0.00001;
        return curSegment.length() < 2 * accuracy;
    }

    private boolean rootIsInLeftPart(Segment segment) {
        return (lagrangeInterpolator.interpolate(segment.getStart()) - fValue) *
                (lagrangeInterpolator.interpolate(segment.middle()) - fValue) <= 0;
    }

    private Double specifyRoots(Segment segment) {
        Segment curSegment = segment;
        while (!isSegmentAccurate(curSegment)) {
            if (rootIsInLeftPart(curSegment)) {
                curSegment = new Segment(curSegment.getStart(), curSegment.middle());
            } else {
                curSegment = new Segment(curSegment.middle(), curSegment.getEnd());
            }
        }
        return curSegment.middle();
    }

    private double calcStep() {
        Double dividionCoeff = 100.0;
        return (segmentEnd - segmentStart) / dividionCoeff;
    }

    private boolean isCurSegmentLast(Segment curSegment) {
        return curSegment.getEnd() >= segmentEnd;
    }

    private boolean segmentContainsRoot(Segment segment) {
        return (lagrangeInterpolator.interpolate(segment.getStart()) - fValue) *
                (lagrangeInterpolator.interpolate(segment.getEnd()) - fValue) <= 0;
    }

    private Segment calculateNextSegment(Segment curSegment, double step) {
        if (segmentEnd > curSegment.getEnd() + step) {
            return new Segment(curSegment.getEnd(), curSegment.getEnd() + step);
        }
        return new Segment(curSegment.getEnd(), segmentEnd);
    }

    private ArrayList<Segment> separateRoots() {
        double step = calcStep();
        Segment curSegment = new Segment(segmentStart, segmentStart + step);
        ArrayList<Segment> segmentsWithRoots = new ArrayList<>();

        while (!isCurSegmentLast(curSegment)) {
            if (segmentContainsRoot(curSegment)) {
                segmentsWithRoots.add(curSegment);
            }
            curSegment = calculateNextSegment(curSegment, step);
        }
        return segmentsWithRoots;
    }
}
