package com.mp.piggymetrics.account.service;

import org.jnosql.artemis.ConfigurationUnit;
import org.jnosql.diana.api.document.DocumentCollectionManager;
import org.jnosql.diana.api.document.DocumentCollectionManagerFactory;
import org.jnosql.diana.mongodb.document.MongoDBDocumentCollectionManager;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@ApplicationScoped
public class EntityManagerProducer {

    private static final String DATABASE = "accounts_db";

    @Inject
    @ConfigurationUnit(name = "document")
    private DocumentCollectionManagerFactory<MongoDBDocumentCollectionManager> managerFactory;

    @Produces
    public DocumentCollectionManager getEntityManager() {
        return managerFactory.get(DATABASE);
    }

    public void close(@Disposes DocumentCollectionManager entityManager) {
        entityManager.close();
    }

}
