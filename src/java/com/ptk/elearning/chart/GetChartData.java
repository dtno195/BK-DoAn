/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.chart;

import com.ptk.elearning.business.ExamResultBusiness;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class GetChartData {
    
    public String getDataChartDetail(String chartName, int year, int month, int subjectId, int userId, ExamResultBusiness examResultBusiness) throws JSONException {
        List<ChartData> chartDataArray = new ArrayList<ChartData>();
        if (month == 0) {
            for (int i = 0; i < 12; i++) {
                ChartData data = new ChartData();
                data.setLabel("Tháng " + (i + 1));
                data.setValue(examResultBusiness.avgScore(year, i + 1, subjectId, userId));
                chartDataArray.add(data);
            }
        } else {
            ChartData data = new ChartData();
            data.setLabel("Tháng " + month);
            data.setValue(examResultBusiness.avgScore(year, month, subjectId, userId));
            chartDataArray.add(data);
        }
        JSONObject finalJSonObj = new JSONObject();
        JSONArray chartData = new JSONArray();
        JSONArray xaxisArr = new JSONArray();
        JSONObject xaxisObj = new JSONObject();
        JSONObject dataObj = new JSONObject();
//        JSONObject dataObj1 = new JSONObject();
        JSONArray subject = new JSONArray();
        for (int i = 0; i < chartDataArray.size(); i++) {
            xaxisArr.put(chartDataArray.get(i).getLabel());
            subject.put(chartDataArray.get(i).getValue());
        }
        xaxisObj.put("category", xaxisArr);
        chartData.put(xaxisObj);
        dataObj = new JSONObject();
        dataObj.put("name", "Môn học");
        dataObj.put("color", "#3FAA61");
        dataObj.put("data", subject);
        chartData.put(dataObj);
        
//        dataObj1 = new JSONObject();
//        dataObj1.put("name", "Môn Toans");
//        dataObj1.put("color", "red");
//        dataObj1.put("data", subject);
//        chartData.put(dataObj1);
        finalJSonObj.put(chartName, chartData);
        String tempStr = finalJSonObj.toString();
        return tempStr;
    }
}
