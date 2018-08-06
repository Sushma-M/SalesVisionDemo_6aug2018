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

import com.salesdb.Products;
import com.salesdb.Sales;


/**
 * ServiceImpl object for domain model class Products.
 *
 * @see Products
 */
@Service("salesdb.ProductsService")
@Validated
public class ProductsServiceImpl implements ProductsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductsServiceImpl.class);

    @Lazy
    @Autowired
    @Qualifier("salesdb.SalesService")
    private SalesService salesService;

    @Autowired
    @Qualifier("salesdb.ProductsDao")
    private WMGenericDao<Products, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Products, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "salesdbTransactionManager")
    @Override
    public Products create(Products products) {
        LOGGER.debug("Creating a new Products with information: {}", products);

        Products productsCreated = this.wmGenericDao.create(products);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(productsCreated);
    }

    @Transactional(readOnly = true, value = "salesdbTransactionManager")
    @Override
    public Products getById(Integer productsId) {
        LOGGER.debug("Finding Products by id: {}", productsId);
        return this.wmGenericDao.findById(productsId);
    }

    @Transactional(readOnly = true, value = "salesdbTransactionManager")
    @Override
    public Products findById(Integer productsId) {
        LOGGER.debug("Finding Products by id: {}", productsId);
        try {
            return this.wmGenericDao.findById(productsId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No Products found with id: {}", productsId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "salesdbTransactionManager")
    @Override
    public List<Products> findByMultipleIds(List<Integer> productsIds, boolean orderedReturn) {
        LOGGER.debug("Finding Products by ids: {}", productsIds);

        return this.wmGenericDao.findByMultipleIds(productsIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "salesdbTransactionManager")
    @Override
    public Products update(Products products) {
        LOGGER.debug("Updating Products with information: {}", products);

        List<Sales> saleses = products.getSaleses();
        if(saleses != null && Hibernate.isInitialized(saleses)) {
            saleses.forEach(_sales -> _sales.setProducts(products));
        }

        this.wmGenericDao.update(products);
        this.wmGenericDao.refresh(products);

        return products;
    }

    @Transactional(value = "salesdbTransactionManager")
    @Override
    public Products delete(Integer productsId) {
        LOGGER.debug("Deleting Products with id: {}", productsId);
        Products deleted = this.wmGenericDao.findById(productsId);
        if (deleted == null) {
            LOGGER.debug("No Products found with id: {}", productsId);
            throw new EntityNotFoundException(MessageResource.create("com.wavemaker.runtime.entity.not.found"), Products.class.getSimpleName(), productsId);
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "salesdbTransactionManager")
    @Override
    public void delete(Products products) {
        LOGGER.debug("Deleting Products with {}", products);
        this.wmGenericDao.delete(products);
    }

    @Transactional(readOnly = true, value = "salesdbTransactionManager")
    @Override
    public Page<Products> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Products");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "salesdbTransactionManager")
    @Override
    public Page<Products> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Products");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "salesdbTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service salesdb for table Products to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "salesdbTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service salesdb for table Products to {} format", options.getExportType());
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
    public Page<Sales> findAssociatedSaleses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated saleses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("products.id = '" + id + "'");

        return salesService.findAll(queryBuilder.toString(), pageable);
    }

    /**
     * This setter method should only be used by unit tests
     *
     * @param service SalesService instance
     */
    protected void setSalesService(SalesService service) {
        this.salesService = service;
    }

}