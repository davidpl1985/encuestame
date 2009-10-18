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
import javax.persistence.Embeddable;

/**
 * ProjetCatLocationId.
 *
 * @author Picado, Juan juan@encuestame.org
 * @since October 17, 2009
 */
@Embeddable
public class ProjetCatLocationId implements Serializable {

    private int locateId;
    private int projectId;

    public ProjetCatLocationId() {
    }

    public ProjetCatLocationId(int locateId, int projectId) {
        this.locateId = locateId;
        this.projectId = projectId;
    }

    @Column(name = "locate_id", nullable = false)
    public int getLocateId() {
        return this.locateId;
    }

    public void setLocateId(int locateId) {
        this.locateId = locateId;
    }

    @Column(name = "project_id", nullable = false)
    public int getProjectId() {
        return this.projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof ProjetCatLocationId))
            return false;
        ProjetCatLocationId castOther = (ProjetCatLocationId) other;

        return (this.getLocateId() == castOther.getLocateId())
                && (this.getProjectId() == castOther.getProjectId());
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + this.getLocateId();
        result = 37 * result + this.getProjectId();
        return result;
    }

}
