
package com.ushahidi.android.app.checkin;

/**
 * Created by IntelliJ IDEA. User: Ahmed Date: 2/17/11 Time: 6:53 PM To change
 * this template use File | Settings | File Templates.
 */
public class Checkin {
    private String id;

    private String user;

    private String loc;

    private String msg;

    private String date;

    private String lat;

    private String lon;

    private String name;

    private String image;
    
    private String thumbnail;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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
        this.name = CheckinUtil.getCheckinUser(name);
    }

    public String getName() {
        return this.name;
    }

    public void setImage(String checkinId) {
        
        this.image = CheckinUtil.getCheckinMedia(checkinId);
    }

    public String getImage() {
        return this.image;
    }
    
    public void setThumbnail(String checkinId)  {
        if(CheckinUtil.getCheckinThumbnail(checkinId) !=null)
            this.thumbnail = CheckinUtil.getCheckinThumbnail(checkinId);
    }
    
    public String getThumbnail() {
        return this.thumbnail;
    }
}
