import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;

public class Sort {

	private String from,to;
	private HashMap<String , ArrayList<File>> hashMap= new HashMap<String , ArrayList<File>> ();

	public Sort(String from,String to){
		this.from=from;
		this.to=to;
		this.checkFiles();
	}

	//check the Types Of File and store in hashMap
	private void checkFiles(){
		File file= new File(from);
		String key[]=null,split[]=null;
		ArrayList<File> list= new ArrayList<File>();
		File[] fileContain= file.listFiles();

		for(File out: fileContain){
			if(out.isFile() ){
				try{
					key=out.getName().split("\\.");
					//		System.out.println("key is "+key[key.length-1]);
				}catch(Exception a){

				}

				if(!hashMap.containsKey(key[key.length-1])){
					for(File in: fileContain){
						try{
							split=in.getName().split("\\.");

						}catch(Exception a){

						}
						if(key[key.length-1].equalsIgnoreCase(split[split.length-1])){
							//			System.out.println(in.getName());
							list.add(in);
						}

					}
					/*	System.out.println("key is "+ key[key.length-1]);
					for(File f: list){
						System.out.println(f);
					}
					 */hashMap.put(key[key.length-1], new ArrayList<File>(list));
					 list.clear();
				}


			}

		}

	}

	public void copy(){
		File sor= new File(from);

		File[] internalFile= sor.listFiles();

		try{
			for(String key: hashMap.keySet()){
				File des=new File(to+"/"+key);
				des.mkdir();
				for(File data: hashMap.get(key)){
					Files.copy(new File(from+"/"+data.getName()).toPath(), new File(des+"/"+data.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING );

				}
			}
		}catch(Exception a){
			System.out.println(a);
		}

	}

	public void  move(){
		
		File sor= new File(from);
		File[] internalFile= sor.listFiles();

		try{
			for(String key: hashMap.keySet()){
				File des=new File(to+"/"+key);
				des.mkdir();
				for(File data: hashMap.get(key)){
					Files.move(new File(from+"/"+data.getName()).toPath(), new File(des+"/"+data.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING );

				}
			}
		}catch(Exception a){
			System.out.println(a);
		}
		
	}
}
