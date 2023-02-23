package com.example.mynote.utils;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.Random;

public class CustomerIdGenerator implements IdentifierGenerator {
    private final int DEFAULT_ID_LENGTH = 5;

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        String propertiesName = sharedSessionContractImplementor.getEntityPersister(o.getClass().getName(), o)
                .getIdentifierPropertyName();
        String objectName = o.getClass().getSimpleName();
        for(int i =0; i < 3; i++) {
            String result = generateRandomId(DEFAULT_ID_LENGTH);
            String query = String.format("select %s from %s where %s = '%s'",
                    propertiesName,
                    objectName,
                    propertiesName,
                    result
                    );
            if(sharedSessionContractImplementor.createQuery(query).uniqueResult() == null){
                return result;
            }
        }
        return null;
    }

    private String generateRandomId(int length){
        int leftLimit = 97;
        int alphabetTotal = 26;
        Random random = new Random();
        StringBuilder string = new StringBuilder(length);
        for(int i = 0;i < length; i++ ){
            char input = (char) (leftLimit + random.nextInt(alphabetTotal));
            string.append(input);
        }
        return string.toString().toUpperCase();
    }
}
