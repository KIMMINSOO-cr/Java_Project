import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.IOException;


public class DB_MAN {

    // --- 1. DB 연결 정보 ---
    
    String strDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String strURL = "jdbc:sqlserver://localhost:1433;DatabaseName=AcademicDB;";
    String strEncrypt = "encrypt=true;trustServerCertificate=true;";
    String strUser = "sa";
    String strPWD = "inha1958"; 

    // --- 2. DB 작업용 객체 ---
    Connection DB_con; 
    Statement DB_stmt;  
    ResultSet DB_rs;    


    public void dbOpen() throws IOException {
        try {
            // 1. JDBC 드라이버 로드
            Class.forName(strDriver); 
            
            // 2. URL에 SSL 설정 추가 (오류 방지)
            strURL += strEncrypt; 
            
            // 3. DB 연결 (ID, PW 사용)
            DB_con = DriverManager.getConnection(strURL, strUser, strPWD); 
            
            // 4. SQL 실행 객체 생성
            DB_stmt = DB_con.createStatement();
            
        } catch (Exception e) {
            System.err.println("DB 연결 오류(dbOpen): " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void dbClose() throws IOException {
        try {
            if (DB_rs != null) DB_rs.close();       
            if (DB_stmt != null) DB_stmt.close();   
            if (DB_con != null) DB_con.close();   
            
        } catch (SQLException e) {
            System.err.println("DB 연결 해제 오류(dbClose): " + e.getMessage());
            e.printStackTrace();
        }
    }
}