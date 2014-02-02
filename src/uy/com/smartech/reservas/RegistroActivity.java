package uy.com.smartech.reservas;

import uy.com.smartech.reservas.controller.ServiceFactory;
import uy.com.smartech.reservas.controller.ServiceLayer;
import uy.com.smartech.reservas.controller.data.DataResponse;
import uy.com.smartech.reservas.util.UserSession;
import android.os.AsyncTask;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothProfile.ServiceListener;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ShowToast")
public class RegistroActivity extends Activity {

	private TextView nombre;
	private TextView email;
	private TextView pass1;
	private TextView pass2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registro);

		Button btnRegistro = (Button) findViewById(R.id.buttonNewUse);

		nombre = (TextView) findViewById(R.id.editTextNombre);
		email = (TextView) findViewById(R.id.editTextEmail);
		pass1 = (TextView) findViewById(R.id.editTextPass1);
		pass2 = (TextView) findViewById(R.id.editTextPass2);

		btnRegistro.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String strPass1 = pass1.getText().toString();
				String strPass2 = pass2.getText().toString();

				if (!strPass1.equals(strPass2)) {
					Toast.makeText(RegistroActivity.this,
							"Las contraseñas no coinciden", Toast.LENGTH_LONG);
					return;
				} else {

					fireTaskForCreateNewUser();
				}

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.registro, menu);
		return true;
	}

	public void fireTaskForCreateNewUser() {
		new AsyncTask<Void, DataResponse, DataResponse>() {

			protected DataResponse doInBackground(Void... params) {

				ServiceLayer sl = ServiceFactory.getInstance();

				try {
					DataResponse res = sl.newUser(nombre.getText().toString(),
							email.getText().toString(), pass1.getText()
									.toString(), "");

					return res;
				} catch (Exception e) {
					e.printStackTrace();
					Toast.makeText(RegistroActivity.this, e.getMessage(),
							Toast.LENGTH_LONG).show();
				}

				return null;
			}

			protected void onPostExecute(DataResponse result) {

				if (result == null)
					return;

				System.out.println(result);
				if (result.getData().isSuccess()) {

					Toast.makeText(RegistroActivity.this,
							"Usuario creado correctamente", Toast.LENGTH_LONG).show();
					finish();
				} else {

					Toast.makeText(RegistroActivity.this, "Error "
							+ result.getData().getMsg(), Toast.LENGTH_LONG).show();
					return;
				}
			};

		}.execute();
	}

	
	
	
}
