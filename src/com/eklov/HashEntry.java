package com.eklov;

public class HashEntry {

    private String key;
    private String value;
    private HashEntry next;

    HashEntry(String key, String value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }

    public String getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }

    public HashEntry getNext() {
        return next;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setNext(HashEntry next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "{ " + getKey() + " | " + getValue() + " }";
    }
}
