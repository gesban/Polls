package com.loopcupcakes.apps.polls.model.entities.parse;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by evin on 1/26/16.
 */
@ParseClassName("Topic")
public class Topic extends ParseObject{

    public Topic(){

    }

    public String getName(){
        return getString("name");
    }

    public void setName(String name){
        put("name", name);
    }

    public String getDescription(){
        return getString("description");
    }

    public void setDescription(String description){
        put("description", description);
    }

    public String getDelimiter(){
        return getString("delimiter");
    }

    public void setDelimiter(String delimiter){
        put("delimiter", delimiter);
    }

    public Integer getYear(){
        return getInt("year");
    }

    public void setYear(int year){
        put("year", year);
    }

    public Integer getPriority(){
        return getInt("priority");
    }

    public void setPriority(int priority){
        put("priority", priority);
    }

    public void setDocs(String docs){
        put("docs", docs);
    }

    public String getDocs(){
        return getString("docs");
    }
}
