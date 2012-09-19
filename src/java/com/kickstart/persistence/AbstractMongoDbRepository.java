package com.kickstart.persistence;

import org.bson.types.ObjectId;

import com.mongodb.Mongo;
import com.strategicgains.repoexpress.domain.TimestampedIdentifiable;
import com.strategicgains.repoexpress.event.DefaultTimestampedIdentifiableRepositoryObserver;
import com.strategicgains.repoexpress.mongodb.MongodbRepository;
import com.strategicgains.repoexpress.mongodb.ObjectIdAdapter;

/**
 * @author toddf
 * @since Sep 19, 2012
 */
public abstract class AbstractMongoDbRepository<T extends TimestampedIdentifiable>
extends MongodbRepository<T, ObjectId>
{
	private static final String DATABASE_NAME = "kickstart";

	@SuppressWarnings("unchecked")
    public AbstractMongoDbRepository(Mongo mongo, Class<T> type)
    {
	    super(mongo, DATABASE_NAME, type);
	    initializeObservers();
	    setIdentifierAdapter(new ObjectIdAdapter());
    }

    protected void initializeObservers()
    {
		addObserver(new DefaultTimestampedIdentifiableRepositoryObserver<T>());
    }
}
