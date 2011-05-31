package me.chebotaev.diploma.generator.model;

import java.util.HashMap;
import java.util.Map;

public class Rating {

    Map<Integer, Integer> moods = new HashMap<Integer, Integer>();

    public void add(int moodId, int value) {
        moods.put(moodId, value);
    }

    public int getValue(int moodId) {
        return moods.get(moodId);
    }

    public Map<Integer, Integer> getMoods() {
        return moods;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for(Map.Entry<Integer, Integer> entry : moods.entrySet()) {
            sb.append(entry.getKey());
            sb.append('-');
            sb.append(entry.getValue());
            sb.append(';');
        }
        sb.append(']');
        return sb.toString();
    }
}
