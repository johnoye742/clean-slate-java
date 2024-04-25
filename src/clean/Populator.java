/**
 * 
 */
package clean;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * I just copied the Main.java file and reverse it to create files instead of deleting 
 * Don't worry if the code in this file is shitty, it works, it's simple, it creates one file for each directory 
 * I remember when I wrote a code like the one in the @method generateFileName() and Melvin Jones Repol liked it so much, he wanted it added on Webvium, can't remember what part though, if you're reading this bro... Send a DM please, it's been a while
 */
public class Populator {
	protected static ArrayList<String> directories;
	static int count = 0;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// An array of strings of the absolute path of directories indexed from the root directory the user gives
		directories = new ArrayList<String>();
		
		Scanner sn = new Scanner(System.in);
		System.out.println("Welcome To Clean Slate Populator");
		System.out.print("File contains: ");
		String contains = sn.nextLine();
		System.out.print("Directory: ");
		String directory = sn.nextLine();
		
		// Add the directory given by the user as the root directory to be indexed
		directories.add(0, directory);
		
		// Start the creation process: GENESIS
		try {
			// It creates one file for each directory
			createFiles(contains);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		directories.forEach((dir) -> {
			System.out.println(dir);
		});
		
	}

	public static boolean createFiles(String contains) throws IOException {
		// If the count is more than the directory size, stop the application
		if(count > directories.size()) System.exit(0);
		
		// Get a directory from the list of directories
		File fe = new File(directories.get(count));
		
		
		// List files from the above directory
		File[] fes = fe.listFiles();
		
		if(fes.length > 0) {
			// Loop through the file array above
			for(File f : fes) {
				// Add to the directory list if it is a directory and then start again from the beginning
				if(f.isDirectory()) {
					directories.add(f.getAbsolutePath());
					
					// Create the new file with random name
					File fe2 = new File(f.getAbsolutePath() + File.separatorChar + generateFileName(26) + contains);
					if(fe2.createNewFile()) System.out.println("Successfully created " + fe2.getAbsoluteFile());
					
					// Add count so that it goes to the newly added directory when it starts again
					count++;
					
					// Call the same function to start again
					createFiles(contains);
				}
			
				
			}
		}
		return false;
		
	}
	
	public static String generateFileName(int length) {
		String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789[]abcdefghijklmnopqrstuvwxyz";
		StringBuilder sb = new StringBuilder("");
		
		for(int i = 0; i < length; i++) {
			sb.append(alpha.charAt(new Random().nextInt(0, alpha.length() - 1)));
		}
		
		return sb.toString();
	}
}
