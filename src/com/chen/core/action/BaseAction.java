package com.chen.core.action;

import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseAction extends ActionSupport {
	
	//选项框  全选/全不选
	protected String[] selectedRow;

	public String[] getSelectedRow() {
		return selectedRow;
	}

	public void setSelectedRow(String[] selectedRow) {
		this.selectedRow = selectedRow;
	}
	
	
}
