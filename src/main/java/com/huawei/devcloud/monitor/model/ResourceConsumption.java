package com.huawei.devcloud.monitor.model;


public class ResourceConsumption {

    public ResourceConsumption(float total, float used, float empty) {
        this.total = total;
        this.used = used;
        this.empty = empty;
    }

    public ResourceConsumption() {
        this.total = 0;
        this.used = 0;
        this.empty = 0;
    }

    private float total = 0;

    private float used = 0;

    private float empty = 0;

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getUsed() {
        return used;
    }

    public void setUsed(float used) {
        this.used = used;
    }

    public float getEmpty() {
        return empty;
    }

    public void setEmpty(float empty) {
        this.empty = empty;
    }

    @Override
    public String toString() {
        return "ResourceConsumption{" +
                "total=" + total +
                ", used=" + used +
                ", empty=" + empty +
                '}';
    }

}
