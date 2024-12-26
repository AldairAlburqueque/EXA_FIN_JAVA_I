package modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidades.WaterMachine;
import interfaces.WaterMachineInterface;
import util.MySqlConexion;

public class WaterMachineModel implements WaterMachineInterface {

	@Override
	public List<WaterMachine> listWaterMachines() {
		// TODO Auto-generated method stub
		List<WaterMachine> lista = new ArrayList<WaterMachine>();
		
		Connection cn = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		
		try {
			cn = MySqlConexion.getConexion();
			cs = cn.prepareCall("{CALL sp_listar_waterMachine()}");
			rs = cs.executeQuery();
			
			while(rs.next()) {
				WaterMachine wm = new WaterMachine();
				wm.setIdWaterMachine(rs.getString("idWaterMachine"));
				wm.setModel(rs.getString("model"));
				wm.setMainColor(rs.getString("mainColor"));
				wm.setWashingCapacity(rs.getString("washingCapacity"));
				wm.setWarranty(rs.getString("warranty"));
				wm.setPrice(rs.getString("price"));
				wm.setBrand(rs.getString("brand"));
				lista.add(wm);
			}
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (cs != null) cs.close();
				if (cn != null) cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return lista;
	}

	@Override
	public int updateWaterMachine(WaterMachine refrigerator) {
		// TODO Auto-generated method stub
		int valor = 0;
		Connection cn = null;
		CallableStatement cs = null;
		
		try {
			cn = MySqlConexion.getConexion();
			cs = cn.prepareCall("{CALL sp_Actualizar_WaterMachine(?,?,?,?,?,?,?)}");
			
			cs.setString(1, refrigerator.getIdWaterMachine());
			cs.setString(2, refrigerator.getModel());
			cs.setString(3, refrigerator.getMainColor());
			cs.setString(4, refrigerator.getWashingCapacity());
			cs.setString(5, refrigerator.getWarranty());
			cs.setString(6, refrigerator.getPrice());
			cs.setString(7, refrigerator.getBrand());
		
			valor = cs.executeUpdate(); 
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (cs!= null) cs.close();
				if (cn!= null) cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return 0;
	}

	@Override
	public WaterMachine getWaterMachine(String idRefrigerator) {
		// TODO Auto-generated method stub
		WaterMachine wm = null;
		Connection cn = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		
		try {
			cn = MySqlConexion.getConexion();
			cs = cn.prepareCall("{CALL sp_GetWaterMachineById(?)}");
			cs.setString(1,	idRefrigerator);
			rs = cs.executeQuery();
			
			if(rs.next()) {
				wm = new WaterMachine();
				wm.setIdWaterMachine(rs.getString("idWaterMachine"));
				wm.setModel(rs.getString("model"));
				wm.setMainColor(rs.getString("mainColor"));
				wm.setWashingCapacity(rs.getString("washingCapacity"));
				wm.setWarranty(rs.getString("warranty"));
				wm.setPrice(rs.getString("price"));
				wm.setBrand(rs.getString("brand"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (cs!= null) cs.close();
				if (cn!= null) cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return wm;
	}

	@Override
	public int deleteWaterMachine(String idRefrigerator) {
		// TODO Auto-generated method stub
		int valor = 0;
		Connection cn = null;
		CallableStatement cs = null;
		
		
		try {
			cn=MySqlConexion.getConexion();			
			cs = cn.prepareCall("{call sp_Eliminar_WaterMachine(?)}");
			cs.setString(1, idRefrigerator);
			valor = cs.executeUpdate();
			
	        
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(cs !=null) cs.close();
				if(cn !=null) cn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
		return valor;
	}

	@Override
	public int registerWaterMachine(WaterMachine refrigerator) {
		// TODO Auto-generated method stub
		int valor = 0;
		Connection cn = null;
		CallableStatement cs = null;

		try {
			cn = MySqlConexion.getConexion();
			cs = cn.prepareCall("{CALL sp_Crear_WaterMachine(?,?,?,?,?,?)}");

			cs.setString(1, refrigerator.getModel());
			cs.setString(2, refrigerator.getMainColor());
			cs.setString(3, refrigerator.getWashingCapacity());
			cs.setString(4, refrigerator.getWarranty());
			cs.setString(5, refrigerator.getPrice());
			cs.setString(6, refrigerator.getBrand());

			valor = cs.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (cs != null)
					cs.close();
				if (cn != null)
					cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return 0;
	
	}

}

