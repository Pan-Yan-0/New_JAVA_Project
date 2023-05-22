package FILE_Study;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Test6 extends JFrame{

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new Test6("demo");
    }
    JPanel jp1, jp2;
    JLabel jl1, jl2;
    JComboBox jcb;
    JList jl;
    JScrollPane jsp;
    public Test6(String name){
        super(name);
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp1.setLayout(new FlowLayout());
        jp2.setLayout(new FlowLayout());
        jl1 = new JLabel("你的籍贯是");
        jl2 = new JLabel("你喜欢去旅游的地区");
        String[] jg = { "北京", "上海", "武汉", "随州" };
        jcb = new JComboBox(jg);

        jp1.add(jl1);
        jp1.add(jcb);

        jl = new JList(jg);
        jl.setVisibleRowCount(3);
        jsp = new JScrollPane(jl);
        jp2.add(jl2);
        jp2.add(jsp);
        this.setLayout(new GridLayout(2, 1));
        this.add(jp1);
        this.add(jp2);
        this.setLocation(200, 200);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
    }

}