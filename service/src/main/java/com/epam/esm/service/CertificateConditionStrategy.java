package com.epam.esm.service;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.validator.GiftCertificateValidator;

public enum CertificateConditionStrategy {

    NAME {
        @Override
        public void changeCondition(GiftCertificate oldCertificate, GiftCertificate newCertificate) {
            if(GiftCertificateValidator.isNameValid(newCertificate.getName())) {
                oldCertificate.setName(newCertificate.getName());
            }
        }
    },
    DESCRIPTION {
        @Override
        public void changeCondition(GiftCertificate oldCertificate, GiftCertificate newCertificate) {
            if(GiftCertificateValidator.isDescriptionValid(newCertificate.getDescription())) {
                oldCertificate.setDescription(newCertificate.getDescription());
            }
        }
    },
    PRICE {
        @Override
        public void changeCondition(GiftCertificate oldCertificate, GiftCertificate newCertificate) {
            if(GiftCertificateValidator.isPriceValid(newCertificate.getPrice())) {
                oldCertificate.setPrice(newCertificate.getPrice());
            }
        }
    },
    DURATION {
        @Override
        public void changeCondition(GiftCertificate oldCertificate, GiftCertificate newCertificate) {
            if(GiftCertificateValidator.isDurationValid(newCertificate.getDuration())) {
                oldCertificate.setDuration(newCertificate.getDuration());
            }
        }
    },
    TAGS {
        @Override
        public void changeCondition(GiftCertificate oldCertificate, GiftCertificate newCertificate) {
            if(GiftCertificateValidator.areTagsValid(newCertificate.getTags())) {
                oldCertificate.setTags(newCertificate.getTags());
            }
        }
    };

    public abstract void changeCondition(GiftCertificate oldCertificate, GiftCertificate newCertificate);
}
