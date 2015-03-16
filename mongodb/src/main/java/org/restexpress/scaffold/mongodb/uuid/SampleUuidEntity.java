package org.restexpress.scaffold.mongodb.uuid;

import org.restexpress.plugin.hyperexpress.Linkable;

import com.strategicgains.repoexpress.mongodb.AbstractUuidMongodbEntity;

/**
 * This is a sample entity identified by a UUID (instead of a MongoDB ObjectID).
 * It also contains createdAt and updatedAt properties that are automatically maintained
 * by the persistence layer (SampleUuidEntityRepository).
 */
public class SampleUuidEntity
extends AbstractUuidMongodbEntity
implements Linkable
{
	public SampleUuidEntity()
	{
	}
}
