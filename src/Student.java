public class Student {
    private String userName;
    private String userPassword;
    private int Phone;

    public Student() {
    }

    public Student(String userName, String userPassword, int Phone) {
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

    /**
     * 设置
     * @param userPassword
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * 获取
     * @return Phone
     */
    public int getPhone() {
        return Phone;
    }

    /**
     * 设置
     * @param Phone
     */
    public void setPhone(int Phone) {
        this.Phone = Phone;
    }

    public String toString() {
        return "Student{userName = " + userName + ", userPassword = " + userPassword + ", Phone = " + Phone + "}";
    }
}
