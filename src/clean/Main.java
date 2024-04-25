/**
 * 
 */
package clean;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 */
public class Main {
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
		System.out.println("Welcome To Clean Slate");
		System.out.print("File contains: ");
		String contains = sn.nextLine();
		System.out.print("Directory: ");
		String directory = sn.nextLine();
		
		// Add the directory given by the user as the root directory to be indexed
		directories.add(0, directory);
		
		// Start the deleting process
		deleteFiles(contains);
		
		/*		
		directories.forEach((dir) -> {
			System.out.println(dir);
		});
		*/
	}

	public static boolean deleteFiles(String contains) {
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
					// Add count so that it goes to the newly added directory when it starts again
					count++;
					// Call the same function to start again
					deleteFiles(contains);
				}
			
				// Checks if the filename contains the string passed into the method
				if(f.getName().contains(contains)) {
					// Delete the file if it contains that
					if(f.delete()) {
						System.out.println(f.getAbsolutePath() + " Successfully Deleted!");
					}
				}
				
			}
		}
		return false;
		
	}
}
