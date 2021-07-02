package model;

import java.time.LocalDate;

public class UserTM {
    private String username;
    private String userType;
    private String nic;
    private String address;
    private String contactNumber;
    private String email;
    private LocalDate joinedDate;

    public UserTM() {
    }

    public UserTM(String username, String userType, String nic, String address, String contactNumber, String email, LocalDate joinedDate) {
        this.username = username;
        this.userType = userType;
        this.nic = nic;
        this.address = address;
        this.contactNumber = contactNumber;
        this.email = email;
        this.joinedDate = joinedDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(LocalDate joinedDate) {
        this.joinedDate = joinedDate;
    }

    @Override
    public String toString() {
        return "USerTM{" +
                "username='" + username + '\'' +
                ", userType='" + userType + '\'' +
                ", nic='" + nic + '\'' +
                ", address='" + address + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", email='" + email + '\'' +
                ", joinedDate=" + joinedDate +
                '}';
    }
}
