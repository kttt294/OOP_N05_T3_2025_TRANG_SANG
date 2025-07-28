import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
    private JPanel mainPanel;

    public MainFrame() {
        setTitle("Quản lý rạp chiếu phim");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        // Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menuQuanLy = new JMenu("Quản lý");
        JMenuItem menuKhachHang = new JMenuItem("Khách hàng");
        JMenuItem menuThoat = new JMenuItem("Thoát");

        menuQuanLy.add(menuKhachHang);
        menuBar.add(menuQuanLy);
        menuBar.add(menuThoat);
        setJMenuBar(menuBar);

        // Main panel
        mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);

        // Action listeners
        menuKhachHang.addActionListener(e -> showPanel(new KhachHangPanel()));
        menuThoat.addActionListener(e -> System.exit(0));

        // Mặc định hiển thị panel khách hàng
        showPanel(new KhachHangPanel());
    }

    private void showPanel(JPanel panel) {
        mainPanel.removeAll();
        mainPanel.add(panel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
} 