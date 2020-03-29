package com.mp.piggymetrics.account.client;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class SSLAffirmativeHostnameVerifier implements HostnameVerifier {
	@Override
	public boolean verify(String hostname, SSLSession session) {
		/**
		 * Currently the custom "HostnameVerifier" is not honored in mpRestClient-1.3.
		 * The issue was fixed by PR
		 * https://github.com/OpenLiberty/open-liberty/pull/11378, but it seems not
		 * released yet.
		 */
		System.out.println("SSLAffirmativeHostnameVerifier.verify() invoked");
		return true;
	}
}
