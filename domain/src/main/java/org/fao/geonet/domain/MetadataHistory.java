package org.fao.geonet.domain;

import org.fao.geonet.entitylistener.MetadataEntityListenerManager;
import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * An entity representing a metadata object in the database. The xml, groups and operations are lazily loaded so accessing then will
 * need to
 * be done in a thread that has a bound EntityManager. Also they can trigger database access if they have not been cached and therefore
 * can
 * cause slowdowns so they should only be accessed in need.
 *
 * @author Jesse
 */
@Entity
@Table(name = MetadataHistory.TABLENAME)
@Access(AccessType.PROPERTY)
@EntityListeners(MetadataEntityListenerManager.class)
@SequenceGenerator(name=MetadataHistory.ID_SEQ_NAME, initialValue=100, allocationSize=1)
public class MetadataHistory extends GeonetEntity {
    public static final String TABLENAME = "MetadataHistory";
    static final String ID_SEQ_NAME = "metadata_history_id_seq";

    private int _id;
    private String _data;
    private int _version;
    private int _userid;
    private Date _datetime;

    /**
     * Get the id of the metadata.
     * @return the id of the metadata
     */
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = ID_SEQ_NAME)
    @Column(nullable = false)
    public int getId() {
        return _id;
    }

    /**
     * Set the id of the metadata. This is a generated value and as such new instances should not have this set as it will simply be
     * ignored and could result in reduced performance.
     * @param _id the id of the metadata
     * @return this entity object
     */
    public MetadataHistory setId(int _id) {
        this._id = _id;
        return this;
    }
    
    public String getData(){
        return _data;
    }
    
    public MetadataHistory setData(String data)
    {
        _data=data;
        return this;
    }
    
    public int getVersion(){
        return _version;
    }
    
    public MetadataHistory setVersion(int version){
        _version=version;
        return this;
    }
    
    public int getUserId(){
        return _userid;
    }
    
    public MetadataHistory setUserId(int id)
    {
        _userid = id;
        return this;
    }
    
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getItemDate(){
        return _datetime;
    }
    
    public MetadataHistory setItemDate(Date date){
        _datetime = date;
        return this;
    }
}
