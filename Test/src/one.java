import java.sql.*;

public class one {
    static final String DB_URL = "jdbc:mysql://192.168.237.3:3308/MusicStreamDB";
    static final String USER = "root";
    static final String PASS = "1234";
    static Connection conn = null;
    static Statement stmt = null;

    public static void main(String[] args) {
        try {
            // JDBC 드라이버 로딩
            Class.forName("com.mysql.cj.jdbc.Driver");

            // MySQL에 연결
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            while (true) {
                // 메뉴 출력
                System.out.println("------------------------------------------------------------");
                System.out.println("1. Connection  2. Find Music");
                System.out.println("3. Insert Music 4. Delete Music");
                System.out.println("5. Find User    6. Insert User");
                System.out.println("7. Delete User");
                System.out.println("99. Quit");
                System.out.println("------------------------------------------------------------");

                // 사용자 입력 받기
                java.util.Scanner scanner = new java.util.Scanner(System.in);
                int choice = scanner.nextInt();

                // 선택된 작업 수행
                switch (choice) {
                    case 1:
                        // Connection
                        System.out.println("Connected to the database.");
                        break;
                    case 2:
                        // Find Music
                        executeQuery("SELECT * FROM Music");
                        break;
                    case 3:
                        // Insert Music
                        System.out.println("Enter values for column1, column2, column3:");

                        // 사용자로부터 입력 받기
                        String musicValue1 = scanner.next();
                        String musicValue2 = scanner.next();
                        String musicValue3 = scanner.next();

                        // 예시로써 단순히 "INSERT INTO Music ..." 쿼리를 실행
                        executeUpdate("INSERT INTO Music (column1, column2, column3) VALUES ('" + musicValue1 + "', '" + musicValue2 + "', '" + musicValue3 + "')");
                        break;
                    case 4:
                        // Delete Music
                        System.out.println("Enter Music ID to delete:");

                        // 사용자로부터 입력 받기
                        int musicIdToDelete = scanner.nextInt();

                        // 예시로써 단순히 "DELETE FROM Music WHERE id = ..." 쿼리를 실행
                        executeUpdate("DELETE FROM Music WHERE id = " + musicIdToDelete);
                        break;
                    case 5:
                        // Find User
                        executeQuery("SELECT * FROM User");
                        break;
                    case 6:
                        // Insert User
                        System.out.println("Enter values for user_id, user_name, user_email:");

                        // 사용자로부터 입력 받기
                        String userId = scanner.next();
                        String userName = scanner.next();
                        String userEmail = scanner.next();

                        // 예시로써 단순히 "INSERT INTO User ..." 쿼리를 실행
                        executeUpdate("INSERT INTO User (user_id, user_name, user_email) VALUES ('" + userId + "', '" + userName + "', '" + userEmail + "')");
                        break;
                    case 7:
                        // Delete User
                        System.out.println("Enter User ID to delete:");

                        // 사용자로부터 입력 받기
                        String userIdToDelete = scanner.next();

                        // 예시로써 단순히 "DELETE FROM User WHERE user_id = ..." 쿼리를 실행
                        executeUpdate("DELETE FROM User WHERE user_id = '" + userIdToDelete + "'");
                        break;
                  
                    case 99:
                        // Quit
                        System.out.println("Quitting the program.");
                        conn.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 쿼리 실행 메소드
    private static void executeQuery(String query) throws SQLException {
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
        }
    }

    // 업데이트 쿼리 실행 메소드
    private static void executeUpdate(String query) throws SQLException {
        int rowsAffected = stmt.executeUpdate(query);
        System.out.println("Rows affected: " + rowsAffected);
    }
}
