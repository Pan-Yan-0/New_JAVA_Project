package FILE_Study;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Test4 {
    public static void main(String[] args) throws IOException {
        //细节：如果父级路径是不存在的，那么方法会有异常IOException
        //细节：createNewFile方法创建的一定是文件，如果路径中没有包含后缀名，则创建一个没有后缀的文件
        /*File f1 = new File("src/FILE_Study/b.txt");
        boolean newFile = f1.createNewFile();
        System.out.println(newFile);*/

        //细节：window当中路径是唯一的，如果当前路径已经存在，则创建失败，返回false
        File f2 =new File("src\\FILE_Study\\bbb");
        String filename = "output.txt";
        String content = "Hello, world!\nThis is a new line.";

        try {
            FileWriter fileWriter = new FileWriter("src/FILE_Study/a.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(content);
            bufferedWriter.newLine();
            bufferedWriter.write("This is another line.");
            bufferedWriter.newLine();
            bufferedWriter.write(1+" "+23);
            bufferedWriter.close();

            System.out.println("文件已写入成功！");
        } catch (IOException e) {
            System.err.println("文件写入失败：" + e.getMessage());
        }
    }
}
