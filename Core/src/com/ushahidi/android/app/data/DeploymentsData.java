package com.ushahidi.android.app.data;

public class DeploymentsData {
    private String id;

    private String name;

    private String desc;

    private String url;

    private String date;

    private String lat;

    private String lon;

    private String catId;
    
    private String active;


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getActive() {
        return this.active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
    
    public void setCatId(String catId) {
        
        this.catId = catId;
    }

    public String getCatId() {
        return this.catId;
    }
    
}
