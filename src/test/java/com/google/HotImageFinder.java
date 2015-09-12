package com.google;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class HotImageFinder {
	private class Like {
		String imgUrl;
		boolean positive;
		Date when;
	}
	public static void main(String[] args) {
		Queue<Like> window = new PriorityQueue<Like>();
		List<Like> input = new LinkedList<Like>();
		Date winStart = input.get(0).when;
		HashMap<String, Integer> ratings = new HashMap<String, Integer>();
		Iterator<Like> i = input.iterator();
		while(i.hasNext()) {
			Like l = i.next();
			window.add(l);
			Calendar c = new GregorianCalendar();
			c.setTime(l.when);
			c.add(Calendar.HOUR, -24);
			if(window.peek().when.before(c.getTime())) {
				Like head = window.poll();
				if(ratings.containsKey(l.imgUrl)) {
					ratings.get(l.imgUrl);
				}
				
				window.add(l);
				
			} else {
				window.add(l);
			}
		}
	}
}
