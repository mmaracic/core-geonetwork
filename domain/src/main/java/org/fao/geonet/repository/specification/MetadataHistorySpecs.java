/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fao.geonet.repository.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import org.fao.geonet.domain.MetadataHistory;
import org.fao.geonet.domain.MetadataHistory_;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author marijom
 */
public class MetadataHistorySpecs {
    
    private MetadataHistorySpecs() {}
    
    public static Specification<MetadataHistory> hasMetadataId(final int metadataId){
        return new Specification<MetadataHistory>() {
            @Override
            public Predicate toPredicate(Root<MetadataHistory> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return cb.equal(root.get(MetadataHistory_.metadataId), metadataId);
            }            
        };
    }

    public static Specification<MetadataHistory> hasMaximumVersion(final int metadataId){
        return new Specification<MetadataHistory>() {
            @Override
            public Predicate toPredicate(Root<MetadataHistory> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                Subquery<Integer> sq = cq.subquery(Integer.class);
                Root<MetadataHistory> sqRoot = sq.from(MetadataHistory.class);
                sq.select(cb.max(sqRoot.get(MetadataHistory_.version))).where(cb.equal(root.get(MetadataHistory_.metadataId), metadataId));
                Expression<Integer> maxValue =  sq.getSelection();
                return cb.and(cb.equal(root.get(MetadataHistory_.metadataId), metadataId),cb.equal(root.get(MetadataHistory_.version),maxValue));
            }            
        };
    }
}
