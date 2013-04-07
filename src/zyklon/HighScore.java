/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zyklon;


public class HighScore implements Comparable {
    public int score;
    public String name;
    
    @Override
    public int compareTo(Object t) {
        if (t instanceof HighScore) {
            HighScore comp = (HighScore) t;
            
            return comp.score - this.score;
        }
        
        return 0;
    }    
}
