package server;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;


public class DataPool {
	File dir;

	DataPool() {
		dir = new File(System.getProperty("user.home"), "ShootingMaster_saves");
		System.out.println(dir.getAbsolutePath());

		if (!dir.exists()) {
			dir.mkdirs();
		}
	}
	
	public boolean save(Map<String, Account> t) {
		try {
			for (String key : t.keySet()) {
				ObjectOutputStream dos = new ObjectOutputStream(new FileOutputStream(new File(dir, key)));
				Account val = t.get(key);
				dos.writeObject(val);
				dos.close();
			}			
			return true;
		} catch (IOException e) {
			System.out.println("save failed.." + e.getMessage());
			return false;
		}
	}
	
	

	
	public Map<String, Account> load() {
		Map<String, Account> map = new HashMap<>();
		File[] childs = dir.listFiles();
//		if (childs.length == 0) {
//			map.put("qwe", new Account("qwe", "qwe", "123"));
//			return map;
//		}
		try {

			for (File c : childs) {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(c));
				Account ac = (Account) ois.readObject();
				map.put(c.getName(), ac);
				ois.close();
			}
			return map;

		} catch (ClassNotFoundException | IOException e) {
			for (File c : childs) {
				c.delete();
			}
			System.out.println(e.toString());
			return null;
		}

	}

}
