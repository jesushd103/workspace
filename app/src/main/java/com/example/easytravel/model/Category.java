package com.example.easytravel.model;

import java.util.List;

public class Category {

    private String alias;
    private String title;
    private List<String> parent_aliases;
    private List<String> country_whitelist;
    private List<String> country_blacklist;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getParent_aliases() {
        return parent_aliases;
    }

    public void setParent_aliases(List<String> parent_aliases) {
        this.parent_aliases = parent_aliases;
    }

    public List<String> getCountry_whitelist() {
        return country_whitelist;
    }

    public void setCountry_whitelist(List<String> country_whitelist) {
        this.country_whitelist = country_whitelist;
    }

    public List<String> getCountry_blacklist() {
        return country_blacklist;
    }

    public void setCountry_blacklist(List<String> country_blacklist) {
        this.country_blacklist = country_blacklist;
    }

}