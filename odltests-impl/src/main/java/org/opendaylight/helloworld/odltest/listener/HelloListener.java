/*
 * Copyright (c) 2015 - 2016 Keren inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.helloworld.odltest.listener;

import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.opendaylight.controller.md.sal.common.api.data.LogicalDatastoreType;
import org.opendaylight.genius.datastoreutils.AsyncDataTreeChangeListenerBase;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.odltests.api.rev170507.OdltestsApi;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloListener extends AsyncDataTreeChangeListenerBase<OdltestsApi, HelloListener>
		implements AutoCloseable {
	private static final Logger LOG = LoggerFactory.getLogger(HelloListener.class);

	private DataBroker databroker;

	public HelloListener(DataBroker databroker) {
		super(OdltestsApi.class, HelloListener.class);
		this.databroker = databroker;
	}

	public void start() {
		LOG.info("{} started.", HelloListener.class.getSimpleName());
		registerListener(LogicalDatastoreType.CONFIGURATION, databroker);
	}

	@Override
	protected void add(InstanceIdentifier<OdltestsApi> key, OdltestsApi dataObjectModification) {
		LOG.info("{} - keren says hi", dataObjectModification.getName());
	}

	@Override
	protected HelloListener getDataTreeChangeListener() {
		return this;
	}

	@Override
	protected InstanceIdentifier<OdltestsApi> getWildCardPath() {
		return InstanceIdentifier.builder(OdltestsApi.class).build();
	}

	@Override
	protected void remove(InstanceIdentifier<OdltestsApi> key, OdltestsApi dataObjectModification) {

	}

	@Override
	protected void update(InstanceIdentifier<OdltestsApi> key, OdltestsApi dataObjectModificationBefore,
			OdltestsApi dataObjectModificationAfter) {

	}

}
