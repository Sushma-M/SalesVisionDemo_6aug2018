/*Copyright (c) 2015-2016 mobeteq-com All Rights Reserved.
 This software is the confidential and proprietary information of mobeteq-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with mobeteq-com*/
package com.salesdb.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class SalesByRepsResponse implements Serializable {


    @ColumnAlias("NAME")
    private String name;

    @ColumnAlias("LEADS")
    private BigInteger leads;

    @ColumnAlias("SALES")
    private BigInteger sales;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getLeads() {
        return this.leads;
    }

    public void setLeads(BigInteger leads) {
        this.leads = leads;
    }

    public BigInteger getSales() {
        return this.sales;
    }

    public void setSales(BigInteger sales) {
        this.sales = sales;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SalesByRepsResponse)) return false;
        final SalesByRepsResponse salesByRepsResponse = (SalesByRepsResponse) o;
        return Objects.equals(getName(), salesByRepsResponse.getName()) &&
                Objects.equals(getLeads(), salesByRepsResponse.getLeads()) &&
                Objects.equals(getSales(), salesByRepsResponse.getSales());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(),
                getLeads(),
                getSales());
    }
}
