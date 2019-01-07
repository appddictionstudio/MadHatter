
package com.Madhatter.MadHatter.models;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "topic")

public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String topic;
    
    @Column
    private String parent_topic;
    
    @Column
    private String sort_order;

    public Topic() {}

    public Topic(String topic, String parent_topic, String sort_order) {
    	this.topic = topic;
    	this.parent_topic = parent_topic;
    	this.sort_order = sort_order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getCertName() {
        return topic;
    }

    public void setCertName(String topic) {
        this.topic = topic;
    }
    
    public String getParentTopic() {
        return parent_topic;
    }

    public void setParentTopic(String parent_topic) {
        this.parent_topic = parent_topic;
    }
    
    public String getSortOrder() {
        return sort_order;
    }

    public void setSortOrder(String sort_order) {
        this.sort_order = sort_order;
    }
}