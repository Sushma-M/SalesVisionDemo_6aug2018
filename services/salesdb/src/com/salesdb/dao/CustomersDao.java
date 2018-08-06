/*Copyright (c) 2015-2016 mobeteq-com All Rights Reserved.
 This software is the confidential and proprietary information of mobeteq-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with mobeteq-com*/
package com.salesdb.dao;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wavemaker.runtime.data.dao.WMGenericDaoImpl;

import com.salesdb.Customers;

/**
 * Specifies methods used to obtain and modify Customers related information
 * which is stored in the database.
 */
@Repository("salesdb.CustomersDao")
public class CustomersDao extends WMGenericDaoImpl<Customers, Integer> {

    @Autowired
    @Qualifier("salesdbTemplate")
    private HibernateTemplate template;


    @Override
    public HibernateTemplate getTemplate() {
        return this.template;
    }
}