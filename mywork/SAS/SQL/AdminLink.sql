Link Name   : adminlink
Connection  : Successful 
DB Name     : PERP.REGRESS.RDBMS.DEV.US.ORACLE.COM
DB Version  : Oracle Database 10g Enterprise Edition Release 10.2.0.3.0 - Prod


create database link ADMINLINK
  connect to ADMIN identified by "admin"
  using 'perp';