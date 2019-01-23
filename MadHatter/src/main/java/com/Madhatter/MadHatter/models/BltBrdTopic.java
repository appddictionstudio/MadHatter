package com.Madhatter.MadHatter.models;

import java.io.Serializable;


import javax.persistence.*;


@Entity
@Table(name="BLT_BRD_TOPIC")
@NamedQuery(name="BltBrdTopic.findAll", query="SELECT b FROM BltBrdTopic b")
@SequenceGenerator(name = "BLT_BRD_TOPIC_SEQUENCE", sequenceName = "BLT_BRD_TOPIC_SEQ", allocationSize = 1)
public class BltBrdTopic implements Serializable{
		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BLT_BRD_TOPIC_SEQUENCE")
		private long id;
		
		@Lob
		private String description;
		
		@Column(name="TITLE")
		private String title;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}
}
