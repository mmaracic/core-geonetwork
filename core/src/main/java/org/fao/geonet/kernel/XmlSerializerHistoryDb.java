/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fao.geonet.kernel;

import java.sql.SQLException;
import jeeves.server.context.ServiceContext;
import jeeves.xlink.Processor;
import org.fao.geonet.domain.Metadata;
import org.jdom.Element;

/**
 *
 * @author marijom
 */
public class XmlSerializerHistoryDb extends XmlSerializer {

    /**
     * Retrieves the xml element whose id matches the given one. The element is read from the database
     * @param id
     *
     * @return
     * @throws Exception
     */
	protected Element internalSelect(String id, boolean isIndexingTask) throws Exception {
		Element rec = super.internalSelect(id, isIndexingTask);
		if (rec != null) return (Element) rec.detach();
		else return null;
	}

    /**
     * Retrieves the xml element which id matches the given one.
     * @param id
     * @return
     * @throws Exception
     */
	public Element select(ServiceContext context, String id) throws Exception {
		Element rec = internalSelect(id, false);
		if (resolveXLinks()) Processor.detachXLink(rec, context);
		return rec;
	}

    /**
     * Retrieves the xml element which id matches the given one. XLinks are NOT resolved even if they are config'd 
		 * on - this is used when you want to do XLink processing yourself.
     *
     *
     * @param id
     * @return
     * @throws Exception
     */
	public Element selectNoXLinkResolver(String id, boolean isIndexingTask) throws Exception {
		return internalSelect(id, isIndexingTask);
	}

    /**
     * Inserts a metadata into the database. Does not insert the metadata 
		 * into the history table. Instead this is done when an update
		 * is generated on the metadata (eg. from editor).
     *
     *
     * @param newMetadata the metadata to insert
     * @param dataXml the data to set on the metadata before saving
     * @param context a service context
     * @return the saved metadata
     * @throws SQLException
     */
    public Metadata insert(final Metadata newMetadata, final Element dataXml,ServiceContext context) throws SQLException {
		return insertDb(newMetadata, dataXml, context);
	}

    /**
     *  Updates an xml element in the database and the history table. 
		 *  The new metadata replaces the old metadata in the database. The old
		 *  metadata in the database is added to the hostory table the first time
		 *  an update is generated.
     *
     * @param id
     * @param xml
     * @param changeDate
     * @param updateDateStamp
     * @param context
     * @throws SQLException, SVNException
     */
	public void update(String id, Element xml, String changeDate, boolean updateDateStamp, String uuid, ServiceContext context) throws Exception {
		MetadataHistoryDbManager histMan = context.getBean(MetadataHistoryDbManager.class);
		// old XML comes from the database
		updateDb(id, xml, changeDate, xml.getQualifiedName(), updateDateStamp, uuid);

		histMan.addMetadataVersion(id, context, false);
	}

    /**
     * Deletes a metadata record given its id. The metadata record is deleted
		 * from 'table' and from the subversion repo (if present).
     *
     *
     *
     * @param id
     * @param context
     * @throws SQLException, SVNException
     */
    public void delete(String id, ServiceContext context) throws Exception {
            deleteDb(id);
            MetadataHistoryDbManager histMan = context.getBean(MetadataHistoryDbManager.class);
            histMan.deleteMetadataVersions(id, context);
    }    
}
