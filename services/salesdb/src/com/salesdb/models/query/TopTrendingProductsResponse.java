/*Copyright (c) 2015-2016 mobeteq-com All Rights Reserved.
 This software is the confidential and proprietary information of mobeteq-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with mobeteq-com*/
package com.salesdb.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class TopTrendingProductsResponse implements Serializable {


    @JsonProperty("PRODUCT")
    @ColumnAlias("PRODUCT")
    private String product;

    @JsonProperty("SALES")
    @ColumnAlias("SALES")
    private BigInteger sales;

    public String getProduct() {
        return this.product;
    }

    public void setProduct(String product) {
        this.product = product;
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
        if (!(o instanceof TopTrendingProductsResponse)) return false;
        final TopTrendingProductsResponse topTrendingProductsResponse = (TopTrendingProductsResponse) o;
        return Objects.equals(getProduct(), topTrendingProductsResponse.getProduct()) &&
                Objects.equals(getSales(), topTrendingProductsResponse.getSales());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProduct(),
                getSales());
    }
}