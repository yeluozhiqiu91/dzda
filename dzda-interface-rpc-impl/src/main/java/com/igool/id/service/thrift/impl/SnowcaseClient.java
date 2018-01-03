package com.igool.id.service.thrift.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hazelcast.core.HazelcastInstance;
import com.noctarius.snowcast.Snowcast;
import com.noctarius.snowcast.SnowcastSystem;

@Service
public class SnowcaseClient {

	@Autowired
	private HazelcastInstance instance;

	private Snowcast snowcast;

	@PostConstruct
	public void initClient() {
		snowcast = SnowcastSystem.snowcast(instance);
	}


	public Snowcast getSnowcast() {
		return snowcast;
	}
}
