package com.example.onlinefruitvendor.Model;

public class AdminOrders{

    private String name, phone, address, town, street,date, time, totalAmount;

    public AdminOrders(){

    }

    public AdminOrders(String name, String town, String phone, String address, String street, String date, String time, String totalAmount){

        this.name = name;
        this.phone = phone;
        this.address = address;
        this.street = street;
        this.town = town;
        this.date = date;
        this.time = time;
        this.totalAmount = totalAmount;

    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getStreet(){
        return street;
    }

    public void setStreet(String street){
        this.street = street;
    }

    public String getTown(){
        return town;
    }

    public void setTown(String town){
        this.town = town;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String town){
        this.date = date;
    }

    public String getTime(){
        return time;
    }

    public void setTime(String time){
        this.time = time;
    }

    public String getTotalAmount(){
        return totalAmount;
    }

    public void setTotalAmount(String town){
        this.totalAmount = totalAmount;
    }

}
