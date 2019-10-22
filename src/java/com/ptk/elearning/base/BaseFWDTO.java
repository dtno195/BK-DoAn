/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.base;


public interface BaseFWDTO<TModel extends BaseFWModelImpl> extends Comparable<BaseFWDTO> {

    TModel toModel();

    Long getFWModelId();

    String catchName();

}
