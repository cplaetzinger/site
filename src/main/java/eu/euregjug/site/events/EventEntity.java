/*
 * Copyright 2015 EuregJUG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.euregjug.site.events;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Represents events for the EuregJUG.
 *
 * @author Michael J. Simons, 2015-12-26
 */
@Entity
@Table(
	name = "events",
	uniqueConstraints = {
	    @UniqueConstraint(name = "events_uk", columnNames = {"held_on", "name"})
	}
)
public class EventEntity implements Serializable {
    
    /**
     * Types of events
     */
    public static enum Type {

	talk, meetup
    }

    private static final long serialVersionUID = 2005305860095134425L;

    /**
     * Primary key of this event.
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "events_id_seq_generator")
    @SequenceGenerator(name = "events_id_seq_generator", sequenceName = "events_id_seq")
    private Integer id;

    /**
     * Date and time when this event will be held or was held.
     */
    @Column(name = "held_on", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar heldOn;

    /**
     * Name of this event. Must be unique on a given {@link #heldOn date}.
     */
    @Column(name = "name", length = 512, nullable = false)
    private String name;

    /**
     * Description of this event.
     */
    @Column(name = "description", length = 2048, nullable = false)
    private String description;

    /**
     * A flag if a guest needs to register for this event. Defaults to
     * {@code false}.
     */
    @Column(name = "needs_registration", nullable = false)
    private boolean needsRegistration = false;
    
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type = Type.talk;

    /**
     * Needed for Hibernate, not to be called by application code.
     */
    protected EventEntity() {
    }

    /**
     * Creates a new Event on the given {@link #heldOn date} with the name
     * {@code name}.
     *
     * @param heldOn Date for the new event (can be in the past).
     * @param name Name for the new event.
     * @param description Description for the new event.
     */
    public EventEntity(Calendar heldOn, String name, String description) {
	this.heldOn = heldOn;
	this.name = name;
	this.description = description;
    }
    
    public Integer getId() {
	return id;
    }

    public Calendar getHeldOn() {
	return heldOn;
    }

    public String getName() {
	return name;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public boolean isNeedsRegistration() {
	return needsRegistration;
    }

    public void setNeedsRegistration(boolean needsRegistration) {
	this.needsRegistration = needsRegistration;
    }

    public Type getType() {
	return type;
    }

    public void setType(Type type) {
	this.type = type;
    }
    
    @Override
    public int hashCode() {
	int hash = 7;
	hash = 29 * hash + Objects.hashCode(this.heldOn);
	hash = 29 * hash + Objects.hashCode(this.name);
	return hash;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	final EventEntity other = (EventEntity) obj;
	if (!Objects.equals(this.name, other.name)) {
	    return false;
	}
	return Objects.equals(this.heldOn, other.heldOn);
    }
}