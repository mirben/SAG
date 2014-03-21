package com.sag.business.control;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class Util {
	/**
	 * 
	 * @return chaine de caractere correspondante au droit
	 */
	public static String getAuthority() {
		Collection<? extends GrantedAuthority> b = SecurityContextHolder
				.getContext().getAuthentication().getAuthorities();

		Iterator<? extends GrantedAuthority> c = b.iterator();
		String s = "";
		if (c.hasNext()) {
			GrantedAuthority d = c.next();
			s = d.toString();
		}
		return s;
	}
}
