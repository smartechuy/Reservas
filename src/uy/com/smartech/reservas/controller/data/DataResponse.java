package uy.com.smartech.reservas.controller.data;

public class DataResponse {

	private Success data;

	public Success getData() {
		return data;
	}

	public void setData(Success data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "DataResponse [data=" + data + "]";
	}

	
	
}
