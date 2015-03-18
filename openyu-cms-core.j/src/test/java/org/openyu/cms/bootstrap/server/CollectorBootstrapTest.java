package org.openyu.cms.bootstrap.server;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.openyu.commons.bean.BaseCollector;
import org.openyu.commons.bootstrap.CollectorBootstrap;

public class CollectorBootstrapTest {

	private static String[] configLocations = new String[] {
			"applicationContext-init.xml",//
			"META-INF/applicationContext-commons-core-collector.xml",//
			"META-INF/applicationContext-cms-core-collector.xml",//
	};

	@Test
	public void main() {
		// writeToSerFromXml
		CollectorBootstrap.main(configLocations);
	}

	@Test
	public void readFromSer() {
		List<BaseCollector> result = CollectorBootstrap
				.readFromSer(configLocations);
		//
		assertTrue(result.size() > 0);
	}
}
