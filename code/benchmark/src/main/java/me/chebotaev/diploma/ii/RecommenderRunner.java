package me.chebotaev.diploma.ii;

import me.chebotaev.diploma.ii.core.InformationItem;
import me.chebotaev.diploma.ii.impl.model.InMemoryDao;

public class RecommenderRunner {

    public static void main(String[] args) {

        InMemoryDao dao = new InMemoryDao();

        InformationItem ii = dao.createInformationItem();

    }
}
