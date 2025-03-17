package maps;

// List of all maps available
public class MapList {
	
	public static MapData[] mapList = new MapData[] { 
			new LadKrapao(), new CentralCity(), new KlongNongBeung(),
			new OldYork()
			};
	
	public static String[] getMapNameList() {
		String[] mapNameList = new String[mapList.length];
		for (int i = 0; i < mapList.length; i++) {
			mapNameList[i] = mapList[i].getName();
		}
		return mapNameList;
	}
	
	public static MapData getMapFromName(String name) {
		for (MapData map : mapList) {
			if (map.getName().equals(name)) {
				return map;
			}
		}
		return null; // no map match the name
	}
	
}
