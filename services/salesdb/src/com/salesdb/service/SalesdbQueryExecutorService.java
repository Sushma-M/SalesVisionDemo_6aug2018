/*Copyright (c) 2015-2016 mobeteq-com All Rights Reserved.
 This software is the confidential and proprietary information of mobeteq-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with mobeteq-com*/
package com.salesdb.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.OutputStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wavemaker.runtime.data.export.ExportOptions;

import com.salesdb.models.query.*;

public interface SalesdbQueryExecutorService {

    Page<TotalLeadsResponse> executeTotalLeads(Pageable pageable);

    void exportTotalLeads(ExportOptions exportOptions, Pageable pageable, OutputStream outputStream);

    Page<SalesByRepResponse> executeSalesByRep(String repid, Pageable pageable);

    void exportSalesByRep(String repid, ExportOptions exportOptions, Pageable pageable, OutputStream outputStream);

    Page<SalesByRepsResponse> executeSalesByReps(Integer channel, Pageable pageable);

    void exportSalesByReps(Integer channel, ExportOptions exportOptions, Pageable pageable, OutputStream outputStream);

    Page<TopTrendingProductsResponse> executeTopTrendingProducts(Pageable pageable);

    void exportTopTrendingProducts(ExportOptions exportOptions, Pageable pageable, OutputStream outputStream);

    Page<TotalSalesAndRevenueResponse> executeTotalSalesAndRevenue(Pageable pageable);

    void exportTotalSalesAndRevenue(ExportOptions exportOptions, Pageable pageable, OutputStream outputStream);

}