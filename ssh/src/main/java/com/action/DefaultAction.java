package com.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionSupport;

public class DefaultAction extends ActionSupport {

	private static final long serialVersionUID = 5615277846851840080L;
	protected Log log = LogFactory.getLog(getClass());

	@Override
	public String execute() throws Exception {
		log.debug("defaultAction");
		return super.execute();
	}
	
	public static void main(String[] args) {
		int n = 20;
		for (int i = 0; i < n; i--) {
			System.out.print("-");
		}
	}
}
