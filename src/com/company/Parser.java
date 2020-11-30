package com.company;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser
{
    Terminal term = new Terminal();
    public String cmd=new String();
    public String path1="";
    public String path2="";
    public String sign="";
    private ArrayList<String> cmds =new ArrayList<String>();
    private void adding()
    {
        cmds.add("clear");   cmds.add("cp");
        cmds.add("cd");      cmds.add("mv");
        cmds.add("cat");     cmds.add("mkdir");
        cmds.add("rmdir");   cmds.add("rm");
        cmds.add("ls");      cmds.add("exit");
        cmds.add("date");    cmds.add("args");
        cmds.add("pwd");     cmds.add("more");      cmds.add("help");
    }
    public boolean parse(String input) throws Exception
    {
        adding();
        Terminal term = new Terminal();
        String[] s = new String[6];
        String[] clis;
        clis = input.split("[|]+ "); /*split the line if it has | to several commands line*/  /*cmd2=s[Arrays.asList(s).indexOf("|")+1];*/

        for (int i = 0; i < clis.length; i++)
        {
            s = clis[i].split(" ");
            cmd = s[0].toLowerCase();
            if (s.length > 1 && (s[1].equals(">") || s[1].equals(">>") || s[1].equals("<")))
            {
                sign = s[1];
                if (s.length > 2 && (s[1].equals(">") || s[1].equals(">>") || s[1].equals("<"))) path1 = s[2];
                if (s.length > 3 && (s[1].equals(">") || s[1].equals(">>") || s[1].equals("<"))) path2 = s[3];
            }
            else
            {
                if (s.length > 1 && (!s[1].equals(">") && !s[1].equals(">>") && !s[1].equals("<")))
                    path1 = s[1];
                if (s.length > 2 && (!s[1].equals(">") && !s[1].equals(">>") && !s[1].equals("<")))
                    path2 = s[2];
            }
            if (path1.equals(" ") && (cmd.equals("cp") || cmd.equals("mv") ||
                    cmd.equals("mkdir") || cmd.equals("rmdir") || cmd.equals("rm") || cmd.equals("more") || cmd.equals("cat")))
            {
                System.out.println("The parameter1 you entered is wrong!");
                return false;
            }
            if (cmd.equals("cp") || cmd.equals("mv"))
            {
                if (path1.isEmpty())
                {
                    System.out.println("The parameter2 you entered is wrong!");
                    return false;
                }
            }
            if (cmds.contains(cmd))
                term.data(cmd, path1, path2, sign);
            else
            {
                System.out.println("The command you entered is wrong!");
                return false;
            }
        }
        return true;
    }
}