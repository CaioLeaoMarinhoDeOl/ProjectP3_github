/*******************************************************
   Mosel User Guide Example Problems
   ================================= 

   file ugcomp.java
   ````````````````
   Compiling a model into a BIM file.
   
   (c) 2008 Fair Isaac Corporation
       author: S. Heipcke, 2002
********************************************************/

import com.dashoptimization.*;

public class ugcomp
{
 public static void main(String[] args) throws Exception
 {
  XPRM mosel;
  XPRMModel mod;

  mosel = new XPRM();                    // Initialize Mosel

  System.out.println("Compiling `burglar2'");
  mosel.compile("burglar2.mos");         // Compile the model burglar2.mos,
                                         // output the file burglar2.bim 
 }
}
