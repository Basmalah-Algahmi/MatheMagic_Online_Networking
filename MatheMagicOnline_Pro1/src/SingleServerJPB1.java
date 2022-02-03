import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;
import java.io.*;



public class SingleServerJPB1 {
    
    private static final int SERVER_PORT = 8765;
    
    public static void main(String[] args) {
        
       //open file and read data to hashmap
       HashMap<String, String> loginsInfo = new HashMap<String, String>();
       String[] line;  //to store each client's login info  to array string
       
        try {
            File myObj = new File("logins.txt");
            Scanner myReader = new Scanner(myObj);  
            
            while (myReader.hasNextLine()) 
            {
              String data = myReader.nextLine();
              line = data.split(" ");
              loginsInfo.put(line[0],line[1]); //add to hashmap
            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }//end reading login data from a file to a hashmap
        
        //System.out.println(loginsInfo);   
        createCommunicationLoop(loginsInfo);
        
    }//end main
    
    
    //function to handle the client's command 
    public static void createCommunicationLoop(HashMap<String, String> loginsInfo) {
        boolean authorizedUser=false;
        String[] clientCommand;
        String userName="", pwd;
        
        try {
            //create server socket
            ServerSocket serverSocket = 
                    new ServerSocket(SERVER_PORT);
            
            System.out.println("Server started at " +
                    new Date() + "\n");
            
            //listen for a connection
            //using a regular *client* socket
            Socket socket = serverSocket.accept();
            
            //now, prepare to send and receive data
            //on output streams
            DataInputStream inputFromClient = 
                    new DataInputStream(socket.getInputStream());
            
            DataOutputStream outputToClient =
                    new DataOutputStream(socket.getOutputStream());
            
            //server loop listening for the client 
            //and responding
            while(true) 
            {      
                //login first
                String strReceived= inputFromClient.readUTF();
                clientCommand = strReceived.split(" ");//read client command to string array
                String command=clientCommand[0];

                if (!authorizedUser)
                {
                    if(command.equalsIgnoreCase("login"))
                    {
                        //only validate data if client sent the command as 
                        //login userName password
                        if (clientCommand.length==3)
                        {
                            userName=clientCommand[1];
                            pwd=clientCommand[2];

                            //check if username exist
                            if(loginsInfo.containsKey(userName))
                            {
                                //check if its is the right password for that usename
                                if(loginsInfo.get(userName).equals(pwd)){
                                    outputToClient.writeUTF("SUCCESS"); 
                                    authorizedUser=true;
                                }
                                else{
                                    outputToClient.writeUTF("FAILURE: Please provide correct username and password."
                                                              + " Try again."); 
                                }
                            }
                            else{
                                outputToClient.writeUTF("Your login info is incorrect"); 
                            }                       
                        }
                        //if length of array string is <3 or >3
                        else{
                            outputToClient.writeUTF("FAILURE: Please provide correct username and password."
                                                              + " Try again.");   
                        }
                    }//end validation client's login info
                    else{
                          outputToClient.writeUTF("You need to sign in first");  
                    }
                }//end login command 
                
                //solve command
                else{
                    if(command.equalsIgnoreCase("solve")) 
                    {
                        System.out.println("Sending solve to client");
                        //get solve command details
                        String shape= clientCommand[1];
                        float radius, circumference,length, length2,perimeter, area;
                        
                        //create a solve file 
                        File solveFile = new File(userName+"_solutions.txt");

                        if(solveFile.exists()==false){
                                System.out.println("File created: " + solveFile.getName());
                                solveFile.createNewFile();
                        }
                        PrintWriter write = new PrintWriter(new FileWriter(solveFile, true));

                        if(shape.equalsIgnoreCase("-c"))
                        {
                            if(clientCommand.length==3)
                            {
                                radius= Float.parseFloat(clientCommand[2]);
                                circumference= (float)(Math.PI*2*(radius));
                                area= (float) (Math.PI * Math.pow(radius, 2));

                                write.append("radius "+radius+": Circle’s circumference is "+
                                        String.format("%.2f", circumference)
                                        + " and area is "+ String.format("%.2f" ,area)+"\n");
                                outputToClient.writeUTF("radius "+radius+": Circle’s circumference is "+
                                        String.format("%.2f", circumference)
                                        + " and area is "+ String.format("%.2f" ,area)+"\n");                          
                            }
                            else
                            {
                                write.append("Error: No radius found\n");
                                outputToClient.writeUTF("Error: No radius found\n");
                            }  
                        }
                        else if(shape.equalsIgnoreCase("-r"))
                        {
                            if(clientCommand.length==3)
                            {
                                length= Float.parseFloat(clientCommand[2]);
                                perimeter= (float)(4 * length);
                                area= (float) (Math.pow(length, 2));

                                write.append("Side "+length+" "+length+": Rectangle’s perimeter is "+
                                        String.format("%.2f", perimeter)
                                        + " and area is "+ String.format("%.2f" ,area)+"\n");
                                outputToClient.writeUTF("Side "+length+" "+length+": Rectangle’s perimeteris "+
                                        String.format("%.2f", perimeter)
                                        + " and area is "+ String.format("%.2f" ,area)+"\n");                          
                            }
                            else if(clientCommand.length==4)
                            {
                                length= Float.parseFloat(clientCommand[2]);
                                length2= Float.parseFloat(clientCommand[3]);
                                perimeter= (float)(2*(length+length2));
                                area= (float) (length*length2);

                               write.append("Side "+length+" "+length2+": Rectangle’s perimeter is "+
                                        String.format("%.2f", perimeter)
                                        + " and area is "+ String.format("%.2f" ,area)+"\n");
                                outputToClient.writeUTF("Side "+length+" "+length2+": Rectangle’s perimeteris "+
                                        String.format("%.2f", perimeter)
                                        + " and area is "+ String.format("%.2f" ,area)+"\n");                            
                            }
                            else
                            {
                                write.append("Error: No radius found\n");
                                outputToClient.writeUTF("Error: No sides found\n");
                            }     
                        }  
                         write.close();
                        }
                    //end solve command
                    
                    else if(command.equalsIgnoreCase("list")) 
                    {
                      System.out.println("Sending LIST to client");
                      //append all data to string then send it to the client
                      String fileContent="";
                       
                        if(clientCommand.length==1)
                       {
                           File myObj = new File(userName+"_solutions.txt");
                           Scanner myReader = new Scanner(myObj);
                           fileContent= userName+"\n";
                           while (myReader.hasNextLine()) 
                           {
                                String data = myReader.nextLine();
                                fileContent+=data+"\n";
                           }
                           outputToClient.writeUTF(fileContent);
                           myReader.close();                     
                       }
                        //not sure if need to validate all that, if not, length is
                        //enough to know that it is list -all
                        else if(clientCommand.length==2)
                        {
                            //String allFlag= currencies[1];
                            if (clientCommand[1].equalsIgnoreCase("-all"))
                            {
                                if(userName.equals("root"))
                                {
                                    for (String userFile : loginsInfo.keySet())
                                    {
                                        //go over keys in hashmap, then open file for each
                                        //get content of the file and append it to the string
                                        //finally, send the string to client side                               
                                        File myObj = new File(userFile+"_solutions.txt");                                 
                                        fileContent+= userFile+"\n";
                                        if (myObj.exists()){
                                            Scanner myReader = new Scanner(myObj);
                                            System.out.println(myObj.exists());
                                            while (myReader.hasNextLine()) 
                                            {
                                                 String data = myReader.nextLine();
                                                 fileContent+=data+"\n";
                                            }
                                             myReader.close();
                                        }
                                        else{
                                            fileContent+= "No interactions yet\n";
                                        }                                  
                                }   
                                outputToClient.writeUTF(fileContent);                              
                                }
                                else{
                                    outputToClient.writeUTF("Error: you are not the root user\n");
                                }    
                            }
                            else {
                                System.out.println("Unknown command received: " 
                                    + strReceived);
                                outputToClient.writeUTF("300 invalid command");
                            }
                            
                       }                   
                }
                //end list command   
                else if(command.equalsIgnoreCase("logout")) 
                {
                    //System.out.println("Shutting down server...");
                    outputToClient.writeUTF("200 OK");
                    authorizedUser=false; 
                    //socket.close();      //then close the client     
                    /*program errors if user tries to log back in
                    no validation need*/
                }
                
                //end logout command
                else if(command.equalsIgnoreCase("SHUTDOWN"))
                {
                    System.out.println("\nShutting down server...");
                    outputToClient.writeUTF("\n200 OK...");
                    serverSocket.close();//close the server
                    socket.close();      //then close the client
                    break;  //get out of loop
                }
                    //end shutdown command
                else {
                    System.out.println("Unknown command received: " 
                        + strReceived);
                    outputToClient.writeUTF("300 invalid command");
                    }
                } 
            }//end server loop
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }//end try-catch
    }//end createCommunicationLoop
}