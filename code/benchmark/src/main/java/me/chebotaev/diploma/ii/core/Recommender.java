package me.chebotaev.diploma.ii.core;

import java.util.Map;

public interface Recommender {

    double compareItems(InformationItem left, InformationItem right);

    double compareItemsMaps(Map<InformationItem, Double> a, Map<InformationItem, Double> b);

    Map<InformationItem, Double> getMostLike(InformationItem item, InformationItemDao dao);

    Map<InformationItem, Double> getMostLike(Map<InformationItem, Double> items, InformationItemDao dao);
}
