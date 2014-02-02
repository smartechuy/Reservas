package uy.com.smartech.reservas.controller;

public class ServiceFactory {
	public static ServiceLayer getInstance() {
		return new ServiceLayerImpl();
	}
}
