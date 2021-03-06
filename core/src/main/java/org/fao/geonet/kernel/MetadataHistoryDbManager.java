/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fao.geonet.kernel;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import jeeves.server.UserSession;
import jeeves.server.context.ServiceContext;
import jeeves.transaction.AfterCommitTransactionListener;
import jeeves.transaction.BeforeRollbackTransactionListener;
import org.fao.geonet.domain.MetadataHistory;
import org.fao.geonet.domain.User;
import org.fao.geonet.repository.MetadataHistoryRepository;
import org.fao.geonet.repository.UserRepository;
import org.fao.geonet.repository.specification.MetadataHistorySpecs;
import org.fao.geonet.utils.Xml;
import org.jdom.Element;
import org.springframework.transaction.TransactionStatus;

/**
 *
 * @author marijom
 */
public class MetadataHistoryDbManager implements AfterCommitTransactionListener, BeforeRollbackTransactionListener {
    // configure via setter in Geonetwork app
    private ServiceContext context;

    @Override
    public void afterCommit(TransactionStatus transaction) {
        return;
    }

    @Override
    public void beforeRollback(TransactionStatus transaction) {
        return;
    }
    
    public void setContext(ServiceContext context) {
        this.context = context;
    }
    
    public void startMetadataVersioning(final String strId, final ServiceContext context) throws Exception {
        deleteMetadataVersions(strId, context);
        addMetadataVersion(strId, context, true);
    }
    
    public void addMetadataVersion(final String strId, final ServiceContext context, boolean addFirst) throws Exception {
        UserSession uSess = context.getUserSession();
        MetadataHistoryRepository mdhRepo = context.getBean(MetadataHistoryRepository.class);
        UserRepository uRepo = context.getBean(UserRepository.class);
        DataManager dataMan = context.getBean(DataManager.class);
               
        User user = uSess.getPrincipal();
        User managedUser = uRepo.findOne(user.getId());
        String ipAddr = context.getIpAddress();
        Calendar cal = Calendar.getInstance();
        Date now = cal.getTime();
        
        try{
            Integer id = Integer.parseInt(strId);
            Element md = dataMan.getMetadata(strId);
            String mdString = Xml.getString(md);
            List<MetadataHistory> histories = mdhRepo.findAll(MetadataHistorySpecs.hasMetadataId(id));
            Integer version = 1;
            MetadataHistory prevHistory = null;
            if (histories.size()>0){
                prevHistory = mdhRepo.findOne(MetadataHistorySpecs.hasMaximumVersion(id));
                version = prevHistory.getVersion()+1;
            }
            if ((addFirst &&  prevHistory==null) || prevHistory!=null){
                if (prevHistory==null || mdString.compareTo(prevHistory.getData())!=0){
                    MetadataHistory mdh = new MetadataHistory();
                    mdh.setVersion(version);
                    mdh.setData(mdString);
                    mdh.setMetadataId(id);
                    mdh.setItemDate(now);
                    mdh.setItemUser(managedUser);
                    mdhRepo.save(mdh);
                }
            }
        } catch(NumberFormatException ex){
            ex.printStackTrace();
        }
    }
    
    public void deleteMetadataVersions(final String strId, final ServiceContext context) throws Exception {
         MetadataHistoryRepository mdhRepo = context.getBean(MetadataHistoryRepository.class);
         
        try{
            Integer id = Integer.parseInt(strId);
            mdhRepo.deleteAll(MetadataHistorySpecs.hasMetadataId(id));
        } catch(NumberFormatException ex){
            ex.printStackTrace();
        }
    }
}
