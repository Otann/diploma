package me.chebotaev.diploma.ii.core;

import java.util.Map;
import java.util.UUID;

public interface InformationItem {

    UUID getUUID();

    Map<String, String> getMetaMap();
    String getMeta(String key);

    Map<InformationItem, Double> getComponents();
    Double getComponentWeight(InformationItem component);

    Map<InformationItem, Double> getParents();
    Double getParentWeight(InformationItem parent);

}