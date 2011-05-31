package me.chebotaev.diploma.generator.model;

import java.util.HashSet;
import java.util.Set;

public class Tag {

    static Set<Tag> all = new HashSet<Tag>();
    static int newId = 1;

    int id;
    String name = null;

    public Tag(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name == null ? "Tag #" + id : String.format("Tag #%d (%s)", id, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        if (id != tag.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
