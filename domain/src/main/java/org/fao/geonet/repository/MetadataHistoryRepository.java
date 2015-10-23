/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fao.geonet.repository;

import org.fao.geonet.domain.MetadataHistory;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author marijom
 */
public interface MetadataHistoryRepository extends GeonetRepository<MetadataHistory, Integer>, JpaSpecificationExecutor<MetadataHistory>{
    
}
