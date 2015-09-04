package org.fao.geonet.domain;

import com.vividsolutions.jts.util.Assert;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * An id for {@link org.fao.geonet.domain.HarvesterData}.
 *
 * Created by Jesse on 1/23/14.
 */
@Embeddable
@Access(AccessType.PROPERTY)
public class HarvesterDataId implements Serializable {
    private String harvesterUuid;
    private String idKey;

    public HarvesterDataId() {
        // needed for JPA
    }

    public HarvesterDataId(String harvesterUuid, String key) {
        setHarvesterUuid(harvesterUuid);
        setIdKey(key);
    }

    /**
     * The uuid of the harvester that the {@link org.fao.geonet.domain.HarvesterData} belongs to.
     *
     * @return uuid of the harvester that the {@link org.fao.geonet.domain.HarvesterData} belongs to.
     */
    public String getHarvesterUuid() {
        return harvesterUuid;
    }

    /**
     * Set uuid of the harvester that the {@link org.fao.geonet.domain.HarvesterData} belongs to.
     * @param harvesterUuid uuid of the harvester that the {@link org.fao.geonet.domain.HarvesterData} belongs to.
     */
    public void setHarvesterUuid(String harvesterUuid) {
        this.harvesterUuid = harvesterUuid;
    }

    /**
     * Get the key identifying the data entity (within the scope of the harvester).
     *
     * @return the key.
     */
    public String getIdKey() {
        return idKey;
    }

    /**
     * Set the key identifying the data entity (within the scope of the harvester).  Max length is 255
     * @param key  identifying the data entity (within the scope of the harvester).
     */
    @Column(nullable = false, length = 255)
    public void setIdKey(String key) {
        Assert.isTrue(key.length() <= 255);
        this.idKey = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HarvesterDataId that = (HarvesterDataId) o;

        if (!harvesterUuid.equals(that.harvesterUuid)) return false;
        if (!idKey.equals(that.idKey)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = harvesterUuid.hashCode();
        result = 31 * result + idKey.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ID {" +
               "harvesterUuid='" + harvesterUuid + '\'' +
               ", key='" + idKey + '\'' +
               '}';
    }
}
