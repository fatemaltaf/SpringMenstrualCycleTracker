package Main;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
//@SecondaryTable(name="Registration")
public class Tracker implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer tracker_id;
    private String start_date;
    private String last_date;
    private String blood_flow;
    private String pain;
    private String emotions;
    // private String email;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name="user_id")
    private Registration registration;
    
    public Registration getRegistration(){
        return registration;
    }

    public void setRegistration(Registration registration){
        this.registration=registration;
    }

    /**
     * @return the start_date
     */
    public String getStart_date() {
        return start_date;
    }

    /**
     * @param start_date the start_date to set
     */
    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    /**
     * @return the last_date
     */
    public String getLast_date() {
        return last_date;
    }

    /**
     * @param last_date the last_date to set
     */
    public void setLast_date(String last_date) {
        this.last_date = last_date;
    }

    /**
     * @return the blood_flow
     */
    public String getBlood_flow() {
        return blood_flow;
    }

    /**
     * @param blood_flow the blood_flow to set
     */
    public void setBlood_flow(String blood_flow) {
        this.blood_flow = blood_flow;
    }

    /**
     * @return the pain
     */
    public String getPain() {
        return pain;
    }

    /**
     * @param pain the pain to set
     */
    public void setPain(String pain) {
        this.pain = pain;
    }

    /**
     * @return the emotions
     */
    public String getEmotions() {
        return emotions;
    }

    /**
     * @param emotions the emotions to set
     */
    public void setEmotions(String emotions) {
        this.emotions = emotions;
    }
    
    

}
