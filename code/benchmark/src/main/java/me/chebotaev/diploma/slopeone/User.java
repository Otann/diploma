package me.chebotaev.diploma.slopeone;

class User {
    String content;
    public User(String s) {
        content = s;
    }

    public int hashCode() { return content.hashCode();}
    public String toString() { return content; }
}
