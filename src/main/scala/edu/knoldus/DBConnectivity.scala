package edu.knoldus

import java.sql.{Connection, DriverManager}

class DBConnectivity{

  def getDBConnection: Connection={
    Class.forName("com.mysql.jdbc.Driver")
    val dbUrl = "jdbc:mysql://localhost:3306/myDB"
    val username = "root"
    val password = "root"
    val conn = DriverManager.getConnection(dbUrl, username, password)
    conn
  }
}
