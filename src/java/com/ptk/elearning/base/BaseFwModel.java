/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.base;


public interface BaseFwModel<TDTO extends BaseFWDTOImpl> extends java.io.Serializable {
    public TDTO toDTO();
}
