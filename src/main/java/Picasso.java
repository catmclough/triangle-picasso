import processing.core.PApplet;

/**
 * This program uses recursion to draw a series of triangles, decreasing in size, within one another.
 * The triangles rotate around the position of the mouse.
 * The color of the lines changes depending on the mouse's position.
 * While the mouse is pressed the triangles return to their original position.
 *
 * @author Catherine McLoughlin <cgm289@nyu.edu>
 *
 */
@SuppressWarnings("serial")
public class Picasso extends PApplet {
    int d = 5; //Amount the size of each line will decrement
    float size; //variable for keeping track of the length of one side of the triangle
    float angle = 0;
    int r = 10; //degrees triangle will rotate

    // Providing a main allows the project to be exported as an application
    static public void main(String args[]) {
        PApplet.main("Picasso");
    }

    /**
     * Runs once at beginning of program. Sets size of the window.
     */
    public void setup(){
        size(500, 500);
    }

    /**
     * Looping function. Sets specifications for drawing and calls function that draws our shapes.
     */
    public void draw(){
        frameRate(10); //slows down the program so rotation can be easily seen
        background(0);
        stroke(255);
        strokeWeight(2);
        drawInnerTriangles(-(width/2), height/2, 0, 0, width/2, height/2); //draw triangles with top vertices at 0,0
    }

    /**
     * Takes the X and Y positions of 3 points as parameters and draws lines between them to form a triangle.
     * Then, the points are changed and the function calls itself recursively, until the size of the triangles are too
     * small and the function is exited.
     *
     * @param point1X
     * @param point1Y
     * @param point2X
     * @param point2Y
     * @param point3X
     * @param point3Y
     */
    public void drawInnerTriangles(float point1X, float point1Y, float point2X, float point2Y, float point3X, float point3Y){
        size = dist(point1X, point1Y, point2X, point2Y);
        if (size <= d) { //base case. triangles will stop drawing when one side reaches this length
            return;
        } else {
            pushMatrix();
            translate(mouseX, mouseY); //change center point to mouse position
            rotate(radians(angle));
            stroke(width-mouseX, mouseX, mouseY); //stroke color is based on mouse position
            //draw our triangle
            line(point1X, point1Y, point2X, point2Y);
            line(point2X, point2Y, point3X, point3Y);
            line(point3X, point3Y, point1X, point1Y);
            popMatrix();

            //change direction of rotation based on mouse position
            if (mouseX < width/2) {
                angle += r;
            } else {
                angle -= r;
            }

            //increase rotation speed up, to 500
            if (r < 500 && r > -500 ) {
                if (angle < 0)
                    r--;
                else
                    r++;
            }

            //if mouse is pressed, bring triangles back to original orientation
            if (mousePressed) {
                angle = 0;
                r = 0;
            }

            //bring points of triangle closer together, decreasing the area and length of each side
            point1X += d*2;
            point1Y -= d;
            point2Y += d;
            point3X -= d*2;
            point3Y -= d;

            //recursive call with updated points
            drawInnerTriangles(point1X, point1Y, point2X, point2Y, point3X, point3Y);
        }
    }
}
