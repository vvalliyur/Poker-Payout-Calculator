/*
 * Custom class of that stores the players name
 * and amount that they won or lost.
 */

public class NameDouble {
    String name;
    Double val;
    
    public NameDouble(String name, Double val) {
        this.name = name;
        this.val = val;
    }
    
    public String getName() {
        return name;
    }
    
    public Double getVal() {
        return val;
    }
    
    public void setVal(Double d) {
        this.val = d;
    }



}
