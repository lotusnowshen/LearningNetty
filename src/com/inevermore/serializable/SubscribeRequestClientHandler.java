package com.inevermore.serializable;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class SubscribeRequestClientHandler extends ChannelHandlerAdapter
{
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception
	{
		for (int i = 0; i < 10; i++)
		{
			ctx.write(subReq(i));
		}
		ctx.flush();
	}

	private SubscribeRequest subReq(int i)
	{
		SubscribeRequest req = new SubscribeRequest();
		req.setAddress("china Shenzhen");
		req.setPhoneNumber("138xxxxxxxx");
		req.setProductName("Netty 权威指南");
		req.setSubReqID(i);
		req.setUserName("guochunyang");
		return req;
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception
	{
		System.out.println("Receive server response : [" + msg + "]");
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception
	{
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception
	{
		cause.printStackTrace();
		ctx.close();
	}
}
