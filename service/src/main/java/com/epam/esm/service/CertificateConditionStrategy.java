package com.epam.esm.service;

import com.epam.esm.dao.constant.GiftCertificateColumnName;
import com.epam.esm.dao.creator.FieldCondition;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.validator.GiftCertificateValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * Enum contains methods for certificate fields.
 *
 * @author Dzmitry Ahinski
 */
public enum CertificateConditionStrategy {

    NAME {
        @Override
        public void addCondition(GiftCertificate certificate, List<FieldCondition> conditionList) {
            if(GiftCertificateValidator.isNameValid(certificate.getName())) {
                conditionList.add(new FieldCondition(GiftCertificateColumnName.NAME, certificate.getName()));
            }
        }
    },
    DESCRIPTION {
        @Override
        public void addCondition(GiftCertificate certificate, List<FieldCondition> conditionList) {
            if(GiftCertificateValidator.isDescriptionValid(certificate.getDescription())) {
                conditionList.add(new FieldCondition(GiftCertificateColumnName.DESCRIPTION, certificate.getDescription()));
            }
        }
    },
    PRICE {
        public void addCondition(GiftCertificate certificate, List<FieldCondition> conditionList) {
            if(GiftCertificateValidator.isPriceValid(certificate.getPrice())) {
                conditionList.add(new FieldCondition(GiftCertificateColumnName.PRICE, certificate.getPrice().toString()));
            }
        }
    },
    DURATION {
        public void addCondition(GiftCertificate certificate, List<FieldCondition> conditionList) {
            if(GiftCertificateValidator.isDurationValid(certificate.getDuration())) {
                conditionList.add(new FieldCondition(GiftCertificateColumnName.DURATION, Integer.toString(certificate.getDuration())));
            }
        }
    };

    /**
     * Adds new field condition to the list.
     *
     * @param certificate as GiftCertificate object
     * @param conditionList with FieldCondition objects
     */
    public abstract void addCondition(GiftCertificate certificate, List<FieldCondition> conditionList);

    /**
     * Creates list with FieldCondition objects.
     *
     * @param certificate as GiftCertificate object
     * @return list with FieldCondition objects
     */
    public static List<FieldCondition> createConditionsList(GiftCertificate certificate) {
        List<FieldCondition> conditionList = new ArrayList<>();
        for(CertificateConditionStrategy conditionStrategy : CertificateConditionStrategy.values()) {
            conditionStrategy.addCondition(certificate, conditionList);
        }
        return conditionList;
    }
}
