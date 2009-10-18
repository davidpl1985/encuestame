/**
 * encuestame: system online surveys Copyright (C) 2009 encuestame Development
 * Team
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of version 3 of the GNU General Public License as published by the
 * Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 * Place, Suite 330, Boston, MA 02111-1307 USA
 */

package org.encuestame.core.persistence.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CatLocation.
 *
 * @author Picado, Juan juan@encuestame.org
 * @since October 17, 2009
 */
@Entity
@Table(name = "cat_location")
public class CatLocation implements Serializable {

    private int locateId;
    private String tidtype;
    private String description;
    private int level;
    private String active;
    private Float lat;
    private Float lng;

    public CatLocation() {
    }

    public CatLocation(int locateId, String tidtype, String description,
            int level) {
        this.locateId = locateId;
        this.tidtype = tidtype;
        this.description = description;
        this.level = level;
    }

    public CatLocation(int locateId, String tidtype, String description,
            int level, String active, Float lat, Float lng) {
        this.locateId = locateId;
        this.tidtype = tidtype;
        this.description = description;
        this.level = level;
        this.active = active;
        this.lat = lat;
        this.lng = lng;
    }

    @Id
    @Column(name = "locate_id", unique = true, nullable = false)
    public int getLocateId() {
        return this.locateId;
    }

    public void setLocateId(int locateId) {
        this.locateId = locateId;
    }

    @Column(name = "tidtype", nullable = false, length = 10)
    public String getTidtype() {
        return this.tidtype;
    }

    public void setTidtype(String tidtype) {
        this.tidtype = tidtype;
    }

    @Column(name = "description", nullable = false)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "level", nullable = false)
    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Column(name = "active", length = 2)
    public String getActive() {
        return this.active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    @Column(name = "lat", precision = 10, scale = 6)
    public Float getLat() {
        return this.lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    @Column(name = "lng", precision = 10, scale = 6)
    public Float getLng() {
        return this.lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }

}
