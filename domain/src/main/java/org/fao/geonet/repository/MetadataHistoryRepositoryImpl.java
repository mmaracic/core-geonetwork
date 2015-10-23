/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fao.geonet.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author marijom
 */
public class MetadataHistoryRepositoryImpl implements MetadataHistoryRepository{

    @PersistenceContext
    EntityManager _entityManager;


}
