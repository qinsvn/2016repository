/**
 * 
 */
package com.proxy.http.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/user")
public class CommonUserController {

	@RequestMapping("userList")
	public String userList(){
		return "/index";
	}
}
