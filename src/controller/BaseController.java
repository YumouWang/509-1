package controller;

import model.Model;

public abstract class BaseController {
	Model model;
	
	public BaseController(Model model){
		this.model = model;
	}
	
	// act function need to be implemented by sub class
	public abstract boolean act();
}
