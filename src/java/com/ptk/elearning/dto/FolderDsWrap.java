/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.dto;

import java.util.List;


public class FolderDsWrap {

    private String text;
    private String iconCls;
    private List<FolderDataSource> items;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public List<FolderDataSource> getItems() {
        return items;
    }

    public void setItems(List<FolderDataSource> items) {
        this.items = items;
    }
}
