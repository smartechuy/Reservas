package uy.com.smartech.reservas;

import uy.com.smartech.reservas.controller.ServiceFactory;
import uy.com.smartech.reservas.controller.ServiceLayer;
import uy.com.smartech.reservas.controller.data.DataResponse;
import uy.com.smartech.reservas.util.UserSession;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EntrarActiviti extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_entrar_activiti);
		setTitle("GetInside.uy");

		Button btnLogin = (Button) findViewById(R.id.buttonNewUser);

		btnLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				fireTaskForLogin();

			}
		});

		Button btnRegistro = (Button) findViewById(R.id.buttonRegistro);

		btnRegistro.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intentMain = new Intent(EntrarActiviti.this,
						RegistroActivity.class);
				startActivity(intentMain);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.entrar_activiti, menu);
		return true;
	}

	public void fireTaskForLogin() {
		new AsyncTask<Void, Boolean, Boolean>() {

			protected Boolean doInBackground(Void... params) {

				ServiceLayer sl = ServiceFactory.getInstance();

				try {

					TextView textName = (TextView) findViewById(R.id.name);
					TextView textPass = (TextView) findViewById(R.id.pass1);

					return sl.login(textName.getText().toString(), textPass
							.getText().toString());

				} catch (Exception e) {
					e.printStackTrace();
					Toast.makeText(EntrarActiviti.this, e.getMessage(),
							Toast.LENGTH_LONG).show();
				}

				return null;
			}

			protected void onPostExecute(Boolean result) {

				if (result == null)
					return;

				if (result) {

					TextView textName = (TextView) findViewById(R.id.name);
					UserSession.setUserId(textName.getText().toString());

					Intent intentMain = new Intent(EntrarActiviti.this,
							HomeActiviti.class);
					startActivity(intentMain);
					finish();
				} else {

					Toast.makeText(EntrarActiviti.this,
							"Usuario o contraseña incorrecto",
							Toast.LENGTH_LONG).show();
					return;
				}
			};

		}.execute();
	}

}
