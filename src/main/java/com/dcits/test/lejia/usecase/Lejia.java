package com.dcits.test.lejia.usecase;

import com.dcits.test.lejia.data.LoginData;
import com.dcits.test.lejia.data.StudentInfo;
import com.dcits.test.lejia.page.LoginPage;
import com.dcits.test.lejia.page.MainPage;
import com.dcits.yi.tool.AssertUtil;
import com.dcits.yi.ui.usecase.UseCase;

public class Lejia {	
	
	public LoginPage loginPage;
	public MainPage mainPage;
		
	public LoginData loginData;
	
	public StudentInfo stuData;
	
	@UseCase(name="乐驾ERP登录测试")
	public void login() {
		loginPage.open();
		loginPage.login(loginData.username, loginData.password);	
		loginPage.sleep(1);
		mainPage.verifyLogin(loginData.nickName);
	}
	
	
	@UseCase(name="报名受理")
	public void OP1001() {
		mainPage.switchMenu("学员管理", "报名受理");
		
		//先搜索指定的学生信息是否存在，如果存在就先删除
		if (mainPage.module_search(stuData.credentNo)) {
			mainPage.module_del(1);
			//重新搜索，是否已经真的删除掉了？
			mainPage.search_button.click();
			AssertUtil.isTrue(mainPage.table_no_data_tip.isExist(), "删除未成功， 搜索关键字[{}]", stuData.credentNo);
			mainPage.module_reset_search();
		}	
		
		//新增一个学员
		
	}
	
	
	@UseCase(name="退学管理", enabled=false)
	public void OP1005() {
		mainPage.switchMenu("学员管理", "退学管理");
		
	}
}
