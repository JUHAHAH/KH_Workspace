package edu.kh.collection.run;

import edu.kh.collection.pack2.model.service.SetService;

public class SetRun {
	public static void main(String[] args) {
		SetService sS = new SetService();
		
		sS.lottoNumberGenerator();
	}
}
