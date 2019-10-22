/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.dto;

import java.util.List;

public class WrapperDTO {

    private TblQuestionsDTO questionsDTO;
    private List<TblAnswerDTO> lstAnswerDTOs;

    public TblQuestionsDTO getQuestionsDTO() {
        return questionsDTO;
    }

    public void setQuestionsDTO(TblQuestionsDTO questionsDTO) {
        this.questionsDTO = questionsDTO;
    }

    public List<TblAnswerDTO> getLstAnswerDTOs() {
        return lstAnswerDTOs;
    }

    public void setLstAnswerDTOs(List<TblAnswerDTO> lstAnswerDTOs) {
        this.lstAnswerDTOs = lstAnswerDTOs;
    }
}
