Create Or Replace Package Sas_Pkg Is

   -- Author  : DO VINH
   -- Created : 1/27/2008 2:43:51 PM
   -- Purpose : 
   -- Public type declarations
   -- Public constant declarations
   -- Public variable declarations
   -- Public function and procedure declarations
   Function Get_Url(Sas_User_Name Varchar2
                   ,App_Code      Varchar2) Return Varchar2;

   Function Get_Userdb(Sas_User_Name Varchar2) Return Varchar2;

   Function Get_Pwd_Userdb(Sas_User_Name Varchar2) Return Varchar2;

   Procedure Setting_Ape(Sas_User_Name Varchar2);

End Sas_Pkg;
/
Create Or Replace Package Body Sas_Pkg Is

   -- Private type declarations
   --TYPE < TypeName > IS < Datatype >;
   -- Private constant declarations
   --< ConstantName > CONSTANT < Datatype > := < VALUE >;
   -- Private variable declarations
   --< VariableName > < Datatype >;
   -- Function and procedure implementations
   Function Get_Url(Sas_User_Name Varchar2
                   ,App_Code      Varchar2) Return Varchar2 Is
      v_App_Host Varchar2(32);
      v_App_Port Number;
   Begin
      Begin
         Select Env.App_Host
               ,Env.App_Port
         Into   v_App_Host
               ,v_App_Port
         From   Accounts               Acc
               ,Subscriptions          Sub
               ,Environments@Adminlink Env
         Where  Acc.Acc_Id = Sub.Acc_Id
         And    Sub.Sub_Id = Env.Sub_Id
         And    Acc.User_Name = Sas_User_Name;
      Exception
         When Others Then
            v_App_Host := Null;
            v_App_Port := 7778;
      End;
      Return v_App_Host || ':' || v_App_Port || '//forms/frmservlet?config=' || Sas_User_Name || '_PERP' || '&otherparams=p_root_app_name=' || App_Code;
   End;

   ------------------------------------------------------------------------------------------------------------
   ------------------------------------------------------------------------------------------------------------
   ------------------------------------------------------------------------------------------------------------
   Function Get_Userdb(Sas_User_Name Varchar2) Return Varchar2 Is
   Begin
      Return Sas_User_Name;
   End;

   ------------------------------------------------------------------------------------------------------------
   ------------------------------------------------------------------------------------------------------------
   ------------------------------------------------------------------------------------------------------------
   Function Get_Pwd_Userdb(Sas_User_Name Varchar2) Return Varchar2 Is
   Begin
      Return Sas_User_Name;
   End;

   ------------------------------------------------------------------------------------------------------------
   ------------------------------------------------------------------------------------------------------------
   ------------------------------------------------------------------------------------------------------------
   Procedure Setting_Ape(Sas_User_Name Varchar2) Is
   Begin
      Null;
   End;

------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------
Begin
   -- Initialization
   Null;
End Sas_Pkg;
/
