// Nand2Tetris - VM Translator 
import java.io.*;
import java.util.*;

public class VMTranslator 
{
    public static int index = 0;
    public static int value = 0;
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int Bootstrap_Ocurrance = 0;
        String[] input;
        String line;
        String Preline;
        String Arithmetic_ASM;
        String Memory_ASM;
        String Branching_ASM;
        String Functions_ASM;
        String Function[] = {"function","call","return"};
        String DirectoryName;
        String Filename;

        // Try:catch - used in file handling - catch is used to catch any Exceptions or errors!
        try
        {
            System.out.println("Enter the Directory name");
            DirectoryName = in.next();                // Input the folder name
            File Directory = new File("H:\\Code\\VM Files\\"+ DirectoryName);  // Reading the files from the folder
            FileWriter writer = new FileWriter("H:\\Code\\ASM Files\\" + DirectoryName +".asm");  // Writing to the .asm file
            for(File file : Directory.listFiles())
            {
                Filename = file.getName();                         // Storing the name of the file to "Filename"
                if(Filename.contains(".vm"))
                {
                    Scanner scan = new Scanner(file);
                    while(scan.hasNextLine())              // while loop is used here: to iterate till the last line in the .vm file
                    {
                        Preline = scan.nextLine();            // taking the line as input using scanner class from .vm file and storing it in string "Preline" 

                        if(Preline.indexOf("/") == 0 || Preline.length() == 0)   // Ignoring Comments and Blank Spaces
                        {

                        } 
                        else
                        {
                            input = Preline.split("//");           // splitting the sentence from any Inline Comments
                            line = input[0];                   // using the first split and storing back to the string "line"
                            String words[] = line.split("\\s"); // Splitting the sentence into words
                           
                            if(line.contains("push") || line.contains("pop")) // if the line contains push pop commands    
                            {   
                                Memory_ASM = Memory_Segments_Commands(line,Filename);
                                writer.write(Memory_ASM);      // writing memory segments to .asm file
                            }
                            else if(words.length == 1 && !(words[0].equals("return")))       // if the line contains a single word
                            {
                                Arithmetic_ASM = Arithmetic_Logic_Commands(words[0]);
                                writer.write(Arithmetic_ASM);  // writing arithmetic & logical operations to .asm file
                            }
                            else if(words.length == 2)        // if the line contains 2 words
                            {
                                Branching_ASM = Branching_Commands(line);
                                writer.write(Branching_ASM);   // writing branching commands to .asm file
                            }
                            else if(Arrays.asList(Function).contains(words[0]))  // if the line contains function commands
                            {
                                if(Filename.contains("SimpleFunction.vm"))
                                {
                                    Functions_ASM = Functions_Calls(line);
                                    writer.write(Functions_ASM);
                                }
                                else
                                {
                                    // Bootstrap Code
                                    
                                    String BootstrapCode =
                                    "// Bootstrap Code\n" +           
                                    "@256\n"+
                                    "D=A\n"+
                                    "@SP\n"+
                                    "M=D\n"+
                                    "@SP\n" +
                                    "D=M\n" +
                                    "@R13\n" +
                                    "M=D\n" +
                                    "@RET." + index + "\n" +
                                    "D=A\n" +
                                    "@SP\n" +
                                    "A=M\n" +
                                    "M=D\n" +
                                    "@SP\n" +
                                    "M=M+1\n" +
                                    "@LCL\n" +
                                    "D=M\n" +
                                    "@SP\n" +
                                    "A=M\n" +
                                    "M=D\n" +
                                    "@SP\n" +
                                    "M=M+1\n" +
                                    "@ARG\n" +
                                    "D=M\n" +
                                    "@SP\n" +
                                    "A=M\n" +
                                    "M=D\n" +
                                    "@SP\n" +
                                    "M=M+1\n" +
                                    "@THIS\n" +
                                    "D=M\n" +
                                    "@SP\n" +
                                    "A=M\n" +
                                    "M=D\n" +
                                    "@SP\n" +
                                    "M=M+1\n" +
                                    "@THAT\n" +
                                    "D=M\n" +
                                    "@SP\n" +
                                    "A=M\n" +
                                    "M=D\n" +
                                    "@SP\n" +
                                    "M=M+1\n" +
                                    "@R13\n" +
                                    "D=M\n" +
                                    "@" + "0" + "\n" +
                                    "D=D-A\n" +
                                    "@ARG\n" +
                                    "M=D\n" +
                                    "@SP\n" +
                                    "D=M\n" +
                                    "@LCL\n" +
                                    "M=D\n" +
                                    "@" + "Sys.init" + "\n" +
                                    "0;JMP\n" +
                                    "\n";
                                    
                                    if(Bootstrap_Ocurrance == 0)           // First Bootstrap Occurance
                                    {
                                        writer.write(BootstrapCode);
                                        Bootstrap_Ocurrance ++;
                                        Functions_ASM = Functions_Calls(line);
                                        writer.write(Functions_ASM);
                                    }
                                    else
                                    {
                                        Functions_ASM = Functions_Calls(line);
                                        writer.write(Functions_ASM);
                                    }
                                }
                            }
                        }
                    }
                    scan.close();
                }
            }
            writer.close();
            System.out.println(DirectoryName + ".asm file created successfully!");
        }
        catch(IOException e)
        {
            System.out.println("Unfortunately, An error occured!");
        }
        in.close();
    } 

    // Arithmetic Logical Commands

    public static String Arithmetic_Logic_Commands(String line)                  
    {

        if(line.equals("add"))                              // Add command
            {
                String ADD =
                "// ADD \n"+
                "@SP\n"+
                "A=M\n"+
                "A=A-1\n"+
                "A=A-1\n"+
                "D=M\n"+
                "A=A+1\n"+
                "D=D+M\n"+
                "@SP\n"+
                "M=M-1\n"+
                "M=M-1\n"+
                "A=M\n"+
                "M=D\n"+
                "@SP\n"+
                "M=M+1\n"+
                "\n";
                return ADD;   
            }
            else if(line.equals("eq"))                      // "Equal to" command
            {
                
                String EQ = 
                "// EQ \n"+
                "@SP\n"+
                "AM=M-1\n"+
                "D=M\n"+
                "@SP\n"+
                "AM=M-1\n"+
                "D=M-D\n"+
                "@TRUE"+ index + "\n" +
                "D;JEQ\n"+
                "@FALSE"+ index + "\n" +
                "0;JMP\n"+
                "(TRUE"+ index +")" +"\n" +
                "@SP\n"+
                "A=M\n"+
                "M=-1\n"+
                "@SP\n"+
                "M=M+1\n"+
                "@END"+ index + "\n" +
                "0;JMP\n"+
                "(FALSE"+ index +")" +"\n" +
                "@SP\n"+
                "A=M\n"+
                "M=0\n"+
                "@SP\n"+
                "M=M+1\n"+
                "(END"+ index +")" +"\n" +
                "\n";
                index ++;
                return EQ;  
            }
            else if(line.equals("lt"))                      // "Less than" command
            {
                String LT = 
                "// LT \n"+
                "@SP\n"+
                "AM=M-1\n"+
                "D=M\n"+
                "@SP\n"+
                "AM=M-1\n"+
                "D=M-D\n"+
                "@TRUE"+ index + "\n" +
                "D;JLT\n"+
                "@FALSE"+ index + "\n" +
                "0;JMP\n"+
                "(TRUE"+ index +")" +"\n" +
                "@SP\n"+
                "A=M\n"+
                "M=-1\n"+
                "@SP\n"+
                "M=M+1\n"+
                "@END"+ index + "\n" +
                "0;JMP\n"+
                "(FALSE"+ index +")" +"\n" +
                "@SP\n"+
                "A=M\n"+
                "M=0\n"+
                "@SP\n"+
                "M=M+1\n"+
                "(END"+ index +")" +"\n" +
                "\n";
                index ++;
                return LT;  
            }
            else if(line.equals("gt"))                       // "greator than" command
            {
                String GT = 
                "// GT \n"+
                "@SP\n"+
                "AM=M-1\n"+
                "D=M\n"+
                "@SP\n"+
                "AM=M-1\n"+
                "D=M-D\n"+
                "@TRUE"+ index + "\n" +
                "D;JGT\n"+
                "@FALSE"+ index + "\n" +
                "0;JMP\n"+
                "(TRUE"+ index +")" +"\n" +
                "@SP\n"+
                "A=M\n"+
                "M=-1\n"+
                "@SP\n"+
                "M=M+1\n"+
                "@END"+ index + "\n" +
                "0;JMP\n"+
                "(FALSE"+ index +")" +"\n" +
                "@SP\n"+
                "A=M\n"+
                "M=0\n"+
                "@SP\n"+
                "M=M+1\n"+
                "(END"+ index +")" +"\n" +
                "\n";
                index++;
                return GT;  
            }
            else if(line.equals("sub"))                           // subtraction command 
            {
                String SUB = 
                "// SUB \n"+
                "@SP\n"+
                "A=M\n"+
                "A=A-1\n"+
                "A=A-1\n"+
                "D=M\n"+
                "A=A+1\n"+
                "D=D-M\n"+
                "@SP\n"+
                "M=M-1\n"+
                "M=M-1\n"+
                "A=M\n"+
                "M=D\n"+
                "@SP\n"+
                "M=M+1\n"+
                "\n";
                return SUB;  

            }
            else if(line.equals("neg"))                           // negation command
            {
                String NEG = 
                "// NEG \n"+
                "@SP\n"+
                "M=M-1\n"+
                "A=M\n"+
                "M=-M\n"+
                "@SP\n"+
                "M=M+1\n"+
                "\n";
                return NEG;  
            }
            else if(line.equals("and"))                           // and command
            {
                String AND =
                "// AND \n"+
                "@SP\n"+
                "A=M\n"+
                "A=A-1\n"+
                "A=A-1\n"+
                "D=M\n"+
                "A=A+1\n"+
                "D=D&M\n"+
                "@SP\n"+
                "M=M-1\n"+
                "M=M-1\n"+
                "A=M\n"+
                "M=D\n"+
                "@SP\n"+
                "M=M+1\n"+
                "\n";
                return AND;  
            }
            else if(line.equals("or"))                            // or command
            {
                String OR =
                "// OR \n"+
                "@SP\n"+
                "A=M\n"+
                "A=A-1\n"+
                "A=A-1\n"+
                "D=M\n"+
                "A=A+1\n"+
                "D=D|M\n"+
                "@SP\n"+
                "M=M-1\n"+
                "M=M-1\n"+
                "A=M\n"+
                "M=D\n"+
                "@SP\n"+
                "M=M+1\n"+
                "\n";
                return OR;  
            }
            else if(line.equals("not"))                          // not command
            {
                String NOT =
                "// NOT \n"+
                "@SP\n" +
                "M=M-1\n" +
                "A=M\n"+
                "M=!M\n"+
                "@SP\n"+
                "M=M+1\n"+
                "\n";
                return NOT;  
            }
            else                                               // else return empty string
            {
                return "";
            }
    } 


    // Memory Segments Commands
    public static String Memory_Segments_Commands(String line, String filename)       
    {
        String words[] = line.split("\\s");              // splitting the entire line into words
        if(words[0].equals("push"))
        {
            if(words[1].equals("constant"))                             // push constant
            {
                String value = words[2];
                String x = "@" + value;
                String pushconstant = 
                "// PUSH CONSTANT "+ value + "\n"+
                x +"\n" +
                "D=A\n" +
                "@SP\n" +
                "A=M\n" +
                "M=D\n" +
                "@SP\n" +
                "M=M+1\n"+
                "\n";
                return pushconstant;   
            }
            else if(words[1].equals("argument"))                       // push argument
            {
                String value = words[2];
                String x = "@" + value;
                String pushargument = 
                "// PUSH ARGUMENT "+ value + "\n"+
                x +"\n" +
                "D=A\n"+
                "@ARG\n"+
                "A=M\n"+
                "D=D+A\n"+
                "A=D\n"+
                "D=M\n"+
                "@SP\n"+
                "A=M\n"+
                "M=D\n"+
                "@SP\n"+
                "M=M+1\n"+
                "\n";
                return pushargument;   
            }
            else if(words[1].equals("local"))                         // push local
            {
                String value = words[2];
                String x = "@" + value;
                String pushlocal = 
                "// PUSH LOCAL "+ value + "\n"+
                x +"\n" +
                "D=A\n"+
                "@LCL\n"+
                "A=M\n"+
                "D=D+A\n"+
                "A=D\n"+
                "D=M\n"+
                "@SP\n"+
                "A=M\n"+
                "M=D\n"+
                "@SP\n"+
                "M=M+1\n"+
                "\n";
                return pushlocal;   
            }
            else if(words[1].equals("that"))                          // push that
            {
                String value = words[2];
                String x = "@" + value;
                String pushtthat = 
                "// PUSH THAT "+ value + "\n"+
                x +"\n" +
                "D=A\n"+
                "@THAT\n"+
                "A=M\n"+
                "D=D+A\n"+
                "A=D\n"+
                "D=M\n"+
                "@SP\n"+
                "A=M\n"+
                "M=D\n"+
                "@SP\n"+
                "M=M+1\n"+
                "\n";
                return pushtthat;   
            }
            else if(words[1].equals("this"))                             // push this
            {
                String value = words[2];
                String x = "@" + value;
                String pushthis = 
                "// PUSH THIS "+ value + "\n"+
                x +"\n" +
                "D=A\n"+
                "@THIS\n"+
                "A=M\n"+
                "D=D+A\n"+
                "A=D\n"+
                "D=M\n"+
                "@SP\n"+
                "A=M\n"+
                "M=D\n"+
                "@SP\n"+
                "M=M+1\n"+
                "\n";
                return pushthis;   
            }
            else if(words[1].equals("temp"))                           // push temp
            {
                String value = words[2];
                int x = Integer.parseInt(value)+5;
                String pushtemp = 
                "// PUSH TEMP "+ value + "\n"+
                "@"+x+"\n"+
                "D=M\n"+
                "@SP\n"+
                "A=M\n"+
                "M=D\n"+
                "@SP\n"+
                "M=M+1\n"+
                "\n";
                return pushtemp;   
            }
            else if(words[1].equals("static"))                        // push static
            {
                String value = words[2];
                String pushstatic = 
                "// PUSH STATIC "+ value + "\n"+
                "@"+filename+"."+words[1]+"."+value+"\n"+
                "D=M\n"+
                "@SP\n"+
                "A=M\n"+
                "M=D\n"+
                "@SP\n"+
                "M=M+1\n"+
                "\n";
                return pushstatic;   
            }
            else if(words[1].equals("pointer") && words[2].equals("0"))           // push pointer 0
            {

                String pushpointer =
                "// PUSH POINTER "+ words[2] + "\n"+
                "@THIS\n"+
                "D=M\n"+
                "@SP\n"+
                "A=M\n"+
                "M=D\n"+
                "@SP\n"+
                "M=M+1\n"+
                "\n";
                return pushpointer;   
            }
            else if(words[1].equals("pointer") && words[2].equals("1"))               // push pointer 1
            {

                String pushpointer = 
                "// PUSH POINTER "+ words[2] + "\n"+
                "@THAT\n"+
                "D=M\n"+
                "@SP\n"+
                "A=M\n"+
                "M=D\n"+
                "@SP\n"+
                "M=M+1\n"+
                "\n";
                return pushpointer;   
            }
            else                                                                   // else return empty string
            {
                return "";
            }
        }
        else if(words[0].equals("pop"))                                              // if the first word is pop
        {
            if(words[1].equals("local"))                                               // pop local
            { 
                String value = words[2];
                String x = "@" + value;
                String poplocal =
                "// POP LOCAL "+ value + "\n"+
                x +"\n" +
                "D=A\n"+
                "@LCL\n"+
                "A=M\n"+
                "D=D+A\n"+
                "@LCL\n"+
                "M=D\n"+
                "@SP\n"+
                "M=M-1\n"+
                "A=M\n"+
                "D=M\n"+
                "@LCL\n"+
                "A=M\n"+
                "M=D\n"+
                x +"\n" +
                "D=A\n"+
                "@LCL\n"+
                "A=M\n"+
                "D=A-D\n"+
                "@LCL\n"+
                "M=D\n"+
                "\n";
                return poplocal;   
            }
            else if(words[1].equals("argument"))                                   // pop argument
            {
                String value = words[2];
                String x = "@" + value;
                String popargument = 
                "// POP ARGUMENT "+ value + "\n"+
                x +"\n" +
                "D=A\n"+
                "@ARG\n"+
                "A=M\n"+
                "D=D+A\n"+
                "@ARG\n"+
                "M=D\n"+
                "@SP\n"+
                "M=M-1\n"+
                "A=M\n"+
                "D=M\n"+
                "@ARG\n"+
                "A=M\n"+
                "M=D\n"+
                x +"\n" +
                "D=A\n"+
                "@ARG\n"+
                "A=M\n"+
                "D=A-D\n"+
                "@ARG\n"+
                "M=D\n"+
                "\n";
                return popargument;   
            } 
            else if(words[1].equals("this"))                                   // Pop this
            {
                String value = words[2];
                String x = "@" + value;
                String popthis = 
                "// POP THIS "+ value + "\n"+
                x +"\n" +
                "D=A\n"+
                "@THIS\n"+
                "A=M\n"+
                "D=D+A\n"+
                "@THIS\n"+
                "M=D\n"+
                "@SP\n"+
                "M=M-1\n"+
                "A=M\n"+
                "D=M\n"+
                "@THIS\n"+
                "A=M\n"+
                "M=D\n"+
                x +"\n" +
                "D=A\n"+
                "@THIS\n"+
                "A=M\n"+
                "D=A-D\n"+
                "@THIS\n"+
                "M=D\n"+
                "\n";
                return popthis;   
            }
            else if(words[1].equals("that"))                                   // Pop that
            {
                String value = words[2];
                String x = "@" + value;
                String popthat = 
                "// POP THAT "+ value + "\n"+
                x +"\n" +
                "D=A\n"+
                "@THAT\n"+
                "A=M\n"+
                "D=D+A\n"+
                "@THAT\n"+
                "M=D\n"+
                "@SP\n"+
                "M=M-1\n"+
                "A=M\n"+
                "D=M\n"+
                "@THAT\n"+
                "A=M\n"+
                "M=D\n"+
                x +"\n" +
                "D=A\n"+
                "@THAT\n"+
                "A=M\n"+
                "D=A-D\n"+
                "@THAT\n"+
                "M=D\n"+
                "\n";
                return popthat;   
            }
            else if(words[1].equals("temp"))                                       // Pop temp
            {
                String value = words[2];
                int x = Integer.parseInt(value)+5;
                String poptemp = 
                "// POP TEMP "+ value + "\n"+
                "@SP\n"+
                "M=M-1\n"+
                "A=M\n"+
                "D=M\n"+
                "@"+x+"\n"+
                "M=D\n"+
                "\n";
                return poptemp;   
            }
            else if(words[1].equals("pointer") && words[2].equals("0"))            // Pop pointer 0
            {
                String poppointer = 
                "// POP POINTER "+ words[2] + "\n"+
                "@SP\n"+
                "M=M-1\n"+
                "A=M\n"+
                "D=M\n"+
                "@THIS\n"+
                "M=D\n"+
                "\n";
                return poppointer;   
            }
            else if(words[1].equals("pointer") && words[2].equals("1"))            // Pop pointer 1
            {
                String poppointer = 
                "// POP POINTER "+ words[2] + "\n"+
                "@SP\n"+
                "M=M-1\n"+
                "A=M\n"+
                "D=M\n"+
                "@THAT\n"+
                "M=D\n"+
                "\n";
                return poppointer;   
            }
            else if(words[1].equals("static"))                                     // Pop Static
            {
                String value = words[2];
                String popstatic = 
                "// POP STATIC "+ value + "\n"+
                "@SP\n"+
                "M=M-1\n"+
                "A=M\n"+
                "D=M\n"+
                "@"+filename+"."+words[1]+"."+value+"\n"+
                "M=D\n"+
                "\n";
                return popstatic;   
            }
            else                 // else return an empty string
            {
                return "";
            }
              
        }
        else                     // else return an empty string
        {
            return "";
        }
    }


    // Branching Commands
    public static String Branching_Commands(String line)                   
    {
        String words[] = line.split("\\s");               // splitting the entire line into words
        if(words[0].equals("label"))                      // if the first word is label
        {
            String label = "// LABEL \n"+
            "(" + words[1] + ")"+"\n"+
            "\n";
            return label;
        }
        else if(words[0].equals("if-goto"))               // if the first word is if-goto
        {
            String if_goto = 
            "// IF GOTO "+ words[1] +"\n"+
            "@SP\n"+
            "M=M-1\n"+
            "A=M\n"+
            "D=M\n"+
            "@"+words[1]+"\n"+
            "D;JNE\n"+
            "\n";
            return if_goto;
        }
        else if(words[0].equals("goto"))                 // if the first word is goto
        {
            String Goto = 
            "// GOTO "+ words[1] +"\n"+
            "@"+words[1]+"\n"+
            "0;JMP\n"+
            "\n";
            return Goto;
        }
        else                                            // else return an empty string
        {
            return "";
        }
    } 

    // Function call Commands
    public static String Functions_Calls(String line)
    {
        String words[] = line.split("\\s");                     // splitting the entire line into words

        if(words[0].equals("function"))                         // if the first word is function
        {
            String function = 
            "(" + words[1] + ")"+"\n"+
            "@SP\n"+
            "A=M\n";
            for (int i = 0; i < Integer.parseInt(words[2]); i += 1) 
            {
                function +=
                "M=0\n" +
                "A=A+1\n";
            }
            function = function +
            "D=A\n"+
            "@SP\n"+
            "M=D\n"+
            "\n";
            return function;
        }
        else if(words[0].equals("call"))                              // if the first word is call
        {
            String f = words[1];
            String n = words[2];
            String call = "// CALL "+ f + "\n"+
            "@Sys.vm-return-address-"+value+"\n"+
            "D=A\n"+
            "@SP\n"+
            "A=M\n"+
            "M=D\n"+
            "@SP\n"+
            "M=M+1\n"+
            "@LCL\n"+
            "D=M\n"+
            "@SP\n"+
            "A=M\n"+
            "M=D\n"+
            "@SP\n"+
            "M=M+1\n"+
            "@ARG\n"+
            "D=M\n"+
            "@SP\n"+
            "A=M\n"+
            "M=D\n"+
            "@SP\n"+
            "M=M+1\n"+
            "@THIS\n"+
            "D=M\n"+
            "@SP\n"+
            "A=M\n"+
            "M=D\n"+
            "@SP\n"+
            "M=M+1\n"+
            "@THAT\n"+
            "D=M\n"+
            "@SP\n"+
            "A=M\n"+
            "M=D\n"+
            "@SP\n"+
            "M=M+1\n"+
            "@5\n"+
            "D=A\n"+
            "@"+n+"\n"+
            "D=D+A\n"+
            "@SP\n"+
            "D=M-D\n"+
            "@ARG\n"+
            "M=D\n"+
            "@SP\n"+
            "D=M\n"+
            "@LCL\n"+
            "M=D\n"+
            "@"+f+"\n"+
            "0;JMP\n"+
            "(Sys.vm-return-address-"+value+")\n"+
            "\n";
            value++;
            return call;
        }
        else if(words[0].equals("return"))                    // if the first word is return
        {
            String Return = 
            "// RETURN \n"+
            "@LCL\n"+
            "D=M\n"+
            "@R13\n"+
            "M=D\n"+
            "@5\n"+
            "D=D-A\n"+
            "A=D\n"+
            "D=M\n"+
            "@R14\n"+
            "M=D\n"+
            "@SP\n"+
            "AM=M-1\n"+
            "D=M\n"+
            "M=0\n"+
            "@ARG\n"+
            "A=M\n"+
            "M=D\n"+
            "D=A+1\n"+
            "@SP\n"+
            "M=D\n"+
            "@R13\n"+
            "A=M-1\n"+
            "D=M\n"+
            "@THAT\n"+
            "M=D\n"+
            "@2\n"+
            "D=A\n"+
            "@R13\n"+
            "D=M-D\n"+
            "A=D\n"+
            "D=M\n"+
            "@THIS\n"+
            "M=D\n"+
            "@3\n"+
            "D=A\n"+
            "@R13\n"+
            "D=M-D\n"+
            "A=D\n"+
            "D=M\n"+
            "@ARG\n"+
            "M=D\n"+
            "@4\n"+
            "D=A\n"+
            "@R13\n"+
            "D=M-D\n"+
            "A=D\n"+
            "D=M\n"+
            "@LCL\n"+
            "M=D\n"+
            "@R14\n"+
            "A=M\n"+
            "0;JMP\n"+
            "\n";
            return Return;
        }
        else                      // else return an empty string
        {
            return "";
        }
    }
}
