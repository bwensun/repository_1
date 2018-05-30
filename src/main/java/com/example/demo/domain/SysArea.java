package com.example.demo.domain;

/**
 * 省市区信息实体类
 *
 * @author CJ
 */
public class SysArea {
    /**
     * 对应邮政编码
     */
    private Integer id;

    /**
     * 名称
     */
    private String areaname;

    /** */
    private Integer pid;

    /**
     * 简称
     */
    private String shortname;

    /**
     * 经度
     */
    private Double lng;

    /**
     * 纬度
     */
    private Double lat;

    /**
     * 级别
     */
    private Integer level;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 备用字段1
     */
    private String wait1;

    /**
     * 备用字段2
     */
    private String wait2;

    /**
     * 备用字段3
     */
    private String wait3;

    /**
     * 备用字段4
     */
    private String wait4;

    /**
     * 是否已经代理 1:已经代理，其他则没有代理
     */
    private Integer isused;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname == null ? null : areaname.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname == null ? null : shortname.trim();
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getWait1() {
        return wait1;
    }

    public void setWait1(String wait1) {
        this.wait1 = wait1 == null ? null : wait1.trim();
    }

    public String getWait2() {
        return wait2;
    }

    public void setWait2(String wait2) {
        this.wait2 = wait2 == null ? null : wait2.trim();
    }

    public String getWait3() {
        return wait3;
    }

    public void setWait3(String wait3) {
        this.wait3 = wait3 == null ? null : wait3.trim();
    }

    public String getWait4() {
        return wait4;
    }

    public void setWait4(String wait4) {
        this.wait4 = wait4 == null ? null : wait4.trim();
    }

    public Integer getIsused() {
        return isused;
    }

    public void setIsused(Integer isused) {
        this.isused = isused;
    }
}