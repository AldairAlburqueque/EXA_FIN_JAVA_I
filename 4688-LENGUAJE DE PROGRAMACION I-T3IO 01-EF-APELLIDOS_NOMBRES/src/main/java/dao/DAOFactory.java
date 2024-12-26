package dao;

import interfaces.BrandInterface;
import interfaces.WaterMachineInterface;

public abstract class DAOFactory {
	public static final int MYSQL = 1;
	public static final int SQLSERVER = 2;
	public static final int ORACLE = 3;
	
	public abstract BrandInterface getBrand();
	public abstract WaterMachineInterface getWaterMachine();
	
	public static DAOFactory getDaoFactory(int tipo) {
		switch (tipo) {
		case MYSQL:
			return new MySqlDAOFactory();
			
		case SQLSERVER:
			return null;
			
		case ORACLE:
			return null;
		default:
			return null;
		}
	}
}
