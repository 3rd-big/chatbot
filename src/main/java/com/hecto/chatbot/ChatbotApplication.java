package com.hecto.chatbot;


import com.hecto.chatbot.service.EchoServer;

import java.io.IOException;

public class ChatbotApplication {

	public static void main(String[] args) throws IOException {
		EchoServer echoServer = new EchoServer();
		echoServer.ReadyCommunication();
	}
}
