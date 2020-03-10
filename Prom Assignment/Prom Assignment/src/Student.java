/** Student class 
 *  Sherwin Chiu and Kyro Nassif
 *  Student object created for use in sorting tables
 *  2/13/2020
 */
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

class Student implements Comparator<Student>{
    private String name;
    private String id;
    private ArrayList<Student> partners;
    private boolean paid;
    private ArrayList<Student> undesired;
    private ArrayList<String> accommodations;
    public Student(String name, String id, ArrayList<Student> partners){ //constructor
        this.name = name;
        this.id = id;
        this.partners = partners;
    }
    public Student(String name, String id){ // constructor
        this.name = name;
        this.id = id;
    }
    /**
     * @return String
     */
    public String getName(){
        return this.name;
    }
    /**
     * @return String
     */
    public String getId(){
        return id;
    }
    /**
     * @return ArrayList<Student>
     */
    public ArrayList<Student> getPartners(){
        return this.partners;
    }
    /**
     * @param partners
     */
    public void setPartners(ArrayList<Student> partners){
        this.partners = partners;
    }
    /**
     * @return ArrayList<Student>
     */
    public ArrayList<Student> getUndesired(){
        return this.undesired;
    }
    /**
     * @param undesired
     */
    public void setUndesired(ArrayList<Student> undesired){
        this.undesired = undesired;
    }
    /**
     * @return boolean
     */
    public boolean hasPaid(){
        return this.paid;
    }
    /**
     * @param paid
     */
    public void setPaid(boolean paid){
        this.paid = paid;
    }
    /**
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name) && Objects.equals(id, student.id);
    }
    /**
     * @param accommodations
     */
    public void setAccommodations(ArrayList<String> accommodations){
        this.accommodations = accommodations;
    }
    /**
     * @param s1
     * @param s2
     * @return int
     */
    @Override
    public int compare(Student s1, Student s2) {
        if (Integer.parseInt(s1.getId()) > Integer.parseInt(s2.getId()))
            return 1;
        else if (s1.getId().equals(s2.getId()))
            return 0;
        else
            return -1;
    }
}