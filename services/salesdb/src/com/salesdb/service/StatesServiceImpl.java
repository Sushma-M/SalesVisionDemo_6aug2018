/*Copyright (c) 2015-2016 mobeteq-com All Rights Reserved.
 This software is the confidential and proprietary information of mobeteq-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with mobeteq-com*/
package com.salesdb.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.wavemaker.commons.MessageResource;
import com.wavemaker.runtime.data.dao.WMGenericDao;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.DataExportOptions;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;

import com.salesdb.Customers;
import com.salesdb.States;


/**
 * ServiceImpl object for domain model class States.
 *
 * @see States
 */
@Service("salesdb.StatesService")
@Validated
public class StatesServiceImpl implements StatesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatesServiceImpl.class);

    @Lazy
    @Autowired
    @Qualifier("salesdb.CustomersService")
    private CustomersService customersService;

    @Autowired
    @Qualifier("salesdb.StatesDao")
    private WMGenericDao<States, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<States, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "salesdbTransactionManager")
    @Override
    public States create(States states) {
        LOGGER.debug("Creating a new States with information: {}", states);

        States statesCreated = this.wmGenericDao.create(states);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(statesCreated);
    }

    @Transactional(readOnly = true, value = "salesdbTransactionManager")
    @Override
    public States getById(Integer statesId) {
        LOGGER.debug("Finding States by id: {}", statesId);
        return this.wmGenericDao.findById(statesId);
    }

    @Transactional(readOnly = true, value = "salesdbTransactionManager")
    @Override
    public States findById(Integer statesId) {
        LOGGER.debug("Finding States by id: {}", statesId);
        try {
            return this.wmGenericDao.findById(statesId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No States found with id: {}", statesId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "salesdbTransactionManager")
    @Override
    public List<States> findByMultipleIds(List<Integer> statesIds, boolean orderedReturn) {
        LOGGER.debug("Finding States by ids: {}", statesIds);

        return this.wmGenericDao.findByMultipleIds(statesIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "salesdbTransactionManager")
    @Override
    public States update(States states) {
        LOGGER.debug("Updating States with information: {}", states);

        List<Customers> customerses = states.getCustomerses();
        if(customerses != null && Hibernate.isInitialized(customerses)) {
            customerses.forEach(_customers -> _customers.setStates(states));
        }

        this.wmGenericDao.update(states);
        this.wmGenericDao.refresh(states);

        return states;
    }

    @Transactional(value = "salesdbTransactionManager")
    @Override
    public States delete(Integer statesId) {
        LOGGER.debug("Deleting States with id: {}", statesId);
        States deleted = this.wmGenericDao.findById(statesId);
        if (deleted == null) {
            LOGGER.debug("No States found with id: {}", statesId);
            throw new EntityNotFoundException(MessageResource.create("com.wavemaker.runtime.entity.not.found"), States.class.getSimpleName(), statesId);
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "salesdbTransactionManager")
    @Override
    public void delete(States states) {
        LOGGER.debug("Deleting States with {}", states);
        this.wmGenericDao.delete(states);
    }

    @Transactional(readOnly = true, value = "salesdbTransactionManager")
    @Override
    public Page<States> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all States");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "salesdbTransactionManager")
    @Override
    public Page<States> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all States");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "salesdbTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service salesdb for table States to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "salesdbTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service salesdb for table States to {} format", options.getExportType());
        this.wmGenericDao.export(options, pageable, outputStream);
    }

    @Transactional(readOnly = true, value = "salesdbTransactionManager")
    @Override
    public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "salesdbTransactionManager")
    @Override
    public Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable) {
        return this.wmGenericDao.getAggregatedValues(aggregationInfo, pageable);
    }

    @Transactional(readOnly = true, value = "salesdbTransactionManager")
    @Override
    public Page<Customers> findAssociatedCustomerses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated customerses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("states.id = '" + id + "'");

        return customersService.findAll(queryBuilder.toString(), pageable);
    }

    /**
     * This setter method should only be used by unit tests
     *
     * @param service CustomersService instance
     */
    protected void setCustomersService(CustomersService service) {
        this.customersService = service;
    }

}