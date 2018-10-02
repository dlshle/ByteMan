package bytereader;

import java.util.Scanner;

/**
 *
 * @author Xuri Li 
 */
public class CLIPresentation {
    
    private Scanner input = new Scanner(System.in);
    private Console console;
    
    public CLIPresentation(){
        
    }
    
    public static void main(String[] args){
        if(isGUI(args))
            frmPresent.startGui();
        else{
            CLIPresentation cli = new CLIPresentation();
            cli.mainLoop();
        }
    }
    
    public static boolean isGUI(String[] args){
        return args.length==1&&args.equals("gui");
    }
    
    public void mainLoop(){
        String line = null;
        do{
            System.out.print("Input the file you want to read, or input gui to enable the Graphic User Interface(You can have multiple GUIs).\n>");
            line = getInput();
            if(line.equals("-1"))
                System.exit(0);
            if(line.toLowerCase().equals("gui")){
                frmPresent.startGui();
                continue;
            }
            if(line.equals("exit"))
                break;
            console = new Console(line);
            if(!console.isAvaliable()){
                System.out.println("Invalid file.");
                continue;
            } 
            //is avaliable
            subRoutine();
        }while(true);
    }
    
    /**
     * excute when the file is readable
     */
    public void subRoutine(){
        String line = null;
        System.out.println("File reading complete");
        System.out.println("Analyze complete:");
        System.out.println(console.getFileInfo());
        do{
            System.out.println(getBrifDoc());
            System.out.print(">");
            line = getInput();
            System.out.println(console.executeCommand(line));
        } while(!line.equals("exit"));
    }
    
    public String getBrifDoc(){
        return "Input help for complete documentation. Input exit to exit the subroutine.\nd -> show decimal bytes.\nb -> show binary stream.\nh -> show hexidecimal stream.\nc -> show char stream.\nci -> show char to int stream.";
    }
    
    public String getInput(){
        return input.nextLine();
    }
    
}
