package GoogleChallenge4Code1A2;
//package com.google.challenges;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by ShIvS on 7/12/2017.
 */
public class Answer {

    public static final int SLOPE_PRECISION_LEVEL = 3;

    public static void main(String[] args) {

        //input 1
        int[] dimensions = new int[]{3, 2};
        int[] captain_position = new int[]{1, 1};
        int[] badguy_position = new int[]{2, 1};
        int distance = 4;
        //System.out.println("************************Answer for input 1" + answer(dimensions, captain_position, badguy_position ,distance));

        //input 2
        dimensions = new int[]{300, 275};
        captain_position = new int[]{150, 150};
        badguy_position = new int[]{185, 100};
        distance = 500;
        //System.out.println("************************Answer for input 2" + answer(dimensions, captain_position, badguy_position ,distance));

        answer(dimensions, captain_position, badguy_position ,distance);

    }

    public static int answer(int[] dimensions, int[] captain_position, int[] badguy_position, int distance) {
        // Your code goes here.
        if(dimensions[0]>1000) {
                throw new RuntimeException("trying to figure out dimentions");
        }
        int numberOfWays = 0;

        HashSet<Slope> slopes = Slope.generateSlopes(SLOPE_PRECISION_LEVEL);
        /*
        System.out.println("all slopes generated");
        for(Slope slope : slopes){
            System.out.print(slope.toString() + ", ");
        }
        */
        Slope slope = new Slope(3,5);
        Point start = new Point(captain_position[0], captain_position[1]);
        Ray ray = new Ray(start, slope);

        return numberOfWays;
    }
}

class Ray{
    private Point startPoint;
    private Point endPoint;
    private final double lengthOfRay;
    //private int[][] travellingInDimension;

    public static boolean isPointInside(int[][] dimension) {

        return false;
    }

    public Ray(Point start, Point end, double len) {
        this.startPoint = start;
        this.endPoint = end;
        this.lengthOfRay = len;
    }

    public Ray(Point start, Slope slope){
        this.startPoint = start;
        Point endPoint = new Point(startPoint.getX() + slope.getxDen(), startPoint.getY() + slope.getyNum() );
        this.endPoint = endPoint;
        double base = startPoint .getX() - endPoint.getX();
        double height = startPoint.getY() - endPoint.getY();
        lengthOfRay = Math.sqrt( base*base + height*height );
    }

    //returns next point ray reaches in box
    public Point extendRay(int[] dimensions){

        return null;
    }

    //returns a point the ray reaches after travelling a distance 'lengthOfRay'
    public static Point runTheRay(Ray ray, int[] dimensions) {

        return null;
    }

    //creates new ray object when present ray reaches the edge or crosses it
    public Ray createNewRay(Ray presentRay, Point outsideEndPoint){

        return null;
    }

    public boolean isRayOnEdge(int[] dimentions) {
        return false;
    }
    public boolean isRayOnCorner(int[] dimensions){
        return false;
    }
    public boolean isRayInsideTheBox(int[] dimentions){
        return false;
    }
}

class Slope {
    private int xDen;
    private int yNum;

    public int getxDen() {
        return xDen;
    }

    public int getyNum() {
        return yNum;
    }

    // slope = rise/run
    public Slope(int xDen, int yNum) {
        if(xDen == 0)
            throw new RuntimeException("Ha ha");

        this.xDen = xDen;
        this.yNum = yNum;
        simplifySlope();
    }

    private void simplifySlope() {
        int gcd = findGCD(yNum, xDen);
        if(gcd>1) {
            yNum = yNum / gcd;
            xDen = xDen / gcd;
        }
    }

    public int findGCD(int num, int den) {

        /* close one ;-) */
        if(num < 0)
            num = num * -1;
        if(den < 0)
            den = den * -1;


        return den==0 ? num : findGCD(den, num%den);
    }

    @Override
    public boolean equals(Object obj) {
        Slope incoming = (Slope)obj;
        return (incoming.getxDen()==xDen && incoming.getyNum()==yNum);
    }

    @Override
    public int hashCode() {

        return (xDen + yNum);
    }

    @Override
    public String toString() {
        return " (" + xDen + "," + yNum+")";
    }

    /**
     * generates all slopes to the precision of
     * Answer.SLOPE_PRECISION_LEVEL square size
     */
    public static HashSet<Slope> generateSlopes(int precision){
        HashSet<Slope> slopes = new HashSet<>();
//        ArrayList<Slope> slopes  = new ArrayList<>();
        for (int i = -precision; i <= precision; i++) {
            for (int j = -precision; j <= precision ; j++) {
                if(i != 0 && j != 0) {
                    Slope slope = new Slope(i,j);
                    slopes.add(slope);
                }
            }
        }
        return slopes;
    }
}

class Point{
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}
/**
 * conditions yet to be handled
 * 1) Vertical and horizontal Rays
 * */
/*
checking output of generateSlopes()
(-3,2),     (-1,1),  (-2,1),  (1,-2),  (1,-1),  (2,-3),  (-1,-1),
(-3,1),     (-2,3),  (-1,2),  (1,-3),  (2,-1),  (3,-2),  (-2,-1),
(-1,-2),    (-1,3),  (1,1),  (3,-1),  (-3,-1),  (-1,-3),  (1,2),
(2,1),     (-3,-2), (-2,-3),  (1,3),  (3,1),    (2,3),    (3,2),


 */