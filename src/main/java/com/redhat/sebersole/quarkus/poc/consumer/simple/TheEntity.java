package com.redhat.sebersole.quarkus.poc.consumer.simple;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TheEntity {
	@Id
	public Integer id;
	public String name;
}
