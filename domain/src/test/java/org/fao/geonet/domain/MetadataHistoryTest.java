/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fao.geonet.domain;

import java.util.Calendar;
import java.util.Date;
import org.fao.geonet.repository.AbstractSpringDataTest;
import org.fao.geonet.repository.MetadataHistoryRepository;
import org.fao.geonet.repository.UserRepository;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author marijom
 */
public class MetadataHistoryTest extends AbstractSpringDataTest{
    
    @Autowired
    private MetadataHistoryRepository mrepo;
    
    @Autowired
    private UserRepository urepo;
    
    /**
     * Test of getId method, of class MetadataHistory.
     */
    @Test
    public void insertTtest() {
        User u = new User();
        u.setName("Test").setSurname("User").setUsername("tuser");
        urepo.saveAndFlush(u);
        
        Calendar c = Calendar.getInstance();
        MetadataHistory mh = new MetadataHistory();
        mh.setData("Test metadata").setItemDate(c.getTime()).setItemUser(u).setMetadataId(7).setVersion(3);
        mrepo.saveAndFlush(mh);
    }
}
