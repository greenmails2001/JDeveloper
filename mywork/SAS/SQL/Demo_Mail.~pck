Create Or Replace Package Demo_Mail Is

   ----------------------- Customizable Section -----------------------
   -- Customize the SMTP host, port and your domain name below.
   Smtp_Host Varchar2(256) := 'smtp.easycgi.com';

   Smtp_Port Pls_Integer := 25;

   Smtp_Domain Varchar2(256) := 'easycgi.com';

   -- Customize the signature that will appear in the email's MIME header.
   -- Useful for versioning.
   Mailer_Id Constant Varchar2(256) := 'Mailer by Oracle UTL_SMTP';

   --------------------- End Customizable Section ---------------------
   -- A unique string that demarcates boundaries of parts in a multi-part email
   -- The string should not appear inside the body of any part of the email.
   -- Customize this if needed or generate this randomly dynamically.
   Boundary Constant Varchar2(256) := '-----7D81B75CCC90D2974F7A1CBD';

   First_Boundary Constant Varchar2(256) := '--' || Boundary ||
                                            Utl_Tcp.Crlf;

   Last_Boundary Constant Varchar2(256) := '--' || Boundary || '--' ||
                                           Utl_Tcp.Crlf;

   -- A MIME type that denotes multi-part email (MIME) messages.
   Multipart_Mime_Type Constant Varchar2(256) := 'multipart/mixed; boundary="' ||
                                                 Boundary || '"';

   Max_Base64_Line_Width Constant Pls_Integer := 76 / 4 * 3;

   -- A simple email API for sending email in plain text in a single call.
   -- The format of an email address is one of these:
   --   someone@some-domain
   --   "Someone at some domain" <someone@some-domain>
   --   Someone at some domain <someone@some-domain>
   -- The recipients is a list of email addresses  separated by
   -- either a "," or a ";"
   Procedure Mail(Sender     In Varchar2
                 ,Recipients In Varchar2
                 ,Subject    In Varchar2
                 ,Message    In Varchar2);

   -- Extended email API to send email in HTML or plain text with no size limit.
   -- First, begin the email by begin_mail(). Then, call write_text() repeatedly
   -- to send email in ASCII piece-by-piece. Or, call write_mb_text() to send
   -- email in non-ASCII or multi-byte character set. End the email with
   -- end_mail().
   Function Begin_Mail(Sender     In Varchar2
                      ,Recipients In Varchar2
                      ,Subject    In Varchar2
                      ,Mime_Type  In Varchar2 Default 'text/plain'
                      ,Priority   In Pls_Integer Default Null)
      Return Utl_Smtp.Connection;

   -- Write email body in ASCII
   Procedure Write_Text(Conn    In Out Nocopy Utl_Smtp.Connection
                       ,Message In Varchar2);

   -- Write email body in non-ASCII (including multi-byte). The email body
   -- will be sent in the database character set.
   Procedure Write_Mb_Text(Conn    In Out Nocopy Utl_Smtp.Connection
                          ,Message In Varchar2);

   -- Write email body in binary
   Procedure Write_Raw(Conn    In Out Nocopy Utl_Smtp.Connection
                      ,Message In Raw);

   -- APIs to send email with attachments. Attachments are sent by sending
   -- emails in "multipart/mixed" MIME format. Specify that MIME format when
   -- beginning an email with begin_mail().
   -- Send a single text attachment.
   Procedure Attach_Text(Conn      In Out Nocopy Utl_Smtp.Connection
                        ,Data      In Varchar2
                        ,Mime_Type In Varchar2 Default 'text/plain'
                        ,Inline    In Boolean Default True
                        ,Filename  In Varchar2 Default Null
                        ,Last      In Boolean Default False);

   -- Send a binary attachment. The attachment will be encoded in Base-64
   -- encoding format.
   Procedure Attach_Base64(Conn      In Out Nocopy Utl_Smtp.Connection
                          ,Data      In Raw
                          ,Mime_Type In Varchar2 Default 'application/octet'
                          ,Inline    In Boolean Default True
                          ,Filename  In Varchar2 Default Null
                          ,Last      In Boolean Default False);

   -- Send an attachment with no size limit. First, begin the attachment
   -- with begin_attachment(). Then, call write_text repeatedly to send
   -- the attachment piece-by-piece. If the attachment is text-based but
   -- in non-ASCII or multi-byte character set, use write_mb_text() instead.
   -- To send binary attachment, the binary content should first be
   -- encoded in Base-64 encoding format using the demo package for 8i,
   -- or the native one in 9i. End the attachment with end_attachment.
   Procedure Begin_Attachment(Conn         In Out Nocopy Utl_Smtp.Connection
                             ,Mime_Type    In Varchar2 Default 'text/plain'
                             ,Inline       In Boolean Default True
                             ,Filename     In Varchar2 Default Null
                             ,Transfer_Enc In Varchar2 Default Null);

   -- End the attachment.
   Procedure End_Attachment(Conn In Out Nocopy Utl_Smtp.Connection
                           ,Last In Boolean Default False);

   -- End the email.
   Procedure End_Mail(Conn In Out Nocopy Utl_Smtp.Connection);

   -- Extended email API to send multiple emails in a session for better
   -- performance. First, begin an email session with begin_session.
   -- Then, begin each email with a session by calling begin_mail_in_session
   -- instead of begin_mail. End the email with end_mail_in_session instead
   -- of end_mail. End the email session by end_session.
   Function Begin_Session Return Utl_Smtp.Connection;

   -- Begin an email in a session.
   Procedure Begin_Mail_In_Session(Conn       In Out Nocopy Utl_Smtp.Connection
                                  ,Sender     In Varchar2
                                  ,Recipients In Varchar2
                                  ,Subject    In Varchar2
                                  ,Mime_Type  In Varchar2 Default 'text/plain'
                                  ,Priority   In Pls_Integer Default Null);

   -- End an email in a session.
   Procedure End_Mail_In_Session(Conn In Out Nocopy Utl_Smtp.Connection);

   -- End an email session.
   Procedure End_Session(Conn In Out Nocopy Utl_Smtp.Connection);

