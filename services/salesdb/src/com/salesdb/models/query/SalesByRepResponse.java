/*Copyright (c) 2015-2016 mobeteq-com All Rights Reserved.
 This software is the confidential and proprietary information of mobeteq-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with mobeteq-com*/
package com.salesdb.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;
import java.util.Objects;

import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class SalesByRepResponse implements Serializable {


    @ColumnAlias("ID")
    private BigInteger id;

    @ColumnAlias("REP_NAME")
    private String repName;

    @ColumnAlias("ENTRY_DATE")
    private Date entryDate;

    @ColumnAlias("SALES")
    private BigInteger sales;

    @ColumnAlias("IS_RENEWAL")
    private Boolean isRenewal;

    @ColumnAlias("CITY")
    private String city;

    @ColumnAlias("PRODUCT_NAME")
    private String productName;

    @ColumnAlias("QUOTE_ID")
    private BigInteger quoteId;

    public BigInteger getId() {
        return this.id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getRepName() {
        return this.repName;
    }

    public void setRepName(String repName) {
        this.repName = repName;
    }

    public Date getEntryDate() {
        return this.entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public BigInteger getSales() {
        return this.sales;
    }

    public void setSales(BigInteger sales) {
        this.sales = sales;
    }

    public Boolean getIsRenewal() {
        return this.isRenewal;
    }

    public void setIsRenewal(Boolean isRenewal) {
        this.isRenewal = isRenewal;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigInteger getQuoteId() {
        return this.quoteId;
    }

    public void setQuoteId(BigInteger quoteId) {
        this.quoteId = quoteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SalesByRepResponse)) return false;
        final SalesByRepResponse salesByRepResponse = (SalesByRepResponse) o;
        return Objects.equals(getId(), salesByRepResponse.getId()) &&
                Objects.equals(getRepName(), salesByRepResponse.getRepName()) &&
                Objects.equals(getEntryDate(), salesByRepResponse.getEntryDate()) &&
                Objects.equals(getSales(), salesByRepResponse.getSales()) &&
                Objects.equals(getIsRenewal(), salesByRepResponse.getIsRenewal()) &&
                Objects.equals(getCity(), salesByRepResponse.getCity()) &&
                Objects.equals(getProductName(), salesByRepResponse.getProductName()) &&
                Objects.equals(getQuoteId(), salesByRepResponse.getQuoteId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getRepName(),
                getEntryDate(),
                getSales(),
                getIsRenewal(),
                getCity(),
                getProductName(),
                getQuoteId());
    }
}
