package com.devjob.devjob_app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pageable {

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("size")
    @Expose
    private Integer size;
    @SerializedName("offset")
    @Expose
    private Integer offset;
    @SerializedName("totalElements")
    @Expose
    private Integer totalElements;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

}
