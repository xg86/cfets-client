/**
 * 
 */
package com.darren.mock;

/**
 * Copyright © 2018 xQuant Info. Tech Ltd. All rights reserved.
 * 
 * @Package: com.darren.mock
 * @author: guanglai.zhou
 * @date: 2018-07-27 10:38:31
 */
public class MessageAImpl implements IMessageService<MessageA> {

	@Override
	public void handleMessage(MessageA message) {
		// TODO Auto-generated method stub

	}

	@Override
	public MessageA resolveMessage(MessageA message) {
		return null;
	}

	@Override
	public AbstractMessage getMessage() {
		MessageB b = new MessageB();
		b.setB("B");
		return b;
	}

}
