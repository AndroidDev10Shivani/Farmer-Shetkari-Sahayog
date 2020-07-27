package com.sample.shetkarisahayogfarmer;

public class DemandHelperClass {
    String name, address, date, farmProduct, quantityRequirement;
    long applicationID;

    public DemandHelperClass() {
    }

    public DemandHelperClass(String name, String address, String date, String farmProduct, String quantityRequirement, long applicationID) {
        this.name = name;
        this.address = address;
        this.date = date;
        this.farmProduct = farmProduct;
        this.quantityRequirement = quantityRequirement;
        this.applicationID = applicationID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFarmProduct() {
        return farmProduct;
    }

    public void setFarmProduct(String farmProduct) {
        this.farmProduct = farmProduct;
    }

    public String getQuantityRequirement() {
        return quantityRequirement;
    }

    public void setQuantityRequirement(String quantityRequirement) {
        this.quantityRequirement = quantityRequirement;
    }

    public long getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(long applicationID) {
        this.applicationID = applicationID;
    }
}
