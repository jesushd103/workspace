package com.example.easytravel.model;
import java.util.Date;

public class Event {

    private int attending_count;
    private String category;
    private String cost;
    private String cost_max;
    private String description;
    private String event_site_url;
    private String id;
    private String image_url;
    private int interested_count;
    private boolean is_canceled;
    private boolean is_free;
    private boolean is_official;
    private double latitude;
    private double longitude;
    private String name;
    private String tickets_url;
    private Date time_end;
    private Date time_start;
    private Location location;
    private String business_id;

    public void setAttending_count(int attending_count) {
        this.attending_count = attending_count;
    }
    public int getAttending_count() {
        return attending_count;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public String getCategory() {
        return category;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
    public String getCost() {
        return cost;
    }

    public void setCost_max(String cost_max) {
        this.cost_max = cost_max;
    }
    public String getCost_max() {
        return cost_max;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    public void setEvent_site_url(String event_site_url) {
        this.event_site_url = event_site_url;
    }
    public String getEvent_site_url() {
        return event_site_url;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
    public String getImage_url() {
        return image_url;
    }

    public void setInterested_count(int interested_count) {
        this.interested_count = interested_count;
    }
    public int getInterested_count() {
        return interested_count;
    }

    public void setIs_canceled(boolean is_canceled) {
        this.is_canceled = is_canceled;
    }
    public boolean getIs_canceled() {
        return is_canceled;
    }

    public void setIs_free(boolean is_free) {
        this.is_free = is_free;
    }
    public boolean getIs_free() {
        return is_free;
    }

    public void setIs_official(boolean is_official) {
        this.is_official = is_official;
    }
    public boolean getIs_official() {
        return is_official;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public double getLatitude() {
        return latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public double getLongitude() {
        return longitude;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setTickets_url(String tickets_url) {
        this.tickets_url = tickets_url;
    }
    public String getTickets_url() {
        return tickets_url;
    }

    public void setTime_end(Date time_end) {
        this.time_end = time_end;
    }
    public Date getTime_end() {
        return time_end;
    }

    public void setTime_start(Date time_start) {
        this.time_start = time_start;
    }
    public Date getTime_start() {
        return time_start;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    public Location getLocation() {
        return location;
    }

    public void setBusiness_id(String business_id) {
        this.business_id = business_id;
    }
    public String getBusiness_id() {
        return business_id;
    }

}