End;
/
Create Or Replace Package Body Demo_Mail Is

   -- Return the next email address in the list of email addresses, separated
   -- by either a "," or a ";".  The format of mailbox may be in one of these:
   --   someone@some-domain
   --   "Someone at some domain" <someone@some-domain>
   --   Someone at some domain <someone@some-domain>
   Function Get_Address(Addr_List In Out Varchar2) Return Varchar2 Is
      Addr Varchar2(256);
      i    Pls_Integer;
      Function Lookup_Unquoted_Char(Str  In Varchar2
                                   ,Chrs In Varchar2) Return Pls_Integer As
         c            Varchar2(5);
         i            Pls_Integer;
         Len          Pls_Integer;
         Inside_Quote Boolean;
      Begin
         Inside_Quote := False;
         i            := 1;
         Len          := Length(Str);
         While (i <= Len) Loop
            c := Substr(Str
                       ,i
                       ,1);
            If (Inside_Quote) Then
               If (c = '"') Then
                  Inside_Quote := False;
               Elsif (c = '\') Then
                  i := i + 1; -- Skip the quote character
               End If;
               Goto Next_Char;
            End If;
            If (c = '"') Then
               Inside_Quote := True;
               Goto Next_Char;
            End If;
            If (Instr(Chrs
                     ,c) >= 1) Then
               Return i;
            End If;
            <<next_Char>>
            i := i + 1;
         End Loop;
         Return 0;
      End;
   
   Begin
      Addr_List := Ltrim(Addr_List);
      i         := Lookup_Unquoted_Char(Addr_List
                                       ,',;');
      If (i >= 1) Then
         Addr      := Substr(Addr_List
                            ,1
                            ,i - 1);
         Addr_List := Substr(Addr_List
                            ,i + 1);
      Else
         Addr      := Addr_List;
         Addr_List := '';
      End If;
      i := Lookup_Unquoted_Char(Addr
                               ,'<');
      If (i >= 1) Then
         Addr := Substr(Addr
                       ,i + 1);
         i    := Instr(Addr
                      ,'>');
         If (i >= 1) Then
            Addr := Substr(Addr
                          ,1
                          ,i - 1);
         End If;
      End If;
      Return Addr;
   End;

   -- Write a MIME header
   Procedure Write_Mime_Header(Conn  In Out Nocopy Utl_Smtp.Connection
                              ,Name  In Varchar2
                              ,Value In Varchar2) Is
   Begin
      Utl_Smtp.Write_Data(Conn
                         ,Name || ': ' || Value || Utl_Tcp.Crlf);
   End;

   -- Mark a message-part boundary.  Set <last> to TRUE for the last boundary.
   Procedure Write_Boundary(Conn In Out Nocopy Utl_Smtp.Connection
                           ,Last In Boolean Default False) As
   Begin
      If (Last) Then
         Utl_Smtp.Write_Data(Conn
                            ,Last_Boundary);
      Else
         Utl_Smtp.Write_Data(Conn
                            ,First_Boundary);
      End If;
   End;

   ------------------------------------------------------------------------
   Procedure Mail(Sender     In Varchar2
                 ,Recipients In Varchar2
                 ,Subject    In Varchar2
                 ,Message    In Varchar2) Is
      Conn Utl_Smtp.Connection;
   Begin
      Conn := Begin_Mail(Sender
                        ,Recipients
                        ,Subject);
      Write_Text(Conn
                ,Message);
      End_Mail(Conn);
   End;

   ------------------------------------------------------------------------
   Function Begin_Mail(Sender     In Varchar2
                      ,Recipients In Varchar2
                      ,Subject    In Varchar2
                      ,Mime_Type  In Varchar2 Default 'text/plain'
                      ,Priority   In Pls_Integer Default Null)
      Return Utl_Smtp.Connection Is
      Conn Utl_Smtp.Connection;
   Begin
      Conn := Begin_Session;
      Begin_Mail_In_Session(Conn
                           ,Sender
                           ,Recipients
                           ,Subject
                           ,Mime_Type
                           ,Priority);
      Return Conn;
   End;

   ------------------------------------------------------------------------
   Procedure Write_Text(Conn    In Out Nocopy Utl_Smtp.Connection
                       ,Message In Varchar2) Is
   Begin
      Utl_Smtp.Write_Data(Conn
                         ,Message);
   End;

   ------------------------------------------------------------------------
   Procedure Write_Mb_Text(Conn    In Out Nocopy Utl_Smtp.Connection
                          ,Message In Varchar2) Is
   Begin
      Utl_Smtp.Write_Raw_Data(Conn
                             ,Utl_Raw.Cast_To_Raw(Message));
   End;

   ------------------------------------------------------------------------
   Procedure Write_Raw(Conn    In Out Nocopy Utl_Smtp.Connection
                      ,Message In Raw) Is
   Begin
      Utl_Smtp.Write_Raw_Data(Conn
                             ,Message);
   End;

   ------------------------------------------------------------------------
   Procedure Attach_Text(Conn      In Out Nocopy Utl_Smtp.Connection
                        ,Data      In Varchar2
                        ,Mime_Type In Varchar2 Default 'text/plain'
                        ,Inline    In Boolean Default True
                        ,Filename  In Varchar2 Default Null
                        ,Last      In Boolean Default False) Is
   Begin
      Begin_Attachment(Conn
                      ,Mime_Type
                      ,Inline
                      ,Filename);
      Write_Text(Conn
                ,Data);
      End_Attachment(Conn
                    ,Last);
   End;

   ------------------------------------------------------------------------
   Procedure Attach_Base64(Conn      In Out Nocopy Utl_Smtp.Connection
                          ,Data      In Raw
                          ,Mime_Type In Varchar2 Default 'application/octet'
                          ,Inline    In Boolean Default True
                          ,Filename  In Varchar2 Default Null
                          ,Last      In Boolean Default False) Is
      i   Pls_Integer;
      Len Pls_Integer;
   Begin
      Begin_Attachment(Conn
                      ,Mime_Type
                      ,Inline
                      ,Filename
                      ,'base64');
      -- Split the Base64-encoded attachment into multiple lines
      i   := 1;
      Len := Utl_Raw.Length(Data);
      While (i < Len) Loop
         If (i + Max_Base64_Line_Width < Len) Then
            Utl_Smtp.Write_Raw_Data(Conn
                                   ,Utl_Encode.Base64_Encode(Utl_Raw.Substr(Data
                                                                           ,i
                                                                           ,Max_Base64_Line_Width)));
         Else
            Utl_Smtp.Write_Raw_Data(Conn
                                   ,Utl_Encode.Base64_Encode(Utl_Raw.Substr(Data
                                                                           ,i)));
         End If;
         Utl_Smtp.Write_Data(Conn
                            ,Utl_Tcp.Crlf);
         i := i + Max_Base64_Line_Width;
      End Loop;
      End_Attachment(Conn
                    ,Last);
   End;

   ------------------------------------------------------------------------
   Procedure Begin_Attachment(Conn         In Out Nocopy Utl_Smtp.Connection
                             ,Mime_Type    In Varchar2 Default 'text/plain'
                             ,Inline       In Boolean Default True
                             ,Filename     In Varchar2 Default Null
                             ,Transfer_Enc In Varchar2 Default Null) Is
   Begin
      Write_Boundary(Conn);
      Write_Mime_Header(Conn
                       ,'Content-Type'
                       ,Mime_Type);
      If (Filename Is Not Null) Then
         If (Inline) Then
            Write_Mime_Header(Conn
                             ,'Content-Disposition'
                             ,'inline; filename="' || Filename || '"');
         Else
            Write_Mime_Header(Conn
                             ,'Content-Disposition'
                             ,'attachment; filename="' || Filename || '"');
         End If;
      End If;
      If (Transfer_Enc Is Not Null) Then
         Write_Mime_Header(Conn
                          ,'Content-Transfer-Encoding'
                          ,Transfer_Enc);
      End If;
      Utl_Smtp.Write_Data(Conn
                         ,Utl_Tcp.Crlf);
   End;

   ------------------------------------------------------------------------
   Procedure End_Attachment(Conn In Out Nocopy Utl_Smtp.Connection
                           ,Last In Boolean Default False) Is
   Begin
      Utl_Smtp.Write_Data(Conn
                         ,Utl_Tcp.Crlf);
      If (Last) Then
         Write_Boundary(Conn
                       ,Last);
      End If;
   End;

   ------------------------------------------------------------------------
   Procedure End_Mail(Conn In Out Nocopy Utl_Smtp.Connection) Is
   Begin
      End_Mail_In_Session(Conn);
      End_Session(Conn);
   End;

   ------------------------------------------------------------------------
   Function Begin_Session Return Utl_Smtp.Connection Is
      Conn Utl_Smtp.Connection;
   Begin
      -- open SMTP connection
      Conn := Utl_Smtp.Open_Connection(Smtp_Host
                                      ,Smtp_Port);
      Utl_Smtp.Helo(Conn
                   ,Smtp_Domain);
      Return Conn;
   End;

   ------------------------------------------------------------------------
   Procedure Begin_Mail_In_Session(Conn       In Out Nocopy Utl_Smtp.Connection
                                  ,Sender     In Varchar2
                                  ,Recipients In Varchar2
                                  ,Subject    In Varchar2
                                  ,Mime_Type  In Varchar2 Default 'text/plain'
                                  ,Priority   In Pls_Integer Default Null) Is
      My_Recipients Varchar2(32767) := Recipients;
      My_Sender     Varchar2(32767) := Sender;
   Begin
      -- Specify sender's address (our server allows bogus address
      -- as long as it is a full email address (xxx@yyy.com).
      Utl_Smtp.Mail(Conn
                   ,Get_Address(My_Sender));
      -- Specify recipient(s) of the email.
      While (My_Recipients Is Not Null) Loop
         Utl_Smtp.Rcpt(Conn
                      ,Get_Address(My_Recipients));
      End Loop;
      -- Start body of email
      Utl_Smtp.Open_Data(Conn);
      -- Set "From" MIME header
      Write_Mime_Header(Conn
                       ,'From'
                       ,Sender);
      -- Set "To" MIME header
      Write_Mime_Header(Conn
                       ,'To'
                       ,Recipients);
      -- Set "Subject" MIME header
      Write_Mime_Header(Conn
                       ,'Subject'
                       ,Subject);
      -- Set "Content-Type" MIME header
      Write_Mime_Header(Conn
                       ,'Content-Type'
                       ,Mime_Type);
      -- Set "X-Mailer" MIME header
      Write_Mime_Header(Conn
                       ,'X-Mailer'
                       ,Mailer_Id);
      -- Set priority:
      --   High      Normal       Low
      --   1     2     3     4     5
      If (Priority Is Not Null) Then
         Write_Mime_Header(Conn
                          ,'X-Priority'
                          ,Priority);
      End If;
      -- Send an empty line to denotes end of MIME headers and
      -- beginning of message body.
      Utl_Smtp.Write_Data(Conn
                         ,Utl_Tcp.Crlf);
      If (Mime_Type Like 'multipart/mixed%') Then
         Write_Text(Conn
                   ,'This is a multi-part message in MIME format.' ||
                    Utl_Tcp.Crlf);
      End If;
   End;

   ------------------------------------------------------------------------
   Procedure End_Mail_In_Session(Conn In Out Nocopy Utl_Smtp.Connection) Is
   Begin
      Utl_Smtp.Close_Data(Conn);
   End;

   ------------------------------------------------------------------------
   Procedure End_Session(Conn In Out Nocopy Utl_Smtp.Connection) Is
   Begin
      Utl_Smtp.Quit(Conn);
   End;

End;
/
