package com.dangducluan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/dashboard")
public class DashBoardController {
	@GetMapping
	public String Default() {
		return "dashboard";
	}
}
