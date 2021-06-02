package com.bsuir.weapons.data;

import java.util.*;

public class ResultSet {

    private final Map<String, Object> objectMap;

    public ResultSet(Map<String, Object> objectMap) {
        this.objectMap = objectMap;
    }

    public ResultSet() {
        this.objectMap = new HashMap<>();
    }

    public void add(String key, Object value) {
        objectMap.put(key, value);
    }

    public void addAll(Map<String, Object> map) {
        objectMap.putAll(map);
    }

    public int getSize() {
        return objectMap.size();
    }

    public Object getObject(String key) {
        return objectMap.get(key);
    }

    public String getString(String key) {
        return (String) objectMap.get(key);
    }

    public Integer getInteger(String key) {
        return (Integer) objectMap.get(key);
    }

    public Float getFloat(String key) {
        return (Float) objectMap.get(key);
    }

    public Boolean getBoolean(String key) {
        return (Boolean) objectMap.get(key);
    }
}
