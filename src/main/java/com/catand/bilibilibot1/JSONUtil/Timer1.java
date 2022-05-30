package com.catand.bilibilibot1.JSONUtil;

import com.catand.bilibilibot1.plugin.CatandPlugin;

import java.util.*;
import java.awt.event.*;
import javax.swing.Timer;

import static java.lang.Thread.sleep;

public class Timer1 implements ActionListener {
	static boolean sended = false;
	Timer t = new Timer(1000, this);
	GregorianCalendar calendar = new GregorianCalendar();
	static int day = new Date().getDate() - 1;

	public Timer1() {
		t.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Date date = new Date();
		calendar.setTime(date);
		if (e.getSource() == t) {
			if (calendar.get(Calendar.DAY_OF_MONTH) != day && !sended && calendar.get(Calendar.HOUR_OF_DAY) == 8 ) {
				//当时间为早八时报时
				sended = true;
				day = new Date().getDate();
				CatandPlugin.share();
			}
		}
	}

}