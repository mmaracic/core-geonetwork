package org.fao.geonet.domain;

import java.io.Serializable;
import org.fao.geonet.entitylistener.MetadataEntityListenerManager;
import org.hibernate.annotations.Type;

import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * An entity containing records about changes in metadata elements
 * 
 * @author Marijo
 */
@Entity
@Table(name = MetadataHistory.TABLENAME)
@Access(AccessType.PROPERTY)
//@EntityListeners(MetadataHistoryEntityListenerManager.class)
@SequenceGenerator(name=MetadataHistory.ID_SEQ_NAME, initialValue=100, allocationSize=1)
public class MetadataHistory extends GeonetEntity implements Serializable {
    public static final String TABLENAME = "MetadataHistory";
    static final String ID_SEQ_NAME = "metadata_history_id_seq";

    private int _id;
    private int _metadata_id;
    private String _data;
    private int _version;
    private User _user;
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
    
    @Column(nullable = false)
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Type(type="org.hibernate.type.StringClobType") // this is a work around for postgres so postgres can correctly load clobs
    public String getData(){
        return _data;
    }
    
    public MetadataHistory setData(String data){
        _data=data;
        return this;
    }
    
    @Column(nullable = false)
    public int getVersion(){
        return _version;
    }
    
    public MetadataHistory setVersion(int version){
        _version=version;
        return this;
    }
    
    @ManyToOne
    public User getItemUser(){
        return _user;
    }
    
    public MetadataHistory setItemUser(User user){
        _user = user;
        return this;
    }
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(nullable = false)
    public Date getItemDate(){
        return _datetime;
    }
    
    public MetadataHistory setItemDate(Date date){
        _datetime = date;
        return this;
    }
    
    @Column(nullable = false)
    public int getMetadataId(){
        return _metadata_id;
    }
    
    public MetadataHistory setMetadataId(int metadataId){
        _metadata_id=metadataId;
        return this;
    }
}
