/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */

package org.opensearch.indices.replication.checkpoint;

import org.opensearch.common.inject.Inject;
import org.opensearch.index.shard.IndexShard;

import java.util.Objects;

public class SegmentReplicationCheckpointPublisher {

    private final PublishAction publishAction;

    @Inject
    public SegmentReplicationCheckpointPublisher(PublishCheckpointAction publishAction) {
        this(publishAction::publish);
    }

    public SegmentReplicationCheckpointPublisher(PublishAction publishAction) {
        this.publishAction = Objects.requireNonNull(publishAction);
    }

    public void publish(IndexShard indexShard) {
        publishAction.publish(indexShard);
    }

    public interface PublishAction {
        void publish(IndexShard indexShard);
    }

    public static final SegmentReplicationCheckpointPublisher EMPTY = new SegmentReplicationCheckpointPublisher(indexShard -> {});
}
