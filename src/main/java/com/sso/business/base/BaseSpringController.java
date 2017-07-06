package com.sso.business.base;

/**
 * Created by yt on 2017-7-6.
 */
public class BaseSpringController {
	/**
	 * 成功处理方法
	 * @date 2016年8月16日 下午3:41:58
	 * @param <T> 返回结果的类型限制
	 * @return 返回包装好的json格式
	 */
	public <T> JsonResult<T> success() {
		JsonResult<T> result = new JsonResult<T>();
		return result;
	}

	/**
	 * 成功处理方法
	 * @date 2016年8月16日 下午3:41:58
	 * @param <T> 返回结果的类型限制
	 * @param t 数据对象
	 * @return 返回包装好的json格式
	 */
	public <T> JsonResult<T> success(T t) {
		JsonResult<T> result = new JsonResult<T>();
		result.setData(t);
		return result;
	}

	/**
	 * 成功处理方法
	 * @date 2016年8月16日 下午3:41:58
	 * @param <T> 返回结果的类型限制
	 * @param message 提示信息
	 * @param t 数据对象
	 * @return 返回包装好的json格式
	 */
	public <T> JsonResult<T> success(String message, T t) {
		JsonResult<T> result = new JsonResult<T>();
		result.setData(t);
		result.setMessage(message);
		return result;
	}

	/**
	 * 错误处理方法
	 * @date 2016年8月16日 下午3:41:58
	 * @param <T> 返回结果的类型限制
	 * @param code 错误码
	 * @param error 错误信息
	 * @return 返回包装好的json格式
	 */
	public <T> JsonResult<T> fail(int code, String error) {
		JsonResult<T> result = new JsonResult<T>();
		result.setCode(code);
		result.setMessage(error);
		return result;
	}
}
