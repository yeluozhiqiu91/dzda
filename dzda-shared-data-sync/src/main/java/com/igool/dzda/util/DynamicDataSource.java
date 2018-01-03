package com.igool.dzda.util;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author H J .
 * @date 2017/8/30.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    public static Object dataSource;
    @Override
    protected Object determineCurrentLookupKey() {

        System.out.println("-dataSource-"+dataSource);
        return dataSource;
    }

}
