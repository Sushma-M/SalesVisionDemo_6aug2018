/*Copyright (c) 2015-2016 mobeteq-com All Rights Reserved.
 This software is the confidential and proprietary information of mobeteq-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with mobeteq-com*/
package com.salesdb.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class TotalSalesAndRevenueResponse implements Serializable {


    @ColumnAlias("SALESREVENUE")
    private BigInteger salesrevenue;

    @ColumnAlias("TOTALSALES")
    private BigInteger totalsales;

    public BigInteger getSalesrevenue() {
        return this.salesrevenue;
    }

    public void setSalesrevenue(BigInteger salesrevenue) {
        this.salesrevenue = salesrevenue;
    }

    public BigInteger getTotalsales() {
        return this.totalsales;
    }

    public void setTotalsales(BigInteger totalsales) {
        this.totalsales = totalsales;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TotalSalesAndRevenueResponse)) return false;
        final TotalSalesAndRevenueResponse totalSalesAndRevenueResponse = (TotalSalesAndRevenueResponse) o;
        return Objects.equals(getSalesrevenue(), totalSalesAndRevenueResponse.getSalesrevenue()) &&
                Objects.equals(getTotalsales(), totalSalesAndRevenueResponse.getTotalsales());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSalesrevenue(),
                getTotalsales());
    }
}