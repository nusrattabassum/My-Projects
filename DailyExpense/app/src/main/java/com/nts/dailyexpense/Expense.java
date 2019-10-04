package com.nts.dailyexpense;

public class Expense {

    private int id;
    //private String type;
    private String amount;
    private String date;
    private String time;
    //private byte[] image;

    public Expense() {
    }

    public Expense(int id, String amount, String date, String time){
        this.id = id;
        //this.type = type;
        this.amount = amount;
        this.date = date;
        this.time = time;
        //this.image = image;
    }

    public int getId() {
        return id;
    }

    //public String getType() { return type; }

    public String getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

//    public byte[] getImage() { return image; }
//
//    public void setImage(byte[] image) {
//        this.image = image;
//    }
}
