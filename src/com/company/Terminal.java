package com.company;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Terminal
{
    public void data(String cmd,String path1,String path2,String sign) throws Exception
    {
        if(cmd.equals("help"))
            help(path1,sign);
        else if(cmd.equals("mkdir"))
            mkdir(path1);
        else if(cmd.equals("mv"))
            mv(path1,path2);
        else if(cmd.equals("rm"))
            rm(path1);
        else if(cmd.equals("ls"))
          ls(path1,sign);
        else if(cmd.equals("args"))
            args(path1,sign);
        else if(cmd.equals("cat"))
            cat(path1,path2);
        else if(cmd.equals("rmdir"))
            rmdir(path1);
        else if(cmd.equals("cd"))
            cd(path1);
        else if(cmd.equals("more"))
            more(path1);
        else if(cmd.equals("exit"))
            exit();
        else if(cmd.equals("pwd"))
            pwd(path1,sign);
        else if(cmd.equals("cp"))
            cp(path1,path2);
        else if(cmd.equals("date"))
            date(path1,sign);
        else if(cmd.equals("clear"))
            clear();

    }
    public void cp(String path1,String path2)throws Exception // copy from .. to
    {
        Files.copy(Paths.get(path1), Paths.get(path2).resolve(Paths.get(path1).getFileName()), StandardCopyOption.REPLACE_EXISTING);
    }
    public void mv(String path1,String path2) throws Exception // move from .. to
    {
        Files.move(Paths.get(path1), Paths.get(path2).resolve(Paths.get(path1).getFileName()), StandardCopyOption.REPLACE_EXISTING);
    }
    public void rm(String path1) throws Exception // Delete file
    {
        if(Files.exists(Paths.get(path1)))
            Files.delete(Paths.get(path1));
        else System.out.println("This directory is not existed");
    }
    public void pwd(String path1,String sign)throws Exception  // show where i am
    {
        String directory = System.getProperty("user.dir");
        if(!sign.equals(" ")) {
            if(sign.equals(">"))
                Files.write(Paths.get(path1), directory.getBytes() ,StandardOpenOption.TRUNCATE_EXISTING);
            else if(sign.equals(">>"))
                Files.write(Paths.get(path1), directory.getBytes(), StandardOpenOption.APPEND);
        }
        System.out.println("\n"+directory);
    }
    public void mkdir(String path)
    {
        try
        {
            if(Files.exists(Paths.get(path)))
                System.out.println("This directory is existed");
            else
                Files.createDirectory(Paths.get(path));
        }
        catch (Exception e)
        {
            System.out.println("Ther is no directory like that");
        }
    }
    public void help(String path, String sign) throws Exception
    {
        String Cdirectory = System.getProperty("user.dir");
        String help= "date: Displays system date and time\n";
        help+="help: List all commands and functionalities\n";
        help+="args: List all commands arguments\n";
        help+="clear: Clears the console\n";
        help+="cd: Changes current working directory\n";
        help+="ls: List all contents of current directory\n";
        help+="pwd: Displays the absolute path of current directory\n";
        help+="cp: Copies files\n";
        help+="mv: Moves files\n";
        help+="mkdir: Creates a new directory\n";
        help+="rmdir: Deletes a directory\n";
        help+="rm: Deletes a file\n";
        help+="cat: Displays contents of a file and concatenates files and display output\n";
        help+="more: Let us display and scroll down the output in one direction only\n";
        help+="exit: Terminates the program\n";
        if(!sign.equals(" "))
        {
            if(sign.equals(">"))Files.write(Paths.get(path),help.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
            else if(sign.equals(">>"))Files.write(Paths.get(path), help.getBytes(), StandardOpenOption.APPEND);
        }
        System.out.print(help);

    }
    private static void date(String path, String sign) throws Exception
    {
        Date date = new Date();
        System.out.println(date.toString());
        if(!sign.equals(" "))
        {
            if(sign.equals(">"))Files.write(Paths.get(path), date.toString().getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
            else if(sign.equals(">>"))Files.write(Paths.get(path), date.toString().getBytes(), StandardOpenOption.APPEND);
        }
    }
    private void rmdir(String path) throws Exception
    {

        if(Files.exists(Paths.get(path)))
        {
            Files.delete(Paths.get(path));
        }
        else System.out.println("This directory is not existed");
    }
    private void cat (String path ,String file2path) throws Exception {
        String content= new String();
        File file=new File(path);
        if(file.exists())
            content = new Scanner(file).useDelimiter("\\Z").next();
        else System.out.println("File not found.");

        if(!file2path.equals(" "))
        {
            File file2 =new File(file2path);
            if(file.exists())
                content +=new Scanner(file2).useDelimiter("\\Z").next();
            else System.out.println("File not found.");
        }
        System.out.println(content);
    }
    private void clear()
    {
        for(int i=0 ; i<100 ; i++)
            System.out.println(" ");
    }
    public static void cd(String path)
    {
        System.setProperty("user.dir",path);
        String Cdirectory = System.getProperty("user.dir");
        System.out.println(Cdirectory);
    }
    private void ls(String patth,String sign) throws Exception
    {
        String Cdirectory = System.getProperty("user.dir"),temp="";
        ArrayList<String> fileNames = new ArrayList<>();
        DirectoryStream<Path> directory = Files.newDirectoryStream(Paths.get(Cdirectory));
        int i=0;
        for (Path path : directory) {

            fileNames.add(path.toString());
            temp+=fileNames.get(i)+" , ";
            System.out.print(fileNames.get(i)+" , ");
            i++;
        }
        if(!sign.equals("")) {
            if(sign.equals(">"))Files.write(Paths.get(patth), temp.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
            else if(sign.equals(">>"))Files.write(Paths.get(patth), temp.getBytes(), StandardOpenOption.APPEND);
        }
    }

    private void more(String filepath)throws Exception
    {
        File file=new File(filepath);
        int c=1;
        Scanner get =new Scanner(System.in);
        Scanner sc = new Scanner(file);
        while(c!=0)
        {
            for(int i=0 ; i<30 && sc.hasNextLine() ; i++)
                System.out.println(sc.nextLine());
            if(sc.hasNextLine())
            {
                System.out.println("\nThere is more want to show more ?! press 0 to stop else to show more\n");
                c=get.nextInt();
            }
            else
                break;
        }
    }
    private void args(String path,String sign)throws Exception {
        String args="\ncd      >>  The destination directory i.e. (E://)\n"+
                "rm      >>  The file to delete i.e. (E://file.txt)\n"+
                "mkdir   >>  The path and name of directory to creat i.e. (E://file)\n"+
                "rmdir   >>  The path and name of directory to remove i.e. (E://file)\n"+
                "cp      >>  The path and name of file to copy and the copy destination directory path i.e. (E://file F://file)\n"+
                "mv      >>  The path and name of file to move and destination directory path i.e. (E://file F://file)\n"+
                "more    >>  The path and name of the file to display i.e (E://file.txt)\n"+
                "cat     >>  The path and name of the file to display i.e (E://file.txt)\n"+
                "clear   >>  No parameters\n"+
                "pwd     >>  No parameters or The path of file to write on the Cdirectory i.e. (E://file.txt)\n"+
                "ls      >>  No parameters or The path of file to write on the list i.e. (E://file.txt)\n"+
                "help    >>  No parameters or The path of file to write on the Help info. i.e. (E://file.txt)\n"+
                "date    >>  No parameters or The path of file to write on the date i.e. (E://file.txt)\n"+
                "args    >>  No parameters or The path of file to write on the arguments/parameters i.e. (E://file.txt)\n"+
                "exit    >>  No parameters\n";
        if(!sign.equals(" "))
        {
             if(sign.equals(">"))Files.write(Paths.get(path), args.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
             else if(sign.equals(">>"))Files.write(Paths.get(path), args.getBytes(), StandardOpenOption.APPEND);
        }
        if (path == "cd")
            System.out.print("Number of args is 1: Destination directory");
        else if (path.equals("rm"))
            System.out.print("Number of args is 1: The file to delete");
        else if (path.equals("mkdir"))
            System.out.print("Number of args is 1: The path and name of directory to create");
        else if (path.equals("rmdir"))
            System.out.print("Number of args is 1: The path and name of directory to remove");
        else if (path.equals("cp"))
            System.out.print("Number of args is 2: Source Path, Destination Path");
        else if (path.equals("mv"))
            System.out.print("Number of args is 2: Source Path, Destination Path");
        else if (path.equals("more"))
            System.out.print("Number of args is 1: The path and name of the file to diplay");
        else if (path.equals("cat"))
            System.out.print("Number of args is 1 or 2: path of files to be cat");
        else if (path.equals("clear"))
            System.out.print("Number of args is 0");
        else if (path.equals("pwd"))
            System.out.print("Number of args is 0 or 1: The path of file to write on the Cdirectory");
        else if (path.equals("ls"))
            System.out.print("Number of args is 0 or 1: The path of file to write on the list");
        else if (path.equals("help"))
            System.out.print("Number of args is 0 or 1: The path of file to write on the Help info");
        else if (path.equals("date"))
            System.out.print("Number of args is 0 or 1: The path of file to write on the date");
        else if (path.equals("args"))
            System.out.print("Number of args is 0 or 1: The path of file to write on the arguments/parameters");
        else if (path.equals("exit"))
            System.out.print("Number of args is 0");
        else
            System.out.print(args);
    }
    private void exit()
    {
        System.exit(0);
    }
}