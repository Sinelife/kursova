package main;

import java.util.List;

import javax.swing.JComboBox;

import domain.Device;

public class Tse {
	
	
	
	
	public static int getDeviceIdByDeviceName(String name, int id, JComboBox<String> ComboBox,List<Device> devices)
	{
		name = String.valueOf(ComboBox.getSelectedItem());
		for(Device device : devices) 
		{
			id = device.getId();
			if(device.getName().equals(name))
			{
				break;
			}
		}
		return id;
	}

}
