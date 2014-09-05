package org.restexpress.scaffold.mongodb.persistence;

import org.restexpress.scaffold.mongodb.domain.SampleUuidEntity;

import com.mongodb.MongoClient;
import com.strategicgains.repoexpress.mongodb.MongodbUuidEntityRepository;

public class SampleUuidEntityRepository
extends MongodbUuidEntityRepository<SampleUuidEntity>
{
	@SuppressWarnings("unchecked")
    public SampleUuidEntityRepository(MongoClient mongo, String dbName)
	{
		super(mongo, dbName, SampleUuidEntity.class);
	}
}
