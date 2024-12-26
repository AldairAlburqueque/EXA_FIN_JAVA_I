package interfaces;

import java.util.List;
import entidades.WaterMachine;

public interface WaterMachineInterface {
	public List<WaterMachine> listWaterMachines();
	public int updateWaterMachine(WaterMachine waterMachine);
	public WaterMachine getWaterMachine(String idWaterMachine);
	public int deleteWaterMachine(String idWaterMachine);
	public int registerWaterMachine(WaterMachine waterMachine);
}
