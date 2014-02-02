package uy.com.smartech.reservas.controller;

import java.util.List;

import uy.com.smartech.reservas.controller.data.DataCategory;
import uy.com.smartech.reservas.controller.data.DataLocal;
import uy.com.smartech.reservas.controller.data.DataResponse;

public interface ServiceLayer {

	boolean login(String userId, String password);

	DataResponse newUser(String userId, String email, String password,
			String phoneNumber);

	List<DataCategory> getCategories();

	List<DataLocal> getLocalesByCategory(String categorId);

	void addLocalToFavorite(String localId, String userId);
}
