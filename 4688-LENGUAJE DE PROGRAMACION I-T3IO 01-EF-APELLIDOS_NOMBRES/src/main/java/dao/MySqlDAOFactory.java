package dao;

import interfaces.BrandInterface;
import interfaces.WaterMachineInterface;
import modelo.BrandModel;
import modelo.WaterMachineModel;

public class MySqlDAOFactory extends DAOFactory {

	@Override
	public BrandInterface getBrand() {
		// TODO Auto-generated method stub
		return new BrandModel();
	}

	@Override
	public WaterMachineInterface getWaterMachine() {
		// TODO Auto-generated method stub
		return new WaterMachineModel();
	}

}
