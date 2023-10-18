package Main;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.ToString;

@Entity
public class Survey implements Serializable {
    
    @Id
    private String question1;
    private String question2;
    private String question3;
    private String question4;
    private String question5;
    private String question6;
    
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
     * @return the question1
     */
    public String getQuestion1() {
        return question1;
    }

    /**
     * @param question1 the question1 to set
     */
    public void setQuestion1(String question1) {
        this.question1 = question1;
    }

    /**
     * @return the question2
     */
    public String getQuestion2() {
        return question2;
    }

    /**
     * @param question2 the question2 to set
     */
    public void setQuestion2(String question2) {
        this.question2 = question2;
    }

    /**
     * @return the question3
     */
    public String getQuestion3() {
        return question3;
    }

    /**
     * @param question3 the question3 to set
     */
    public void setQuestion3(String question3) {
        this.question3 = question3;
    }

    /**
     * @return the question4
     */
    public String getQuestion4() {
        return question4;
    }

    /**
     * @param question4 the question4 to set
     */
    public void setQuestion4(String question4) {
        this.question4 = question4;
    }

    /**
     * @return the question5
     */
    public String getQuestion5() {
        return question5;
    }

    /**
     * @param question5 the question5 to set
     */
    public void setQuestion5(String question5) {
        this.question5 = question5;
    }

    /**
     * @return the question6
     */
    public String getQuestion6() {
        return question6;
    }

    /**
     * @param question6 the question6 to set
     */
    public void setQuestion6(String question6) {
        this.question6 = question6;
    }
}
