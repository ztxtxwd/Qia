package com.github.qia.pojo;

import java.sql.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(solrCoreName="core2")
public class Area {

	@Id
	private int id;
	
	private String qTitle;
	
	private String qVideo;
	
	private String qDetail;
	
	private String qUser;
	
	private String comments;
}
