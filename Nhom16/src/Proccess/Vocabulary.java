package Proccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import login.Connect;
/**
 * Vocabulary class to manage vocabulary entries in the database.
 */
public class Vocabulary {
    private Connect cn = new Connect();
    private String Word;
    private String Type;
    private String Pronounce;
    private String Meaning;

    // Constructor to initialize the Vocabulary object
    public Vocabulary(String Word, String Type, String Pronounce, String Meaning) {
        this.Word = Word;
        this.Type = Type;
        this.Pronounce = Pronounce;
        this.Meaning = Meaning;
    }
    public Vocabulary() {
        this.Word = "";
        this.Type = "";
        this.Pronounce = "";
        this.Meaning = "";
    }

    public void setCn(Connect cn) {
        this.cn = cn;
    }

    public void setWord(String Word) {
        this.Word = Word;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public void setPronounce(String Pronounce) {
        this.Pronounce = Pronounce;
    }

    public void setMeaning(String Meaning) {
        this.Meaning = Meaning;
    }
        public String getWord() {
        return Word;
    }

    public String getType() {
        return Type;
    }

    public String getPronounce() {
        return Pronounce;
    }

    public String getMeaning() {
        return Meaning;
    }
    // Retrieve all vocabulary entries
    public List<Vocabulary> getVocabulary() throws SQLException {
        List<Vocabulary> list = new ArrayList<>();
        String sql = "SELECT * FROM Vocabulary"; // Ensure this matches your table name
        try (Connection connection = cn.connectSQL();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Vocabulary vocabulary = new Vocabulary(
                    resultSet.getString("Word"),
                    resultSet.getString("Type"),
                    resultSet.getString("Pronounce"),
                    resultSet.getString("Meaning")
                );
                list.add(vocabulary);
            }
        }
        return list;
    }
    // Retrieve a specific vocabulary entry by word
    public Vocabulary getVocabulary(String wd) throws SQLException {
        String sql = "SELECT * FROM Vocabulary WHERE Word=?"; // Ensure this matches your table name
        try (Connection connection = cn.connectSQL();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, wd);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Vocabulary(
                    resultSet.getString("Word"),
                    resultSet.getString("Type"),
                    resultSet.getString("Pronounce"),
                    resultSet.getString("Meaning")
                );
            }
        }
        return null; // Return null if no record found
    }
    // Insert a new vocabulary entry
    public boolean insertData(Vocabulary obj) throws SQLException {
        String sql = "INSERT INTO Vocabulary (Word, Type, Pronounce, Meaning) VALUES (?, ?, ?, ?)"; // Ensure this matches your table name
        try (Connection connection = cn.connectSQL();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, obj.getWord());
            statement.setString(2, obj.getType());
            statement.setString(3, obj.getPronounce());
            statement.setString(4, obj.getMeaning());
            return statement.executeUpdate() > 0; // Return true if insert is successful
        }
    }
    public boolean editData(Vocabulary obj) throws SQLException {
    String sql = "UPDATE Vocabulary SET Type=?, Pronounce=?, Meaning=? WHERE Word=?";
    try (Connection connection = cn.connectSQL();
         PreparedStatement statement = connection.prepareStatement(sql)) {
        // Đặt các tham số theo đúng thứ tự
        statement.setString(1, obj.getType());       // Tham số 1: Type
        statement.setString(2, obj.getPronounce());  // Tham số 2: Pronounce
        statement.setString(3, obj.getMeaning());     // Tham số 3: Meaning
        statement.setString(4, obj.getWord());        // Tham số 4: Word (điều kiện WHERE)

        return statement.executeUpdate() > 0; // Trả về true nếu cập nhật thành công
    }
    }
    // Delete a vocabulary entry by word
    public boolean deleteData(String wd) throws SQLException 
    {
        String sql = "DELETE FROM Vocabulary WHERE Word=?"; // Ensure this matches your table name
        try (Connection connection = cn.connectSQL();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, wd);
            return statement.executeUpdate() > 0; // Return true if delete is successful
        }
    }
public Vocabulary seach(String wd) throws SQLException {
    String sql = "SELECT * FROM Vocabulary WHERE Word=?"; // Đảm bảo tên cột chính xác
    try (Connection connection = cn.connectSQL();
         PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, wd); // Thiết lập tham số cho từ cần tìm

        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                // Nếu tìm thấy, tạo và trả về đối tượng Vocabulary
                Vocabulary vocab = new Vocabulary();
                vocab.setWord(resultSet.getString("Word")); // Đảm bảo tên cột chính xác
                vocab.setType(resultSet.getString("Type")); // Tên cột cho loại
                vocab.setPronounce(resultSet.getString("Pronounce")); // Tên cột cho phát âm
                vocab.setMeaning(resultSet.getString("Meaning")); // Tên cột cho nghĩa
                return vocab; // Trả về đối tượng Vocabulary
            } else {
                return null;
            }
        }
    }
}
}