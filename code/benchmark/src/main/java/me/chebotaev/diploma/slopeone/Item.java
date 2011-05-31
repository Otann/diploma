package me.chebotaev.diploma.slopeone;

public class Item {
    String content;
    public Item(String s) {
        content = s;
    }
    public int hashCode() { return content.hashCode();}
    public String toString() { return content; }
}
