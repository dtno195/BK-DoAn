///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.ptk.elearning.util;
//
//import com.ptk.elearning.common.CommonConstant;
//import io.socket.client.IO;
//import io.socket.client.Socket;
//import java.net.URISyntaxException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class NotificationUtil {
//
//    public static Boolean senNotif() {
//        try {
//
//            IO.Options opts = new IO.Options();
//            opts.forceNew = true;
//            opts.reconnection = false;
//            final Socket socket = IO.socket(CommonConstant.SOCKET_URI);
//            if (socket.connected()) {
//                socket.close();
//            }
//            socket.connect();
//            socket.emit("notify everyone", "Xin chao cac ban");
////            socket.on("notify everyone", new Emitter.Listener() {
////                @Override
////                public void call(Object... os) {
////                    System.out.println("da chay");
////                    socket.emit("action from java", "chat message");
////                }
////            });
//        } catch (URISyntaxException ex) {
//            Logger.getLogger(NotificationUtil.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//    }
//}
