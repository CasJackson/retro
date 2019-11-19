/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvd;

import com.mycompany.dvd.controller.DVDController;
import com.mycompany.dvd.dao.DVDDao;
import com.mycompany.dvd.dao.DVDDaoFileImpl;
import com.mycompany.dvd.ui.DVDView;

/**
 *
 * @author cas
 */
public class App {
    public static void main(String[] args) {
        DVDView view = new DVDView();
        DVDDao dao = new DVDDaoFileImpl ("DVD.txt");
        DVDController controller = new DVDController (view, dao);
        controller.run();
    }
}
