package org.restexpress.scaffold.mongodb.persistence;

import org.restexpress.scaffold.mongodb.domain.SampleOidEntity;

import com.mongodb.MongoClient;
import com.strategicgains.repoexpress.mongodb.MongodbEntityRepository;

public class SampleOidEntityRepository
extends MongodbEntityRepository<SampleOidEntity>
{
	@SuppressWarnings("unchecked")
    public SampleOidEntityRepository(MongoClient mongo, String dbName)
	{
		super(mongo, dbName, SampleOidEntity.class);
	}
}
