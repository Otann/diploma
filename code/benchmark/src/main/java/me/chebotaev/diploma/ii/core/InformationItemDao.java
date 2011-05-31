package me.chebotaev.diploma.ii.core;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

public interface InformationItemDao {

    InformationItem createInformationItem();

    void deleteInformationItem(InformationItem item);

    InformationItem getByUUID(UUID uuid);

    void setMeta(InformationItem item, String key, String value);
    void removeMeta(InformationItem item, String key);

    void setComponentWeight(InformationItem item, InformationItem component, Double weight);
    void removeComponent(InformationItem item, InformationItem component);

    Collection<InformationItem> multigetComponents(Collection<InformationItem> items);
    Collection<InformationItem> multigetParents(Collection<InformationItem> items);
    Collection<InformationItem> multigetByUUID(Collection<UUID> uuids);
    Collection<InformationItem> multigetByMeta(String key, String value);
    Map<UUID, String> searchByMetaPrefix(String key, String prefix);

}
