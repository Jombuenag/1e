import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

// /home/takuma/workspace/1e/files/

public class Main {

	Scanner userInput = new Scanner(System.in);
	FileInputStream fis = null;
	FileOutputStream fos = null;
	DataOutputStream dos = null;
	String folderOrigin = "/home/takuma/workspace/1e/files/";
	String folderDestination = "/home/takuma/workspace/1e/files/";
	String files ="";
	String menuItem = "";
	
	
	public static void main(String[] args) {
		Main m = new Main();
		m.menu();
	}
	
	public void menu(){
		try{
			StringBuilder str = new StringBuilder();
			str.append("------WELCOME TO OPEN / COPY PROGRAM------");
			str.append("\n");
			str.append("\n1 - Open File");
			str.append("\n2 - Copy File");
			str.append("\n3 - Exit");
			System.out.println(str);
			menuItem = userInput.nextLine();
			
			if(menuItem.contains("1")){
				openFile();
			}else if(menuItem.contains("2")){
				copyFile(folderOrigin, folderDestination);
			}else if(menuItem.contains("3")){
				System.out.println("Thanks for using this shit");
				System.exit(0);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void openFile(){
		File directory = new File(folderOrigin);
		String files = "";
		String fileName = "";
		int count = 0;
		
		File[] listOfFiles = directory.listFiles();
		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		    	  files = listOfFiles[i].getName();
		    	  System.out.println(files);
		      }
		    }
		System.out.println("Which file do you want to retrieve");
		fileName = userInput.nextLine();
		System.out.println("You selected " + fileName);
		
		try {
			fis = new FileInputStream(folderOrigin + files);
		while(true){
			byte[] buffer = new byte[1024];

			int nBytes = fis.read(buffer);
			count += nBytes;
			System.out.println(count);
			if(nBytes <= 0){
				break;
			}
		}
		System.out.println("Total count :" + count);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		
		} catch (EOFException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try{
			fis.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		}
	}
	
	public void copyFile(String folderOrigin, String folderDestination){
	File directory = new File(folderOrigin);
	File copiedFile = new File(folderDestination);
	String copiedFileName = "";
		
		File[] listOfFiles = directory.listFiles();
		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		    	  files = listOfFiles[i].getName();
		    	  System.out.println(files);
		      }
		    }
		System.out.println("Which file do you want to copy");
		files = userInput.nextLine();
	
		copiedFileName = (folderDestination + userInput.nextLine());
		try {
			System.out.println("Put a name to the new file");
			copiedFileName = userInput.nextLine();
					
			int reading = 0;
			File newFile = new File(folderOrigin, files);
			newFile.createNewFile();
			File newCopiedFile = new File(folderDestination, files);
			newCopiedFile.createNewFile();
			
			 fis = new FileInputStream(folderOrigin + files);
			 fos = new FileOutputStream(folderDestination + copiedFileName);
			 dos = new DataOutputStream(fos);
			byte[] buffer = new byte[1024];
			
			while((reading = fis.read(buffer)) >0){
				dos.write(buffer,0, reading);
			}
			
			
			System.out.println("You created the file "+ copiedFileName);
			fis.close();
			fos.close();
			dos.close();
			
		}catch(FileNotFoundException fnfe){
			fnfe.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public void tryToClose(){
		
	}
	
	
}
	
	
	
	/*
	 	String folder = "/home/takuma/workspace/1e/files/";
		String fileName = "";
		String extension = "";
		Scanner sc = new Scanner(System.in);
		int reading = 0;
		File directory = new File(folder);
	    
		
		
		File[] listOfFiles = directory.listFiles();
		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		        System.out.println(listOfFiles[i].getName()+extension);
		      }
		    }

SHOW FOLDER


		System.out.println("NAME OF THE FILE");
		fileName = sc.nextLine();
		
		try {
			File newFile = new File(folder, fileName);
			newFile.createNewFile();
			System.out.println("YOU CREATED " + newFile);
			
			 fi = new FileInputStream(folder + "input");
			 fo = new FileOutputStream(folder + "output");
			 dos = new DataOutputStream(fo);
			
			byte[] buffer = new byte[1024];
			
			while((reading = fi.read(buffer)) >0){
				dos.write(buffer,0, reading);
			}
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}finally{
			tryToClose(fi);
			tryToClose(fo);
			tryToClose(dos);
		}
	
	}
	*/
