package SYSTEM;

public class Student {
    private String userName;
    private String userPassword;
    private long Phone;

    public Student() {
    }

    public Student(String userName, String userPassword, long Phone) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.Phone = Phone;
    }

    /**
     * 获取
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取
     * @return userPassword
     */
    public String getUserPassword() {
        return userPassword;
    }

    public long getPhone() {
        return Phone;
    }

    public void setPhone(long phone) {
        Phone = phone;
    }

    /**
     * 设置
     * @param userPassword
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }





    public String toString() {
        return "SYSTEM.Student{userName = " + userName + ", userPassword = " + userPassword + ", Phone = " + Phone + "}";
    }
}
