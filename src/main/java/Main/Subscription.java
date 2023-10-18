package Main;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.ToString;

@Entity
public class Subscription implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String card_number;
    private String card_name;
    private String cvv_number;
    private String expiry_date;
    private String pin;
    private String fullname;
    private String email;
    private String address;
    private String city;
    private String state;
    private String zipcode;

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
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getCard_number() {
        return card_number;
    }
    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }
    public String getCard_name() {
        return card_name;
    }
    public void setCard_name(String card_name) {
        this.card_name = card_name;
    }
    public String getCvv_number() {
        return cvv_number;
    }
    public void setCvv_number(String cvv_number) {
        this.cvv_number = cvv_number;
    }
    public String getExpiry_date() {
        return expiry_date;
    }
    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }
    public String getPin() {
        return pin;
    }
    public void setPin(String pin) {
        this.pin = pin;
    }
    public String getFullname() {
        return fullname;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getZipcode() {
        return zipcode;
    }
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    
}
