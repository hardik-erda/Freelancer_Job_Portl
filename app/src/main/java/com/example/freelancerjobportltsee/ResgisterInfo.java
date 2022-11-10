package com.example.freelancerjobportltsee;

public class ResgisterInfo {
    // String variables for storing info
    private String fname,lname,email,mobile;

    // an empty constructor is required when using Firebase Realtime Database.
    public ResgisterInfo(){

    }

    //created getter and setter methods for all our variables.
    public String getFname(){
        return fname;
    }
    public void setFname(String fname){
        this.fname = fname;
    }

    public String getLname(){
        return lname;
    }
    public void setLname(String lname){
        this.lname = lname;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getMobile(){
        return mobile;
    }
    public void setMobile(String mobile){
        this.mobile = mobile;
    }


}
