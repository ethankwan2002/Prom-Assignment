/** Table class 
 *  Sherwin Chiu and Kyro Nassif
 *  Tables used for Prom Display
 *  2/13/2020
 */

// Imports
import java.awt.*;
import java.util.ArrayList;

class Table {
    private int size;
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Rectangle> nameRect = new ArrayList<>();
    private int x;
    private int y;
    private int chairX;
    private int chairY;
    private double angleIncr = (2.0 * Math.PI / (double) this.students.size());
    private boolean dragged = false;
    private final int MAX_SIZE = 10;
    private int diameter = 150;
    private Rectangle tableRect = new Rectangle(this.x, this.y, this.diameter, this.diameter);

    public Table(int size) {
        this.size = size;
    }
    public Table(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }
    /**
     * @return int
     */
    public int getSize() {
        return this.size;
    }
    public void clearTable() {
        students = new ArrayList<>();
    }
    /**
     * @param b
     */
    public void setDragged(boolean b) {
        this.dragged = b;
    }
    /**
     * @param r
     */
    public void setDiameter(int r) {
        this.diameter = r;
        this.tableRect.width = r;
        this.tableRect.height = r;
    }
    /**
     * @return boolean
     */
    public boolean getDragged() {
        return this.dragged;
    }
    /**
     * @param s
     */
    public void addStudent(Student s) {
        this.students.add(s);
    }
    /**
     * @param s
     */
    public void removeStudent(Student s) {
        for (int i = 0; i < this.size; i++) {
            if (s.getId() == this.students.get(i).getId())
                this.students.set(i, null);
        }
    }
    /**
     * @return ArrayList<Student>
     */
    public ArrayList<Student> getStudents() {
        return this.students;
    }
    /**
     * @param s
     */
    public void setStudents(ArrayList<Student> s) {
        for (int i = 0; i < s.size(); i++) {
            this.nameRect.add(new Rectangle());
        }
        this.students = s;
    }
    /**
     * @return int
     */
    public int getX() {
        return this.x;
    }
    /**
     * @param x
     */
    public void setX(int x) {
        this.x = x;
        tableRect.x = x;
    }
    /**
     * @return int
     */
    public int getY() {
        return this.y;
    }
    /**
     * @param y
     */
    public void setY(int y) {
        this.y = y;
        tableRect.y = y;
    }
    /**
     * @return int
     */
    public int getDiameter() {
        return this.diameter;
    }
    /**
     * @return boolean
     */
    public boolean isFull() {
        if (this.size == this.MAX_SIZE)
            return true;
        else
            return false;
    }
    /**
     * @return Rectangle
     */
    public Rectangle getTableRect() {
        return this.tableRect;
    }
    /**
     * @param index
     * @return Rectangle
     */
    public Rectangle getNameRect(int index) {
        return this.nameRect.get(index);
    }
    /**
     * @param g
     */
    public void drawTable(Graphics g) {
        g.drawOval(this.x, this.y, this.diameter, this.diameter);
    }
    public void refreshChairs(){
        int nameRectSize = nameRect.size();
        for (int i = students.size(); i > nameRectSize; i--) {
            this.nameRect.add(new Rectangle());
        }
        for (int i = 0; i < this.students.size(); i++){
            this.chairX = (int)(Math.cos((double)i*angleIncr)*(this.diameter/2+this.diameter/10));
            this.chairY = (int)(Math.sin((double)i*angleIncr)*(this.diameter/2+this.diameter/10));
            if (this.chairY>= 0){
                this.nameRect.get(i).setRect(this.x+this.diameter/2+this.chairX-this.students.get(i).getName().length(),
                        this.y+this.diameter/2+this.chairY+15, this.students.get(i).getName().length()*5, 14);
            }else{
                this.nameRect.get(i).setRect(this.x+this.diameter/2+this.chairX-this.students.get(i).getName().length(),
                        this.y+this.diameter/2+this.chairY-25, this.students.get(i).getName().length()*5, 14);
            }
        }
    }
    /**
     * @param g
     */
    public void drawChair(Graphics g) {
        this.angleIncr = (2.0 * Math.PI / (double) this.students.size());
        if (this.students.size() > 0) {
            for (int i = 0; i < this.students.size(); i++) {
                this.chairX = (int) (Math.cos((double) i * angleIncr) * (this.diameter / 2 + this.diameter / 10));
                this.chairY = (int) (Math.sin((double) i * angleIncr) * (this.diameter / 2 + this.diameter / 10));
                if (this.chairY >= 0) {
                    g.drawString(this.students.get(i).getName(),
                            this.x + this.diameter / 2 + this.chairX - this.students.get(i).getName().length() * 2,
                            this.y + this.diameter / 2 + this.chairY + 25);
                } else {
                    g.drawString(this.students.get(i).getName(),
                            this.x + this.diameter / 2 + this.chairX - this.students.get(i).getName().length() * 2,
                            this.y + this.diameter / 2 + this.chairY - 15);
                }
            }
        }
    }
}