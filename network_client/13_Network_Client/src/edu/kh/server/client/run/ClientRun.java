package edu.kh.server.client.run;

import edu.kh.server.client.model.service.ClientService;

public class ClientRun {
	public static void main(String[] args) {
		new ClientService().clientStart();
	}
}
