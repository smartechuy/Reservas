package uy.com.smartech.reservas.controller.data;

public class Success {

	private boolean success;
	private String msg;
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "Success [success=" + success + ", msg=" + msg + "]";
	}
	
	
}
