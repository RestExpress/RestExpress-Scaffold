package org.restexpress.scaffold.mongodb.service;

import java.util.List;

import org.restexpress.common.query.QueryFilter;
import org.restexpress.common.query.QueryOrder;
import org.restexpress.common.query.QueryRange;
import org.restexpress.scaffold.mongodb.domain.SampleUuidEntity;
import org.restexpress.scaffold.mongodb.persistence.SampleUuidEntityRepository;

import com.strategicgains.repoexpress.domain.Identifier;
import com.strategicgains.syntaxe.ValidationEngine;

/**
 * This is the 'service' or 'business logic' layer, where business logic, syntactic and semantic
 * domain validation occurs, along with calls to the persistence layer.
 */
public class SampleUuidEntityService
{
	private SampleUuidEntityRepository samples;
	
	public SampleUuidEntityService(SampleUuidEntityRepository samplesRepository)
	{
		super();
		this.samples = samplesRepository;
	}

	public SampleUuidEntity create(SampleUuidEntity entity)
	{
		ValidationEngine.validateAndThrow(entity);
		return samples.create(entity);
	}

	public SampleUuidEntity read(Identifier id)
    {
		return samples.read(id);
    }

	public void update(SampleUuidEntity entity)
    {
		ValidationEngine.validateAndThrow(entity);
		samples.update(entity);
    }

	public void delete(Identifier id)
    {
		samples.delete(id);
    }

	public List<SampleUuidEntity> readAll(QueryFilter filter, QueryRange range, QueryOrder order)
    {
		return samples.readAll(filter, range, order);
    }

	public long count(QueryFilter filter)
    {
		return samples.count(filter);
    }
}
