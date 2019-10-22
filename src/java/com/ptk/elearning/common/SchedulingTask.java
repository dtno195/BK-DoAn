/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.common;

import com.ptk.elearning.dto.FullCalendar;
import com.ptk.elearning.util.DateUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;


@Controller
public class SchedulingTask {

    @Scheduled(fixedDelay = 60000)
    public void demoServiceMethod() throws Exception {
//        String sql = ""
//                + "SELECT     "
//                + "   pl.plan_id id,pl.content description, pl.type icon, pl.user_id userId,     "
//                + "	pl.to_date end, pl.from_date start, pl.title title, pl.color color ,     "
//                + "	us.email email      "
//                + "FROM TBL_PLANS pl     "
//                + "INNER JOIN tbl_users us      "
//                + "ON us.user_id = pl.user_id     "
//                + "WHERE from_date >= ? AND from_date >= ?";
//        String sqlDelete = "DELETE FROM tbl_plans WHERE plan_id = ?";
////        DAO dao = new DAO();
//        ResultSet rs = dao.getListRemind(sql, DateUtil.getTimeAfter15Minutes(), DateUtil.getTimeAfter16Minutes());
//        try {
//            if (rs != null) {
//                while (rs.next()) {
//                    FullCalendar cal = new FullCalendar();
//                    cal.setTitle(rs.getString("title"));
//                    cal.setDescription(rs.getString("description"));
//                    cal.setIcon(rs.getString("icon"));
//                    cal.setColor(rs.getString("color"));
//                    cal.setEnd(rs.getDate("end"));
//                    cal.setStart(rs.getDate("start"));
//                    cal.setEmail(rs.getString("email"));
//                    Long id = rs.getLong("id");
//                    dao.delete(sqlDelete, id);
//                    Mail.send(rs.getString("email"), "Bạn có công việc cần thực hiện vào " + rs.getDate("start"), "Nội dung công việc :" + rs.getString("description"));
//                }
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(SchedulingTask.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }
}
