 package modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidades.Brand;
import interfaces.BrandInterface;
import util.MySqlConexion;

public class BrandModel implements BrandInterface {

	@Override
	public List<Brand> listBrands() {
		// TODO Auto-generated method stub
		List<Brand> listaBrand = new ArrayList<Brand>();
		
		Connection cn = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		
		try {
			cn = MySqlConexion.getConexion();
			cs = cn.prepareCall("{CALL sp_Listar_Brand()}");
			rs = cs.executeQuery();
			
			while(rs.next()) {
				Brand brand = new Brand();
				brand.setIdBrand(rs.getString("idBrand"));
				brand.setName(rs.getString("name"));
				listaBrand.add(brand);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(cs != null) cs.close();
				if(cn != null) cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return listaBrand;
	}

}
