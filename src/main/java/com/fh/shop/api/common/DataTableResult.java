package com.fh.shop.api.common;

import java.io.Serializable;
import java.util.List;

public class DataTableResult implements Serializable {

    private Long draw;
    private Long recordsFiltered;
    private Long recordsTotal;
    private List data;




    public DataTableResult(Long draw, Long recordsFiltered, Long recordsTotal, List data){
        this.draw=draw;
        this.recordsFiltered=recordsFiltered;
        this.recordsTotal=recordsTotal;
        this.data=data;
    }


    public Long getDraw() {
        return draw;
    }

    public void setDraw(Long draw) {
        this.draw = draw;
    }

    public Long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public Long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
