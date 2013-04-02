package org.springside.modules.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * Struts2中典型CRUD Action的抽象基类.
 * 
 * 主要定义了对Preparable,ModelDriven接口的使用,以及CRUD函数和返回值的命名.
 *
 * @param <T> CRUDAction所管理的对象类型.
 * 
 * @author calvin
 */
public abstract class CrudActionSupport<T> extends ActionSupport implements ModelDriven<T>, Preparable {

	private static final long serialVersionUID = -1653204626115064950L;

	/** 进行增删改操作后,以redirect方式重新打开action默认页的result名.*/
	public static final String RELOAD = "reload";
	
	//状态正确标识符
	private static final int STATUS_SUCCESS = 200;
	
	//状态失败标识符
	private static final int STATUS_FAILED = 300;
	
	private static final String RESULT_AJAX = "ajax";
	
	private int statusCode = STATUS_SUCCESS;
	
	//forward跳转的url
	private String forwardUrl;
	
	//ajax为ajax提交的标识符
	private String ajax;
	
	//lookup为查找的标识符
	private String lookup;
	
	//rel为依赖的tab标签id
	private String rel;
	
	//确认消息
	private String confirmMsg;
	
	//navTabId为依赖的tab标签id（用于新增数据后，刷新rel对应的tab数据）
	private String navTabId;
	
	//callBackType为提交后，处理的js方法，一般为关闭当前页
	private String callbackType;
	
	//响应出现异常情况的错误消息
	private String message;
	
	private String targetType;

	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * Action函数, 默认的action函数, 默认调用list()函数.
	 */
	@Override
	public String execute() throws Exception {
		return list();
	}

	//-- CRUD Action函数 --//
	/**
	 * Action函数,显示Entity列表界面.
	 * 建议return SUCCESS.
	 */
	public abstract String list() throws Exception;

	/**
	 * Action函数,显示新增或修改Entity界面.
	 * 建议return INPUT.
	 */
	@Override
	public abstract String input() throws Exception;

	/**
	 * Action函数,新增或修改Entity. 
	 * 建议return RELOAD.
	 */
	public abstract String save() throws Exception;

	/**
	 * Action函数,删除Entity.
	 * 建议return RELOAD.
	 */
	public abstract String delete() throws Exception;

	//-- Preparable函数 --//
	/**
	 * 实现空的prepare()函数,屏蔽了所有Action函数都会执行的公共的二次绑定.
	 */
	public void prepare() throws Exception {
	}

	/**
	 * 定义在input()前执行二次绑定.
	 */
	public void prepareInput() throws Exception {
		prepareModel();
	}

	/**
	 * 定义在save()前执行二次绑定.
	 */
	public void prepareSave() throws Exception {
		prepareModel();
	}

	/**
	 * 等同于prepare()的内部函数,供prepardMethodName()函数调用. 
	 */
	protected abstract void prepareModel() throws Exception;
	
	protected String executeCommand(){
		return executeCommand(null);
	}
	protected String executeCommand(String message){
		if ((ajax == null || !ajax.trim().equals("1")) && message == null)
		{
			return SUCCESS;
		}
		if(message == null)
		{
			return ajaxForwardSuccess("操作成功");
		}
		else
		{
			return ajaxForwardError(message);
		}
	}
	
	private String ajaxForward(int statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
		return RESULT_AJAX;
	}
	
	protected String ajaxForwardSuccess(String message) {
		return ajaxForward(STATUS_SUCCESS, message);
	}
	protected String ajaxForwardError(String message) {
		return ajaxForward(STATUS_FAILED, message);
	}

	public String getForwardUrl() {
		return forwardUrl;
	}

	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}

	public String getLookup() {
		return lookup;
	}

	public void setLookup(String lookup) {
		this.lookup = lookup;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getConfirmMsg() {
		return confirmMsg;
	}

	public void setConfirmMsg(String confirmMsg) {
		this.confirmMsg = confirmMsg;
	}

	public String getNavTabId() {
		return navTabId;
	}

	public void setNavTabId(String navTabId) {
		this.navTabId = navTabId;
	}

	public String getCallbackType() {
		return callbackType;
	}

	public void setCallbackType(String callbackType) {
		this.callbackType = callbackType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAjax() {
		return ajax;
	}

	public void setAjax(String ajax) {
		this.ajax = ajax;
	}
	
	public String getTargetType() {
		if (lookup != null && lookup.length() > 0)
		{
			return "dialog";
		}
		return "navTab";
	}
}
