/**
 * 
 */
package com.darren.mock;

/**
 * Copyright © 2018 xQuant Info. Tech Ltd. All rights reserved.
 * 
 * @Package: com.darren.mock
 * @author: guanglai.zhou
 * @date: 2018-07-27 10:34:07
 */
public interface IMessageService<T extends AbstractMessage> {

	/**
	 * 消息的处理
	 * @param message
	 */
	public void handleMessage(T message);

	/**
	 * 消息的解析
	 * @param message
	 * @return
	 */
	public T resolveMessage(T message);
	
	/**
	 * 获取一个消息
	 * @return
	 */
	public AbstractMessage getMessage();
}
